package com.senai.ordemServico.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.senai.ordemServico.domain.model.Cliente;
import com.senai.ordemServico.domain.repository.ClienteRepository;
import com.senai.ordemServico.domain.service.CadastroClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CadastroClienteService cadastroCliente;
	
	@GetMapping
	public List<Cliente> listar() {
		//buscar no banco de dados
		//Agora teremos uma lista de metodos já prontos
		return clienteRepository.findAll();
		//Colocamos o método 'findByNome' e o que procuro.
	}
	//Esse ("/{clienteId}") não pode ser retirado
	@GetMapping("/{clienteId}")//{}pega o valor digitado
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return cadastroCliente.salvar(cliente);
	}
	
	//Atualizar um cliente
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar (@Valid @PathVariable Long clienteId, 
			@RequestBody Cliente cliente){
		//se não existe o cliente  volte não encontrado
		 if(!clienteRepository.existsById(clienteId)) {
			 return ResponseEntity.notFound().build();
		}
		 cliente.setId(clienteId);
		 cliente = cadastroCliente.salvar(cliente);
		 return ResponseEntity.ok(cliente);
	}
	
	//Deletar
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover (@PathVariable Long clienteId){
		//se não existe o cliente  volte não encontrado
		 if(!clienteRepository.existsById(clienteId)) {
			 return ResponseEntity.notFound().build();
		}
		 cadastroCliente.excluir(clienteId);
		 return ResponseEntity.noContent().build();
		 //noContent é o status 204
	}
	
}