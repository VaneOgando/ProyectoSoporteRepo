package com.inventario.primefaces.beans;

import com.inventario.jpa.data.*;
import com.inventario.spring.service.ConsultarInventarioServicio;
import com.inventario.spring.service.DetalleEquipoServicio;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class DetalleEquipoBean {

	@ManagedProperty("#{detalleEquipoServicio}")
	private DetalleEquipoServicio detalleEquipoServicio;

	private EquipoEntity equipo = new EquipoEntity();
	private List<HistorialInventarioEntity> historial = new ArrayList<HistorialInventarioEntity>();
	private List<HistorialInventarioEntity> itemsBuscados;
	private String usuario = null;

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

	public List<HistorialInventarioEntity> getHistorial() {
		return historial;
	}

	public void setHistorial(List<HistorialInventarioEntity> historial) {
		this.historial = historial;
	}

	public List<HistorialInventarioEntity> getItemsBuscados() {
		return itemsBuscados;
	}

	public void setItemsBuscados(List<HistorialInventarioEntity> itemsBuscados) {
		this.itemsBuscados = itemsBuscados;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void cargarDetalleEquipo() {

		itemsBuscados = null;
		equipo 	  = detalleEquipoServicio.obtenerEquipo(equipo.getNumSerie());
		historial = detalleEquipoServicio.obtenerHistorialEquipo(equipo.getNumSerie());

		//Equipo posee usuario
		if (equipo.getEstado().getNombre().equals("Asignado")){
			usuario = detalleEquipoServicio.obtenerUsuarioAsignado(equipo.getNumSerie());
		}

	}

}

