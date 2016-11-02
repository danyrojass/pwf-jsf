package agregame.contacto;

import java.io.Serializable;
import java.util.Date;

public class ContactoAgenda implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6350905202631853050L;
	private Integer id;
	private String nombre;
	private String apellido;
	private String alias;
	private Integer telefono;
	private String email;
	private String direccion;
	private Date fechacreacion;
	private Date fechamodificacion;
	
	public ContactoAgenda() {
		this.nombre = "";
		this.apellido = "";
		this.alias = "";
		this.telefono = 0;
		this.email = "";
		this.direccion = "";
		this.fechacreacion = null;
		this.fechamodificacion = null;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Integer getTelefono() {
		return telefono;
	}
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Date getFechacreacion() {
		return fechacreacion;
	}
	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	public Date getFechamodificacion() {
		return fechamodificacion;
	}
	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}
	
	public String toPost() {
		return "{\"nombre\":\"" + nombre + "\",\"apellido\":\"" + apellido
				+ "\",\"alias\":\"" + alias + "\",\"telefono\":\"" + telefono
				+ "\",\"email\":\"" + email + "\",\"direccion\":\"" + direccion
				+ "\"}";
	}
}
