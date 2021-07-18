package br.com.tech4me.produtos.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.produtos.service.ProdutosService;
import br.com.tech4me.produtos.shared.ProdutosDto;
import br.com.tech4me.produtos.view.model.ProdutosRequest;
import br.com.tech4me.produtos.view.model.ProdutosResponse;

@RestController
@RequestMapping("/api/produtos")
public class ProdutosController {
    @Autowired
    ProdutosService service;

    @GetMapping
    public ResponseEntity<List<ProdutosResponse>> obterTodosProdutos() {
        ModelMapper model = new ModelMapper();
        List<ProdutosDto> prodDto = service.obterTodos();
        List<ProdutosResponse> produtoResponse = prodDto.stream()
        .map(prod -> model.map(prod, ProdutosResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutosResponse> cadastrarUmProduto(@RequestBody ProdutosRequest produto) {
        ModelMapper model = new ModelMapper();
        ProdutosDto prodDto = model.map(produto, ProdutosDto.class);
        prodDto = service.cadastrarProduto(prodDto);
        return new ResponseEntity<>(model.map(prodDto, ProdutosResponse.class), HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/{id}")
    public void removerUmProduto(@PathVariable String id){
        service.removerProduto(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutosResponse> atualizarUmaProdutos(@PathVariable String id, @RequestBody ProdutosRequest produto){
        ModelMapper model = new ModelMapper();
        ProdutosDto prodDto = model.map(produto, ProdutosDto.class);
        prodDto = service.atualizarProduto(id, prodDto);
        return new ResponseEntity<>(model.map(prodDto, ProdutosResponse.class), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutosDto> obterUmProdutos(@PathVariable String id){
        Optional<ProdutosDto> prod = service.procurarPorId(id);
        if(prod.isPresent()){
            return new ResponseEntity<>(new ModelMapper().map(prod.get(),ProdutosDto.class), HttpStatus.FOUND);
        } 
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
}
