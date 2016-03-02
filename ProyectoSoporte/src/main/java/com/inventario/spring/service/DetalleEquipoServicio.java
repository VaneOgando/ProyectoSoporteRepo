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

	/*ATRIBUTO*/
	protected EntityManager entityManager;


	/*METODOS*/
	@Transactional
	 public EquipoEntity obtenerEquipo(String numSerie) throws DataAccessException {

		List<EquipoEntity> resultList = getEntityManager().createNamedQuery("HQL_EQUIPO_POR_NUMSERIE")
									.setParameter("numSerie", numSerie)
									.getResultList();
		if(resultList.size() < 1){
			return null;
		}else{
			return resultList.get(0);
		}

	}

	@Transactional
	public List<HistorialInventarioEntity> obtenerHistorialEquipo(String numSerie) throws DataAccessException {

		List<HistorialInventarioEntity> resultList = getEntityManager().createNamedQuery("HQL_HISTORIAL_POR_EQUIPO")
													.setParameter("numSerie", numSerie)
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
	public String obtenerUsuarioAsignado(String numSerie) throws DataAccessException {

		List<String> resultList = getEntityManager().createNamedQuery("HQL_HISTORIAL_USUARIO_ASIGNADO_EQUIPO")
							.setParameter("numSerie", numSerie)
							.getResultList();

		if(resultList.size() < 1){
			return null;
		}else{
			return resultList.get(0).toString();
		}

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
