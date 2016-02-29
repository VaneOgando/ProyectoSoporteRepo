package com.inventario.primefaces.beans;

import com.inventario.jpa.data.*;
import com.inventario.spring.service.CrearRecursoServicio;
import com.inventario.spring.service.DetalleAccesorioServicio;
import com.inventario.spring.service.DetalleEquipoServicio;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class CrearRecursoBean {

	/*ATRIBUTOS*/
	@ManagedProperty("#{crearRecursoServicio}")
	private CrearRecursoServicio crearRecursoServicio;

	private EquipoEntity equipo;
	private AccesorioEntity accesorio;

	private String opcion = "0";
	private String observacion;
	private String incidencia;

	private Date fechaActual = new Date();


	/*METODOS*/
	@PostConstruct
	public void init(){

		if(opcion.equals("0")){

			equipo = new EquipoEntity();
			equipo.setModelo(new ModeloEntity());
			equipo.getModelo().setMarca(new MarcaEntity());
			equipo.setEstado(new EstadoEntity());

		}else{

			accesorio = new AccesorioEntity();
			accesorio.setModelo(new ModeloEntity());
			accesorio.getModelo().setMarca(new MarcaEntity());
			accesorio.setEstado(new EstadoEntity());
		}

	}


	public String crearRecurso(){

		return "";
	}

	public List<String> completarMarcas(String nombreMarca) {

		List<String> results = crearRecursoServicio.completarMarca(nombreMarca);

		return results;
	}

	public List<String> completarModelos(String nombreModelo) {

		String marcaId;
		List<String> results = new ArrayList<String>();

		if(opcion.equals("0")){

			marcaId = crearRecursoServicio.obtenerMarcaId(getEquipo().getModelo().getMarca());

			results = crearRecursoServicio.completarModelo(nombreModelo, marcaId);

		}



		return results;
	}

	public List<String> completarCategorias(String nombreMarca) {

		List<String> results = crearRecursoServicio.completarMarca(nombreMarca);

		return results;
	}



	/*GET & SET*/
	public CrearRecursoServicio getCrearRecursoServicio() {
		return crearRecursoServicio;
	}

	public void setCrearRecursoServicio(CrearRecursoServicio crearRecursoServicio) {
		this.crearRecursoServicio = crearRecursoServicio;
	}

	public EquipoEntity getEquipo() {
		return equipo;
	}

	public void setEquipo(EquipoEntity equipo) {
		this.equipo = equipo;
	}

	public AccesorioEntity getAccesorio() {
		return accesorio;
	}

	public void setAccesorio(AccesorioEntity accesorio) {
		this.accesorio = accesorio;
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

}

