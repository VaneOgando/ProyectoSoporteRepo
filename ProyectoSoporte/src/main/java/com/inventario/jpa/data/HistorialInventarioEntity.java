package com.inventario.jpa.data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="HISTORIALINVENTARIO")
public class HistorialInventarioEntity {
	@Id
	@GeneratedValue
	@Column(name = "IDHISTORIALINV")
	private String id;
	@Column(name = "FECHAGESTION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaGestion;
	@Column(name = "DESCRIPCION")
	private String descripcion;
	@Column(name = "IDINCIDENCIA")
	private String idIncidencia;
	@Column(name = "RESPSOPORTE")
	private String responsableSoporte;
	@Column(name = "USUARIOASIG")
	private String usuarioAsignado;

	@ManyToOne
	@JoinColumn(name = "FKCATEGORIA")
	private CategoriaEntity categoria;

	@ManyToOne
	@JoinColumn(name = "FKEQUIPO")
	private EquipoEntity equipo;

	@ManyToOne
	@JoinColumn(name = "FKACCESORIO")
	private AccesorioEntity accesorio;


	/*GET AND SET*/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFechaGestion() {
		return fechaGestion;
	}

	public void setFechaGestion(Date fechaGestion) {
		this.fechaGestion = fechaGestion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIdIncidencia() {
		return idIncidencia;
	}

	public void setIdIncidencia(String idIncidencia) {
		this.idIncidencia = idIncidencia;
	}

	public String getResponsableSoporte() {
		return responsableSoporte;
	}

	public void setResponsableSoporte(String responsableSoporte) {
		this.responsableSoporte = responsableSoporte;
	}

	public String getUsuarioAsignado() {
		return usuarioAsignado;
	}

	public void setUsuarioAsignado(String usuarioAsignado) {
		this.usuarioAsignado = usuarioAsignado;
	}

	public CategoriaEntity getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEntity categoria) {
		this.categoria = categoria;
	}

	public EquipoEntity getEquipo() {
		return equipo;
	}

	public void setEquipo(EquipoEntity equipo) {
		this.equipo = equipo;
	}

	public AccesorioEntity getAccesorio() {
		return accesorio;
	}

	public void setAccesorio(AccesorioEntity accesorio) {
		this.accesorio = accesorio;
	}
}
