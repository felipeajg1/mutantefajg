package com.mercadolibre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mercadolibre.entity.CandidatoDna;
import com.mercadolibre.enums.TipoCandidatoEnum;


/**
 * Interface que determina la conexión con el jpaRepository
 * @author Felipe Andres Jamioy Girón
 *
 */
@Repository
public interface MutanteRepository extends JpaRepository<CandidatoDna, Long> {

    @Query("SELECT COUNT(c.tipo) FROM CandidatoDna c WHERE c.tipo=:tipo")
    Long consultaTipoCandidato(@Param("tipo") TipoCandidatoEnum tipo);
}
