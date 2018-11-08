package com.enlawebdekaaf.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.enlawebdekaaf.app.daointerface.IDaoUsuario;
import com.enlawebdekaaf.app.entity.Tusuario;

public class DaoUsuario implements IDaoUsuario {

	@Override
	public boolean insert(EntityManager em, Tusuario usuario) throws Exception {
		em.persist(usuario);
		return true;
	}

	@Override
	public Tusuario getByIdUsuario(EntityManager em, int idUsuario) throws Exception {

		return em.find(Tusuario.class, idUsuario);
	}

	@Override
	public Tusuario getByCorreoElectronico(EntityManager em, String correoElectronico) throws Exception {

		TypedQuery<Tusuario> query = em.createNamedQuery("Tusuario.getByCorreoElectronico", Tusuario.class);
		query.setParameter("correoElectronico", correoElectronico);

		// Valdria getResultList(); y luego coger el primero p.e pero con
		// setMaxResults(1) devuelve 1
		List<Tusuario> listaUsuario = query.setMaxResults(1).getResultList();
		if (listaUsuario.size() > 0)// Encuentra a 1 usuario
		{
			return listaUsuario.get(0);// retorna primer registro
		}

		return null;
	}

	@Override
	public boolean update(EntityManager em, Tusuario usuario) throws Exception {
		em.merge(usuario); 
		
		return true;
		
	}

}
