package com.inventario.primefaces.beans;

import com.inventario.jpa.data.*;
import com.inventario.spring.service.DetalleEquipoServicio;
import com.inventario.spring.service.ModificarEquipoServicio;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class ModificarEquipoBean {

	/*ATRIBUTOS*/
	@ManagedProperty("#{modificarEquipoServicio}")
	private ModificarEquipoServicio modificarEquipoServicio;

	private EquipoEntity equipo = new EquipoEntity();
	private HistorialInventarioEntity historial;

	private String observacion;
	private String incidencia;

	private Boolean modificacion = false;
	private Boolean primeraVez = false;

	private Date fechaActual = new Date();

	private List<MarcaEntity> marcas = new ArrayList<MarcaEntity>();
	private MarcaEntity marca = new MarcaEntity();

	private List<ModeloEntity> modelos  = new ArrayList<ModeloEntity>();
	private ModeloEntity modelo = new ModeloEntity();


	/*METODOS*/

	public void cargarEquipo() {

		//Evento prerenderview
		if (primeraVez == false) {
			equipo = modificarEquipoServicio.obtenerEquipo(equipo.getNumSerie());

			marcas = modificarEquipoServicio.cargarMarcas();
			marca = equipo.getModelo().getMarca();

			modelos = modificarEquipoServicio.cargarModelos(equipo.getModelo().getMarca());
			modelo = equipo.getModelo();

			primeraVez = true;
		}

	}

	public void cargarModelos() {

		modelo = new ModeloEntity();

		if(!marca.getNombre().equals("")){

			//Validar existencia de marca
			if (modificarEquipoServicio.obtenerMarcaPorNombre(marca.getNombre()) != null){

				setMarca(modificarEquipoServicio.obtenerMarcaPorNombre(marca.getNombre()));
				modelos = modificarEquipoServicio.cargarModelos(getMarca());

			}else{
				marca.setId(0);
				modelos = new ArrayList<ModeloEntity>();
			}

		}else{
			modelos = new ArrayList<ModeloEntity>();
		}

	}


	public String modificarEquipo(){


		return "";
	}


	/*GET & SET*/

	public ModificarEquipoServicio getModificarEquipoServicio() {
		return modificarEquipoServicio;
	}

	public void setModificarEquipoServicio(ModificarEquipoServicio modificarEquipoServicio) {
		this.modificarEquipoServicio = modificarEquipoServicio;
	}

	public EquipoEntity getEquipo() {
		return equipo;
	}

	public void setEquipo(EquipoEntity equipo) {
		this.equipo = equipo;
	}

	public HistorialInventarioEntity getHistorial() {
		return historial;
	}

	public void setHistorial(HistorialInventarioEntity historial) {
		this.historial = historial;
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

	public Boolean getModificacion() {
		return modificacion;
	}

	public void setModificacion(Boolean modificacion) {
		this.modificacion = modificacion;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public List<MarcaEntity> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<MarcaEntity> marcas) {
		this.marcas = marcas;
	}

	public MarcaEntity getMarca() {
		return marca;
	}

	public void setMarca(MarcaEntity marca) {
		this.marca = marca;
	}

	public List<ModeloEntity> getModelos() {
		return modelos;
	}

	public void setModelos(List<ModeloEntity> modelos) {
		this.modelos = modelos;
	}

	public ModeloEntity getModelo() {
		return modelo;
	}

	public void setModelo(ModeloEntity modelo) {
		this.modelo = modelo;
	}
}

