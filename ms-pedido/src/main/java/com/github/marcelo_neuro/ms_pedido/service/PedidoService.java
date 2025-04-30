package com.github.marcelo_neuro.ms_pedido.service;

import com.github.marcelo_neuro.ms_pedido.dto.PedidoDTO;
import com.github.marcelo_neuro.ms_pedido.entity.ItemDoPedido;
import com.github.marcelo_neuro.ms_pedido.entity.Pedido;
import com.github.marcelo_neuro.ms_pedido.entity.Status;
import com.github.marcelo_neuro.ms_pedido.repository.ItemDoPedidoRepository;
import com.github.marcelo_neuro.ms_pedido.repository.PedidoRepository;
import com.github.marcelo_neuro.ms_pedido.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemDoPedidoRepository itemDoPedidoRepository;

    @Transactional(readOnly = true)
    public List<PedidoDTO> findAll() {
        return pedidoRepository.findAll()
                .stream()
                .map(PedidoDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public PedidoDTO findById(Long id) {
        return new PedidoDTO(pedidoRepository.findById(id)
                .orElseThrow(() -> {
                   return new ResourceNotFoundException("Entidade não encontrada, id: " + id);
                }));
    }

    @Transactional
    public PedidoDTO save(PedidoDTO dto) {
        Pedido entity = new Pedido();
        entity.setData(LocalDate.now());
        entity.setStatus(Status.REALIZADO);
        copyToEntity(entity, dto);

        entity = pedidoRepository.save(entity);
        itemDoPedidoRepository.saveAll(entity.getItens());

        return new PedidoDTO(entity);
    }

    private void copyToEntity(Pedido entity, PedidoDTO dto) {

        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());

        dto.getItens().forEach((p) -> {
            ItemDoPedido ip = new ItemDoPedido();

            ip.setQuantidade(p.getQuantidade());
            ip.setDescricao(p.getDescricao());
            ip.setValorUnitario(p.getValorUnitario());
            ip.setPedido(p.getPedido());

            entity.getItens().add(ip);
        });
    }
}
