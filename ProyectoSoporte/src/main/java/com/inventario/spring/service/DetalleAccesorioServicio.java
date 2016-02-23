package com.inventario.spring.service;

import com.inventario.jpa.data.AccesorioEntity;
import com.inventario.jpa.data.CategoriaEntity;
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

	/*ATRIBUTO*/
	protected EntityManager entityManager;


	/*METODOS*/
	@Transactional
	 public AccesorioEntity obtenerAccesorio(String idAccesorio) throws DataAccessException {

		AccesorioEntity resultList = (AccesorioEntity) getEntityManager().createNamedQuery("HQL_ACCESORIO_POR_ID")
														.setParameter("idAccesorio", idAccesorio)
														.getSingleResult();
		return resultList;
	}

	@Transactional
	public List<HistorialInventarioEntity> obtenerHistorialAccesorio(String idAccesorio) throws DataAccessException {

		List<HistorialInventarioEntity> resultList = getEntityManager().createNamedQuery("HQL_HISTORIAL_POR_ACCESORIO")
													.setParameter("idAccesorio", idAccesorio)
													.getResultList();
		return resultList;
	}

	@Transactional
	public List<CategoriaEntity> obtenerCategoriaHistorial(String tipoCategoria) throws DataAccessException {

		List<CategoriaEntity> resultList = getEntityManager().createNamedQuery("HQL_CATEGORIA_POR_TIPO")
											.setParameter("tipoCategoria", tipoCategoria)
											.getResultList();
		return resultList;
	}

	@Transactional
	public String obtenerUsuarioAsignado(String idAccesorio) throws DataAccessException {

		String resultList = getEntityManager().createNamedQuery("HQL_HISTORIAL_USUARIO_ASIGNADO_ACCESORIO")
							.setParameter("idAccesorio", idAccesorio)
							.getSingleResult().toString();

		return resultList;
	}


	/*GET & SET*/
	public EntityManager getEntityManager() {
		return entityManager;
	}
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


}
