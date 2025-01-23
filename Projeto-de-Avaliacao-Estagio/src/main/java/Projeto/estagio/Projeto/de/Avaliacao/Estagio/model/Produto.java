package Projeto.estagio.Projeto.de.Avaliacao.Estagio.model;
//Classe Produto

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Produto {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
     @Column
     private String nome;
     @Column
     private String descricao;
     @Column
     private String categoria;
     @Column
     private double preco;
     //Construtores
     public Produto() {
     }
     public Produto(int id, String nome, String descricao, String categoria, double preco) {
         this.id = id;
         this.nome = nome;
         this.descricao = descricao;
         this.categoria = categoria;
         this.preco = preco;
     
         }
     //Getters and Setters
     public int getId() {
         return id;
     }
     public void setId(int id) {
         this.id = id;
     }
     public String getNome() {
         return nome;
     }
     public void setNome(String nome) {
         this.nome = nome;
     }
     public String getDescricao() {
         return descricao;
     }
     public void setDescricao(String descricao) {
         this.descricao = descricao;
     }
     public String getCategoria() {
         return categoria;
     }
     public void setCategoria(String categoria) {
         this.categoria = categoria;
     }
     public double getPreco() {
         return preco;
     }
     public void setPreco(double preco) {
         this.preco = preco;
     }

}
