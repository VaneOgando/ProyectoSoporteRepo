package com.inventario.jpa.data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="ESTADO")

@NamedQueries(value={

		@NamedQuery(name = "HQL_ESTADO",
		query = "SELECT e FROM EstadoEntity e")

})

public class EstadoEntity {
	@Id
	@GeneratedValue
	@Column(name = "IDESTADO")
	private long id;
	@Column(name = "ESTADO")
	private String nombre;

	@OneToMany(mappedBy = "estado")
	private List<EquipoEntity> equipos;

	@OneToMany(mappedBy = "estado")
	private List<AccesorioEntity> accesorios;


	/*GET AND SET*/

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

	public List<EquipoEntity> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<EquipoEntity> equipos) {
		this.equipos = equipos;
	}

	public List<AccesorioEntity> getAccesorios() {
		return accesorios;
	}

	public void setAccesorios(List<AccesorioEntity> accesorios) {
		this.accesorios = accesorios;
	}
}
