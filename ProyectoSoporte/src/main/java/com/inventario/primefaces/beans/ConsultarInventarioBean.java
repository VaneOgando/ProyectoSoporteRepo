package com.inventario.primefaces.beans;

import com.inventario.jpa.data.Equipo;
import com.inventario.spring.service.ConsultarInventarioServicio;
import com.inventario.spring.service.InicioSesionServicio;
import com.inventario.util.constante.Constantes;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class ConsultarInventarioBean {

	@ManagedProperty("#{consultarInventarioServicio}")
	private ConsultarInventarioServicio consultarInventarioServicio;
	private ExternalContext context;

	private List<Equipo> listEquipos;
	private boolean respuesta;

	public ConsultarInventarioServicio getConsultarInventarioServicio() {
		return consultarInventarioServicio;
	}

	public void setConsultarInventarioServicio(ConsultarInventarioServicio consultarInventarioServicio) {
		this.consultarInventarioServicio = consultarInventarioServicio;
	}

	public ExternalContext getContext() {
		return context;
	}

	public void setContext(ExternalContext context) {
		this.context = context;
	}

	public List<Equipo> getListEquipos() {
		return listEquipos;
	}

	public void setListEquipos(List<Equipo> listEquipos) {
		this.listEquipos = listEquipos;
	}

	public boolean isRespuesta() {
		return respuesta;
	}

	public boolean getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(boolean respuesta) {
		this.respuesta = respuesta;
	}

	@PostConstruct
	public void init() {
		listEquipos = consultarInventarioServicio.ConsultarEquipos();
	}

}