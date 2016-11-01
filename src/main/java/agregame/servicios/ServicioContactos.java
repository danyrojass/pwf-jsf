package agregame.servicios;

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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


@ManagedBean(name = "servicioContactos")
@ApplicationScoped
public class ServicioContactos {

	private static final String URLBASE = "https://desa03.konecta.com.py/pwf";
	private static final String REST = "rest";
	private static final String AGENDA = "agenda";
	
	public class GsonFromJson{
		public int total;
		public List<ContactoAgenda> lista;
		
		public GsonFromJson(){
			this.total=0;
			this.lista= new ArrayList<ContactoAgenda>();
		}
	}
	
	ClientConfig config = new DefaultClientConfig();
	Client client = Client.create(config);
	WebResource service = client.resource(getBaseURI());
	WebResource restWS  = service.path(REST);
	
     
    public List<ContactoAgenda> getContactos() {

    	List<ContactoAgenda> contactos = new ArrayList<ContactoAgenda>();
    	Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
    	
        String Json = restWS.path(AGENDA).accept(MediaType.APPLICATION_JSON).get(String.class);
        JsonParser parser = new JsonParser();
        JsonObject rootObejct = parser.parse(Json).getAsJsonObject();
        JsonElement contactosJson = rootObejct.get("lista");
        Type personaListaType = new TypeToken<List<ContactoAgenda>>() {}.getType();
        contactos = gson.fromJson(contactosJson, personaListaType);
 
        return contactos;
    }
     

	private static URI getBaseURI() {
		return UriBuilder.fromUri(URLBASE).build();
	}
}