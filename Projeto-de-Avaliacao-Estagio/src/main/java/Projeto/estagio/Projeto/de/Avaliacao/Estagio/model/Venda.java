package Projeto.estagio.Projeto.de.Avaliacao.Estagio.model;
//Classe Venda

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Venda {
    @Id
    private int id;
    @Column
    private Cliente cliente;
    @Column
    private List<Produto> produtos;
    @Column
    private double precoTotal;
    @Column
    private LocalDate dataVenda;
    @Column
    private String formaDePag;
    //Construtores
    public Venda() {
    }
    public Venda(int id, Cliente cliente, List<Produto> produtos, double precoTotal, LocalDate dataVenda, String formaDePag) {
        this.id = id;
        this.cliente = cliente;
        this.produtos = produtos;
        this.precoTotal = precoTotal;
        this.dataVenda = dataVenda;
        this.formaDePag = formaDePag;
    }
    //Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public List<Produto> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    public double getPrecoTotal() {
        return precoTotal;
    }
    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }
    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }
    public String getFormaDePag() {
        return formaDePag;
    }
    public void setFormaDePag(String formaDePag) {
        this.formaDePag = formaDePag;
    }

}
