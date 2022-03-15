package com.mercadolibre.dto;

import java.io.Serializable;

public class IsMutantDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributo que hace referencia si es mutante o no
	 */
	private Boolean isMutant;
	
	/**
	 * Atributo que hace referencia al codigo http
	 */
	private String httpCodeMessage;

	/**
	 * @return the isMutant
	 */
	public Boolean getIsMutant() {
		return isMutant;
	}

	/**
	 * @param isMutant the isMutant to set
	 */
	public void setIsMutant(Boolean isMutant) {
		this.isMutant = isMutant;
	}

	/**
	 * @return the httpCodeMessage
	 */
	public String getHttpCodeMessage() {
		return httpCodeMessage;
	}

	/**
	 * @param httpCodeMessage the httpCodeMessage to set
	 */
	public void setHttpCodeMessage(String httpCodeMessage) {
		this.httpCodeMessage = httpCodeMessage;
	}
	
}
