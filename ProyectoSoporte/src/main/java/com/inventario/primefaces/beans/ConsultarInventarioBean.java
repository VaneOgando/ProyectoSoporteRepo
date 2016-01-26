package com.inventario.primefaces.beans;

import com.inventario.jpa.data.EquipoEntity;
import com.inventario.spring.service.ConsultarInventarioServicio;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class ConsultarInventarioBean {

	@ManagedProperty("#{consultarInventarioServicio}")
	private ConsultarInventarioServicio consultarInventarioServicio;

	private List<EquipoEntity> items;
	private List<EquipoEntity> itemsBuscados;



	public ConsultarInventarioServicio getConsultarInventarioServicio() {
		return consultarInventarioServicio;
	}

	public void setConsultarInventarioServicio(ConsultarInventarioServicio consultarInventarioServicio) {
		this.consultarInventarioServicio = consultarInventarioServicio;
	}

	public List<EquipoEntity> getItems() {
		return items;
	}

	public void setItems(List<EquipoEntity> items) {
		this.items = items;
	}

	public List<EquipoEntity> getItemsBuscados() {
		return itemsBuscados;
	}

	public void setItemsBuscados(List<EquipoEntity> itemsBuscados) {
		this.itemsBuscados = itemsBuscados;
	}

	@PostConstruct
	private void init() {

		items = consultarInventarioServicio.ObtenerEquipos();

	}

}