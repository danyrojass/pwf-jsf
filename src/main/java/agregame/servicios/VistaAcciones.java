package agregame.servicios;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

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
	private List<ContactoAgenda> contactos;
	String search;

	@ManagedProperty("#{servicioContactos}")
	private ServicioContactos servicio;
	
	@PostConstruct
	public void init() {
		this.search = "";
		this.setContactos(servicio.getContactos(null));        
	}
	
	private void newContacto(){
		contacto = new ContactoAgenda();
	}
	
	public void filtrarContactos(){
		contactos = servicio.getContactos(getSearch()); 
	}
	
	public String verContacto(){
		newContacto();
		setContacto(servicio.getContacto(getContactoSeleccionado().getId()));
		return "views/ver.xhtml";
	}
	
	public String editarContacto(){
		newContacto();
		setContacto(servicio.getContacto(getContactoSeleccionado().getId()));
		return "views/editar.xhtml";
	}
	

	public String modificarContacto(){
	    
		servicio.putContacto(getContacto());
		init();
		return "../index.xhtml";
	}
	
	public void borrarContacto(){
		servicio.deleteContacto(getContactoSeleccionado().getId());
		setContactos(servicio.getContactos(null));
	}
	
	public String crearContacto(){
		newContacto();
		return "views/crear.xhtml";
	}
	
	public String nuevoContacto(){
	    servicio.postContacto(getContacto());
		init();
		return "../index.xhtml";
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

	public List<ContactoAgenda> getContactos() {
		return contactos;
	}

	public void setContactos(List<ContactoAgenda> contactos) {
		this.contactos = contactos;
	}
	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
}
