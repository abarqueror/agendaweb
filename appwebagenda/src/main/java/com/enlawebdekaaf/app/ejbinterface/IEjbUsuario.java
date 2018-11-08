package com.enlawebdekaaf.app.ejbinterface;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;


import com.enlawebdekaaf.app.entity.Tusuario;

@Local
public interface IEjbUsuario {

	public Map<String,String> insert() ;
	//public Tusuario getByIdUsuario();
	public void getByIdUsuario(int idUsuario);
	
	//public void update(); hasta Vid 38 - P13
	public Map<String,String> update();
	
	//Vid 41 - P16
	public Map<String,String> login(String contrasenia);
	public void getByCorreoElectronico(String correoElectronico);
	
	public void setListaUsuario(List<Tusuario> listaTusuario);
	public List<Tusuario> getListaUsuario();
	public void setUsuario(Tusuario usuario);
	public Tusuario getUsuario();
	
	public void setCorreoElectronicoAnterior(String correoElectronicoAnterior);
	public String getCorreoElectronicoAnterior();
	
	public String getContraseniaRepita();
	public void setContraseniaRepita(String contraseniaRepita);
	
	public void setContraseniaAnterior(String contraseniaAnterior);
	public String getContraseniaAnterior();
	
	public void setContraseniaNueva(String contraseniaNueva);
	public String getContraseniaNueva();
	
	
	
	
}
