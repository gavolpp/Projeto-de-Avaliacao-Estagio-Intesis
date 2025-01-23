package Projeto.estagio.Projeto.de.Avaliacao.Estagio.controller;
//Classe ClienteController

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

import Projeto.estagio.Projeto.de.Avaliacao.Estagio.model.Cliente;
import Projeto.estagio.Projeto.de.Avaliacao.Estagio.repository.ClienteRepository;

@RestController
@RequestMapping("/apiCliente")
public class ClienteController {
    //dependência com ClienteRepository
    @Autowired
    private ClienteRepository clienteRepo;
    
    //metodo para inserir clientes
    @PostMapping("/inserir")
    public void inserir(@RequestBody Cliente c){
        clienteRepo.save(c);
    }
    
    //metodo para listar todos os clientes
    @GetMapping("/listarTodos")
    public List<Cliente> listarTodos(){
        return clienteRepo.findAll();
    }
    //metodo para listar cliente por id
    @GetMapping("/listarPorId/{id}")
    public Optional<Cliente> listarPorId(@PathVariable int id){
        return clienteRepo.findById(id);
    }
    
    //metodo para listar clientes por nome
    @GetMapping("/listarPorNome/{nome}")
    public List<Cliente> listarPorNome(@PathVariable String nome){
        return clienteRepo.findByNome(nome);
    }
    
    //metodo para listar clientes por parte do nome
    @GetMapping("/listarPorParteNome/{nome}")
    public List<Cliente> listarPorParteNome(@PathVariable String nome){
        return clienteRepo.findByParteN(nome);
    }
    
    //metodo para listar clientes por cpf
    @GetMapping("/listarPorCpf/{cpf}")
    public List<Cliente> listarPorCpf(@PathVariable String cpf){
        return clienteRepo.findByCpf(cpf);
    }
    
    //metodo para listar clientes por telefone
    @GetMapping("/listarPorTelefone/{telefone}")
    public List<Cliente> listarPorTelefone(@PathVariable String telefone){
        return clienteRepo.findByTelefone(telefone);
    }
    
    //metodo para deletar um cliente
    @DeleteMapping("/deletar/{id}")
    public void deletar(@PathVariable int id){
        clienteRepo.deleteById(id);
    }
    
    //metodo para atualizar um cliente
    @PutMapping("/atualizar")
    public Cliente atualizar(@RequestBody Cliente c){
        return clienteRepo.save(c);
    }




}
