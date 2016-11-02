package agregame.servicios;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import agregame.contacto.ContactoAgenda;

@ManagedBean(name="vistaBasica")
@ViewScoped
public class VistaBasica implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String search;

	private List<ContactoAgenda> contactos;
     
	@ManagedProperty("#{servicioContactos}")
	private ServicioContactos servicio;
	
	@PostConstruct
	public void init() {
		this.search = "";
		this.contactos = servicio.getContactos(null);        
	}
	
	public void filtrarContactos(){
		System.out.println(this.search + this.search);
		//contactos = servicio.getContactos(getFiltro(); 
	}
	     
	public List<ContactoAgenda> getContactos() {
		return contactos;
	}
	 
	public void setServicio(ServicioContactos servicio) {
		this.servicio = servicio;
	}

	public ServicioContactos getServicio() {
		return servicio;
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
