package mx.com.odraudek99.angular2.taller.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Restaurante {

	@Id
	public Integer id;
	public String nombre;
	public String direccion;
	public String descripcion;
	public String imagen;
	public String precio;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Restaurante [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", descripcion="
				+ descripcion + ", imagen=" + imagen + ", precio=" + precio + "]";
	}
	
	
	

}
