import {Component} from "angular2/core";

import {PeliculasListComponent} 
from "./components/peliculas.list.component";

import {PeliculasFooterComponent} 
from "./components/peliculas-footer.component";

import {ContactoComponent} 
from "./components/contacto.component";

import {CrearPeliculaComponent} 
from "./components/crear-pelicula.component";

import {ROUTER_DIRECTIVES, RouteConfig, Router} from "angular2/router";




@Component({
	selector: "mi-app",

	templateUrl: "app/view/peliculas.html",
	directives: [PeliculasListComponent, 
				PeliculasFooterComponent, 
				ContactoComponent,
				CrearPeliculaComponent,
				ROUTER_DIRECTIVES]
})

@RouteConfig([

	{
		path: "/peliculas", 
		name: "Peliculas",
		component: PeliculasListComponent,
		useAsDefault: false
	},{
		path: "/contacto", 
		name: "Contacto",
		component: ContactoComponent,
		useAsDefault: false
	},{
		path: "/crear-pelicula", 
		name: "CrearPelicula",
		component: CrearPeliculaComponent,
		useAsDefault: false
	},{
		path: "/crear-pelicula/:titulo/:director/:anio", 
		name: "CrearPeliculaBasadaEnOtra",
		component: CrearPeliculaComponent,
		useAsDefault: false
	}

	
])

export class AppComponent  {
	
	public titulo:string = "Hola Peliculas Angular2";
	

}