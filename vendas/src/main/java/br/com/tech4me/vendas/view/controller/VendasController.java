package br.com.tech4me.vendas.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.vendas.service.VendasService;
import br.com.tech4me.vendas.shared.VendasDto;
import br.com.tech4me.vendas.view.model.VendasRequest;
import br.com.tech4me.vendas.view.model.VendasResponse;

@RestController
@RequestMapping("/api/vendas")
public class VendasController {

    @Autowired
    VendasService service;

    @GetMapping
    public ResponseEntity<List<VendasResponse>> obterTodasVenda() {
        ModelMapper model = new ModelMapper();
        List<VendasDto> venDto = service.listarTodos();
        List<VendasResponse> vendaResponse = venDto.stream()
        .map(vend -> model.map(vend, VendasResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(vendaResponse,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VendasResponse> obterPorUmId(@PathVariable String id){
        Optional<VendasDto> vendas = service.obterPorId(id);
        
        if (vendas.isPresent()) {
            return new ResponseEntity<>(new ModelMapper().map(vendas.get(), VendasResponse.class), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<VendasResponse> efetuarUmaVenda(@RequestBody @Valid VendasRequest vendas ){
        ModelMapper model = new ModelMapper();
        VendasDto vendasDto = model.map(vendas, VendasDto.class);

        Optional<VendasDto> vend = service.realizarVenda(vendasDto);

        if(vend.isPresent()) {
            return new ResponseEntity<>(model.map(vendasDto, VendasResponse.class), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
