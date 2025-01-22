package Projeto.estagio.Projeto.de.Avaliacao.Estagio.repository;

//Cliente repository

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Projeto.estagio.Projeto.de.Avaliacao.Estagio.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    //retornar lista por nome
    public List<Cliente> findByNome(String nome);
    //retornar lista por parte do nome
    @Query("select a from Cliente a where a.nome like ?1")
    public List<Cliente> findByParteN(String nome);
    //retornar lista por cpf
    public List<Cliente> findByCPF(String cpf);
    //retornar lista por telefone
    public List<Cliente> findByTelefone(String telefone);
}
