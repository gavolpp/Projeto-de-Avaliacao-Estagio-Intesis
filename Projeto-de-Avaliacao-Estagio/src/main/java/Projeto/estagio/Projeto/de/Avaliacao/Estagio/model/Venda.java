package Projeto.estagio.Projeto.de.Avaliacao.Estagio.model;
//Classe Venda

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @MapKeyJoinColumn(name = "cliente_id")
    private Cliente cliente;  // Relacionamento com Cliente

    @ElementCollection
    @CollectionTable(name = "produto_quantidade", joinColumns = @JoinColumn(name = "venda_id"))
    @MapKeyJoinColumn(name = "produto_id")
    @Column(name = "quantidade")
    private Map<Produto, Integer> produtosQuantidades = new HashMap<>();  // Mapa de produtos e suas quantidades

    @Column
    private double precoTotal=0;
    @Column
    private String dataVenda;
    @Column
    private String formaDePag;

    //Construtores

    public Venda() {
    }
    public Venda(Cliente cliente, String formaPagamento, String dataVenda) {
        this();
        this.cliente = cliente;
        this.formaDePag = formaPagamento;
        this.dataVenda = dataVenda;
    }

    // Método para adicionar um produto à venda
    public void adicionarProduto(Produto produto, Integer quantidade) {
        if (produto == null || quantidade <= 0) {
            return;  // Se o produto for nulo ou a quantidade for inválida, não adiciona
        }

        // Atualiza a quantidade ou adiciona o produto
        produtosQuantidades.put(produto, produtosQuantidades.getOrDefault(produto, 0) + quantidade);

        // Atualiza o preço total
        precoTotal += produto.getPreco() * quantidade;
    }

    // Método para remover um produto da venda
    public void removerProduto(Produto produto) {
        if (produto == null || !produtosQuantidades.containsKey(produto)) {
            return;  // Se o produto não existe na venda, não faz nada
        }

        // Remove o produto e ajusta o preço total
        Integer quantidade = produtosQuantidades.get(produto);
        precoTotal -= produto.getPreco() * quantidade;
        produtosQuantidades.remove(produto);
    }

    // Método para editar a quantidade de um produto na venda
    public void editarProduto(Produto produto, int novaQuantidade) {
        if (produto == null || !produtosQuantidades.containsKey(produto) || novaQuantidade <= 0) {
            return;  // Se o produto não existe ou a nova quantidade for inválida, não faz nada
        }

        // Obtém a quantidade antiga do produto
        int quantidadeAntiga = produtosQuantidades.get(produto);

        // Atualiza o preço total (remove o preço antigo e adiciona o novo preço)
        precoTotal -= produto.getPreco() * quantidadeAntiga;
        precoTotal += produto.getPreco() * novaQuantidade;

        // Atualiza a quantidade do produto
        produtosQuantidades.put(produto, novaQuantidade);
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
    public Map<Produto, Integer> getProdutosQuantidades() {
        return produtosQuantidades;
    }

    public void setProdutosQuantidades(Map<Produto, Integer> produtosQuantidades) {
        this.produtosQuantidades = produtosQuantidades;
    }
    public double getPrecoTotal() {
        return precoTotal;
    }
    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public String getDataVenda() {
        return dataVenda;
    }
    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }
    public String getFormaDePag() {
        return formaDePag;
    }
    public void setFormaDePag(String formaDePag) {
        this.formaDePag = formaDePag;
    }
}
