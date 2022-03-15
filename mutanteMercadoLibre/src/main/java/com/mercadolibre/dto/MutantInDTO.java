package com.mercadolibre.dto;

import java.io.Serializable;


/**
 * Clase que contiene la lista dna
 * @author Felipe Andres Jamioy Giron
 *
 */
public class MutantInDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * dna de la persona
	 */
	private String[] dna;

	/**
	 * @return the dna
	 */
	public String[] getDna() {
		return dna;
	}

	/**
	 * @param dna the dna to set
	 */
	public void setDna(String[] dna) {
		this.dna = dna;
	}
	
}