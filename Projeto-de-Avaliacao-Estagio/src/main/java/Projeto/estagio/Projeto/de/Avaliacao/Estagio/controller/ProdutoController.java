package Projeto.estagio.Projeto.de.Avaliacao.Estagio.controller;

//Produto Controller

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Projeto.estagio.Projeto.de.Avaliacao.Estagio.model.Produto;
import Projeto.estagio.Projeto.de.Avaliacao.Estagio.repository.ProdutoRepository;

@RestController
@RequestMapping("/apiProduto")
public class ProdutoController {
    //depêndencia com Produto repository
    @Autowired
    private ProdutoRepository produtoRepo;
    
    //metodo para inserir produtos
    @PostMapping("/inserir")
    public void inserir(@RequestBody Produto p){
        produtoRepo.save(p);
    }
    
    //metodo para listar todos os produtos
    @GetMapping("/listarTodos")
    public List<Produto> listarTodos(){
        return produtoRepo.findAll();
    }
    
    //metodo para listar produto por id
    @GetMapping("/listarPorId/{id}")
    public Optional<Produto> listarPorId(@PathVariable int id){
        return produtoRepo.findById(id);
    }
    
    //metodo para listar produto por nome
    @GetMapping("/listarPorNome/{nome}")
    public List<Produto> listarPorNome(@PathVariable String nome){
        return produtoRepo.getByNome(nome);
    }
    
    //metodo para listar produto por parte do nome
    @GetMapping("/listarPorParteNome/{nome}")
    public List<Produto> listarPorParteNome(@PathVariable String nome){
        return produtoRepo.getByParteN(nome);
    }
    
    //metodo para listar produto por descrição
    @GetMapping("/listarPorDescricao/{descricao}")
    public List<Produto> listarPorDescricao(@PathVariable String descricao){
        return produtoRepo.getByDescricao(descricao);
    }
    
    //metodo para listar produto por categoria
    @GetMapping("/listarPorCategoria/{categoria}")
    public List<Produto> listarPorCategoria(@PathVariable String categoria){
        return produtoRepo.getByCategoria(categoria);
    }
    
    //metodo para listar produto por preço menor do que o informado
    @GetMapping("/listarPorPreco/{preco}")
    public List<Produto> listarPorPreco(@PathVariable double preco){
        return produtoRepo.getByPreco(preco);
    }

    //metodo para deletar um produto
    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable int id){
        produtoRepo.deleteById(id);
    }
    
    //metodo para atualizar um produto
    @PutMapping("/atualizar")
    public Produto atualizar(@RequestBody Produto p){
        return produtoRepo.save(p);
    }
}
