package com.senai.ordemServico.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.senai.ordemServico.domain.validationGroup;
import com.senai.ordemServico.domain.exception.NegocioException;

@Entity
public class OrdemServico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Valid
	@ConvertGroup(from = Default.class, to = validationGroup.ClienteId.class)
	@NotNull
	@ManyToOne
	private Cliente cliente;

	@NotBlank
	private String descricao;
	@NotNull
	private BigDecimal preco;

	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private StatusOrdemServico status;

	// Somente leitura
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime dataAbertura;

	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime dataFinalizacao;

	@OneToMany(mappedBy = "ordemServico")
	private List<Comentario> comentarios = new ArrayList<>();

	// Construtores, Gets e Sets e hash code
	public OrdemServico() {

	}

	public OrdemServico(Long id, Cliente cliente, String descricao, BigDecimal preco, StatusOrdemServico status,
			LocalDateTime dataAbertura, LocalDateTime dataFinalizacao) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.descricao = descricao;
		this.setPreco(preco);
		this.status = status;
		this.dataAbertura = dataAbertura;
		this.dataFinalizacao = dataFinalizacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemServico other = (OrdemServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void finalizar() {
		// Se o status não estiver aberto
		if (!StatusOrdemServico.ABERTA.equals(getStatus())) {
			throw new NegocioException("Ordem de serviço não pode ser finalizada");
		}
		// esses set já temos na propria classe na linha 103 e 119
		setStatus(StatusOrdemServico.FINALIZADA);
		setDataFinalizacao(LocalDateTime.now());

	}

}
