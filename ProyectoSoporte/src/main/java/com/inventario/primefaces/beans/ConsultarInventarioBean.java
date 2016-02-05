package com.inventario.primefaces.beans;

import com.inventario.jpa.data.CategoriaEntity;
import com.inventario.jpa.data.EstadoEntity;
import com.inventario.jpa.data.MarcaEntity;
import com.inventario.jpa.data.ModeloEntity;
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

	private List<EstadoEntity> estados;
	private EstadoEntity estado;

	private List<MarcaEntity> marcas;
	private MarcaEntity marca;

	private List<ModeloEntity> modelos;
	private ModeloEntity modelo;

	private List<CategoriaEntity> categorias;
	private CategoriaEntity categoria;


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

	@PostConstruct
	private void init() {

		cargarRecursosOpcion();

	}

	public void cargarRecursosOpcion() {

		requestContext = RequestContext.getCurrentInstance();
		//Limpiar input busqueda
		requestContext.execute("PF('itemTabla').clearFilters()");
		itemsBuscados = null;

		inicializarFiltros();

		estados = consultarInventarioServicio.cargarEstados();
		marcas = consultarInventarioServicio.cargarMarcas();

		if(opcion.equals("0")) {		//Cargar equipos
			requestContext.execute("ocultarCategoria();");
			items = consultarInventarioServicio.ObtenerEquipos();
		} else {						//Cargar accesorios
			requestContext.execute("mostrarCategoria();");
			categorias = consultarInventarioServicio.cargarCategorias("accesorio");
			items = consultarInventarioServicio.ObtenerAccesorios();
		}
	}

	public void cargarModelos(){		//evento al seleccionar marca

		modelos = consultarInventarioServicio.cargarModelos(getMarca());
	}

	public void inicializarFiltros(){

		estado = new EstadoEntity();
		marca = new MarcaEntity();
		modelo = new ModeloEntity();
		categoria = new CategoriaEntity();
	}

	public void bt_action_filtrar(){

		if (opcion.equals("0")){		//Filtrar equipos
			items = consultarInventarioServicio.filtrarEquipos(getEstado(), getMarca(), getModelo());
		} else {                            //Filtrar accesorios
			items = consultarInventarioServicio.filtrarAccesorio(getEstado(), getMarca(), getModelo(), getCategoria());
		}
	}
}