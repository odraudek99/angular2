import {Component} from "angular2/core";
import {Pelicula} from "../model/pelicula";
import {PeliculasService} from "../services/peliculas.services";

import {ROUTER_DIRECTIVES, RouteConfig, Router} from "angular2/router";




@Component({
	selector: "peliculas-list",
	templateUrl: "app/view/peliculas-list.html",
	providers: [PeliculasService],
	directives: [ROUTER_DIRECTIVES]
})

export class PeliculasListComponent  {

	public pelicula:Pelicula;
	public peliculaElegida:Pelicula;
	public mostrarDatos:boolean;
	public peliculas;

	public datoServicio ;

	constructor(private peliculasServices:PeliculasService) {
	
		this.datoServicio = peliculasServices.getPeliculas();	

		this.mostrarDatos=false;
		
		this.peliculas =peliculasServices.getPeliculas();	

		this.pelicula = this.peliculas[0];
		this.peliculaElegida = this.pelicula;
		
		
	}

	onShowHide() {
		this.mostrarDatos=! this.mostrarDatos;
	}
	onHideShow(value) {
		this.mostrarDatos= value;	
	}

	debug(titulo = null) {
		if (titulo != null) {
			console.log(this.pelicula.titulo);
		} else {
			console.log(this.pelicula);	
		}
		
	}

	onCambiarPelicula(pelicula) {
		this.pelicula=pelicula;
		this.peliculaElegida=pelicula;
	}

}













