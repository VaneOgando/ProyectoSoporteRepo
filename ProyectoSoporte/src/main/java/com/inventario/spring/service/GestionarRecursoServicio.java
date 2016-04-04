package com.inventario.spring.service;

import com.inventario.jpa.data.*;
import com.inventario.util.constante.Constantes;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.metadata.PostgresTableMetaDataProvider;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
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
	public List<Object> buscarEquiposPorUsuario(String usuario) throws DataAccessException {

		List<String> resultList = getEntityManager().createNamedQuery("SQL_EQUIPO_ASIGNADO_USUARIO")
				.setParameter(1, Constantes.D_ID_ESTADO_ASIGNACION)
				.setParameter(2, usuario)
				.setParameter(3, Constantes.D_CAT_HISTORIAL_ASIGNACION)
				.getResultList();

		List<Object> equipos = new ArrayList<Object>();

		for (String numSerie : resultList){

			equipos.add( obtenerEquipo(numSerie) );

		}

		return equipos;
	}

	@Transactional
	public String buscarUsuarioAsignadoE(EquipoEntity equipo) throws DataAccessException {

		List<Object> resultList = getEntityManager().createNamedQuery("SQL_HISTORIAL_USUARIO_ASIGNADO_EQUIPO")
				.setParameter(1, equipo.getNumSerie())
				.setParameter(2, Constantes.D_CAT_HISTORIAL_ASIGNACION)
				.getResultList();

		if(resultList.size() < 1){
			return null;
		}else{
			return resultList.get(0).toString();
		}
	}

	@Transactional
	public String buscarUsuarioAsignadoA(AccesorioEntity accesorio) throws DataAccessException {

		List<Object> resultList = getEntityManager().createNamedQuery("SQL_HISTORIAL_USUARIO_ASIGNADO_ACCESORIO")
				.setParameter(1, accesorio.getId())
				.setParameter(2, Constantes.D_CAT_HISTORIAL_ASIGNACION)
				.getResultList();

		if(resultList.size() < 1){
			return null;
		}else{
			return resultList.get(0).toString();
		}
	}

	@Transactional
	public EquipoEntity obtenerEquipo(String numSerie) throws DataAccessException {

		List<EquipoEntity> resultList = getEntityManager().createNamedQuery("HQL_EQUIPO_POR_NUMSERIE")
				.setParameter("numSerie", numSerie)
				.getResultList();
		if(resultList.size() < 1){
			return null;
		}else{
			return resultList.get(0);
		}

	}

	@Transactional
	public CategoriaEntity obtenerCategoria(int idCategoria) throws DataAccessException {

		List<CategoriaEntity> resultList = getEntityManager().createNamedQuery("HQL_CATEGORIA_POR_ID")
										.setParameter("idCategoria", idCategoria)
										.getResultList();

		if(resultList.size() < 1){
			return null;
		}else{
			return resultList.get(0);
		}

	}

	@Transactional
	public EstadoEntity obtenerEstado(int idEstado) throws DataAccessException {

		List<EstadoEntity> resultList = getEntityManager().createNamedQuery("HQL_ESTADO_POR_ID")
				.setParameter("idEstado", idEstado)
				.getResultList();

		if(resultList.size() < 1){
			return null;
		}else{
			return resultList.get(0);
		}

	}


	@Transactional
	public boolean gestionarRecurso(HistorialInventarioEntity historial,EquipoEntity equipo, List<AccesorioEntity> accesorios, EstadoEntity estado) throws DataAccessException {

		boolean gestion = false;

		try {

			if (equipo != null) {

				equipo.setEstado(estado);
				entityManager.merge(equipo);

				historial.setEquipo(equipo);
				entityManager.merge(historial);  //persist rastrea futuros cambios setEquipo(null)

				historial.setEquipo(null);
			}

			for (AccesorioEntity accesorio : accesorios){

				accesorio.setEstado(estado);
				entityManager.merge(accesorio);

				historial.setAccesorio( accesorio);
				entityManager.merge(historial);

				historial.setAccesorio(null);

			}

			gestion = true;

		}catch(Exception e){
			gestion = false;
			throw e;
		}finally {

			entityManager.close();
			return gestion;

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
