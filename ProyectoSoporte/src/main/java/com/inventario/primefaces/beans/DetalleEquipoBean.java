package com.inventario.primefaces.beans;

import com.inventario.jpa.data.*;
import com.inventario.spring.service.ConsultarInventarioServicio;
import com.inventario.spring.service.DetalleEquipoServicio;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class DetalleEquipoBean {

	@ManagedProperty("#{detalleEquipoServicio}")
	private DetalleEquipoServicio detalleEquipoServicio;

	private EquipoEntity equipo = new EquipoEntity();


	public DetalleEquipoServicio getDetalleEquipoServicio() {
		return detalleEquipoServicio;
	}

	public void setDetalleEquipoServicio(DetalleEquipoServicio detalleEquipoServicio) {
		this.detalleEquipoServicio = detalleEquipoServicio;
	}

	public EquipoEntity getEquipo() {
		return equipo;
	}

	public void setEquipo(EquipoEntity equipo) {
		this.equipo = equipo;
	}


	public void cargarDetalleEquipo() {

		equipo = detalleEquipoServicio.obtenerEquipo(equipo.getNumSerie());

	}

}

