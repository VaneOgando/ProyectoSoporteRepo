package com.inventario.primefaces.beans;

import com.inventario.spring.service.DetalleEquipoServicio;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CrearRecursoBean {

	private Object recurso = new Object();
	private String opcion = "0";
	private String observacion;
	private String incidencia;


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
}

