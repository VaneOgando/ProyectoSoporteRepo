package com.inventario.primefaces.beans;

import com.inventario.jpa.data.*;
import com.inventario.spring.service.DetalleAccesorioServicio;
import com.inventario.spring.service.DetalleEquipoServicio;
import com.inventario.util.comun.*;
import org.primefaces.context.RequestContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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

	private EstadoEntity estadoACambiar = new EstadoEntity();
	private HistorialInventarioEntity historialCambioEstado = new HistorialInventarioEntity();

	private List<EstadoEntity> estados = new ArrayList<EstadoEntity>();
	private List<HistorialInventarioEntity> historial = new ArrayList<HistorialInventarioEntity>();
	private List<HistorialInventarioEntity> itemsBuscados;

	private String usuario = null;
	private String observacion;
	private String incidencia;

	private Date fechaActual = new Date();

	private Boolean cambiarEstado = false;
	private Boolean asignado = false;
	private Boolean disponible = false;


	/*METODOS*/
	public void cargarDetalleAccesorio() {

		itemsBuscados = null;

		accesorio = detalleAccesorioServicio.obtenerAccesorio(accesorio.getId());
		historial = detalleAccesorioServicio.obtenerHistorialAccesorio(accesorio.getId());

		//Accesorio asignado, posee usuario
		if (accesorio.getEstado().getId() == Constantes.D_ID_ESTADO_ASIGNACION){
			asignado = true;
			usuario = detalleAccesorioServicio.obtenerUsuarioAsignado(accesorio.getId());
		}

		//Accesorio disponible para asignar
		if (accesorio.getEstado().getId() == Constantes.D_ID_ESTADO_CREACION){
			disponible = true;
		}

	}

	public String bt_modificarAccesorio(){

		if (accesorio.getEstado().getId() == Constantes.D_ID_ESTADO_ELIMINADO){

			FacesContext.getCurrentInstance().addMessage("mensajesError", new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERR_RECURSO_ELIMINADO, null));
			return "";

		}else{
			return "modificarAccesorio.xhtml";
		}

	}

	public void validarEstado(){

		if (accesorio.getEstado().getId() == Constantes.D_ID_ESTADO_ELIMINADO) {
			FacesContext.getCurrentInstance().addMessage("mensajesError", new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERR_RECURSO_ELIMINADO, null));
		}else if (accesorio.getEstado().getId() == Constantes.D_ID_ESTADO_ASIGNACION) {
			FacesContext.getCurrentInstance().addMessage("mensajesError", new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.ERR_RECURSO_ASIGNADO, null));

		} else {
			estados	= detalleAccesorioServicio.obtenerCambioEstado(accesorio.getEstado());
			RequestContext.getCurrentInstance().execute("PF('dialogoCambioEstado').show()");
		}

	}

	public void bt_cambiarEstado(){

		try {

			estadoACambiar = detalleAccesorioServicio.obtenerEstado(estadoACambiar.getId());
			crearHistorialCambioEstado();

			cambiarEstado = detalleAccesorioServicio.cambiarEstado(accesorio, estadoACambiar, historialCambioEstado);

			if (cambiarEstado == true) {
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage("mensajesError", new FacesMessage(FacesMessage.SEVERITY_INFO, Constantes.EX_CAMBIO_ESTADO, null));
				FacesContext.getCurrentInstance().getExternalContext().redirect("detalleAccesorio.xhtml?id=" + accesorio.getId());

			} else {
				FacesContext.getCurrentInstance().addMessage("mensajesError", new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERR_CAMBIO_ESTADO, null));
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("mensajesError", new FacesMessage(FacesMessage.SEVERITY_FATAL, Constantes.ERR_CAMBIO_ESTADO, null));
		}finally {
			RequestContext.getCurrentInstance().execute("PF('dialogoCambioEstado').hide()");
			RequestContext.getCurrentInstance().update("mensajesError");

		}
	}


	public void crearHistorialCambioEstado(){

		//Obtener usuario conectado
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		historialCambioEstado.setFechaGestion(fechaActual);
		historialCambioEstado.setDescripcion(observacion);
		historialCambioEstado.setResponsableSoporte( auth.getName() );

		if (estadoACambiar.getId() == Constantes.D_ID_ESTADO_ELIMINADO){
			historialCambioEstado.setCategoria(detalleAccesorioServicio.obtenerCategoriaHistorial(Constantes.D_CAT_HISTORIAL_ELIMINACION));
		}else {
			historialCambioEstado.setCategoria(detalleAccesorioServicio.obtenerCategoriaHistorial(Constantes.D_CAT_HISTORIAL_CAMBIO_ESTADO));
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

	public EstadoEntity getEstadoACambiar() {
		return estadoACambiar;
	}

	public void setEstadoACambiar(EstadoEntity estadoACambiar) {
		this.estadoACambiar = estadoACambiar;
	}

	public HistorialInventarioEntity getHistorialCambioEstado() {
		return historialCambioEstado;
	}

	public void setHistorialCambioEstado(HistorialInventarioEntity historialCambioEstado) {
		this.historialCambioEstado = historialCambioEstado;
	}

	public List<EstadoEntity> getEstados() {
		return estados;
	}

	public void setEstados(List<EstadoEntity> estados) {
		this.estados = estados;
	}

	public Boolean getCambiarEstado() {
		return cambiarEstado;
	}

	public void setCambiarEstado(Boolean cambiarEstado) {
		this.cambiarEstado = cambiarEstado;
	}

	public Boolean getAsignado() {
		return asignado;
	}

	public void setAsignado(Boolean asignado) {
		this.asignado = asignado;
	}

	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}
}

