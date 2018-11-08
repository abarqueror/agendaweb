package com.enlawebdekaaf.app.daointerface;

import javax.persistence.EntityManager;

import com.enlawebdekaaf.app.entity.Tusuario;

public interface IDaoUsuario {
	
	public boolean insert(EntityManager em, Tusuario usuario) throws Exception;
	public Tusuario getByIdUsuario(EntityManager em, int idUsuario) throws Exception;
	public Tusuario getByCorreoElectronico(EntityManager em, String correoElectronico) throws Exception;
	public boolean update(EntityManager em, Tusuario usuario) throws Exception;
	

}
