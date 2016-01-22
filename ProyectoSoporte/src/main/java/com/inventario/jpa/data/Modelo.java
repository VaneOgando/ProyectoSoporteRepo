package com.inventario.jpa.data;

import javax.persistence.*;

@Entity
@Table(name="MODELO")
public class Modelo {
	@Id
	@GeneratedValue
	@Column(name = "IDMODELO")
	private int id;
	@Column(name = "MODELO")
	private String nombre;

	private Marca marca;

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

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
}