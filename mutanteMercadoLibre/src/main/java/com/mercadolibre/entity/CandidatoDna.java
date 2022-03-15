package com.mercadolibre.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mercadolibre.enums.TipoCandidatoEnum;


/**
 * Clase que determina la entidad del candidato
 * @author Felipe Andres Jamioy
 * @version
 */
@Entity
@Table(name = "TBL_CANDIDATODNA")
public class CandidatoDna implements Serializable {

	/**
	 * atributo de serializacion
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributo que determina el id del candidato
	 */
	@Id
	@GeneratedValue
	@Column(name = "IDCANDIDATO")
	private Long idCandidato;
	
	/**
	 * Atributo que determina el tipo del candidato
	 */
	@Column(name = "TIPO")
	@Enumerated(value = EnumType.STRING)
	private TipoCandidatoEnum tipo;

	/**
	 * @return the idCandidato
	 */
	public Long getIdCandidato() {
		return idCandidato;
	}

	/**
	 * @param idCandidato the idCandidato to set
	 */
	public void setIdCandidato(Long idCandidato) {
		this.idCandidato = idCandidato;
	}

	/**
	 * @return the tipo
	 */
	public TipoCandidatoEnum getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(TipoCandidatoEnum tipo) {
		this.tipo = tipo;
	}

}
