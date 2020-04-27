package com.senai.ordemServico.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.ordemServico.domain.exception.NegocioException;
import com.senai.ordemServico.domain.model.Cliente;
import com.senai.ordemServico.domain.repository.ClienteRepository;

@Service
public class CadastroClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	//Cliente vem do modal, clienteRepository repository
	public Cliente salvar (Cliente cliente) {
		Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
		//Se cliente não for null && verifica se o clienteExiste e igual o do banco
		//Lembra que essa class pode atualizar, sendo outro cliente não muda o email
		if(clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já existe cliente com esse e-mail.");
		}
		return clienteRepository.save(cliente);
	}
	
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
}
