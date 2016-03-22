package com.inventario.primefaces.beans;

import com.inventario.jpa.data.*;
import com.inventario.spring.service.DetalleAccesorioServicio;
import com.inventario.spring.service.DetalleEquipoServicio;
import com.inventario.util.constante.Constantes;
import org.primefaces.context.RequestContext;

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

	private EstadoEntity estadoEliminado = new EstadoEntity();
	private HistorialInventarioEntity historialEliminado = new HistorialInventarioEntity();

	private List<HistorialInventarioEntity> historial = new ArrayList<HistorialInventarioEntity>();
	private List<HistorialInventarioEntity> itemsBuscados;

	private String usuario = null;
	private String observacion;
	private String incidencia;

	private Date fechaActual = new Date();
	private Boolean eliminado = false;


	/*METODOS*/
	public void cargarDetalleAccesorio() {

		itemsBuscados = null;

		accesorio = detalleAccesorioServicio.obtenerAccesorio(accesorio.getId());
		historial = detalleAccesorioServicio.obtenerHistorialAccesorio(accesorio.getId());

		//Accesorio posee usuario
		if (accesorio.getEstado().getNombre().equals("Asignado")){
			usuario = detalleAccesorioServicio.obtenerUsuarioAsignado(accesorio.getId());
		}

	}

	public String bt_modificarAccesorio(){

		if (accesorio.getEstado().getId() == Constantes.D_ID_ESTADO_ELIMINADO){

			FacesContext.getCurrentInstance().addMessage("mensajesError", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR! Este recurso se encuentra fuera del inventario", null));
			return "";

		}else{
			return "modificarAccesorio.xhtml";
		}

	}

	public String bt_cambiarEstado(){

		if (accesorio.getEstado().getId() == Constantes.D_ID_ESTADO_ELIMINADO){

			FacesContext.getCurrentInstance().addMessage("mensajesError", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR! Este recurso se encuentra fuera del inventario", null));
			return "";

		}else{
			return "";
		}
	}

	public void validarEstado(){

		if (accesorio.getEstado().getId() == Constantes.D_ID_ESTADO_ELIMINADO) {
			FacesContext.getCurrentInstance().addMessage("mensajesError", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR! Este recurso se encuentra fuera del inventario", null));
		}else if (accesorio.getEstado().getId() == Constantes.D_ID_ESTADO_ASIGNACION) {
			FacesContext.getCurrentInstance().addMessage("mensajesError", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR! Este recurso se encuentra asignado, por favor realizar su respectiva devoluci�n", null));

		} else {
			RequestContext.getCurrentInstance().execute("(confirm('Seguro desea eliminar?')) ? PF('dialogoEliminar').show() : false");
		}

	}

	public String bt_eliminarAccesorio(){

		try {

			estadoEliminado = detalleAccesorioServicio.obtenerEstado(Constantes.D_ID_ESTADO_ELIMINADO);
			crearHistorialEliminar();

			eliminado = detalleAccesorioServicio.eliminarAccesorio(accesorio, estadoEliminado, historialEliminado);

			if (eliminado == true) {

				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO! El recurso se elimino satisfactoriamente", null));

				return "Exito";

			} else {
				FacesContext.getCurrentInstance().addMessage("mensajesError", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR! No se pudo eliminar el recurso", null));
				RequestContext.getCurrentInstance().execute("PF('dialogoEliminar').hide()");
				RequestContext.getCurrentInstance().update("mensajesError");
				return "";

			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("mensajesError", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR! No se pudo eliminar el recurso", null));
			RequestContext.getCurrentInstance().execute("PF('dialogoEliminar').hide()");
			RequestContext.getCurrentInstance().update("mensajesError");
			return "";
		}

	}

	public void crearHistorialEliminar(){

		historialEliminado.setFechaGestion(fechaActual);
		historialEliminado.setDescripcion(observacion);
		historialEliminado.setResponsableSoporte("12345678");  //USUARIO DE LA SESSION
		historialEliminado.setCategoria(detalleAccesorioServicio.obtenerCategoriaHistorial(Constantes.D_CAT_HISTORIAL_ELIMINACION));

		if(!incidencia.equals(""))
			historialEliminado.setIdIncidencia(incidencia);

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

	public EstadoEntity getEstadoEliminado() {
		return estadoEliminado;
	}

	public void setEstadoEliminado(EstadoEntity estadoEliminado) {
		this.estadoEliminado = estadoEliminado;
	}

	public HistorialInventarioEntity getHistorialEliminado() {
		return historialEliminado;
	}

	public void setHistorialEliminado(HistorialInventarioEntity historialEliminado) {
		this.historialEliminado = historialEliminado;
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

	public Boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}
}

