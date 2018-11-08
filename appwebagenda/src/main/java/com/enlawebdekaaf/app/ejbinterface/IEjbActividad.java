package com.enlawebdekaaf.app.ejbinterface;

import java.util.List;
import java.util.Map;

import com.enlawebdekaaf.app.entity.Tactividad;



public interface IEjbActividad {
	public Map<String,String> insert() ;
	
	public void setActividad(Tactividad actividad);
	public Tactividad getActividad();
	public void setListaActividad(List<Tactividad> listaTactividad);
	public List<Tactividad> getListaActividad();
	
	//vid 47 -p 22
	public void getByIdUsuario(int idUsuario);
	public void getByIdUsuarioAndEstado(int idUsuario,boolean estado);
	
	
}
