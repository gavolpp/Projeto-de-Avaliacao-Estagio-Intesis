package Projeto.estagio.Projeto.de.Avaliacao.Estagio.repository;
//venda repository

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Projeto.estagio.Projeto.de.Avaliacao.Estagio.model.Venda;

public interface VendaRepository extends JpaRepository <Venda, Integer> {
    //retornar lista de vendas por cliente
    List<Venda> findByClienteId(int id);
    //retornar lista de vendas por data de venda
    List<Venda> findByDataVenda(LocalDate dataVenda);
    //retornar lista de vendas por forma de pagamento
    List<Venda> findByFormaDePag(String formaDePag);
    //retornar lista de vendas por preço total
    List<Venda> findByPrecoTotal(Double precoTotal);
}
