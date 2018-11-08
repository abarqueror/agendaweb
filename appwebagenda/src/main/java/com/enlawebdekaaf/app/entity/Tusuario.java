package com.enlawebdekaaf.app.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.List;


/**
 * The persistent class for the tusuario database table.
 * 
 */
@Entity
@Table(name="tusuario")
//@NamedQuery(name="Tusuario.findAll", query="SELECT t FROM Tusuario t")
@NamedQuery(name="Tusuario.getByCorreoElectronico", query="SELECT t FROM Tusuario t where t.correoElectronico=:correoElectronico")
public class Tusuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUsuario;
    
	@NotNull(message="El \"Apellido\" es requerido")
	@Size(min=1, max=40,message="El \"Apellido\" debe tener una longitud de entre 1-40")
	private String apellido;
	
	@NotNull(message="El \"Contraseña\" es requerido")
	@Size(min=4, max=700,message="El campo \"Contraseña\" debe tener una longitud de entre 4-700")
	private String contrasenia;
	
	@NotNull(message="El \"Correo electronico\" es requerido")
	@Size(min=6, max=700,message="El campo \"Correo electronico\" debe tener una longitud de entre 6-700")
	@Pattern(regexp="[a-zA-Z0-9\\.\\-\\_]+\\@[a-zA-Z0-9\\-\\_]+\\.[a-zA-Z]{2,4}", message="El campo \"Correo electrónico\" no cumple el formato adecuado. Ejm: ejemplo@gmail.com")
	private String correoElectronico;
	
	@NotNull(message="La \"Fecha de modificacion\" es requerido")
	//@Pattern(regexp="\\d{4}\\/\\d{2}\\/\\d{2}\\s\\d{2}\\:\\d{2}\\:\\d{2}" este patro fallaba con la edicion de un usuario por id ya que el formato fecha de BBDD peta
	@Pattern(regexp="(\\d{4}\\/\\d{2}\\/\\d{2}\\s\\d{2}\\:\\d{2}\\:\\d{2})|(\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}\\:\\d{2}\\:\\d{2}\\.[0-9]{1,3})", message="El formato de la fecha de registro no es el correcto. Ejm: yyyy/mm/dd hh:mm:ss")
	private String fechaModificacion;
	
	@NotNull(message="El campo \"Fecha de nacimiento\" es requerido")
	@Pattern(regexp="\\d{4}\\-\\d{2}\\-\\d{2}", message="El campo \"Fecha de nacimiento\" no cumple el formato adecuado. Ejm: dd/mm/yyyy")
	private String fechaNacimiento;
	
	@Pattern(regexp="(\\d{4}\\/\\d{2}\\/\\d{2}\\s\\d{2}\\:\\d{2}\\:\\d{2})|(\\d{4}\\-\\d{2}\\-\\d{2}\\s\\d{2}\\:\\d{2}\\:\\d{2}\\.[0-9]{1,3})", message="El formato de la fecha de registro no es el correcto. Ejm: yyyy/mm/dd hh:mm:ss")
	@NotNull(message="El campo \"Fecha de registro\" es requerido")
	private String fechaRegistro;
    
	@NotNull(message="El \"Nombre\" es requerido")
	@Size(min=1, max=70,message="El \"Nombre\" debete tener una longitud entre 1-70")
	private String nombre;

	//bi-directional many-to-one association to Tactividad
	@OneToMany(mappedBy="tusuario")
	private List<Tactividad> tactividad;

	public Tusuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Tactividad> getTactividad() {
		return this.tactividad;
	}

	public void setTactividads(List<Tactividad> tactividad) {
		this.tactividad = tactividad;
	}

	public Tactividad addTactividad(Tactividad tactividad) {
		getTactividad().add(tactividad);
		tactividad.setTusuario(this);

		return tactividad;
	}

	public Tactividad removeTactividad(Tactividad tactividad) {
		getTactividad().remove(tactividad);
		tactividad.setTusuario(null);

		return tactividad;
	}

}