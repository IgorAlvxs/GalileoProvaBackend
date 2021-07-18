package br.com.tech4me.vendas.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.vendas.integration.ProdutosFeignClient;
import br.com.tech4me.vendas.model.Vendas;
import br.com.tech4me.vendas.repository.ProdutosRepository;
import br.com.tech4me.vendas.repository.VendasRepository;
import br.com.tech4me.vendas.shared.Produtos;
import br.com.tech4me.vendas.shared.VendasDto;

@Service
public class VendasServiceImpl implements VendasService {
    @Autowired
    private VendasRepository repositorio;

    @Autowired
    private ProdutosFeignClient produtosFC;

    @Autowired
    private ProdutosRepository repositorioProduto;


    @Override
    public List<VendasDto> listarTodos() {
        List<Vendas> vendas = repositorio.findAll();

        return vendas.stream()
        .map(venda -> new ModelMapper().map(venda, VendasDto.class))
        .collect(Collectors.toList());
    }

    @Override
    public Optional<VendasDto> realizarVenda(VendasDto vendas) {
        ModelMapper model = new ModelMapper();
        Vendas venda = model.map(vendas, Vendas.class);
        List<Produtos> produto = repositorioProduto.findAll();
        
        for (Produtos produtos : produto){

            if(produtos.getNome().equalsIgnoreCase(vendas.getProdutoVendido())){
                produtos.setEstoque(produtos.getEstoque() - vendas.getQuantidadeEstoque());
                repositorioProduto.save(produtos);
                venda.setValorTotal(produtos.getValor() * venda.getQuantidadeEstoque());
                venda = repositorio.save(venda);

                VendasDto compra = model.map(venda, VendasDto.class);
                return Optional.of(compra);
            }
        }
        return Optional.empty();
    }
    

    @Override
    public Optional<VendasDto> obterPorId(String id) {
        Optional<Vendas> vend = repositorio.findById(id);

        if(vend.isPresent()) {
            VendasDto vendaDto = new ModelMapper().map(vend.get(), VendasDto.class);
            vendaDto.setProdutos(produtosFC.obterPorProduto(id));

            return Optional.of(vendaDto);
        }
        return Optional.empty();
    }

    
    
}
