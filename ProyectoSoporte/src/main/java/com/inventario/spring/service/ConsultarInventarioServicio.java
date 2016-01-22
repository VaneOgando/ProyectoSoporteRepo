package com.inventario.spring.service;

import com.inventario.jpa.data.Equipo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Component
public class ConsultarInventarioServicio {
	@PersistenceContext
	private EntityManager em;

	List<Equipo> equipos = new ArrayList<Equipo>();
	String query = null;


	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}



	public List<Equipo> ConsultarEquipos(){

		Equipo equ1 = new Equipo();
		Equipo equ2 = new Equipo();


		equ1.setNombre("awewe");
		equipos.add(equ1);

		equ2.setNombre("sdfsdf");
		equipos.add(equ2);

		return equipos;

	}

}
