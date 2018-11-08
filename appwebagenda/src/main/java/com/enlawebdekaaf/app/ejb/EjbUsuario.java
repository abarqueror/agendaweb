package com.enlawebdekaaf.app.ejb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.enlawebdekaaf.app.appwebagenda.Helper;
import com.enlawebdekaaf.app.dao.DaoUsuario;
import com.enlawebdekaaf.app.daointerface.IDaoUsuario;
import com.enlawebdekaaf.app.ejbinterface.IEjbUsuario;
import com.enlawebdekaaf.app.entity.Tusuario;

@Stateless
public class EjbUsuario implements IEjbUsuario {

	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	private EntityTransaction et = null;

	private Tusuario usuario;
	private List<Tusuario> listaUsuario;

	//Vide 39 - p14
	private String correoElectronicoAnterior;
	//Vide 39 - p14
	
	private String contraseniaAnterior;
	private String contraseniaNueva;
	private String contraseniaRepita;

	public EjbUsuario() {

		usuario = new Tusuario();
		System.out.println("EjbUsuario OK Constructor");
	}

	@Override
	public Map<String, String> insert() {
		Map<String, String> returnMap = new HashMap<String, String>();
		String mensajeGeneral = "";
		try {
            			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			String fechaActual = sdf.format(new Date());

			usuario.setFechaRegistro(fechaActual);
			usuario.setFechaModificacion(fechaActual);

			if (!usuario.getContrasenia().equals(contraseniaRepita)) {
				mensajeGeneral += "Las contrasenias no coinciden<br>";
				// System.out.println(mensajeGeneral);
			}
			// if(!mensajeGeneral.equals("")) {
			// return false;
			// }

			ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
			Validator validator = validatorFactory.getValidator();

			// recuperamos un set de validaciones que no cumplen
			Set<ConstraintViolation<Tusuario>> constraint = validator.validate(usuario);

			for (ConstraintViolation<Tusuario> item : constraint) {
				mensajeGeneral += item.getMessage() + "<br>";
				// System.out.println(item.getMessage());
			}
			// Reasignamos la contrasenia ya encriptada
			usuario.setContrasenia(new Helper().encrypt(usuario.getContrasenia()));

			IDaoUsuario iDaoUsuario = new DaoUsuario();

			emf = Persistence.createEntityManagerFactory("appwebagenda");// el nombre en persistence.xml
			em = emf.createEntityManager();
			et = em.getTransaction();

			et.begin(); // comienza la transaccion
			if (iDaoUsuario.getByCorreoElectronico(em, usuario.getCorreoElectronico()) != null) {
				mensajeGeneral += "Ese correo electronico ya fue registrado en el sistema<br>";
				// System.out.println(mensajeGeneral);
			}
			if (!mensajeGeneral.equals("")) {
				et.rollback();

				returnMap.put("correcto", "No");
				returnMap.put("mensajeGeneral", mensajeGeneral);
				return returnMap;
			}
			
			iDaoUsuario.insert(em, usuario);
			et.commit();

			returnMap.put("correcto", "Si");
			returnMap.put("mensajeGeneral", "Registro realizado correctamente<br> ");
			return returnMap;

		} catch (Exception ex) {

			if (et != null) {

				et.rollback();

			}
			System.out.println("Error :" + ex.getMessage());

			returnMap.put("correcto", "No");

			return returnMap;
		} finally {
			if (em != null) {

				em.close();
				em = null;

			}
			if (emf != null) {
				emf.close();
				emf = null;
			}

			et = null;
		}

	}

	
	//public void update() - hasta Vid 38 - P13
	@Override
	public Map<String,String> update()
	{
		Map<String, String> returnMap = new HashMap<String, String>();
		String mensajeGeneral="";
		Tusuario usuarioCorreoExistente;
		
		try {
			usuarioCorreoExistente= new Tusuario();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			String fechaActual = sdf.format(new Date());	
			
			usuario.setFechaModificacion(fechaActual);
			
			//Vid 37 - P12 -Inicio
			if(!contraseniaAnterior.equals(""))
			{
				//Si coincide la contrasenia almacenada en bbdd contenida en la entidad usuario
				//con contraseniaAnterior mandada desde ServletEditar(hay que encriptarla para compararlas)
				if(usuario.getContrasenia().equals(new Helper().encrypt(contraseniaAnterior)))
				{
					//Se validara por javascript que contraseniaAnterior==contraseniaNueva
			        //y que contraseniaNueva==contraseniaNuevaRepita.El resto de validaciones para
					//la nueva contraseña los validará despues las restricciones de entidad
			
					usuario.setContrasenia(new Helper().encrypt(contraseniaNueva));      
				}
				else
				{
			//Vid 38 - P13 Inicio
					mensajeGeneral+="La cotraseña anterior no es la correcta<br>";	
					
				}
			}
			
			//Vid 39 - P14 Ini
			ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
			Validator validator = validatorFactory.getValidator();

			// recuperamos un set de validaciones que no cumplen
			Set<ConstraintViolation<Tusuario>> constraint = validator.validate(usuario);

			for (ConstraintViolation<Tusuario> item : constraint) {
				mensajeGeneral += item.getMessage() + "<br>";
				// System.out.println(item.getMessage());
			}
			//Vid 39 - P14 Fin
			
			//Vid 38 - P13 Inicio
			if(!mensajeGeneral.equals(""))
			{
				
				returnMap.put("correcto", "No");
				returnMap.put("mensajeGeneral", mensajeGeneral);
				
				return returnMap;
				
			}
			//Vid 38 - P13 Fin
				
			//Vid 37 - P12 - Fin
			IDaoUsuario iDaoUsuario=new DaoUsuario(); 
			
			emf = Persistence.createEntityManagerFactory("appwebagenda");// el nombre en persistence.xml
			em = emf.createEntityManager();
			et = em.getTransaction();
			
			//Cargamos la entidad usuario con datos
			et.begin();
			
			//Vid 39 - P14 Ini 
			//No puedo hacer la comprobacion de email igual que en insert porque siempre se encntraria
			//a si mismo si NO he cambio el email.Hare una consulta
			
			usuarioCorreoExistente=iDaoUsuario.getByCorreoElectronico(em, usuario.getCorreoElectronico());
			
			//if (iDaoUsuario.getByCorreoElectronico(em, usuario.getCorreoElectronico()) != null) 
			if (usuarioCorreoExistente != null) 
			{   
				//Si el usuario que ha encontrado es diferente al correo actual de si mismo->Otro usuario ya lo tiene
				if(!usuarioCorreoExistente.getCorreoElectronico().equals(correoElectronicoAnterior))
				{
					mensajeGeneral += "Ese correo electronico ya fue registrado en el sistema<br>";
				}
				
				//No hago nada en caso de que SOLO encuentre el que ya tenia el usuario guardado en bbdd,no lo quiere cambiar
				
				// System.out.println(mensajeGeneral);
			}
			
			if (!mensajeGeneral.equals("")) {
				et.rollback();

				returnMap.put("correcto", "No");
				returnMap.put("mensajeGeneral", mensajeGeneral);
				return returnMap;
			}
			
			
			//entidad usuario cargada previamente en el servlet a traves de getByIdUsuario y 
			//modificada aqui
			iDaoUsuario.update(em, usuario);
			
			et.commit();
			
			//Ha ido bien el update y he de retornar mensaje satisfactorio
			returnMap.put("correcto", "Si");
			returnMap.put("mensajeGeneral", "Datos actualizados correctamente");
			
			return returnMap;
			
			//voy a usar javabean y ya no lo retorno al servlet de editar
			//return usuario;
			
		} 
		catch (Exception ex) {
			if (et != null) {

				et.rollback();

			}
			System.out.println("Error :" + ex.getMessage());

			returnMap.put("correcto", "No");

			return returnMap;
		} finally {
			if (em != null) {

				em.close();
				em = null;

			}
			if (emf != null) 
			{
				
				emf.close();
				emf = null;
				
			}

			et = null;

		}
	}
	
	//Vid 41 - 16
	@Override
	public Map<String,String> login(String contrasenia)
	{	
		Map<String, String> returnMap = new HashMap<String, String>();
		String mensajeGeneral="";
		
		
		try {		
			
			if(usuario==null)
			{
				mensajeGeneral+="Usuario o contraseña incorrecto N1<br>";
			}
			else
			{
				if((new Helper()).encrypt(contrasenia).equals(usuario.getContrasenia()))
				{
					returnMap.put("correcto", "Si");
					returnMap.put("mensajeGeneral", "Inicio de sesión correcto");
					return returnMap;
				}
				else
				{
					mensajeGeneral+="Usuario o contraseña incorrecto N2<br>";
				}
			}
				
			
			if(!mensajeGeneral.equals(""))
			{
				
				returnMap.put("correcto", "No");
				returnMap.put("mensajeGeneral", mensajeGeneral);
				
				return returnMap;				
			}
			return null;
		} 
		catch (Exception ex) {
			if (et != null) {

				et.rollback();

			}
			System.out.println("Error :" + ex.getMessage());

			returnMap.put("correcto", "No");

			return returnMap;
		} finally {
			if (em != null) {

				em.close();
				em = null;

			}
			if (emf != null) 
			{
				
				emf.close();
				emf = null;
				
			}

			et = null;

		}
	}
	@Override
	public void setListaUsuario(List<Tusuario> listaTusuario) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Tusuario> getListaUsuario() {
		return listaUsuario;
	}

	@Override
	public void setUsuario(Tusuario usuario) {
		this.usuario = usuario;

	}

	@Override
	public Tusuario getUsuario() {
		System.out.println("retorno user");
		return usuario;
	}

	@Override
	public String getContraseniaRepita() {
		return contraseniaRepita;
	}

	@Override
	public void setContraseniaRepita(String contraseniaRepita) {
		this.contraseniaRepita = contraseniaRepita;
	}
	
	
	
	@Override
	public String getContraseniaAnterior() {
		return contraseniaAnterior;
	}

	@Override
	public void setContraseniaAnterior(String contraseniaAnterior) {
		this.contraseniaAnterior = contraseniaAnterior;
	}


	@Override
	public String getCorreoElectronicoAnterior() {
		return correoElectronicoAnterior;
	}

	@Override
	public void setCorreoElectronicoAnterior(String correoElectronicoAnterior) {
		this.correoElectronicoAnterior = correoElectronicoAnterior;
	}

	@Override
	public String getContraseniaNueva() {
		return contraseniaNueva;
	}

	@Override
	public void setContraseniaNueva(String contraseniaNueva) {
		this.contraseniaNueva = contraseniaNueva;
	}

	//	public Tusuario getByIdUsuario() {
//usaba esta cabecera cuando no usaba java beans y retornaba usuario al servlet y de ahi a la vista
//de edicion	
	public void getByIdUsuario(int idUsuario) {

		try {
			IDaoUsuario iDaoUsuario=new DaoUsuario(); 
			
			emf = Persistence.createEntityManagerFactory("appwebagenda");// el nombre en persistence.xml
			em = emf.createEntityManager();
			et = em.getTransaction();
			
			//Cargamos la entidad usuario con datos
			et.begin();
			usuario=iDaoUsuario.getByIdUsuario(em, idUsuario);
			et.commit();
			
			//voy a usar javabean y ya no lo retorno al servlet de editar
			//return usuario;
			
		} 
		catch (Exception ex) {
			if (et != null) {

				et.rollback();

			}
			System.out.println("Error :" + ex.getMessage());

			//return null;
		} finally 
		{
			if (em != null) {

				em.close();
				em = null;

			}
			if (emf != null) 
			{
				
				emf.close();
				emf = null;
				
			}

			et = null;

		}
	}

	//Vid 41 - p 16
	@Override
	public void getByCorreoElectronico(String correoElectronico) {

		try {
			IDaoUsuario iDaoUsuario=new DaoUsuario(); 
			
			emf = Persistence.createEntityManagerFactory("appwebagenda");// el nombre en persistence.xml
			em = emf.createEntityManager();
			et = em.getTransaction();
			
			//Cargamos la entidad usuario con datos
			et.begin();
			usuario=iDaoUsuario.getByCorreoElectronico(em, correoElectronico);
			et.commit();
			
			//voy a usar javabean y ya no lo retorno al servlet de editar
			//return usuario;
			
		} 
		catch (Exception ex) {
			if (et != null) {

				et.rollback();

			}
			System.out.println("Error :" + ex.getMessage());

			//return null;
		} finally 
		{
			if (em != null) {

				em.close();
				em = null;

			}
			if (emf != null) 
			{
				
				emf.close();
				emf = null;
				
			}

			et = null;

		}
	}

}
