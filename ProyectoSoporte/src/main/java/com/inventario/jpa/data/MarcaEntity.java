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
<<<<<<< HEAD
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MARCA_SEQ")
	@SequenceGenerator(name="MARCA_SEQ", sequenceName="MARCA_SEQ", allocationSize = 1)
=======
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MARCA_SEQ")
	@SequenceGenerator(name="MARCA_SEQ", sequenceName="MARCA_SEQ", allocationSize=1)
>>>>>>> origin/master
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

<<<<<<< HEAD
=======
	public List<ModeloEntity> getModelos() {
		return modelos;
	}

	public void setModelos(List<ModeloEntity> modelos) {
		this.modelos = modelos;
	}
>>>>>>> origin/master
}
