package Projeto.estagio.Projeto.de.Avaliacao.Estagio.controller;
//Venda Controller

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Projeto.estagio.Projeto.de.Avaliacao.Estagio.model.Cliente;
import Projeto.estagio.Projeto.de.Avaliacao.Estagio.model.Produto;
import Projeto.estagio.Projeto.de.Avaliacao.Estagio.model.ProdutoDTO;
import Projeto.estagio.Projeto.de.Avaliacao.Estagio.model.Venda;
import Projeto.estagio.Projeto.de.Avaliacao.Estagio.model.VendaDTO;
import Projeto.estagio.Projeto.de.Avaliacao.Estagio.repository.ClienteRepository;
import Projeto.estagio.Projeto.de.Avaliacao.Estagio.repository.ProdutoRepository;
import Projeto.estagio.Projeto.de.Avaliacao.Estagio.repository.VendaRepository;

@RestController
@RequestMapping("/apiVenda")
public class VendaController {
    //dependência com venda repository
    @Autowired
    private VendaRepository vendaRepo;
    @Autowired
    private ProdutoRepository produtoRepo;
    @Autowired
    private ClienteRepository clienteRepo;
    
    //metodo para inserir vendas
    @PostMapping("/inserir")
    public ResponseEntity<Venda> criarVenda(@RequestBody VendaDTO vendaDTO) {
    // Recuperar o cliente com base no ID
    Cliente cliente = clienteRepo.findById(vendaDTO.getClienteId())
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

    // Usar a data fornecida pelo usuário
    String dataVenda = vendaDTO.getData();

    // Criar a nova venda com os dados do DTO
    Venda venda = new Venda(cliente, vendaDTO.getFormaPagamento(), dataVenda);

    // Adicionar produtos à venda (pela lista de ProdutoDTO)
    for (ProdutoDTO produtoDTO : vendaDTO.getProdutos()) {
        Produto produto = produtoRepo.findById(produtoDTO.getProdutoId())
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        
        // Adiciona o produto à venda
        venda.adicionarProduto(produto, produtoDTO.getQuantidade());
        }
    // Salva a venda no banco
    vendaRepo.save(venda);

    return ResponseEntity.ok(venda);  // Retorna a venda criada
    }   
    
    @PostMapping("/adicionarProduto/{vendaId}/{produtoId}/{quantidade}")
public ResponseEntity<Venda> adicionarProduto(@PathVariable int vendaId, @PathVariable int produtoId, @PathVariable int quantidade) {
    Venda venda = vendaRepo.findById(vendaId)
        .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

    Produto produto = produtoRepo.findById(produtoId)
        .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

    venda.adicionarProduto(produto, quantidade);

    vendaRepo.save(venda);

    return ResponseEntity.ok(venda);
    }

    @DeleteMapping("/removerProduto/{vendaId}/{produtoId}")
    public ResponseEntity<Venda> removerProduto(@PathVariable int vendaId, @PathVariable int produtoId) {
    Venda venda = vendaRepo.findById(vendaId)
        .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

    Produto produto = produtoRepo.findById(produtoId)
        .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

    venda.removerProduto(produto);

    vendaRepo.save(venda);

    return ResponseEntity.ok(venda);
    }

    @PutMapping("/editarProduto/{vendaId}/{produtoId}/{quantidade}")
    public ResponseEntity<Venda> editarProduto(@PathVariable int vendaId, @PathVariable int produtoId, @PathVariable int quantidade) {
    Venda venda = vendaRepo.findById(vendaId)
        .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

    Produto produto = produtoRepo.findById(produtoId)
        .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

    venda.editarProduto(produto, quantidade);

    vendaRepo.save(venda);

    return ResponseEntity.ok(venda);
    }

    //metodo para listar vendas por cliente
    @GetMapping("/listarPorCliente/{id}")
    public Iterable<Venda> listarPorCliente(@PathVariable int id){
        return vendaRepo.findByClienteId(id);
    }
    
    //metodo para listar vendas por data de venda
    @GetMapping("/listarPorDataVenda/{dataVenda}")
    public Iterable<Venda> listarPorDataVenda(@PathVariable String dataVenda){
        return vendaRepo.findByDataVenda(dataVenda);
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
    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable int id){
        vendaRepo.deleteById(id);
    }

    @PutMapping("/atualizar/{id}")
public ResponseEntity<Venda> atualizar(@PathVariable int id, @RequestBody VendaDTO vendaDTO) {
    Cliente cliente = clienteRepo.findById(vendaDTO.getClienteId())
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

    Venda vendaExistente = vendaRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

    // Atualiza os dados da venda
    vendaExistente.setCliente(cliente);
    vendaExistente.setFormaDePag(vendaDTO.getFormaPagamento());
    vendaExistente.setDataVenda(vendaDTO.getData());

    // Adicionar/atualizar produtos
    for (ProdutoDTO produtoDTO : vendaDTO.getProdutos()) {
        Produto produto = produtoRepo.findById(produtoDTO.getProdutoId())
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        vendaExistente.adicionarProduto(produto, produtoDTO.getQuantidade());
    }

    vendaRepo.save(vendaExistente);
    return ResponseEntity.ok(vendaExistente);
}

}
