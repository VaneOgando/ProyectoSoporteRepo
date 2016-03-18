package com.inventario.primefaces.beans;

import com.inventario.jpa.data.*;
import com.inventario.spring.service.ConsultarInventarioServicio;
import com.inventario.spring.service.DetalleEquipoServicio;
import com.inventario.util.constante.Constantes;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class DetalleEquipoBean {

	/*ATRIBUTOS*/
	@ManagedProperty("#{detalleEquipoServicio}")
	private DetalleEquipoServicio detalleEquipoServicio;

	private EquipoEntity equipo = new EquipoEntity();

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
	public void cargarDetalleEquipo() {

		itemsBuscados = null;

		equipo 	  = detalleEquipoServicio.obtenerEquipo(equipo.getNumSerie());
		historial = detalleEquipoServicio.obtenerHistorialEquipo(equipo.getNumSerie());

		//Equipo posee usuario
		if (equipo.getEstado().getNombre().equals("Asignado")){
			usuario = detalleEquipoServicio.obtenerUsuarioAsignado(equipo.getNumSerie());
		}

	}

	public String bt_modificarEquipo(){

		return "modificarEquipo.xhtml";
	}

	public String bt_eliminarEquipo(){

		//Validacion de estado permitido
		if (!equipo.getEstado().getNombre().equals("Asignado")){

			try{

				//POP UP PARA INGRESAR OBSERVACION E INCIDENCIA

				estadoEliminado = detalleEquipoServicio.obtenerEstado(Constantes.D_ID_ESTADO_ELIMINADO);
				crearHistorialEliminar();

				eliminado = detalleEquipoServicio.eliminarEquipo(equipo, estadoEliminado, historialEliminado);

				if (eliminado == true) {

					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EXITO! El recurso se elimino satisfactoriamente", null));

					return "Exito";

				}else {
					FacesContext.getCurrentInstance().addMessage("mensajesError", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR! No se pudo eliminar el recurso", null));

					return "";

				}

			}catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage("mensajesError", new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR! No se pudo eliminar el recurso", null));

				return "";
			}

		}else{

			FacesContext.getCurrentInstance().addMessage("mensajesError", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR! Este recurso se encuentra asignado, por favor realizar su respectiva devolución", null));

			return "";
		}

	}

	public void crearHistorialEliminar(){

		observacion = "Mensaje de prueba, eliminacion de equipo";

		historialEliminado.setFechaGestion(fechaActual);
		historialEliminado.setDescripcion(observacion);
		historialEliminado.setResponsableSoporte("12345678");  //USUARIO DE LA SESSION
		historialEliminado.setCategoria(detalleEquipoServicio.obtenerCategoriaHistorial(Constantes.D_CAT_HISTORIAL_ELIMINACION));

		if(!incidencia.equals(""))
			historialEliminado.setIdIncidencia(incidencia);

	}


	/*GET & SET*/
	public DetalleEquipoServicio getDetalleEquipoServicio() {
		return detalleEquipoServicio;
	}

	public void setDetalleEquipoServicio(DetalleEquipoServicio detalleEquipoServicio) {
		this.detalleEquipoServicio = detalleEquipoServicio;
	}

	public EquipoEntity getEquipo() {
		return equipo;
	}

	public void setEquipo(EquipoEntity equipo) {
		this.equipo = equipo;
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

	public Boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
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
}

