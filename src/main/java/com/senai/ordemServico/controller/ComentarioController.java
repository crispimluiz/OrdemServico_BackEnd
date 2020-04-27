package com.senai.ordemServico.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.senai.ordemServico.api.model.ComentarioDTO;
import com.senai.ordemServico.api.model.ComentarioInput;
import com.senai.ordemServico.domain.exception.NegocioException;
import com.senai.ordemServico.domain.model.Comentario;
import com.senai.ordemServico.domain.model.OrdemServico;
import com.senai.ordemServico.domain.repository.OrdemServicoRepository;
import com.senai.ordemServico.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServicoService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@GetMapping
	public List<ComentarioDTO> listar(@PathVariable Long ordemServicoId) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new NegocioException("Ordem de serviço não encontrda"));

		return toCollectionModel(ordemServico.getComentarios());

	}

	// transformar List em Collection
	private List<ComentarioDTO> toCollectionModel(List<Comentario> comentarios) {
		return comentarios.stream().map(comentario -> toModel(comentario)).collect(Collectors.toList());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	ComentarioDTO adicionar(@PathVariable Long ordemServicoId, @Valid @RequestBody ComentarioInput comentarioInput) {
		Comentario comentario = gestaoOrdemServicoService.adicionarComentario(ordemServicoId,
				comentarioInput.getDescricao());

		return toModel(comentario);
	}

	// função para mapear a DTO e o Domain criando a função toModel
	private ComentarioDTO toModel(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioDTO.class);
	}
}
