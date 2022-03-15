package com.mercadolibre.dto;

import java.io.Serializable;

public class ConsultaTipoCandidatoDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributo que hace referencia a la cantidad de mutantes
	 */
	private Long countMutant;
	
	/**
	 * Atributo que hace referencia a la cantidad de humanos
	 */
	private Long countHuman;
	
	/**
	 * Atributo que hace referencia al ratio
	 */
	private Double ratio;

	/**
	 * @return the countMutant
	 */
	public Long getCountMutant() {
		return countMutant;
	}

	/**
	 * @param countMutant the countMutant to set
	 */
	public void setCountMutant(Long countMutant) {
		this.countMutant = countMutant;
	}

	/**
	 * @return the countHuman
	 */
	public Long getCountHuman() {
		return countHuman;
	}

	/**
	 * @param countHuman the countHuman to set
	 */
	public void setCountHuman(Long countHuman) {
		this.countHuman = countHuman;
	}

	/**
	 * @return the ratio
	 */
	public Double getRatio() {
		return ratio;
	}

	/**
	 * @param ratio the ratio to set
	 */
	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

}
