package com.inventario.jpa.data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="ACCESORIO")

@NamedQueries(value={

		@NamedQuery(name = "HQL_ACCESORIO",
				query = "SELECT a FROM AccesorioEntity a JOIN a.estado es JOIN a.modelo mo JOIN mo.marca ma JOIN a.categoria ca " +
						"WHERE (:estadoId is null or :estadoId = '0' or es.id = :estadoId) " +
						"AND (:modeloId is null or :modeloId = '0' or mo.id = :modeloId)" +
						"AND (:marcaId is null or :marcaId = '0' or ma.id = :marcaId)" +
						"AND (:categoriaId is null or :categoriaId = '0' or ca.id = :categoriaId)")

})

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

	@OneToMany(mappedBy = "accesorio")
	private List<HistorialInventarioEntity> historiales;


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

	public List<HistorialInventarioEntity> getHistoriales() {
		return historiales;
	}

	public void setHistoriales(List<HistorialInventarioEntity> historiales) {
		this.historiales = historiales;
	}
}
