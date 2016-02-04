package com.inventario.primefaces.beans;

import com.inventario.spring.service.ConsultarInventarioServicio;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ToggleEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class ConsultarInventarioBean {

	@ManagedProperty("#{consultarInventarioServicio}")
	private ConsultarInventarioServicio consultarInventarioServicio;

	RequestContext requestContext;

	private String opcion = "0";

	private List<Object> items;
	private List<Object> itemsBuscados;


	public ConsultarInventarioServicio getConsultarInventarioServicio() {
		return consultarInventarioServicio;
	}

	public void setConsultarInventarioServicio(ConsultarInventarioServicio consultarInventarioServicio) {
		this.consultarInventarioServicio = consultarInventarioServicio;
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public List<Object> getItems() {
		return items;
	}

	public void setItems(List<Object> items) {
		this.items = items;
	}

	public List<Object> getItemsBuscados() {
		return itemsBuscados;
	}

	public void setItemsBuscados(List<Object> itemsBuscados) {
		this.itemsBuscados = itemsBuscados;
	}



	@PostConstruct
	private void init() {
		cargarRecursosOpcion();
	}

	public void cargarRecursosOpcion() {

		requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('itemTabla').clearFilters()");
		itemsBuscados = null;

		if(opcion.equals("0")) {
			requestContext.execute("ocultarCategoria();");
			items = consultarInventarioServicio.ObtenerEquipos();
		} else {
			requestContext.execute("mostrarCategoria();");
			items = consultarInventarioServicio.ObtenerAccesorios();
		}
	}


	public void cargarFiltros(ToggleEvent event){

		System.out.println("Visibilidad " + event.getVisibility());
		//Si se abre, cargar los combos
		//Si se cierra, no hacer nada?

	}

	public void filtrar(){

		//Busqueda de recurso depediendo de opcion y filtros
	}
}