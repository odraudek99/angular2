package mx.com.odraudek99.angular2.taller.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mx.com.odraudek99.angular2.taller.model.Respuesta;
import mx.com.odraudek99.angular2.taller.model.Restaurante;
import mx.com.odraudek99.angular2.taller.repositories.RestauranteRepository;

@RestController 
@RequestMapping(value = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController {

	static int id = 15;
	
    @Autowired
    protected RestauranteRepository restauranteRepository;

    @RequestMapping
    public Respuesta books2() {
    	Iterable<Restaurante> res = restauranteRepository.findAll();
    	Respuesta respuesta = new Respuesta();
    	respuesta.setData(res);
    	respuesta.setStatus("success");
    	Gson gson = new GsonBuilder().create();
        gson.toJson(respuesta);
        
        try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
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


    @RequestMapping(value = "/random-restaurante")
    public Respuesta book() {
    	System.out.println("Recupera un restaurante random ");
    	Iterable<Restaurante> res = restauranteRepository.findAll();    	
    	restauranteRepository.count();
    	int random = (int) (Math.random() * restauranteRepository.count()) + 1;
    	System.out.println("random: "+random);
    	Restaurante resp = restauranteRepository.findOne(random);
    	Respuesta respuesta = new Respuesta();
    	if (resp != null) {
    		respuesta.setData(resp);
        	respuesta.setStatus("success");
    	}
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
    

    @RequestMapping(method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, 
    		consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public Respuesta postRestaurante(@RequestBody Restaurante restaurante) {

    	System.out.println("Entra a restaurante post: "+restaurante.toString());
    	restaurante.setId(id++);
    	Restaurante res = restauranteRepository.save(restaurante);
    	System.out.println("res: "+res);
    	Respuesta respuesta = new Respuesta();
    	respuesta.setStatus("success");
       return respuesta;
       
    }
    
    
    
    @RequestMapping(value="/update-restaurante/{id}", method = RequestMethod.POST)
    public Respuesta handleFileUpload(@PathVariable("id") String id,
    		@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

    	try {
            if (file.isEmpty()) {
                return null;//throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            Path rootLocation = Paths.get("/tmp/");
            Files.copy(file.getInputStream(),
            		rootLocation.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
        	return null;//throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        Respuesta respuesta = new Respuesta();
  		respuesta.setStatus("success");
  		
  		return respuesta;
    }
    



    @RequestMapping(value = "/files/{id}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable Integer id) {
    	System.out.println("id::: "+id);

    	Restaurante res = restauranteRepository.findOne(id);
        Resource file = loadAsResource(res.getImagen());
        System.out.println("file existe: "+(file!=null?true:false));
        System.out.println("filename: "+file.getFilename());
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }
    
    public Resource loadAsResource(String filename) {
        try {
        	
        	System.out.println("filename::: "+filename);
        	Path path = Paths.get("images/").resolve(filename);

        	System.out.println("resource:::: "+new UrlResource(path.toUri()).toString());
            Resource resource = new UrlResource(path.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                return null; //throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
        	return null; //throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }
    

}
