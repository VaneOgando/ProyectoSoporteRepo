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
public class ConsultarInventarioServicio {

	protected EntityManager entityManager;


	public EntityManager getEntityManager() {
		return entityManager;
	}
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}



	@Transactional
	 public List<Object> ObtenerEquipos() throws DataAccessException {

		List<Object> resultList = getEntityManager().createQuery(Constantes.HQL_OBTENER_EQUIPOS).getResultList();

		return resultList;
	}

	@Transactional
	public List<Object> ObtenerAccesorios() throws DataAccessException {

		List<Object> resultList = getEntityManager().createQuery(Constantes.HQL_OBTENER_ACCESORIOS).getResultList();

		return resultList;
	}

	@Transactional
	public List<EstadoEntity> cargarEstados() throws DataAccessException {

		List<EstadoEntity> resultList = getEntityManager().createQuery(Constantes.HQL_OBTENER_ESTADOS).getResultList();

		return resultList;
	}

	@Transactional
	public List<MarcaEntity> cargarMarcas() throws DataAccessException {

		List<MarcaEntity> resultList = getEntityManager().createQuery(Constantes.HQL_OBTENER_MARCAS).getResultList();

		return resultList;
	}

	@Transactional
	public List<ModeloEntity> cargarModelos(MarcaEntity marca) throws DataAccessException {

		List<ModeloEntity> resultList = getEntityManager().createQuery(Constantes.HQL_OBTENER_MODELOS)
										.setParameter("marcaId", marca.getId())
										.getResultList();

		return resultList;
	}

	@Transactional
	public List<CategoriaEntity> cargarCategorias(String tipoCategoria) throws DataAccessException {

		List<CategoriaEntity> resultList = getEntityManager().createQuery(Constantes.HQL_OBTENER_CATEGORIAS)
				 							.setParameter("tipoCategoria", tipoCategoria)
											.getResultList();

		return resultList;
	}

}
