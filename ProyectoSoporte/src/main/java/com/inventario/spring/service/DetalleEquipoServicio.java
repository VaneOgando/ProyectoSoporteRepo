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

	@Transactional
	public List<HistorialInventarioEntity> obtenerHistorialEquipo(String numSerie) throws DataAccessException {

		List<HistorialInventarioEntity> resultList = getEntityManager().createQuery(Constantes.HQL_OBTENER_HISTORIAL_EQUIPO)
											.setParameter("numSerie",numSerie)
											.getResultList();
		return resultList;
	}

	@Transactional
	public String obtenerUsuarioAsignado(String numSerie) throws DataAccessException {

		String resultList = getEntityManager().createQuery(Constantes.HQL_OBTENER_USUARIO_ASIGNADO)
							.setParameter("numSerie",numSerie)
							.getSingleResult().toString();
		return resultList;
	}


}
