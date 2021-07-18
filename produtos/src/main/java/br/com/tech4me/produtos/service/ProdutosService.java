package br.com.tech4me.produtos.service;

import java.util.List;
import java.util.Optional;


import br.com.tech4me.produtos.shared.ProdutosDto;

public interface ProdutosService {
    ProdutosDto cadastrarProduto(ProdutosDto produto);
    List<ProdutosDto> obterTodos();
    void removerProduto(String id);
    Optional<ProdutosDto> procurarPorId(String id);
    ProdutosDto atualizarProduto(String id, ProdutosDto produto);

}
