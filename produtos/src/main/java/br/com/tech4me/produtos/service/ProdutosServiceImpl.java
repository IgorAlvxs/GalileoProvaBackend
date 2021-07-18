package br.com.tech4me.produtos.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.produtos.model.Produtos;
import br.com.tech4me.produtos.repository.ProdutosRepository;
import br.com.tech4me.produtos.shared.ProdutosDto;

@Service
public class ProdutosServiceImpl implements ProdutosService {
    @Autowired
    private ProdutosRepository repository;


    @Override
    public ProdutosDto cadastrarProduto(ProdutosDto produto) {
        ModelMapper model = new ModelMapper();
        Produtos prod = model.map(produto, Produtos.class);
        prod = repository.save(prod);
        return model.map(prod, ProdutosDto.class);
    }

    @Override
    public List<ProdutosDto> obterTodos() {
        List<Produtos> produtos = repository.findAll();

        return produtos.stream()
        .map(produto -> new ModelMapper().map(produto, ProdutosDto.class))
        .collect(Collectors.toList());
    }

    @Override
    public void removerProduto(String id) {
        repository.deleteById(id);
        
    }

    @Override
    public Optional<ProdutosDto> procurarPorId(String id) {
        Optional<Produtos> prod = repository.findById(id);
        if(prod.isPresent()) {
            
            return Optional.of(new ModelMapper().map(prod.get(), ProdutosDto.class));
        }
        return Optional.empty();
    }

    @Override
    public ProdutosDto atualizarProduto(String id, ProdutosDto produto) {
        ModelMapper model = new ModelMapper();
        Produtos prod = model.map(produto, Produtos.class);
        prod.setId(id);
        prod = repository.save(prod);
        return model.map(prod, ProdutosDto.class);
    }
    
}
