package Projeto.estagio.Projeto.de.Avaliacao.Estagio.repository;

//Produto Repository

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Projeto.estagio.Projeto.de.Avaliacao.Estagio.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    //retornar lista por nome
    public List<Produto> getByNome(String nome);
    //retornar lista por parte do nome
    @Query ("select a from Produto a where a.nome like ?1%")
    public List<Produto> findByParteN(String nome);
    //retornar lista por descrição
    public List<Produto> getByDescricao (String descricao);
    //retornar lista por categoria
    public List<Produto> getByCategoria(String categoria);
    //retornar lista por preço
    @Query ("select a from Produto a where a.preco > ?1%")
    public List<Produto> getByPreco (double preco);
}
