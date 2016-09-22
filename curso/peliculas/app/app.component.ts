import {Component} from "angular2/core";

@Component({
	selector: "mi-app",
	
	template: `<h1>{{titulo}}</h1>
				<ul>
					<li>pelicula: <strong>{{pelicula}}</strong></li>
					<li>director: <strong>{{director}}</strong></li>
					<li>año: <strong>{{anio}}</strong></li>
				</ul>
				`
})

export class AppComponent  {
	
	public titulo:string = "Hola Lalo Angular 2"	;
	public pelicula:string = "Sherk" ;
	public director:string ="Lalo" ;
	public anio:number = 2000 ;

}