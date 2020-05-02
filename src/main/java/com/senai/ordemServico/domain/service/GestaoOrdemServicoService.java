package com.senai.ordemServico.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.ordemServico.domain.exception.NegocioException;
import com.senai.ordemServico.domain.model.Cliente;
import com.senai.ordemServico.domain.model.Comentario;
import com.senai.ordemServico.domain.model.OrdemServico;
import com.senai.ordemServico.domain.model.StatusOrdemServico;
import com.senai.ordemServico.domain.repository.ClienteRepository;
import com.senai.ordemServico.domain.repository.ComentarioRepository;
import com.senai.ordemServico.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ComentarioRepository comentarioRepository;

	// Quando abre uma ordem de serviço.
	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado!"));

		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(LocalDateTime.now());
		return ordemServicoRepository.save(ordemServico);

	}

	// Criando o comentário dentro da ordem serviço
	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new NegocioException("Ordem e serviço não encontrado!"));

		Comentario comentario = new Comentario();
		comentario.setDataEnvio(LocalDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);

		return comentarioRepository.save(comentario);
	}

	public void finalizar(Long ordemServicoId) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new NegocioException("Ordem e serviço não encontrado!"));
		ordemServico.finalizar(); // Vou Criar esse metodo dentro de ordemServico Modal.

		ordemServicoRepository.save(ordemServico);
	}

}
