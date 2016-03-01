package com.inventario.spring.service;

import com.inventario.jpa.data.*;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.net.CacheRequest;
import java.util.List;

@Component
public class CrearRecursoServicio {

	/*ATRIBUTO*/
	protected EntityManager entityManager;


	/*METODOS*/
	@Transactional
	public List<CategoriaEntity> cargarCategorias(String tipoCategoria) throws DataAccessException {

		List<CategoriaEntity> resultList = getEntityManager().createNamedQuery("HQL_CATEGORIA_POR_TIPO")
				.setParameter("tipoCategoria", tipoCategoria)
				.getResultList();

		return resultList;
	}

	@Transactional
	public List<MarcaEntity> cargarMarcas() throws DataAccessException {

		List<MarcaEntity> resultList = getEntityManager().createNamedQuery("HQL_MARCA")
										.getResultList();

		return resultList;
	}

	@Transactional
	public MarcaEntity obtenerMarcaPorNombre(String marcaNombre) throws DataAccessException {

		List<MarcaEntity> resultList = getEntityManager().createNamedQuery("HQL_MARCA_OBTENER_ID")
				.setParameter("marcaNombre", marcaNombre)
				.getResultList();

		if (resultList.size() < 1 ){
			return null;
		}else{
			return resultList.get(0);
		}

	}


	@Transactional
	public List<ModeloEntity> cargarModelos(MarcaEntity marca) throws DataAccessException {

		List<ModeloEntity> resultList = getEntityManager().createNamedQuery("HQL_MODELO_POR_MARCA")
				.setParameter("marcaId", marca.getId())
				.getResultList();

		return resultList;
	}


	@Transactional
	 public List<String> completarMarca(String marcaNombre) throws DataAccessException {

		List<String> resultList = getEntityManager().createNamedQuery("HQL_MARCA_COMPLETAR_NOMBRE")
									.setParameter("marcaNombre", marcaNombre + "%")
									.getResultList();

		return resultList;
	}

	@Transactional
	public String obtenerMarcaId(String marca) throws DataAccessException {

		String resultList = getEntityManager().createNamedQuery("HQL_MARCA_OBTENER_ID")
				.setParameter("marcaNombre", marca)
				.getSingleResult().toString();

		return resultList;
	}

	@Transactional
	public List<String> completarModelo(String modeloNombre, String marcaId) throws DataAccessException {

		List<String> resultList = getEntityManager().createNamedQuery("HQL_MODELO_COMPLETAR_NOMBRE")
								  .setParameter("modeloNombre", modeloNombre + "%")
								  .setParameter("marcaId", marcaId)
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
