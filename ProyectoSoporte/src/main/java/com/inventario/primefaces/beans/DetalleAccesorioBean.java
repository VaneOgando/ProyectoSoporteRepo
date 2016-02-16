package com.inventario.primefaces.beans;

import com.inventario.jpa.data.AccesorioEntity;
import com.inventario.jpa.data.EquipoEntity;
import com.inventario.jpa.data.HistorialInventarioEntity;
import com.inventario.spring.service.DetalleAccesorioServicio;
import com.inventario.spring.service.DetalleEquipoServicio;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class DetalleAccesorioBean {

	@ManagedProperty("#{detalleAccesorioServicio}")
	private DetalleAccesorioServicio detalleAccesorioServicio;

	private AccesorioEntity accesorio = new AccesorioEntity();
	private List<HistorialInventarioEntity> historial = new ArrayList<HistorialInventarioEntity>();
	private List<HistorialInventarioEntity> itemsBuscados;
	private String usuario = null;


	public DetalleAccesorioServicio getDetalleAccesorioServicio() {
		return detalleAccesorioServicio;
	}

	public void setDetalleAccesorioServicio(DetalleAccesorioServicio detalleAccesorioServicio) {
		this.detalleAccesorioServicio = detalleAccesorioServicio;
	}

	public AccesorioEntity getAccesorio() {
		return accesorio;
	}

	public void setAccesorio(AccesorioEntity accesorio) {
		this.accesorio = accesorio;
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

	public void cargarDetalleAccesorio() {

		accesorio = detalleAccesorioServicio.obtenerAccesorio(accesorio.getId());
		historial = detalleAccesorioServicio.obtenerHistorialAccesorio(accesorio.getId());

		//Accesorio posee usuario
		if (accesorio.getEstado().getNombre().equals("Asignado")){
			usuario = detalleAccesorioServicio.obtenerUsuarioAsignado(accesorio.getId());
		}

	}

}

