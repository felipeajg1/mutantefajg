package com.mercadolibre.enums;

public enum TipoCandidatoEnum {
	
	/**
	 * Indicador que representa el estado Mutante
	 */
	MUTANTE("enum.mercadolibre.mutante"),
	/**
	 * Indicador que representa el estado Humano
	 */	
	HUMANO("enum.mercadolibre.humano");

	/**
	 * Atributo que contiene la clave del mensaje para la internacionalizacion  
	 */
	private String codigoMensaje;

	/**
	 * Constructor que recibe como parametro el codigo del mensaje
	 * 
	 * @param codigoMensaje, Clave del mensaje para para internacionalizacion
	 */
	TipoCandidatoEnum(String codigoMensaje) {		
		this.codigoMensaje = codigoMensaje;
	}

	/**
	 * Metodo que retorna el valor del atributo
	 * 
	 * @return cadena con el codigo del mensaje
	 */
	public String getCodigoMensaje() {
		return codigoMensaje;
	}
	
	/***
	 * 
	 * Metodo encargado de retornar el enum segun su nombre 
	 * @param nombre
	 * @return
	 */
	public static TipoCandidatoEnum getEnumValue(String nombre) {
		if (nombre != null) {
			for (TipoCandidatoEnum tipoCandidatoEnum : TipoCandidatoEnum.values()) {
				if (nombre.equalsIgnoreCase(tipoCandidatoEnum.name())) {
					return tipoCandidatoEnum;
				}
			}
		}
		return null;
	}
}
