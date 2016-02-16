package com.inventario.spring.service;

import com.inventario.jpa.data.AccesorioEntity;
import com.inventario.jpa.data.EquipoEntity;
import com.inventario.jpa.data.HistorialInventarioEntity;
import com.inventario.util.constante.Constantes;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class DetalleAccesorioServicio {

	protected EntityManager entityManager;


	public EntityManager getEntityManager() {
		return entityManager;
	}
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}



	@Transactional
	 public AccesorioEntity obtenerAccesorio(String idAccesorio) throws DataAccessException {

		AccesorioEntity resultList = (AccesorioEntity) getEntityManager().createQuery(Constantes.HQL_OBTENER_ACCESORIO_ID)
								.setParameter("idAccesorio", idAccesorio)
								.getSingleResult();
		return resultList;
	}

	@Transactional
	public List<HistorialInventarioEntity> obtenerHistorialAccesorio(String idAccesorio) throws DataAccessException {

		List<HistorialInventarioEntity> resultList = getEntityManager().createQuery(Constantes.HQL_OBTENER_HISTORIAL_ACCESORIO)
											.setParameter("idAccesorio", idAccesorio)
											.getResultList();
		return resultList;
	}

	@Transactional
	public String obtenerUsuarioAsignado(String idAccesorio) throws DataAccessException {

		String resultList = getEntityManager().createQuery(Constantes.HQL_OBTENER_USUARIO_ASIGNADO_ACC)
							.setParameter("idAccesorio", idAccesorio)
							.getSingleResult().toString();
		return resultList;
	}


}
