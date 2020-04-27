package com.senai.ordemServico.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//vai scannear e toda a aplicação vai receber a instancia 
//desse biblioteca.
@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper  modelMapper() {
		return new ModelMapper();
	}
}
