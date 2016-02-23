package com.inventario.primefaces.beans;

import com.inventario.jpa.data.*;
import com.inventario.spring.service.ConsultarInventarioServicio;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;

import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class ConsultarInventarioBean {

	/*ATRIBUTOS*/
	@ManagedProperty("#{consultarInventarioServicio}")
	private ConsultarInventarioServicio consultarInventarioServicio;

	RequestContext requestContext;

	private String opcion = "0";

	private List<Object> items = new ArrayList<Object>();
	private List<Object> itemsBuscados;
	private Object itemSeleccionado;

	private List<EstadoEntity> estados;
	private EstadoEntity estado = new EstadoEntity();

	private List<MarcaEntity> marcas;
	private MarcaEntity marca = new MarcaEntity();

	private List<ModeloEntity> modelos;
	private ModeloEntity modelo = new ModeloEntity();

	private List<CategoriaEntity> categorias;
	private CategoriaEntity categoria = new CategoriaEntity();


	/*METODOS*/
	@PostConstruct
	private void init() {

		cargarRecursosOpcion();

	}

	public void cargarRecursosOpcion() {

		inicializarFiltros();
		limpiarFiltros();

		itemsBuscados = null;
		itemSeleccionado = null;

		estados = consultarInventarioServicio.cargarEstados();
		marcas = consultarInventarioServicio.cargarMarcas();

		if(opcion.equals("1")) {
			categorias = consultarInventarioServicio.cargarCategorias("accesorio");
		}
/*
		if (opcion.equals("0")) {        //Cargar equipos
			requestContext.execute("ocultarCategoria();");
		} else {                        //Cargar accesorios
			requestContext.execute("mostrarCategoria();");
			categorias = consultarInventarioServicio.cargarCategorias("accesorio");
		}
*/
		filtrarRecuros();
	}

	public void cargarModelos() {        //evento al seleccionar marca

		if (!marca.getNombre().equals("")) {
			marca.setId(Long.parseLong(consultarInventarioServicio.obtenerMarcaId(marca)));
			modelos = consultarInventarioServicio.cargarModelos(getMarca());
		}else{
			modelos = new ArrayList<ModeloEntity>();
			limpiarFiltros();
		}

	}

	public void filtrarRecuros() {

		if (opcion.equals("0")) {        //Filtrar equipos
			items = consultarInventarioServicio.filtrarEquipos(getEstado(), getMarca(), getModelo());
		} else {                            //Filtrar accesorios
			items = consultarInventarioServicio.filtrarAccesorios(getEstado(), getMarca(), getModelo(), getCategoria());
		}

	}

	public void inicializarFiltros() {

		estado	   = new EstadoEntity();
		marca 	   = new MarcaEntity();
		modelo	   = new ModeloEntity();
		categoria  = new CategoriaEntity();

		estados    = new ArrayList<EstadoEntity>();
		marcas 	   = new ArrayList<MarcaEntity>();
		modelos    = new ArrayList<ModeloEntity>();
		categorias = new ArrayList<CategoriaEntity>();
	}

	public void limpiarFiltros(){

		requestContext = RequestContext.getCurrentInstance();

		//Limpiar filtros, filtrar vacio
		requestContext.execute("PF('itemTabla').clearFilters()");

	}

	public String detalleRecurso() {

		if (itemSeleccionado != null) {

			if (opcion.equals("0")) {        //Detalle equipo
				return "detalleEquipo.xhtml?faces-redirect=true&numSerie=" + ((EquipoEntity) itemSeleccionado).getNumSerie();
			} else {                            //Detalle accesorio
				return "detalleAccesorio.xhtml?faces-redirect=true&id=" + ((AccesorioEntity) itemSeleccionado).getId();
			}
		}

		return "";
	}



	/*GET & SET*/
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

	public Object getItemSeleccionado() {
		return itemSeleccionado;
	}

	public void setItemSeleccionado(Object itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public List<EstadoEntity> getEstados() {
		return estados;
	}

	public void setEstados(List<EstadoEntity> estados) {
		this.estados = estados;
	}

	public EstadoEntity getEstado() {
		return estado;
	}

	public void setEstado(EstadoEntity estado) {
		this.estado = estado;
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

	public List<CategoriaEntity> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaEntity> categorias) {
		this.categorias = categorias;
	}

	public CategoriaEntity getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEntity categoria) {
		this.categoria = categoria;
	}

}