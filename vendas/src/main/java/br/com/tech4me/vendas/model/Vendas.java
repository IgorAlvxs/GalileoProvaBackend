package br.com.tech4me.vendas.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("vendas")
public class Vendas {
    @Id
    private String Id;
    private LocalDate dataVenda;
    private String produtoVendido;
    private int quantidadeEstoque;
    private double valorTotal;

    public void setId(String id) {
        Id = id;
    }
    public String getId() {
        return Id;
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
    
    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    @Override
    public String toString() {
        return "Vendas [dataVenda=" + dataVenda + ", produtoVendido=" + produtoVendido + ", quantidadeEstoque="
                + quantidadeEstoque + "]";
    }
    
}
