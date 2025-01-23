package Projeto.estagio.Projeto.de.Avaliacao.Estagio.model;

import java.util.List;

public class VendaDTO {
    private int clienteId;  // ID do cliente
    private String formaPagamento;  // Forma de pagamento (Cartão, Dinheiro, etc.)
    private List<ProdutoDTO> produtos;  // Lista de produtos e quantidades
    private String data;  // Data da venda

    // Construtores
    public VendaDTO() {
    }

    public VendaDTO(int clienteId, String formaPagamento, List<ProdutoDTO> produtos, String data) {
        this.clienteId = clienteId;
        this.formaPagamento = formaPagamento;
        this.produtos = produtos;
        this.data = data;
    }

    // Getters e Setters
    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<ProdutoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
}
