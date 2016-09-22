import {Component} from "angular2/core";

@Component({
	selector: "mi-app",

	templateUrl: "app/view/peliculas.html"
})

export class AppComponent  {
	
	public titulo:string ;
	public pelicula:string ;
	public director:string ;
	public anio:number  ;

	constructor() {
		this.pelicula = "Batman";
		this.director="Lalo";
		this.anio=2016;
		this.titulo="Hola Angular2";

		//this.holaMundo();
	}

	holaMundo() {
		alert('hola mundo: '+this.pelicula);
	}

}