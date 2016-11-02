package agregame.servicios;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import agregame.contacto.ContactoAgenda;
import agregame.deserializador.DateDeserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


@ManagedBean(name = "servicioContactos")
@ApplicationScoped
public class ServicioContactos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6784412074499110064L;
	private static final String URLBASE = "https://desa03.konecta.com.py/pwf";
	private static final String REST = "rest";
	private static final String AGENDA = "agenda";
	private static final String AGENDAID = "agenda/";
	
	public ArrayList<ContactoAgenda> contactos; 
	
	transient ClientConfig config = new DefaultClientConfig();
	transient Client client = Client.create(config);
	transient WebResource service = client.resource(getBaseURI());
	transient WebResource restWS  = service.path(REST);
	
	private void init(){
		config = new DefaultClientConfig();
		client = Client.create(config);
		service = client.resource(getBaseURI());
		restWS  = service.path(REST);
	}
     
    public List<ContactoAgenda> getContactos(String filtro) {

    	contactos = new ArrayList<ContactoAgenda>();
    	String json;
    	Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
    	
        if(filtro==null){
        	json = restWS.path(AGENDA).accept(MediaType.APPLICATION_JSON).get(String.class);
        }else {
        	json = restWS.path(AGENDA+"filtro="+filtro).accept(MediaType.APPLICATION_JSON).get(String.class);
        }
        JsonParser parser = new JsonParser();
        JsonObject rootObejct = parser.parse(json).getAsJsonObject();
        JsonElement contactosJson = rootObejct.get("lista");
        Type contactoAgendaListaType = new TypeToken<List<ContactoAgenda>>() {}.getType();
        contactos = gson.fromJson(contactosJson, contactoAgendaListaType);
 
        return contactos;
    }
    
    public ContactoAgenda getContacto(Integer id) {
    	init();
    	ContactoAgenda contacto = new ContactoAgenda();
    	String json;
    	Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
    	
    	json = restWS.path(AGENDAID+id.toString()).accept(MediaType.APPLICATION_JSON).get(String.class);

        JsonParser parser = new JsonParser();
        JsonObject rootObejct = parser.parse(json).getAsJsonObject();
        Type contactoAgendaType = new TypeToken<ContactoAgenda>() {}.getType();
        contacto = gson.fromJson(rootObejct, contactoAgendaType);
         
        return contacto;
    }
    
	public void putContacto(ContactoAgenda contacto) {
    	init();
    	@SuppressWarnings("unused")
		ClientResponse clientResponse = restWS.path(AGENDAID+contacto.getId().toString()).type(MediaType.APPLICATION_JSON_TYPE).put(ClientResponse.class, contacto.toPost());
    }
    
    public void deleteContacto(Integer id) {
    	init();
    	@SuppressWarnings("unused")
		ClientResponse clientResponse = restWS.path(AGENDAID+id.toString()).delete(ClientResponse.class);
    }
    
	public void postContacto(ContactoAgenda contacto) {
    	init();
    	@SuppressWarnings("unused")
		ClientResponse clientResponse = restWS.path(AGENDA).type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class, contacto.toPost());
    }
     
	private static URI getBaseURI() {
		return UriBuilder.fromUri(URLBASE).build();
	}
}