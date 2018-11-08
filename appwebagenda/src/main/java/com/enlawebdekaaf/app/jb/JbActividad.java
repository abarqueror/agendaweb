package com.enlawebdekaaf.app.jb;

import java.io.Serializable;



public class JbActividad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int idActividad;
    private int idUsuario;
	private String nombre;
	private String descripcion;
	private boolean estado;
	private String fechaHoraFin;
	private String fechaHoraInicio;
	private String fechaModificacion;
	private String fechaRegistro;
	private String lugar;
	/**
	 * @return the idActividad
	 */
	public int getIdActividad() {
		return idActividad;
	}
	/**
	 * @param idActividad the idActividad to set
	 */
	public void setIdActividad(int idActividad) {
		this.idActividad = idActividad;
	}
	/**
	 * @return the idUsuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the estado
	 */
	public boolean isEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	/**
	 * @return the fechaHoraFin
	 */
	public String getFechaHoraFin() {
		return fechaHoraFin;
	}
	/**
	 * @param fechaHoraFin the fechaHoraFin to set
	 */
	public void setFechaHoraFin(String fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}
	/**
	 * @return the fechaHoraInicio
	 */
	public String getFechaHoraInicio() {
		return fechaHoraInicio;
	}
	/**
	 * @param fechaHoraInicio the fechaHoraInicio to set
	 */
	public void setFechaHoraInicio(String fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}
	/**
	 * @return the fechaModificacion
	 */
	public String getFechaModificacion() {
		return fechaModificacion;
	}
	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	/**
	 * @return the fechaRegistro
	 */
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	/**
	 * @return the lugar
	 */
	public String getLugar() {
		return lugar;
	}
	/**
	 * @param lugar the lugar to set
	 */
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	

}
