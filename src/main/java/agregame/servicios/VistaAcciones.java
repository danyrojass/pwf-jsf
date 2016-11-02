package agregame.servicios;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import com.sun.jersey.api.representation.Form;

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
	
	private void newContacto(){
		contacto = new ContactoAgenda();
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
		Form form = new Form();
	    form.add("nombre", getContacto().getNombre());
	    form.add("apellido", getContacto().getApellido());
	    form.add("alias", getContacto().getAlias());
	    form.add("email", getContacto().getEmail());
	    form.add("direccion", getContacto().getDireccion());
	    
		servicio.putContacto(getContactoSeleccionado().getId(), form);
		
		return "../index.xhtml";
	}
	
	public void borrarContacto(){
		servicio.deleteContacto(getContactoSeleccionado().getId());
	}
	
	public String crearContacto(){
		newContacto();
		return "views/crear.xhtml";
	}
	
	public String nuevoContacto(){
		Form form = new Form();
	    form.add("nombre", getContacto().getNombre());
	    form.add("apellido", getContacto().getApellido());
	    form.add("alias", getContacto().getAlias());
	    form.add("telefono", getContacto().getTelefono());
	    form.add("email", getContacto().getEmail());
	    form.add("direccion", getContacto().getDireccion());
	    servicio.postContacto(form);
		
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
}
