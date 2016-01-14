package com.inventario.spring.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.inventario.jpa.data.Empleado;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EmpleadoServicio {
	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public void regitrar(Empleado emp) {
		// Guardar empleado
		this.em.persist(emp);
	}

}
