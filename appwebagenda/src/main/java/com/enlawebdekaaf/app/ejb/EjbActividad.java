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

import com.enlawebdekaaf.app.dao.DaoActividad;
import com.enlawebdekaaf.app.daointerface.IDaoActividad;
import com.enlawebdekaaf.app.ejbinterface.IEjbActividad;
import com.enlawebdekaaf.app.entity.Tactividad;
import com.enlawebdekaaf.app.entity.Tusuario;

//vid 46 - p 21 m17
@Stateless
public class EjbActividad implements IEjbActividad {
	
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	private EntityTransaction et = null;

	private Tactividad actividad;
	private List<Tactividad> listaActividad;
    
	public EjbActividad() {
		actividad=new Tactividad();
		actividad.setTusuario(new Tusuario());
	}
	
	@Override
	public Map<String, String> insert() {
		Map<String, String> returnMap = new HashMap<String, String>();
		String mensajeGeneral = "";
		try {
			actividad.setEstado(false);//sin concluir la actividad
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			String fechaActual = sdf.format(new Date());

			actividad.setFechaRegistro(fechaActual);
			actividad.setFechaModificacion(fechaActual);

			

			ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
			Validator validator = validatorFactory.getValidator();

			// recuperamos un set de validaciones que no cumplen
			Set<ConstraintViolation<Tactividad>> constraint = validator.validate(actividad);

			for (ConstraintViolation<Tactividad> item : constraint) {
				mensajeGeneral += item.getMessage() + "<br>";
				// System.out.println(item.getMessage());
			}
			

			IDaoActividad iDaoActividad = new DaoActividad();

			emf = Persistence.createEntityManagerFactory("appwebagenda");// el nombre en persistence.xml
			em = emf.createEntityManager();
			et = em.getTransaction();

			et.begin(); // comienza la transaccion
			
			if (!mensajeGeneral.equals("")) {
				et.rollback();

				returnMap.put("correcto", "No");
				returnMap.put("mensajeGeneral", mensajeGeneral);
				return returnMap;
			}
			
			iDaoActividad.insert(em, actividad);
			
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

	@Override
	public void setActividad(Tactividad actividad) {
		this.actividad=actividad;
		
	}

	@Override
	public Tactividad getActividad() {
		
		return this.actividad;
	}

	@Override
	public void setListaActividad(List<Tactividad> listaActividad) {
		this.listaActividad=listaActividad;
		
	}

	@Override
	public List<Tactividad> getListaActividad() {
		return this.listaActividad;
	}
	
	@Override
	public void getByIdUsuario(int idUsuario) {
		//vid 47 - p 22 m 20
		
		try {
			
			IDaoActividad iDaoActividad = new DaoActividad();
			
			emf = Persistence.createEntityManagerFactory("appwebagenda");// el nombre en persistence.xml
			em = emf.createEntityManager();
			et = em.getTransaction();

			et.begin(); // comienza la transaccion
						
			listaActividad=iDaoActividad.getByIdUsuario(em, idUsuario);
			
			et.commit();

			
		} catch (Exception ex) {

			System.out.println("Error :" + ex.getMessage());
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
	@Override
	public void getByIdUsuarioAndEstado(int idUsuario,boolean estado) {
		
		//vid 47 - p 22 m 20
		
				try {
					
					IDaoActividad iDaoActividad = new DaoActividad();
					
					emf = Persistence.createEntityManagerFactory("appwebagenda");// el nombre en persistence.xml
					em = emf.createEntityManager();
					et = em.getTransaction();

					et.begin(); // comienza la transaccion
								
					listaActividad=iDaoActividad.getByIdUsuarioAndEstado(em, idUsuario, estado);
					
					et.commit();

					
				} catch (Exception ex) {

					System.out.println("Error :" + ex.getMessage());
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

	//vid 48 - p23 - 9:50
	@Override
	public void changeStatusActividad(int idActividad,boolean estado) {
try {
			
			IDaoActividad iDaoActividad = new DaoActividad();
			
			emf = Persistence.createEntityManagerFactory("appwebagenda");// el nombre en persistence.xml
			em = emf.createEntityManager();
			et = em.getTransaction();

			et.begin(); // comienza la transaccion
			
			actividad=iDaoActividad.getByIdActividad(em, idActividad);
			actividad.setEstado(estado);
			iDaoActividad.changeStatus(em, actividad);
			
			et.commit();

			
		} catch (Exception ex) {

			System.out.println("Error :" + ex.getMessage());
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
}
