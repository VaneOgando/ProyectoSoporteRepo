package com.inventario.jpa.data;

import javax.persistence.*;


@Entity
@Table(name="estado")
public class EstadoEntity {
	@Id
	@GeneratedValue
	@Column(name = "IDESTADO")
	private long id;
	@Column(name = "ESTADO")
	private String nombre;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
