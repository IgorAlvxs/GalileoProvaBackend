package br.com.tech4me.vendas.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.vendas.shared.VendasDto;

public interface VendasService {
    List<VendasDto> listarTodos();
    Optional<VendasDto> realizarVenda(VendasDto vendas);
    Optional<VendasDto> obterPorId(String id);

}