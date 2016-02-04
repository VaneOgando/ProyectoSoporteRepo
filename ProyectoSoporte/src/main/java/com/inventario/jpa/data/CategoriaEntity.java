package com.inventario.jpa.data;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="CATEGORIA")
public class CategoriaEntity {
	@Id
	@GeneratedValue
	@Column(name = "IDCATEGORIA")
	private long id;
	@Column(name = "CATEGORIA")
	private String nombre;
	@Column(name = "TIPOCATEGORIA")
	private String tipoCategoria;


	@OneToMany(mappedBy = "categoria")
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

	public String getTipoCategoria() {
		return tipoCategoria;
	}

	public void setTipoCategoria(String tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}

	public List<AccesorioEntity> getAccesorios() {
		return accesorios;
	}

	public void setAccesorios(List<AccesorioEntity> accesorios) {
		this.accesorios = accesorios;
	}
}
