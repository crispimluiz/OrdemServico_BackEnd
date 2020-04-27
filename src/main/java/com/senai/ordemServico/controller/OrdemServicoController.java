package com.senai.ordemServico.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.senai.ordemServico.api.model.OrdemServicoDTO;
import com.senai.ordemServico.domain.model.OrdemServico;
import com.senai.ordemServico.domain.repository.OrdemServicoRepository;
import com.senai.ordemServico.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServico;
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)//201
	public OrdemServicoDTO criar(@Valid @RequestBody OrdemServico ordemServico) {
		return toModel(gestaoOrdemServico.criar(ordemServico));
	}
	//Recebe a função toCollectionModel criada abaixo
	@GetMapping
	public List<OrdemServicoDTO> listar(){
		return toCollectionModel(ordemServicoRepository.findAll());
	}
	
	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServicoDTO> buscar(@PathVariable Long ordemServicoId){
		Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoId);
		if(ordemServico.isPresent()) {//toModel metodo abaixo linha 58
			OrdemServicoDTO ordemServicoDTO = toModel(ordemServico.get());
			return ResponseEntity.ok(ordemServicoDTO);
		}//NotFoud volta erro 404
		return ResponseEntity.notFound().build();
	}
	
	//Método que mapea OrdemServico e junta com OrdemServicoDTO
	//Usada no list por id e no post
	private OrdemServicoDTO toModel(OrdemServico ordemServico) {
		return modelMapper.map(ordemServico, OrdemServicoDTO.class);
	}
	
	//Tranformando uma List em uma Collection - Será usada no list
	private List<OrdemServicoDTO> toCollectionModel(List<OrdemServico> ordensServico){
		return ordensServico.stream()
				.map(ordemServico -> toModel(ordemServico))
				.collect(Collectors.toList());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
