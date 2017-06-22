package mx.com.odraudek99.angular2.taller.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
    
    
    @RequestMapping(value = "/upload-file2", method = RequestMethod.POST)
	public Respuesta crearWar(@RequestParam MultipartFile file) {
		
    	System.out.println("upload file: "+file);
    	
    	Respuesta respuesta = new Respuesta();
    	
    	try {
			if (file.getBytes() == null) {
//    		return new ResponseEntity<String>("Debe ingresar un archivo.", HttpStatus.BAD_REQUEST);
				respuesta.setStatus("error");
				System.out.println("error...");
				return respuesta;
			}
			file.getOriginalFilename(); 
			file.getBytes();
			
			

//			Gson gson = new Gson();
//			String json = gson.toJson(ticket);
//			return new ResponseEntity<String>(json, HttpStatus.OK);
			respuesta.setData("{filename: archivo.png}");
			respuesta.setStatus("success");
			System.out.println("success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			respuesta.setStatus("error");
		}
    	return respuesta;
	}
	
    

    
    @RequestMapping(value = "/upload-file", method = RequestMethod.POST, headers = "content-type!=multipart/form-data")
    @ResponseBody
    public Respuesta uploadChunked(
            final HttpServletRequest request,
            final HttpServletResponse response) {
    	Respuesta respuesta = new Respuesta();
    	System.out.println("upload file: ...");
    	try {
    		
        request.getHeader("content-range");//Content-Range:bytes 737280-819199/845769
        request.getHeader("content-length"); //845769
        request.getHeader("content-disposition"); // Content-Disposition:attachment; filename="Screenshot%20from%202012-12-19%2017:28:01.png"
        ServletInputStream sis= request.getInputStream();
        
        for (String as: request.getParameterMap().keySet() ) {
        	System.out.println("as:"+as);
        }
        Enumeration<String> a =null;
        a = request.getAttributeNames();
        String ss = null;
        while ((ss = a.nextElement())!= null) {
        	System.out.println("ss: "+ss);
        	
        }
        
        
        System.out.println("sis: "+sis);
        System.out.println("content-range: "+request.getHeader("content-range"));
        System.out.println("content-length: "+request.getHeader("content-length"));
        System.out.println("content-disposition: "+request.getHeader("content-disposition"));
        
     // Get the size of the file
        int length = request.getContentLength();
      
        // Create the byte array to hold the data
        byte[] bytes = new byte[length];
     
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while ((offset < bytes.length) && (numRead=sis.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
     
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read request body");
        }
     
        // Close the input stream and return bytes
        sis.close();
        FileOutputStream f = new FileOutputStream(new File("/tmp/archivo.png"));
        f.write(bytes);

        //Regex for content range: Pattern.compile("bytes ([0-9]+)-([0-9]+)/([0-9]+)");
        //Regex for filename: Pattern.compile("(?<=filename=\").*?(?=\")");
        
        //return whatever you want to json7
        
        respuesta.setFilename("archivo.png");
		respuesta.setStatus("success");
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //actual content.
    	System.out.println("sale");
		return respuesta;
//        return new UploadedFile();
    }

    	public void writeToDisk(String filename, MultipartFile multipartFile)
    	{
    	    try
    	    {
    	        String fullFileName = "/tmp/" + filename;
    	        FileOutputStream fos = new FileOutputStream(fullFileName);
    	        fos.write(multipartFile.getBytes());
    	        fos.close();
    	    }
    	    catch (Exception ex)
    	    {
    	        ex.printStackTrace();
    	    }
    	}
    

}
