package com.inventario.primefaces.beans;

import com.inventario.spring.service.DetalleEquipoServicio;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.Date;

@ManagedBean
@ViewScoped
public class CrearRecursoBean {

	private Object recurso = new Object();
	private String opcion = "0";
	private String observacion;
	private String incidencia;

	private Date fechaActual = new Date();
	private Date date3;

	public Object getRecurso() {
		return recurso;
	}

	public void setRecurso(Object recurso) {
		this.recurso = recurso;
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(String incidencia) {
		this.incidencia = incidencia;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public Date getDate3() {
		return date3;
	}

	public void setDate3(Date date3) {
		this.date3 = date3;
	}



}

