package br.com.tech4me.vendas.integration;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.tech4me.vendas.shared.Produtos;



@FeignClient(name = "produtos-ms")
public interface ProdutosFeignClient {
    @GetMapping(path = "/api/vendas/{nome}/lista")
    List<Produtos> obterPorProduto(@PathVariable String nome);
}
