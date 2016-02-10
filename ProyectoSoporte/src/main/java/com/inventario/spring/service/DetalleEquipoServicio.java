package com.inventario.spring.service;

import com.inventario.jpa.data.*;
import com.inventario.util.constante.Constantes;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class DetalleEquipoServicio {

	protected EntityManager entityManager;


	public EntityManager getEntityManager() {
		return entityManager;
	}
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}



	@Transactional
	 public EquipoEntity obtenerEquipo(String numSerie) throws DataAccessException {

		EquipoEntity resultList = (EquipoEntity) getEntityManager().createQuery(Constantes.HQL_OBTENER_EQUIPO_NUMSERIE)
								.setParameter("numSerie",numSerie)
								.getSingleResult();
		return resultList;
	}


}
