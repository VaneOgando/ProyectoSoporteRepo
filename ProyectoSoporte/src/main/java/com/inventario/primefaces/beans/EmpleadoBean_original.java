package com.inventario.primefaces.beans;

import com.inventario.jpa.data.UsuarioEntity;
import com.inventario.spring.service.EmpleadoServicio_original;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class EmpleadoBean_original {

	@ManagedProperty("#{empleadoServicio_original}")
	private EmpleadoServicio_original empleadoServicio;

	private UsuarioEntity empleado = new UsuarioEntity();

	public EmpleadoServicio_original getEmpleadoServicio() {
		return empleadoServicio;
	}

	public void setEmpleadoServicio(EmpleadoServicio_original empleadoServicio) {
		this.empleadoServicio = empleadoServicio;
	}

	public UsuarioEntity getEmpleado() {
		return empleado;
	}

	public void setEmpleado(UsuarioEntity empleado) {
		this.empleado = empleado;
	}

	public String registrar() {
		// Llamada al servicio (Controllador)
		empleadoServicio.registrar(empleado);
		// Agregar mensaje
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("El empleado "+this.empleado.getNombre()+" fue registrado satisfactoriamente"));
		return "";
	}
}