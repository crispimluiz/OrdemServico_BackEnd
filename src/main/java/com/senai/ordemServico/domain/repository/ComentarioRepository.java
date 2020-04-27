package com.senai.ordemServico.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.ordemServico.domain.model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
	
}
