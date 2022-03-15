package com.mercadolibre.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.dto.ConsultaTipoCandidatoDTO;
import com.mercadolibre.dto.IsMutantDTO;
import com.mercadolibre.dto.MutantInDTO;
import com.mercadolibre.service.MutanteService;


@RestController
@RequestMapping("/controlador")
public class ControladorMutante {
	
	/**
	 * Atributo que hace referencia al los servicios del mutante
	 */
	@Autowired
    private MutanteService mutanteService;
	
	/**
     * Metodo encargado de verificar si el dna es mutante o no
     * 
     * @author Felipe Andres Jamioy Giron
     * @param mutantInDTO lista de string con el dna
     * @return respuesta si es o no mutante
	 * @throws Exception 
     */
	@PostMapping("/mutant")
    @ResponseStatus(HttpStatus.CREATED)
	public IsMutantDTO isMutant(@RequestBody MutantInDTO mutantInDTO) throws Exception {
		return mutanteService.isMutant(mutantInDTO);		
	}	
	
    /**
     * Metodo encargado de consultar los tipos de candidato que existe si es humano o mutante
     * 
     * @author Felipe Andres Jamioy Giron
     * @param Strind dna
     * @return Encontro coincidencia
     */
	@GetMapping("/stats")
    @ResponseStatus(HttpStatus.CREATED)
	public ConsultaTipoCandidatoDTO consultaTipos() {
		return mutanteService.consultaTipoCandidatos();
	}
    
}
