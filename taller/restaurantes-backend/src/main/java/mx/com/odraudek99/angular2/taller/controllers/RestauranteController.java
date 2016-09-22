package mx.com.odraudek99.angular2.taller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mx.com.odraudek99.angular2.taller.model.Respuesta;
import mx.com.odraudek99.angular2.taller.model.Restaurante;
import mx.com.odraudek99.angular2.taller.repositories.RestauranteRepository;



@RestController // assumes @ResponseBody annotation in methods annotated with  @RequestMapping
@RequestMapping(value = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController {

	static int id = 15;
    @Autowired
    protected RestauranteRepository restauranteRepository;
//
//    @RequestMapping
//    public Iterable<Restaurante> books() {
//    	System.out.println("Entra a buscar restaurantes");
//    	Iterable<Restaurante> res = restauranteRepository.findAll();
//        return res;
//    }
    
    @RequestMapping
    public Respuesta books2() {
//    	System.out.println("Entra a buscar restaurantes");
    	Iterable<Restaurante> res = restauranteRepository.findAll();
    	Respuesta respuesta = new Respuesta();
    	respuesta.setData(res);
    	respuesta.setStatus("success");
    	Gson gson = new GsonBuilder().create();
        gson.toJson(respuesta);
        
        
        try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        return respuesta;
    }

    @RequestMapping(value = "/new")
    public Restaurante save() {
    	System.out.println("new new");
    	
    	Restaurante restaurante = new Restaurante();
    	

    	System.out.println("new new");
        return restauranteRepository.save(restaurante);
    }
    

    @RequestMapping(value = "/{id}")
    public Respuesta book(@PathVariable("id") Integer id) {
    	
    	
    	System.out.println("Recupera un restaurante: "+id);
    	
    	Restaurante res = restauranteRepository.findOne(id);
    	Respuesta respuesta = new Respuesta();
    	
    	if (res != null) {
    		respuesta.setData(res);
        	respuesta.setStatus("success");
    	}
    	
    	
    	
        return respuesta;
    }

//    @RequestMapping(value = "/{random-restaurante}")
//    public Restaurante randomRestaurante(@PathVariable("id") String id) {
//        return restauranteRepository.findOne("1");
//    }
    
    
    @RequestMapping(method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public Respuesta postRestaurante(@RequestBody Restaurante restaurante) {

    	System.out.println("Entra a restaurante post: "+restaurante.toString());
    	restaurante.setId(id++);
    	Restaurante res = restauranteRepository.save(restaurante);
    	System.out.println("res: "+res);
    	Respuesta respuesta = new Respuesta();
    	respuesta.setStatus("success");
       return respuesta;
       
    }
    
    //@RequestMapping(value="/update-restaurante/{id}", method = RequestMethod.POST)
    @RequestMapping(value="/update-restaurante/{id}", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE,
    		consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public Respuesta postRestaurante(@PathVariable("id") String id, @RequestBody Restaurante restaurante) {
    	
    	System.out.println("Entra a: update-restaurante: id: "+id + " Restaurante: "+restaurante);
    	Restaurante res = restauranteRepository.save(restaurante);
       	Respuesta respuesta = new Respuesta();
   		respuesta.setStatus("success");
   		respuesta.setData(res);
   		return respuesta;
       
    }


    @RequestMapping(value="/delete-restaurante/{id}", method = RequestMethod.GET)
    public Respuesta deleteRestaurante(@PathVariable("id") Integer id) {

    	System.out.println("Entra a: delete-restaurante: id: "+id );
       restauranteRepository.delete(id);
       Respuesta respuesta = new Respuesta();
  		respuesta.setStatus("success");
  		
  		return respuesta;
    }
    

}
