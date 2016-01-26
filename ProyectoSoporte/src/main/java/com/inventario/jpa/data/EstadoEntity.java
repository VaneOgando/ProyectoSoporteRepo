package com.inventario.jpa.data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="ESTADO")
public class EstadoEntity {
	@Id
	@GeneratedValue
	@Column(name = "IDESTADO")
	private long id;
	@Column(name = "ESTADO")
	private String nombre;

	@OneToMany(mappedBy = "estado")
	private List<EquipoEntity> equipos;




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
