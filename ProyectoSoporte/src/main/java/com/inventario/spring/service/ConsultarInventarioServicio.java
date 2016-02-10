package com.inventario.spring.service;

import com.inventario.jpa.data.*;
import com.inventario.util.constante.Constantes;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class ConsultarInventarioServicio {

	protected EntityManager entityManager;


	public EntityManager getEntityManager() {
		return entityManager;
	}
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}



	@Transactional
	 public List<Object> obtenerEquipos() throws DataAccessException {

		List<Object> resultList = getEntityManager().createQuery(Constantes.HQL_OBTENER_EQUIPOS).getResultList();

		return resultList;
	}

	@Transactional
	public List<Object> obtenerAccesorios() throws DataAccessException {

		List<Object> resultList = getEntityManager().createQuery(Constantes.HQL_OBTENER_ACCESORIOS).getResultList();

		return resultList;
	}

	@Transactional
	public List<EstadoEntity> cargarEstados() throws DataAccessException {

		List<EstadoEntity> resultList = getEntityManager().createQuery(Constantes.HQL_OBTENER_ESTADOS).getResultList();

		return resultList;
	}

	@Transactional
	public List<MarcaEntity> cargarMarcas() throws DataAccessException {

		List<MarcaEntity> resultList = getEntityManager().createQuery(Constantes.HQL_OBTENER_MARCAS).getResultList();

		return resultList;
	}

	@Transactional
	public List<ModeloEntity> cargarModelos(MarcaEntity marca) throws DataAccessException {

		List<ModeloEntity> resultList = getEntityManager().createQuery(Constantes.HQL_OBTENER_MODELOS)
										.setParameter("marcaId", marca.getId())
										.getResultList();

		return resultList;
	}

	@Transactional
	public List<CategoriaEntity> cargarCategorias(String tipoCategoria) throws DataAccessException {

		List<CategoriaEntity> resultList = getEntityManager().createQuery(Constantes.HQL_OBTENER_CATEGORIAS)
				 							.setParameter("tipoCategoria", tipoCategoria)
											.getResultList();

		return resultList;
	}

	@Transactional
	public List<Object> filtrarEquipos(EstadoEntity estado, MarcaEntity marca, ModeloEntity modelo) throws DataAccessException {

		List<Object> resultList;

		if (estado.getId() != 0 && marca.getId() != 0){

			if (modelo.getId() != 0) {
				resultList = getEntityManager().createQuery(Constantes.HQL_FILTRO_EQ_EST_MAR_MOD)
						.setParameter("estadoId", estado.getId())
						.setParameter("marcaId", marca.getId())
						.setParameter("modeloId", modelo.getId())
						.getResultList();
			}else{
				resultList = getEntityManager().createQuery(Constantes.HQL_FILTRO_EQ_EST_MAR)
						.setParameter("estadoId", estado.getId())
						.setParameter("marcaId", marca.getId())
						.getResultList();

			}

		}else{

			if (estado.getId() == 0 && marca.getId() != 0) {

				if (modelo.getId() != 0){

					resultList = getEntityManager().createQuery(Constantes.HQL_FILTRO_EQ_MAR_MOD)
							.setParameter("marcaId", marca.getId())
							.setParameter("modeloId", modelo.getId())
							.getResultList();
				}else{
					resultList = getEntityManager().createQuery(Constantes.HQL_FILTRO_EQ_MAR)
							.setParameter("marcaId", marca.getId())
							.getResultList();
				}
			}else{

				if (estado.getId() != 0){
					resultList = getEntityManager().createQuery(Constantes.HQL_FILTRO_EQ_EST)
							.setParameter("estadoId", estado.getId())
							.getResultList();
				}else{
					resultList = obtenerEquipos();
				}
			}

		}

		return resultList;
	}

	@Transactional
	public List<Object> filtrarAccesorios(EstadoEntity estado, MarcaEntity marca, ModeloEntity modelo, CategoriaEntity categoria) throws DataAccessException {

		List<Object> resultList;

		if (estado.getId() != 0 ){

			if (marca.getId() != 0 && modelo.getId() != 0 ){

				if(categoria.getId() != 0){
					resultList = getEntityManager().createQuery(Constantes.HQL_FILTRO_ACC_EST_MAR_MOD_CAT)
							.setParameter("estadoId", estado.getId())
							.setParameter("marcaId", marca.getId())
							.setParameter("modeloId", modelo.getId())
							.setParameter("categoriaId", categoria.getId())
							.getResultList();

				}else{
					resultList = getEntityManager().createQuery(Constantes.HQL_FILTRO_ACC_EST_MAR_MOD)
							.setParameter("estadoId", estado.getId())
							.setParameter("marcaId", marca.getId())
							.setParameter("modeloId", modelo.getId())
							.getResultList();
				}


			}else{

				if(marca.getId() != 0){

					if (categoria.getId() != 0){
						resultList = getEntityManager().createQuery(Constantes.HQL_FILTRO_ACC_EST_MAR_CAT)
								.setParameter("estadoId", estado.getId())
								.setParameter("marcaId", marca.getId())
								.setParameter("categoriaId", categoria.getId())
								.getResultList();

					}else {
						resultList = getEntityManager().createQuery(Constantes.HQL_FILTRO_ACC_EST_MAR)
								.setParameter("estadoId", estado.getId())
								.setParameter("marcaId", marca.getId())
								.getResultList();

					}
				}else{

					if (categoria.getId() != 0) {
						resultList = getEntityManager().createQuery(Constantes.HQL_FILTRO_ACC_EST_CAT)
								.setParameter("estadoId", estado.getId())
								.setParameter("categoriaId", categoria.getId())
								.getResultList();
					}else{
						resultList = getEntityManager().createQuery(Constantes.HQL_FILTRO_ACC_EST)
								.setParameter("estadoId", estado.getId())
								.getResultList();

					}
				}
			}

		}else{

			if(marca.getId() != 0 && modelo.getId() != 0){

				if(categoria.getId() != 0){
					resultList = getEntityManager().createQuery(Constantes.HQL_FILTRO_ACC_MAR_MOD_CAT)
							.setParameter("marcaId", marca.getId())
							.setParameter("modeloId", modelo.getId())
							.setParameter("categoriaId", categoria.getId())
							.getResultList();

				}else{
					resultList = getEntityManager().createQuery(Constantes.HQL_FILTRO_ACC_MAR_MOD)
							.setParameter("marcaId", marca.getId())
							.setParameter("modeloId", modelo.getId())
							.getResultList();

				}

			}else{

				if(marca.getId() != 0){

					if(categoria.getId() != 0){
						resultList = getEntityManager().createQuery(Constantes.HQL_FILTRO_ACC_MAR_CAT)
								.setParameter("marcaId", marca.getId())
								.setParameter("categoriaId", categoria.getId())
								.getResultList();

					}else{
						resultList = getEntityManager().createQuery(Constantes.HQL_FILTRO_ACC_MAR)
								.setParameter("marcaId", marca.getId())
								.getResultList();

					}

				}else{

					if(categoria.getId() != 0){
						resultList = getEntityManager().createQuery(Constantes.HQL_FILTRO_ACC_CAT)
								.setParameter("categoriaId", categoria.getId())
								.getResultList();

					}else{
						resultList = getEntityManager().createQuery(Constantes.HQL_OBTENER_ACCESORIOS)
								.getResultList();

					}

				}

			}

		}

		return resultList;
	}


}
