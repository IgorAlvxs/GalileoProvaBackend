package br.com.tech4me.produtos.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("produtos")
public class Produtos {
    @Id
    private String id;
    private String nome;
    private Double valor;
    private int estoque;

    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public int getEstoque() {
        return estoque;
    }
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    
    @Override
    public String toString() {
        return " estoque=" + estoque + ", id=" + id + ", nome=" + nome
                + ", valor=" + valor + "]";
    }

    
}
