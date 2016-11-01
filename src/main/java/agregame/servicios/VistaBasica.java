package agregame.servicios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import agregame.contacto.ContactoAgenda;

@ManagedBean(name="vistaBasica")
@ViewScoped
public class VistaBasica {

	 private List<ContactoAgenda> contactos;
     
	    @ManagedProperty("#{servicioContactos}")
	    private ServicioContactos servicio;
	 
	    @PostConstruct
	    public void init() {
	        contactos = servicio.getContactos();        
	    }
	     
	    public List<ContactoAgenda> getContactos() {
	        return contactos;
	    }
	 
	    public void setServicio(ServicioContactos servicio) {
	        this.servicio = servicio;
	    }
	    
	    public String formatDate(String date) throws ParseException
		{	if (date!=""){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
				sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
				Date temp= sdf.parse(date);
				sdf=new SimpleDateFormat("dd-MM-YYYY hh:mm");
				String dateString=sdf.format(temp);
				return dateString ;
			}
			else return date;
		}
	    
}
