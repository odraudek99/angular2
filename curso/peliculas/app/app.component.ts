import {Component} from "angular2/core";
import {Pelicula} from "./model/pelicula";

@Component({
	selector: "mi-app",

	templateUrl: "app/view/peliculas.html"
})

export class AppComponent  {
	
	public titulo:string = "Hola Peliculas Angular2";
	
	public pelicula:Pelicula;

	constructor() {
		this.pelicula = new Pelicula(1,"Sherk","Lalo",2016);
		

		this.debug();
	}



	debug() {
		console.log(this.pelicula);
	}

}