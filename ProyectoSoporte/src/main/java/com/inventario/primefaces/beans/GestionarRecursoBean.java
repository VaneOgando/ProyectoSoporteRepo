package com.inventario.primefaces.beans;

import com.inventario.jpa.data.*;
import com.inventario.spring.service.GestionarRecursoServicio;
import com.inventario.util.constante.Constantes;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class GestionarRecursoBean {

	/*ATRIBUTOS*/
	@ManagedProperty("#{gestionarRecursoServicio}")
	private GestionarRecursoServicio gestionarRecursoServicio;

	private HistorialInventarioEntity historial;
	private Date fechaActual = new Date();

	private String opcionGestion;
	private String opcionDatatable;


	private Boolean gestion = false;

	private List<AccesorioEntity> accesoriosGestion;
	private List<AccesorioEntity> accesoriosSeleccion;

	private List<Object> items = new ArrayList<Object>();
	private List<Object> itemsBuscados = null;
	private Object itemSeleccionado = null;



	/*METODOS*/
	@PostConstruct
	public void init(){

		bt_limpiarGestion();

		if (FacesContext.getCurrentInstance().isPostback()){

			opcionGestion = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("opcionGestion");

		}else {
			opcionGestion = "A";
		}



	}

	public void bt_limpiarGestion(){

		historial = new HistorialInventarioEntity();

		accesoriosGestion = new ArrayList<AccesorioEntity>();
		accesoriosSeleccion = new ArrayList<AccesorioEntity>();

	}

	public void desplegarDialogo(){

		itemsBuscados = null;
		itemSeleccionado = null;

		RequestContext.getCurrentInstance().update("datatable");
		RequestContext.getCurrentInstance().execute("PF('dialogoRecursoGestion').show()");

	}


	public void buscarUsuarios(){

		opcionDatatable = "U";
		items = new ArrayList<Object>();

		items.add(new Empleado("20493491", "Vanessa"));

		desplegarDialogo();
	}


	public void buscarEquipos(){
		opcionDatatable = "E";

		//Equipos disponibles
		if (opcionGestion.equals("A")){
			items = gestionarRecursoServicio.buscarEquipos(Constantes.D_ID_ESTADO_CREACION);
		}else{ //Equipos asignados

			//No hay usuario especifico
			if (historial.getUsuarioAsignado() == null){
				items = gestionarRecursoServicio.buscarEquipos(Constantes.D_ID_ESTADO_ASIGNACION);
			}else{

			}

		}

		desplegarDialogo();
	}

	public void agregarAccesorios(){
		opcionDatatable = "A";

		//Accesorios disponibles
		if (opcionGestion.equals("A")){
			items = gestionarRecursoServicio.buscarAccesorios(Constantes.D_ID_ESTADO_CREACION);
		}else { //Accesorios asignados

			//No hay usuario especifico
			if (historial.getUsuarioAsignado() == null) {
				items = gestionarRecursoServicio.buscarAccesorios(Constantes.D_ID_ESTADO_ASIGNACION);
			} else {

			}
		}

		desplegarDialogo();
	}

	public void eliminarAccesorios(){

		accesoriosGestion.remove(accesoriosSeleccion.get(0));
	}

	public void bt_seleccionarRecurso(){

		if (itemSeleccionado != null) {

			if (opcionDatatable.equals("U")){

				if (opcionGestion.equals("D")){

					historial.setEquipo(null);
					accesoriosGestion = new ArrayList<AccesorioEntity>();
					accesoriosSeleccion = new ArrayList<AccesorioEntity>();
				}

				historial.setUsuarioAsignado( ((Empleado) itemSeleccionado).getId());

			}else if (opcionDatatable.equals("E")){
				historial.setEquipo( (EquipoEntity) itemSeleccionado);

				if (opcionGestion.equals("D")){
					historial.setUsuarioAsignado(gestionarRecursoServicio.buscarUsuarioAsignadoE(historial.getEquipo()));
				}

			}else if(opcionDatatable.equals("A")){

				if (opcionGestion.equals("D")){
					historial.setUsuarioAsignado(gestionarRecursoServicio.buscarUsuarioAsignadoA((AccesorioEntity) itemSeleccionado));
				}

				accesoriosGestion.add( (AccesorioEntity) itemSeleccionado );

			}

			RequestContext.getCurrentInstance().update("gestionarRecurso:formularioGestion");
			RequestContext.getCurrentInstance().execute("PF('dialogoRecursoGestion').hide()");

		}else{
			FacesContext.getCurrentInstance().addMessage("mensajesDialogo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR! Seleccione un item", null));
			RequestContext.getCurrentInstance().update("datatable:mensajesDialogo");

		}

	}


	/*GET & SET*/

	public GestionarRecursoServicio getGestionarRecursoServicio() {
		return gestionarRecursoServicio;
	}

	public void setGestionarRecursoServicio(GestionarRecursoServicio gestionarRecursoServicio) {
		this.gestionarRecursoServicio = gestionarRecursoServicio;
	}

	public HistorialInventarioEntity getHistorial() {
		return historial;
	}

	public void setHistorial(HistorialInventarioEntity historial) {
		this.historial = historial;
	}

	public String getOpcionGestion() {
		return opcionGestion;
	}

	public void setOpcionGestion(String opcionGestion) {
		this.opcionGestion = opcionGestion;
	}

	public String getOpcionDatatable() {
		return opcionDatatable;
	}

	public void setOpcionDatatable(String opcionDatatable) {
		this.opcionDatatable = opcionDatatable;
	}

	public Boolean getGestion() {
		return gestion;
	}

	public void setGestion(Boolean gestion) {
		this.gestion = gestion;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public List<AccesorioEntity> getAccesoriosSeleccion() {
		return accesoriosSeleccion;
	}

	public void setAccesoriosSeleccion(List<AccesorioEntity> accesoriosSeleccion) {
		this.accesoriosSeleccion = accesoriosSeleccion;
	}

	public List<AccesorioEntity> getAccesoriosGestion() {
		return accesoriosGestion;
	}

	public void setAccesoriosGestion(List<AccesorioEntity> accesoriosGestion) {
		this.accesoriosGestion = accesoriosGestion;
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


}

