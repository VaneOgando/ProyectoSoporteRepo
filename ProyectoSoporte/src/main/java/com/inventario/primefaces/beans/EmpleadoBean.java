package com.inventario.primefaces.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.inventario.jpa.data.Empleado;
import com.inventario.spring.service.EmpleadoServicio;

@ManagedBean
@SessionScoped
public class EmpleadoBean {

	@ManagedProperty("#{empleadoServicio}")
	private EmpleadoServicio empleadoServicio;

	private Empleado empleado = new Empleado();

	public EmpleadoServicio getEmpleadoServicio() {
		return empleadoServicio;
	}

	public void setEmpleadoServicio(EmpleadoServicio empleadoServicio) {
		this.empleadoServicio = empleadoServicio;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public String registrar() {
		// Llamada al servicio (Controllador)
		empleadoServicio.regitrar(empleado);
		// Agregar mensaje
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("El empleado "+this.empleado.getNombre()+" fue registrado satisfactoriamente"));
		return "";
	}
}