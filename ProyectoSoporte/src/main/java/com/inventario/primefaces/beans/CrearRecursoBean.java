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

	private EquipoEntity equipo = new EquipoEntity();
	private AccesorioEntity accesorio = new AccesorioEntity();

	private String opcion = "0";
	private String observacion;
	private String incidencia;

	private Date fechaActual = new Date();

	private List<MarcaEntity> marcas = new ArrayList<MarcaEntity>();
	private MarcaEntity marca = new MarcaEntity();

	private List<ModeloEntity> modelos = new ArrayList<ModeloEntity>();
	private ModeloEntity modelo = new ModeloEntity();

	private List<CategoriaEntity> categorias = new ArrayList<CategoriaEntity>();
	private CategoriaEntity categoria = new CategoriaEntity();



	/*METODOS*/
	@PostConstruct
	public void init(){

		inicializarListas();
		marcas = cargarMarcas();

		if(opcion.equals("1")){
			categorias = cargarCategorias();
		}
	}

	public List<CategoriaEntity> cargarCategorias() {

		List<CategoriaEntity> results = crearRecursoServicio.cargarCategorias("accesorio");

		return results;
	}

	public List<MarcaEntity> cargarMarcas() {

		List<MarcaEntity> results = crearRecursoServicio.cargarMarcas();

		return results;
	}

	public void cargarModelos() {

		if(!marca.getNombre().equals("")){

			if (crearRecursoServicio.obtenerMarcaPorNombre(marca.getNombre()) != null){

				setMarca(crearRecursoServicio.obtenerMarcaPorNombre(marca.getNombre()));
				modelos = crearRecursoServicio.cargarModelos(getMarca());

			}else{
				marca.setId(0);
				modelos = new ArrayList<ModeloEntity>();
			}

		}else{
			marca = new MarcaEntity();
			modelos = new ArrayList<ModeloEntity>();
		}

	}

	public String crearRecurso(){

		return "";
	}

	public void inicializarListas(){

		equipo = new EquipoEntity();
		accesorio = new AccesorioEntity();
		marcas  = new ArrayList<MarcaEntity>();
		modelos = new ArrayList<ModeloEntity>();
		categorias = new ArrayList<CategoriaEntity>();

		marca = new MarcaEntity();
		modelo = new ModeloEntity();
		categoria = new CategoriaEntity();
		
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

	public CategoriaEntity getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEntity categoria) {
		this.categoria = categoria;
	}

	public List<CategoriaEntity> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaEntity> categorias) {
		this.categorias = categorias;
	}
}

