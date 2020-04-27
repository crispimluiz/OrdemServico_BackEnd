package com.senai.ordemServico.domain.exception;

public class EntidadeNaoEncontradaException extends NegocioException{

	private static final long serialVersionUID = 1L;

	//source --> gerar contrutor de super class
	public EntidadeNaoEncontradaException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		//Precisa colocar no Gestão OrdemServico
	}

	
}
