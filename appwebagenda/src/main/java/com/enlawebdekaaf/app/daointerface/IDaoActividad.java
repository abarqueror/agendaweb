package com.enlawebdekaaf.app.daointerface;

import java.util.List;
import javax.persistence.EntityManager;
import com.enlawebdekaaf.app.entity.Tactividad;


public interface IDaoActividad {
	//vid 46 - p 21
	public boolean insert(EntityManager em,Tactividad actividad) throws Exception;
	//vid 47 - p22
	public List<Tactividad> getByIdUsuario(EntityManager em,int idUsuario) throws Exception;
	public List<Tactividad> getByIdUsuarioAndEstado(EntityManager em,int idUsuario,boolean estado) throws Exception;
	//vid 48 - p23
	public Tactividad getByIdActividad(EntityManager em, int idActividad) throws Exception;
	public boolean changeStatus(EntityManager em, Tactividad actividad) throws Exception;
}
