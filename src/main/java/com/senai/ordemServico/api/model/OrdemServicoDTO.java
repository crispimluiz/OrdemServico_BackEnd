package com.senai.ordemServico.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.senai.ordemServico.domain.model.StatusOrdemServico;

public class OrdemServicoDTO {
	// atributos não precisa ter o mesmo nome do model
	private Long id;
	private ClienteResumoDTO cliente;
	private String descricao;
	private BigDecimal preco;
	private StatusOrdemServico status;
	private LocalDateTime dataAbertura;
	private LocalDateTime dataFinalizacao;

	// somente get e sets
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public StatusOrdemServico getStatus() {
		return status;
	}

	public void setStatus(StatusOrdemServico status) {
		this.status = status;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	public ClienteResumoDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteResumoDTO cliente) {
		this.cliente = cliente;
	}

}
