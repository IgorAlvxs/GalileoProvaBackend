package br.com.tech4me.produtos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.tech4me.produtos.model.Produtos;
@Repository
public interface ProdutosRepository extends MongoRepository<Produtos, String> {
    
}
