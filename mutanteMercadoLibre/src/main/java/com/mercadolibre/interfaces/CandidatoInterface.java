package com.mercadolibre.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mercadolibre.dto.ConsultaTipoCandidatoDTO;
import com.mercadolibre.dto.IsMutantDTO;
import com.mercadolibre.dto.MutantInDTO;

/**
 * Interface encargada de proveer los servicios propios del usuario
 * @author Felipe Andres Jamioy Girón
 *
 */
public interface CandidatoInterface {	
	
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
    IsMutantDTO isMutant(@RequestBody MutantInDTO mutantInDTO) throws Exception;
    
	 /**
     * Metodo encargado de consultar los tipos de candidato si es mutante o humano
     * 
     * @author Felipe Andres Jamioy Giron
     * @return DTO respuesta
     */
    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.CREATED)
    ConsultaTipoCandidatoDTO consultaTipoCandidatos();
	
}
