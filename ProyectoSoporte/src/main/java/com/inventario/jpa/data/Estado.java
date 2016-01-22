package com.inventario.jpa.data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ESTADO")
public class Estado {
	@Id
	@GeneratedValue
	@Column(name = "IDESTADO")
	private int id;
	@Column(name = "ESTADO")
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