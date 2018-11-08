package com.enlawebdekaaf.app.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tactividad database table.
 * 
 */
@Entity
//@NamedQuery(name="Tactividad.findAll", query="SELECT t FROM Tactividad t") jpql
@NamedQueries(
		{
			@NamedQuery(name="Tactividad.getByIdUsuario",query="SELECT t FROM Tactividad t WHERE t.tusuario.idUsuario=:idUsuario"),
			@NamedQuery(name="Tactividad.getByIdUsuarioAndEstado",query="SELECT t FROM Tactividad t WHERE t.tusuario.idUsuario=:idUsuario and t.estado=:estado")
		}
		
		)
public class Tactividad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idActividad;
    
	//vid 46 - p21
	//Para poder interactuar con la tabla Tusuario sin necesidar de ir hasta la tabla padre 
	//por ser idUsuario FK y hacer consultas adicionales
	//Al mapear la clase con la entidad no se define la FK mas que con un JoinColumn,
	//se intenta no tener que trabajar con el atributo Tusuario,es mas ineficiente ya que
	//
	//private int idUsuario;
	
	@Lob
	private String descripcion;

	private boolean estado;

	private String fechaHoraFin;

	private String fechaHoraInicio;

	private String fechaModificacion;

	private String fechaRegistro;

	private String lugar;

	private String nombre;

	//bi-directional many-to-one association to Tusuario
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Tusuario tusuario;

	public Tactividad() {
	}

	public int getIdActividad() {
		return this.idActividad;
	}
    
		public void setIdActividad(int idActividad) {
		this.idActividad = idActividad;
	}
		
//	public int getIdUsuario() {
//		return this.idUsuario;
//	}
//		
//	public void setIdUsuario(int idUsuario) {
//		this.idUsuario = idUsuario;
//	}	

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getFechaHoraFin() {
		return this.fechaHoraFin;
	}

	public void setFechaHoraFin(String fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}

	public String getFechaHoraInicio() {
		return this.fechaHoraInicio;
	}

	public void setFechaHoraInicio(String fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}

	public String getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getLugar() {
		return this.lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tusuario getTusuario() {
		return this.tusuario;
	}

	public void setTusuario(Tusuario tusuario) {
		this.tusuario = tusuario;
	}

}