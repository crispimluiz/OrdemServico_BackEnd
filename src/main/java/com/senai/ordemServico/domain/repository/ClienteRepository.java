package com.senai.ordemServico.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.ordemServico.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	/*Aqui fica um controle de componentes onde JpaRepository já tem
	várias funções prontas.
	No controllertenho que instanciar essa classe para receber essas
	funções.*/
	 List<Cliente> findByNome(String nome);
	 List<Cliente> findByNomeContaining(String nome);//parte dos nomes
	 Cliente findByEmail(String email);
}
