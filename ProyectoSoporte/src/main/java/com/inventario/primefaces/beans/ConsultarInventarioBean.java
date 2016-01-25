package com.inventario.primefaces.beans;

import com.inventario.jpa.data.EstadoEntity;
import com.inventario.spring.service.ConsultarInventarioServicio;
import com.inventario.spring.service.InicioSesionServicio;
import com.inventario.util.constante.Constantes;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
@SessionScoped
public class ConsultarInventarioBean {

	@ManagedProperty("#{consultarInventarioServicio}")
	private ConsultarInventarioServicio consultarInventarioServicio;

	private List<EstadoEntity> items;

	public ConsultarInventarioServicio getConsultarInventarioServicio() {
		return consultarInventarioServicio;
	}

	public void setConsultarInventarioServicio(ConsultarInventarioServicio consultarInventarioServicio) {
		this.consultarInventarioServicio = consultarInventarioServicio;
	}

	public List<EstadoEntity> getItems() {
		return items;
	}

	public void setItems(List<EstadoEntity> items) {
		this.items = items;
	}

	public void bt_action_consultar() {
		// Llamada al servicio (Controllador)

		items = consultarInventarioServicio.getAllMarcas();


	}

}