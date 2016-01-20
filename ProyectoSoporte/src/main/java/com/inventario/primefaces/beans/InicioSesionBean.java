package com.inventario.primefaces.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import com.inventario.util.constante.Constantes;

import com.inventario.jpa.data.Empleado;
import com.inventario.spring.service.InicioSesionServicio;
import org.primefaces.context.RequestContext;

import java.io.IOException;

@ManagedBean
@SessionScoped
public class InicioSesionBean {

	@ManagedProperty("#{inicioSesionServicio}")
	private InicioSesionServicio inicioSesionServicio;
	private ExternalContext context;

	private String usuario;
	private String contrasenia;
	private boolean respuesta;

	public InicioSesionServicio getInicioSesionServicio() {
		return inicioSesionServicio;
	}

	public void setInicioSesionServicio(InicioSesionServicio inicioSesionServicio) {
		this.inicioSesionServicio = inicioSesionServicio;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public boolean getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(boolean respuesta) {
		this.respuesta = respuesta;
	}


	public String bt_ingresar_action() {
		// Llamada al servicio (Controllador)
		respuesta = inicioSesionServicio.ValidarUsuarioContrasenia(usuario, contrasenia);

		if (respuesta == true) {
			return "consultarInventario";

		}else {
			this.usuario 	 = "";
			this.contrasenia = "";

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Constantes.ERR_LOGIN_INVALIDO, Constantes.ERR_LOGIN_INVALIDO));
		}
		return "";
	}

}