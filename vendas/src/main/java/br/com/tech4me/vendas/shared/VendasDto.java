package br.com.tech4me.vendas.shared;

import java.time.LocalDate;
import java.util.List;

public class VendasDto {
    private String Id;
    private LocalDate dataVenda;
    private String produtoVendido;
    private int quantidadeEstoque;
    private List<Produtos> produtos;

    
    public String getId() {
        return Id;
    }
    public void setId(String id) {
        Id = id;
    }
    public LocalDate getDataVenda() {
        return dataVenda;
    }
    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }
    public String getProdutoVendido() {
        return produtoVendido;
    }
    public void setProdutoVendido(String produtoVendido) {
        this.produtoVendido = produtoVendido;
    }
    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
    public List<Produtos> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }
    
}
