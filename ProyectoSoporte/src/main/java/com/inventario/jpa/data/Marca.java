package com.inventario.jpa.data;

import javax.persistence.*;

@Entity
@Table(name="MARCA")
public class Marca {
	@Id
	@GeneratedValue
	@Column(name = "IDMARCA")
	private int id;
	@Column(name = "MARCA")
	private String nombre;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}