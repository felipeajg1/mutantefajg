package com.mercadolibre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mercadolibre.dto.ConsultaTipoCandidatoDTO;
import com.mercadolibre.dto.IsMutantDTO;
import com.mercadolibre.dto.MutantInDTO;
import com.mercadolibre.entity.CandidatoDna;
import com.mercadolibre.enums.TipoCandidatoEnum;
import com.mercadolibre.interfaces.CandidatoInterface;
import com.mercadolibre.repository.MutanteRepository;


/**
 * Clase en la cual se encuentra la logica de negocio para el mutante
 * @author Felipe Andres Jamioy Girón
 *
 */
@Service
public class MutanteService implements CandidatoInterface {

	@Autowired
	private MutanteRepository mutanteRepository;

	/**
     * Metodo encargado de verificar si el dna es mutante o no
     * 
     * @author Felipe Andres Jamioy Giron
     * @param mutantInDTO lista de string con el dna
     * @return respuesta si es o no mutante
	 * @throws Exception 
     */
	@Override
	public IsMutantDTO isMutant(@RequestBody MutantInDTO mutantInDTO) throws Exception {
    	
		IsMutantDTO isMutantDTO = new IsMutantDTO();
		isMutantDTO.setIsMutant(false);
		isMutantDTO.setHttpCodeMessage(HttpStatus.FORBIDDEN.toString());
    	
    	try {
    		
    		// Se valida si la matriz es nxn
    		if (mutantInDTO.getDna().length != mutantInDTO.getDna()[0].length()) {
    			isMutantDTO.setIsMutant(false);
    			isMutantDTO.setHttpCodeMessage(HttpStatus.FORBIDDEN.toString());
    			return isMutantDTO;
    		}
    		
    		// Primero valida las horizontales, si encuentra dos coincidencias retorna true
    		Long contador = validarHorizontales(mutantInDTO.getDna());
    		
    		if (contador >= 2) {
    			persistirCandidato(true);
    			isMutantDTO.setIsMutant(true);
    			isMutantDTO.setHttpCodeMessage(HttpStatus.OK.toString());
    			return isMutantDTO;
    		}
    		
    		//Se construye la matriz a partir de la lista de String para validar las verticales y diagonales
    		String matrizMutante[][] = construirMatriz(mutantInDTO.getDna());
    		
    		//Se validan las verticales si encuentra dos concidencias retorna true
    		contador = validarVerticales(matrizMutante, contador);
    		
    		if (contador >= 2) {
    			persistirCandidato(true);
    			isMutantDTO.setIsMutant(true);
    			isMutantDTO.setHttpCodeMessage(HttpStatus.OK.toString());
    			return isMutantDTO;
    		}
    		
    		//Se validan las diagonales y si encuentra dos coincidencias retorna true por el contrario retorna false
    		contador = validarDiagonal(construirMatriz(mutantInDTO.getDna()), contador);
    		
    		if (contador >= 2) {
    			persistirCandidato(true);
    			isMutantDTO.setIsMutant(true);
    			isMutantDTO.setHttpCodeMessage(HttpStatus.OK.toString());
    			return isMutantDTO;
    		}
    		
			persistirCandidato(false);
			
		} catch (Exception e) {
			throw new Exception("Error al consultar el dna del candidato");
		}
    	
		return isMutantDTO;
    	
	}
	
    /**
     * Metodo encargado de consultar los tipos de candidato que existe si es humano o mutante
     * 
     * @author Felipe Andres Jamioy Giron
     * @param Strind dna
     * @return Encontro coincidencia
     */
	@Override
	public ConsultaTipoCandidatoDTO consultaTipoCandidatos() {
		Long mutantes = mutanteRepository.consultaTipoCandidato(TipoCandidatoEnum.MUTANTE);
		Long humanos = mutanteRepository.consultaTipoCandidato(TipoCandidatoEnum.HUMANO);
		
		ConsultaTipoCandidatoDTO consultaTipoCandidatoDTO = new ConsultaTipoCandidatoDTO();
		consultaTipoCandidatoDTO.setCountHuman(humanos);
		consultaTipoCandidatoDTO.setCountMutant(mutantes);	
		if (mutantes == 0 && humanos == 0) {
			consultaTipoCandidatoDTO.setRatio(0.0);
		} else if (humanos==0) {
			consultaTipoCandidatoDTO.setRatio(0.0);
		} else {
			Double ratio =(double) mutantes/humanos;
			consultaTipoCandidatoDTO.setRatio(ratio);
		}

		return consultaTipoCandidatoDTO;
	}
	
    /**
     * Metodo encargado de construir la matriz a partir de la lista de String
     * 
     * @author Felipe Andres Jamioy Giron
     * @param lista de String
     * @return Matriz
     */
	private String[][] construirMatriz(String[] dna) {
		
		String matrizMutante[][] = new String[dna.length][dna.length];
		
		for (int i = 0; i < dna.length; i++) {
			for (int j = 0; j < dna.length; j++) {
				matrizMutante[i][j] = Character.toString(dna[i].charAt(j)).toUpperCase();
			}			
		}
		
		return matrizMutante;		
	}
	
    /**
     * Metodo encargado de validar las horizontales posibles y retorna si encuentra 2 coincidencias de lo contrario
     * termina la operacion retornando un entero con la cantidad encontrada.
     * 
     * @author Felipe Andres Jamioy Giron
     * @param lista de string
     * @return cantidad de coincidencias
     */
	private Long validarHorizontales(String[] dna) {
		
		Long contador = 0L;
		
		for (String dnaHorizontal : dna) {
			
			if (validarMutante(dnaHorizontal.toUpperCase())) {
				contador = contador+1;				
			}
			
			if (contador >= 2) {
				return contador;
			}		
		}
		
		return contador;
		
	}
	
    /**
     * Metodo encargado de validar las verticales posibles y retorna si encuentra 2 coincidencias de lo contrario
     * termina la operacion retornando un entero con la cantidad encontrada.
     * 
     * @author Felipe Andres Jamioy Giron
     * @param matriz y contador actual
     * @return cantidad de coincidencias
     */
	private Long validarVerticales(String matrizMutante[][], Long contador) {
		
		String vertical = "";
		
		for (int i = 0; i < matrizMutante.length; i++) {
			
			for (int j = 0; j < matrizMutante.length; j++) {
				vertical = vertical + matrizMutante[j][i];
			}
			
			if (validarMutante(vertical)) {
				contador = contador+1;				
			}
			
			if (contador >= 2) {
				return contador;
			}
			
			vertical = "";
			
		}
		
		return contador;		
	}
	
    /**
     * Metodo encargado de validar las diagonales posibles y retorna si encuentra 2 coincidencias de lo contrario
     * termina la operacion retornando un entero con la cantidad encontrada.
     * 
     * @author Felipe Andres Jamioy Giron
     * @param matriz, tamaño, contador actual
     * @return cantidad de coincidencias
     */
	private Long validarDiagonal(String matrizMutante[][], Long contador) {
		
		int i = matrizMutante.length-4;
		int j = 0;
		int tamano = 4;
		
		for (int k = 0; k < matrizMutante.length; k++) {
			
			if (i != j) {
				contador = realizarDiagonales(matrizMutante, contador, i, j, tamano);
				tamano = tamano + 1;
				
				if (contador >= 2) {
					return contador;
				}
				
				i = i-1;
			} else {
				contador = recorrerDiagonarPrimaria(matrizMutante, contador);
				return contador;
			}
			
		}		
		
		return contador;
	}
	
    /**
     * Metodo encargado de realizar las diagonales, hace cuatro por recorrido donde encuentra la diagonal, diagonalsecundaria
     * diagonarlinversa y diagonalSecundariaInversa
     * 
     * @author Felipe Andres Jamioy Giron
     * @param matriz, tamaño, contador actual
     * @return cantidad de coincidencias
     */
	private Long realizarDiagonales(String matrizMutante[][], Long contador, int i, int j, int tamano) {
		
		int k = matrizMutante.length - 1;
		String diagonalPrimaria = "";
		String diagonalSecundaria = "";
		String diagonalPrimariaInversa = "";
		String diagonalSecundariaInversa = "";		
			
		for (int iterador = 0; iterador < tamano; iterador++) {			
			diagonalPrimaria = diagonalPrimaria + matrizMutante[i][j];
			diagonalSecundaria = diagonalSecundaria + matrizMutante[j][i];			
			diagonalPrimariaInversa = diagonalPrimariaInversa + matrizMutante[i][k-iterador];
			diagonalSecundariaInversa = diagonalSecundariaInversa +  matrizMutante[j][k-i];			
			i=i+1;
			j=j+1;
		}			
						
		return validarDiagonales(diagonalPrimaria, diagonalSecundaria, 
				diagonalPrimariaInversa, diagonalSecundariaInversa, contador);
	}
	
    /**
     * Metodo encargado de validar si en las diagonales existen coincidencias
     * 
     * @author Felipe Andres Jamioy Giron
     * @param diagonales encontradas en la iteración
     * @return cantidad de coincidencias
     */
	private Long validarDiagonales(String diagonalPrimaria, String diagonalSecundaria, 
			String diagonalPrimariaInversa, String diagonalSecundariaInversa, Long contador) {
		
		if (validarMutante(diagonalPrimaria)) {
			contador = contador + 1;
		}
		
		if (validarMutante(diagonalSecundaria)) {
			contador = contador + 1;
		}
		
		if (validarMutante(diagonalPrimariaInversa)) {
			contador = contador + 1;
		}
		
		if (validarMutante(diagonalSecundariaInversa)) {
			contador = contador + 1;
		}
		
		return contador;
		
	}
	
    /**
     * Metodo encargado de recorrer la diagonal principal y buscar coincidencias.
     * 
     * @author Felipe Andres Jamioy Giron
     * @param matriz y contador actual
     * @return cantidad de coincidencias
     */
	private Long recorrerDiagonarPrimaria(String matrizMutante[][], Long contador) {
		
		int i = 0;
		int j = 0;
		String diagonalPrincipal = "";
		String diagonalInversa = "";
		
		for (; i < matrizMutante.length;) {
			diagonalPrincipal = diagonalPrincipal + matrizMutante[i][j];
			i = i+1;
			j = j+1;
		}
		
		if (validarMutante(diagonalPrincipal)) {
			contador = contador + 1;
			if (contador >= 2) {
				return contador;
			}
		}
		
		i = 0;
		j = matrizMutante.length-1;
		
		for (int k = 0; k < matrizMutante.length; k++) {
			diagonalInversa = diagonalInversa + matrizMutante[i][j];
			i = i+1;
			j = j-1;
		}
		
		if (validarMutante(diagonalInversa)) {
			contador = contador + 1;
			if (contador >= 2) {
				return contador;
			}
		}
		
		return contador;		
	}	
	
    /**
     * Metodo encargado de validar si encuentra las coincidencias en el string de entrada
     * 
     * @author Felipe Andres Jamioy Giron
     * @param Strind dna
     * @return Encontro coincidencia
     */
	private Boolean validarMutante(String dna) {
		
		if (dna.contains("AAAA") || dna.contains("TTTT") ||
				dna.contains("CCCC") || dna.contains("GGGG")) {
			return true;				
		}
		
		return false;
	}
	
    /**
     * Metodo encargado de persistir el candidato
     * 
     * @author Felipe Andres Jamioy Giron
     * @param Candidato
     */
	private void persistirCandidato(Boolean mutante) {
		CandidatoDna candidato = new CandidatoDna();
		
		if (mutante) {
			candidato.setTipo(TipoCandidatoEnum.MUTANTE);
		} else {
			candidato.setTipo(TipoCandidatoEnum.HUMANO);
		}
		mutanteRepository.save(candidato);
	}	
	
}
