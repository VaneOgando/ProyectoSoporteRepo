package com.inventario.spring.service;

import com.inventario.jpa.data.*;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.net.CacheRequest;
import java.util.List;

@Component
public class CrearRecursoServicio {

	/*ATRIBUTO*/
	protected EntityManager entityManager;
	protected EntityTransaction tx = null;


	/*METODOS*/
	@Transactional
	public List<CategoriaEntity> cargarCategorias(String tipoCategoria) throws DataAccessException {

		List<CategoriaEntity> resultList = getEntityManager().createNamedQuery("HQL_CATEGORIA_POR_TIPO")
				.setParameter("tipoCategoria", tipoCategoria)
				.getResultList();

		return resultList;
	}

	@Transactional
	public List<MarcaEntity> cargarMarcas() throws DataAccessException {

		List<MarcaEntity> resultList = getEntityManager().createNamedQuery("HQL_MARCA")
										.getResultList();

		return resultList;
	}

	@Transactional
	public MarcaEntity obtenerMarcaPorNombre(String marcaNombre) throws DataAccessException {

		List<MarcaEntity> resultList = getEntityManager().createNamedQuery("HQL_MARCA_OBTENER_ID")
				.setParameter("marcaNombre", marcaNombre)
				.getResultList();

		if (resultList.size() < 1 ){
			return null;
		}else{
			return resultList.get(0);
		}

	}

	@Transactional
	public List<ModeloEntity> cargarModelos(MarcaEntity marca) throws DataAccessException {

		List<ModeloEntity> resultList = getEntityManager().createNamedQuery("HQL_MODELO_POR_MARCA")
				.setParameter("marcaId", marca.getId())
				.getResultList();

		return resultList;
	}

	@Transactional
	public EstadoEntity obtenerEstado(int idEstadoDefecto) throws DataAccessException {

		List<EstadoEntity> resultList = getEntityManager().createNamedQuery("HQL_ESTADO_POR_ID")
										.setParameter("estadoId", Long.valueOf(idEstadoDefecto))
										.getResultList();

		if(resultList.size() < 1){
			return null;
		}else{
			return resultList.get(0);
		}

	}

	@Transactional
	public CategoriaEntity obtenerCategoriaHistorial(int idCategoriaDefecto) throws DataAccessException {

		List<CategoriaEntity> resultList = getEntityManager().createNamedQuery("HQL_CATEGORIA_POR_ID")
				.setParameter("categoriaId", Long.valueOf(idCategoriaDefecto))
				.getResultList();

		if(resultList.size() < 1){
			return null;
		}else{
			return resultList.get(0);
		}

	}

	@Transactional
	public ModeloEntity obtenerModeloPorNombre(String modeloNombre, Long marcaId) throws DataAccessException {

		List<ModeloEntity> resultList = getEntityManager().createNamedQuery("HQL_MODELO_OBTENER_ID")
				.setParameter("modeloNombre", modeloNombre)
				.setParameter("marcaId", marcaId)
				.getResultList();

		if (resultList.size() < 1 ){
			return null;
		}else{
			return resultList.get(0);
		}

	}

	@Transactional
	public boolean crearRecursoEquipo(MarcaEntity marca, ModeloEntity modelo, EstadoEntity estado, HistorialInventarioEntity historial, EquipoEntity equipo) throws DataAccessException {

		boolean creacion = false;

		try {

			if (marca.getId() == 0){ //Marca no existe
				entityManager.persist(marca);
			}

			modelo.setMarca(marca);

			if(modelo.getId() == 0){//modelo no existe
				entityManager.persist(modelo);
			}

			equipo.setEstado(estado);
			equipo.setModelo(modelo);

			entityManager.persist(equipo);

			historial.setEquipo(equipo);

			entityManager.persist(historial);

			creacion = true;
		}
		catch (RuntimeException e) {

			//Manejar excepciones
			creacion = false;
		}catch(Exception e){
			creacion = false;
		}
		finally {
			entityManager.close();
			return creacion;
		}

	}



	/*GET & SET*/
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
