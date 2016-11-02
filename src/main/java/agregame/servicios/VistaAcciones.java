package agregame.servicios;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import agregame.contacto.ContactoAgenda;

@ManagedBean(name="vistaAcciones")
@SessionScoped
public class VistaAcciones implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -694713719062040535L;
	public ContactoAgenda contacto;
	public ContactoAgenda contactoSeleccionado;
	
	@ManagedProperty("#{servicioContactos}")
	private ServicioContactos servicio;
	
	@PostConstruct
	public void init() {
		this.contacto = getContactoSeleccionado();       
	}
	
	public String verContacto(){
		setContacto(servicio.getContacto(getContactoSeleccionado().getId()));
		return "views/ver.xhtml";
	}

	public String volver(){
		return "../index.xhtml";
	}
	
	public ContactoAgenda getContacto() {
		return contacto;
	}

	public void setContacto(ContactoAgenda contacto) {
		this.contacto = contacto;
	}

	public ServicioContactos getServicio() {
		return servicio;
	}

	public void setServicio(ServicioContactos servicio) {
		this.servicio = servicio;
	}

	public ContactoAgenda getContactoSeleccionado() {
		return contactoSeleccionado;
	}

	public void setContactoSeleccionado(ContactoAgenda contactoSeleccionado) {
		this.contactoSeleccionado = contactoSeleccionado;
	}
}
