package com.inventario.jpa.data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="ESTADO")

@NamedQueries(value={

		@NamedQuery(name = "HQL_ESTADO",
		query = "SELECT e FROM EstadoEntity e"),

		@NamedQuery(name = "HQL_ESTADO_POR_ID",
				query = "SELECT e FROM EstadoEntity e " +
						"WHERE e.id = :idEstado")

})

public class EstadoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESTADO_SEQ")
	@SequenceGenerator(name="ESTADO_SEQ", sequenceName="ESTADO_SEQ", allocationSize = 1)
	@Column(name = "IDESTADO")
	private int id;
	@Column(name = "ESTADO")
	private String nombre;

	@OneToMany(mappedBy = "estado")
	private List<EquipoEntity> equipos;

	@OneToMany(mappedBy = "estado")
	private List<AccesorioEntity> accesorios;


	/*GET AND SET*/

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
