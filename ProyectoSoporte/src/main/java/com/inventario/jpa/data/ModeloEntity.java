package com.inventario.jpa.data;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="modelo")
public class ModeloEntity {
	@Id
	@GeneratedValue
	@Column(name = "IDMODELO")
	private long id;
	@Column(name = "MODELO")
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "FKMARCA")
	private MarcaEntity marca;

	@OneToMany(mappedBy = "modelo")
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

	public MarcaEntity getMarca() {
		return marca;
	}

	public void setMarca(MarcaEntity marca) {
		this.marca = marca;
	}
}
