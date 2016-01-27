package com.inventario.jpa.data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="EQUIPO")
public class EquipoEntity {
	@Id
	@Column(name = "NUMSERIE")
	private String numSerie;
	@Column(name = "EQUIPO")
	private String nombre;
	@Column(name = "PROCESADOR")
	private String procesador;
	@Column(name = "MEMORIARAM")
	private String memoriaRam;
	@Column(name = "DISCODURO")
	private String discoDuro;
	@Column(name = "SISTOPERATIVO")
	private String sistOperativo;
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


	/*GET AND SET*/

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

	public String getProcesador() {
		return procesador;
	}

	public void setProcesador(String procesador) {
		this.procesador = procesador;
	}

	public String getMemoriaRam() {
		return memoriaRam;
	}

	public void setMemoriaRam(String memoriaRam) {
		this.memoriaRam = memoriaRam;
	}

	public String getDiscoDuro() {
		return discoDuro;
	}

	public void setDiscoDuro(String discoDuro) {
		this.discoDuro = discoDuro;
	}

	public String getSistOperativo() {
		return sistOperativo;
	}

	public void setSistOperativo(String sistOperativo) {
		this.sistOperativo = sistOperativo;
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
}