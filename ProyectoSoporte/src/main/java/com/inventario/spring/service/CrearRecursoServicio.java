package com.inventario.spring.service;

import com.inventario.jpa.data.CategoriaEntity;
import com.inventario.jpa.data.EquipoEntity;
import com.inventario.jpa.data.HistorialInventarioEntity;
import com.inventario.jpa.data.MarcaEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CrearRecursoServicio {

	/*ATRIBUTO*/
	protected EntityManager entityManager;


	/*METODOS*/
	@Transactional
	 public List<String> completarMarca(String marcaNombre) throws DataAccessException {

		List<String> resultList = getEntityManager().createNamedQuery("HQL_MARCA_COMPLETAR_NOMBRE")
									.setParameter("marcaNombre", marcaNombre + "%")
									.getResultList();

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
