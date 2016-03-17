package com.inventario.primefaces.beans;

import com.inventario.jpa.data.*;
import com.inventario.spring.service.ConsultarInventarioServicio;
import com.inventario.spring.service.DetalleEquipoServicio;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class DetalleEquipoBean {

	/*ATRIBUTOS*/
	@ManagedProperty("#{detalleEquipoServicio}")
	private DetalleEquipoServicio detalleEquipoServicio;

	private EquipoEntity equipo = new EquipoEntity();

	private List<CategoriaEntity> categorias = new ArrayList<CategoriaEntity>();
	private List<HistorialInventarioEntity> historial = new ArrayList<HistorialInventarioEntity>();
	private List<HistorialInventarioEntity> itemsBuscados;

	private String usuario = null;

	/*METODOS*/
	public void cargarDetalleEquipo() {

		itemsBuscados = null;

		equipo 	  = detalleEquipoServicio.obtenerEquipo(equipo.getNumSerie());
		historial = detalleEquipoServicio.obtenerHistorialEquipo(equipo.getNumSerie());
		categorias = detalleEquipoServicio.obtenerCategoriaHistorial("historial");

		//Equipo posee usuario
		if (equipo.getEstado().getNombre().equals("Asignado")){
			usuario = detalleEquipoServicio.obtenerUsuarioAsignado(equipo.getNumSerie());
		}

	}

	public String bt_modificarEquipo(){

		return "modificarEquipo.xhtml";
	}

	public void bt_eliminarEquipo(){

		//validar que no este asignado - mensaje de q esta asignado y hay q devolverlo
		//Asignado no elimina
		if (!equipo.getEstado().getNombre().equals("Asignado")){


		}else{

			FacesContext.getCurrentInstance().addMessage("mensajesError", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR! Este recurso se encuentra asignado, por favor realizar su respectiva devolución", null));

		}

		//Eliminarlo
				//Cambiar si estado a dañado
				//Crear historial

		//bloquear todos los botones

	}

	/*
	public String modificarEquipo(){

		return "modificarEquipo.xhtml?faces-redirect=true&numSerie=" + equipo.getNumSerie();

	}
	*/

	/*GET & SET*/
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

	public List<CategoriaEntity> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaEntity> categorias) {
		this.categorias = categorias;
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

}

