package com.inventario.jpa.data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="ACCESORIO")
public class AccesorioEntity {
	@Id
	@GeneratedValue
	@Column(name = "IDACCESORIO")
	private String id;
	@Column(name = "NUMSERIE")
	private String numSerie;
	@Column(name = "ACCESORIO")
	private String nombre;
	@Column(name = "CARACTERISTICAS")
	private String caracteristicas;
	@Column(name = "FECHACOMPRA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCompra;

	@ManyToOne
	@JoinColumn(name = "FKESTADO")
	private EstadoEntity estado;

	@ManyToOne
	@JoinColumn(name = "FKMODELO")
	private ModeloEntity modelo;

	@ManyToOne
	@JoinColumn(name = "FKCATEGORIA")
	private CategoriaEntity categoria;

	/*GET AND SET*/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumSerie() {
		return numSerie;
	}

	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public EstadoEntity getEstado() {
		return estado;
	}

	public void setEstado(EstadoEntity estado) {
		this.estado = estado;
	}

	public ModeloEntity getModelo() {
		return modelo;
	}

	public void setModelo(ModeloEntity modelo) {
		this.modelo = modelo;
	}

	public CategoriaEntity getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEntity categoria) {
		this.categoria = categoria;
	}
}
