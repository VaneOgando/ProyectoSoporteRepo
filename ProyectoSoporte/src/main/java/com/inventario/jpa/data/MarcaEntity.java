package com.inventario.jpa.data;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="MARCA")

@NamedQueries(value={

		@NamedQuery(name = "HQL_MARCA",
				query = "SELECT m FROM MarcaEntity m"),

		@NamedQuery(name = "HQL_MARCA_OBTENER_ID",
		query = "SELECT m FROM MarcaEntity m " +
				"WHERE m.nombre = :marcaNombre"),

})

public class MarcaEntity {
	@Id
	@GeneratedValue
	@Column(name = "IDMARCA")
	private long id;
	@Column(name = "MARCA")
	private String nombre;

	@OneToMany(mappedBy = "marca")
	private List<ModeloEntity> modelos;

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
