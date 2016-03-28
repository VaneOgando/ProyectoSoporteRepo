package com.inventario.spring.service;

import com.inventario.jpa.data.*;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class GestionarRecursoServicio {

	/*ATRIBUTOS*/
	protected EntityManager entityManager;


	/*METODOS*/
	@Transactional
	public List<Object> buscarEquipos(int estado) throws DataAccessException {

		List<Object> resultList = getEntityManager().createNamedQuery("HQL_EQUIPO")
				.setParameter("idEstado", estado)
				.setParameter("idMarca", 0)
				.setParameter("idModelo", 0)
				.getResultList();

		return resultList;
	}

	@Transactional
	public String buscarUsuarioAsignadoE(EquipoEntity equipo) throws DataAccessException {

		List<String> resultList = getEntityManager().createNamedQuery("HQL_HISTORIAL_USUARIO_ASIGNADO_EQUIPO")
									.setParameter("numSerie", equipo.getNumSerie())
									.getResultList();

		if(resultList.size() < 1){
			return null;
		}else{
			return resultList.get(0).toString();
		}
	}

	@Transactional
	public List<Object> buscarAccesorios(int estado) throws DataAccessException {

		List<Object> resultList = getEntityManager().createNamedQuery("HQL_ACCESORIO")
				.setParameter("idEstado", estado)
				.setParameter("idModelo", 0)
				.setParameter("idMarca", 0)
				.setParameter("idCategoria", 0)
				.getResultList();

		return resultList;
	}

	@Transactional
	public String buscarUsuarioAsignadoA(AccesorioEntity accesorio) throws DataAccessException {

		List<String> resultList = getEntityManager().createNamedQuery("HQL_HISTORIAL_USUARIO_ASIGNADO_ACCESORIO")
				.setParameter("idAccesorio", accesorio.getId())
				.getResultList();

		if(resultList.size() < 1){
			return null;
		}else{
			return resultList.get(0).toString();
		}
	}










	@Transactional
	public List<EstadoEntity> cargarEstados() throws DataAccessException {

		List<EstadoEntity> resultList = getEntityManager().createNamedQuery("HQL_ESTADO").getResultList();

		return resultList;
	}

	@Transactional
	public List<MarcaEntity> cargarMarcas() throws DataAccessException {

		List<MarcaEntity> resultList = getEntityManager().createNamedQuery("HQL_MARCA").getResultList();

		return resultList;
	}

	@Transactional
	public MarcaEntity obtenerMarcaPorNombre(String nombreMarca) throws DataAccessException {

		List<MarcaEntity> resultList = getEntityManager().createNamedQuery("HQL_MARCA_POR_NOMBRE")
				.setParameter("nombreMarca", nombreMarca)
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
										.setParameter("idMarca", marca.getId())
										.getResultList();

		return resultList;
	}

	@Transactional
	public List<CategoriaEntity> cargarCategorias(String tipoCategoria) throws DataAccessException {

		List<CategoriaEntity> resultList = getEntityManager().createNamedQuery("HQL_CATEGORIA_POR_TIPO")
											.setParameter("tipoCategoria", tipoCategoria)
											.getResultList();

		return resultList;
	}

	@Transactional
	public List<Object> filtrarEquipos(EstadoEntity estado, MarcaEntity marca, ModeloEntity modelo) throws DataAccessException {

		List<Object> resultList = getEntityManager().createNamedQuery("HQL_EQUIPO")
				.setParameter("idEstado", estado.getId())
				.setParameter("idModelo", modelo.getId())
				.setParameter("idMarca", marca.getId())
				.getResultList();

		return resultList;
	}

	@Transactional
	public List<Object> filtrarAccesorios(EstadoEntity estado, MarcaEntity marca, ModeloEntity modelo, CategoriaEntity categoria) throws DataAccessException {

		List<Object> resultList = getEntityManager().createNamedQuery("HQL_ACCESORIO")
				.setParameter("idEstado", estado.getId())
				.setParameter("idModelo", modelo.getId())
				.setParameter("idMarca", marca.getId())
				.setParameter("idCategoria", categoria.getId())
				.getResultList();

		return resultList;
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
