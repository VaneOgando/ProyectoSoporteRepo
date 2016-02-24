package com.inventario.primefaces.beans;

import com.inventario.jpa.data.AccesorioEntity;
import com.inventario.jpa.data.CategoriaEntity;
import com.inventario.jpa.data.EquipoEntity;
import com.inventario.jpa.data.HistorialInventarioEntity;
import com.inventario.spring.service.DetalleAccesorioServicio;
import com.inventario.spring.service.DetalleEquipoServicio;
import org.primefaces.context.RequestContext;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class DetalleAccesorioBean {

	/*ATRIBUTOS*/
	@ManagedProperty("#{detalleAccesorioServicio}")
	private DetalleAccesorioServicio detalleAccesorioServicio;
	RequestContext requestContext;

	private AccesorioEntity accesorio = new AccesorioEntity();

	private List<CategoriaEntity> categorias = new ArrayList<CategoriaEntity>();
	private List<HistorialInventarioEntity> historial = new ArrayList<HistorialInventarioEntity>();
	private List<HistorialInventarioEntity> itemsBuscados;

	private String usuario = null;


	/*METODOS*/
	public void cargarDetalleAccesorio() {

		itemsBuscados = null;

		accesorio = detalleAccesorioServicio.obtenerAccesorio(accesorio.getId());
		historial = detalleAccesorioServicio.obtenerHistorialAccesorio(accesorio.getId());
		categorias = detalleAccesorioServicio.obtenerCategoriaHistorial("historial");

		//Accesorio posee usuario
		if (accesorio.getEstado().getNombre().equals("Asignado")){
			usuario = detalleAccesorioServicio.obtenerUsuarioAsignado(accesorio.getId());
		}

	}

	/*GET & SET*/
	public DetalleAccesorioServicio getDetalleAccesorioServicio() {
		return detalleAccesorioServicio;
	}

	public void setDetalleAccesorioServicio(DetalleAccesorioServicio detalleAccesorioServicio) {
		this.detalleAccesorioServicio = detalleAccesorioServicio;
	}

	public RequestContext getRequestContext() {
		return requestContext;
	}

	public void setRequestContext(RequestContext requestContext) {
		this.requestContext = requestContext;
	}

	public AccesorioEntity getAccesorio() {
		return accesorio;
	}

	public void setAccesorio(AccesorioEntity accesorio) {
		this.accesorio = accesorio;
	}

	public List<CategoriaEntity> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaEntity> categorias) {
		this.categorias = categorias;
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


}

