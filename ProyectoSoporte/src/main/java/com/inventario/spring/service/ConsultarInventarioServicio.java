package com.inventario.spring.service;

import com.inventario.jpa.data.EstadoEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Iterator;
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
	public List<EstadoEntity> getAllMarcas() throws DataAccessException {

		Query query = getEntityManager().createQuery("select c from EstadoEntity c");

		List<EstadoEntity> resultList = query.getResultList();

		return resultList;

	}



}
