package Projeto.estagio.Projeto.de.Avaliacao.Estagio.model;

public class ProdutoDTO {
    private int produtoId;  // ID do produto
    private Integer quantidade;  // Quantidade comprada

    // Getters e Setters
    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
