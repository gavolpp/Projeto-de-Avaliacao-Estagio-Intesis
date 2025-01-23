package Projeto.estagio.Projeto.de.Avaliacao.Estagio.controller;
//Venda Controller

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Projeto.estagio.Projeto.de.Avaliacao.Estagio.model.Produto;
import Projeto.estagio.Projeto.de.Avaliacao.Estagio.model.Venda;
import Projeto.estagio.Projeto.de.Avaliacao.Estagio.repository.ProdutoRepository;
import Projeto.estagio.Projeto.de.Avaliacao.Estagio.repository.VendaRepository;

@RestController
@RequestMapping("/apiVenda")
public class VendaController {
    //dependência com venda repository
    @Autowired
    private VendaRepository vendaRepo;
    private ProdutoRepository produtoRepo;
    
    //metodo para inserir vendas
    @PostMapping("/inserir")
    public void inserir(@RequestBody Venda v){
        vendaRepo.save(v);
    }
    
    //metodo para inserir produto na venda
    @PostMapping("/adicionarProduto/{vendaId}/{produtoId}/{quantidade}")
    public Venda adicionarProduto(@PathVariable int vendaId, @PathVariable int produtoId, @PathVariable int quantidade) {
    Venda venda = vendaRepo.findById(vendaId).orElseThrow(() -> new RuntimeException("Venda não encontrada"));
    Produto produto = produtoRepo.findById(produtoId).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    
    // Adiciona o produto à venda
    venda.getProdutos().add(produto);
    
    // Atualiza o preço total
    venda.setPrecoTotal(venda.getPrecoTotal() + (produto.getPreco() * quantidade));
    
    return vendaRepo.save(venda);  // Atualiza a venda no banco de dados
}
    
    //metodo para listar vendas por cliente
    @GetMapping("/listarPorCliente/{id}")
    public Iterable<Venda> listarPorCliente(@PathVariable int id){
        return vendaRepo.findByClienteId(id);
    }
    
    //metodo para listar vendas por data de venda
    @GetMapping("/listarPorDataVenda/{dataVenda}")
    public Iterable<Venda> listarPorDataVenda(@PathVariable String dataVenda){
        return vendaRepo.findByDataVenda(LocalDate.parse(dataVenda));
    }
    
    //metodo para listar vendas por forma de pagamento
    @GetMapping("/listarPorFormaDePag/{formaDePag}")
    public Iterable<Venda> listarPorFormaDePag(@PathVariable String formaDePag){
        return vendaRepo.findByFormaDePag(formaDePag);
    }
    
    //metodo para listar vendas por preço total
    @GetMapping("/listarPorPrecoTotal/{precoTotal}")
    public Iterable<Venda> listarPorPrecoTotal(@PathVariable double precoTotal){
        return vendaRepo.findByPrecoTotal(precoTotal);
    }

    //metodo para deletar venda por id
    @GetMapping("/deletar/{id}")
    public void deletar(@PathVariable int id){
        vendaRepo.deleteById(id);
    }

    //metodo para atualizar venda
    @PostMapping("/atualizar")
    public Venda atualizar(@RequestBody Venda v){
        return vendaRepo.save(v);
    }

}
