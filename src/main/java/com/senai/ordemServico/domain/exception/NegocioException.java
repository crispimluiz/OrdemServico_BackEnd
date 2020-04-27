package com.senai.ordemServico.domain.exception;

public class NegocioException extends RuntimeException{
//Erro de negocio
	private static final long serialVersionUID = 1L;
	
	public NegocioException(String message) {
		super(message);
	}
	
}
