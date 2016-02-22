package com.inventario.spring.service;

import com.inventario.jpa.data.*;
import com.inventario.util.constante.Constantes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Component
public class ConsultarInventarioServicio {

	/*ATRIBUTOS*/
	protected EntityManager entityManager;


	/*METODOS*/
	@Transactional
	public List<EstadoEntity> cargarEstados() throws DataAccessException {

		List<EstadoEntity> resultList = getEntityManager().createNamedQuery("HQL_ESTADO").getResultList();

		return resultList;
	}

	@Transactional
	public List<MarcaEntity> cargarMarcas() throws DataAccessException {

		List<MarcaEntity> resultList = getEntityManager().createNamedQuery("HQL_MARCA").getResultList();

		return resultList;
	}

	@Transactional
	public String obtenerMarcaId(MarcaEntity marca) throws DataAccessException {

		String resultList = getEntityManager().createNamedQuery("HQL_MARCA_OBTENER_ID")
							.setParameter("marcaNombre", marca.getNombre())
							.getSingleResult().toString();

		return resultList;
	}


	@Transactional
	public List<ModeloEntity> cargarModelos(MarcaEntity marca) throws DataAccessException {

		List<ModeloEntity> resultList = getEntityManager().createNamedQuery("HQL_MODELO_POR_MARCA")
										.setParameter("marcaId", marca.getId())
										.getResultList();

		return resultList;
	}

	@Transactional
	public List<CategoriaEntity> cargarCategorias(String tipoCategoria) throws DataAccessException {

		List<CategoriaEntity> resultList = getEntityManager().createNamedQuery("HQL_CATEGORIA_POR_TIPO")
											.setParameter("tipoCategoria", tipoCategoria)
											.getResultList();

		return resultList;
	}

	@Transactional
	public List<Object> filtrarEquipos(EstadoEntity estado, MarcaEntity marca, ModeloEntity modelo) throws DataAccessException {

		List<Object> resultList = getEntityManager().createNamedQuery("HQL_EQUIPO")
				.setParameter("estadoId", estado.getId())
				.setParameter("modeloId", modelo.getId())
				.setParameter("marcaId", marca.getId())
				.getResultList();

		return resultList;
	}

	@Transactional
	public List<Object> filtrarAccesorios(EstadoEntity estado, MarcaEntity marca, ModeloEntity modelo, CategoriaEntity categoria) throws DataAccessException {

		List<Object> resultList = getEntityManager().createNamedQuery("HQL_ACCESORIO")
				.setParameter("estadoId", estado.getId())
				.setParameter("modeloId", modelo.getId())
				.setParameter("marcaId", marca.getId())
				.setParameter("categoriaId", categoria.getId())
				.getResultList();

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
