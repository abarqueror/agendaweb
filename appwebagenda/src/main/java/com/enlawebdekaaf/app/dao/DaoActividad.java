package com.enlawebdekaaf.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;
import com.enlawebdekaaf.app.daointerface.IDaoActividad;
import com.enlawebdekaaf.app.entity.Tactividad;

//vid 46 - p21

public class DaoActividad implements IDaoActividad {

	@Override
	public boolean insert(EntityManager em, Tactividad actividad) throws Exception {
		em.persist(actividad);
		return true;
	}
	//vid 47 - p22
	@Override
	public List<Tactividad> getByIdUsuario(EntityManager em,int idUsuario) throws Exception
	{   
		TypedQuery<Tactividad> query=em.createNamedQuery("Tactividad.getByIdUsuario",Tactividad.class);
		
		query.setParameter("idUsuario", idUsuario);
		
		return query.getResultList();
	}
	
	@Override
	public List<Tactividad> getByIdUsuarioAndEstado(EntityManager em, int idUsuario, boolean estado) throws Exception {
		TypedQuery<Tactividad> query = em.createNamedQuery("Tactividad.getByIdUsuarioAndEstado", Tactividad.class);

		query.setParameter("idUsuario", idUsuario);
		query.setParameter("estado", estado);

		return query.getResultList();
	}

	//vid 48 - p23
	@Override
	public Tactividad getByIdActividad(EntityManager em, int idActividad) throws Exception
	{
		return em.find(Tactividad.class, idActividad);
	}
	@Override
	public boolean changeStatus(EntityManager em, Tactividad actividad)throws Exception {
		
		em.merge(actividad);
		
		return true;
	}
	
	

}
