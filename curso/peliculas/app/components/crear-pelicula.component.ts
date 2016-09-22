import {Component} from "angular2/core";
import {OnInit} from "angular2/core";
import {Pelicula} from "../model/pelicula";
import {Router, RouteParams} from "angular2/router";
import {PeliculasService} from "../services/peliculas.services";


@Component({
	templateUrl: "app/view/crear-pelicula.html",
	providers:[PeliculasService]
})



export class CrearPeliculaComponent implements OnInit {
	
	public TituloPelicula = "";

	public nuevaPelicula:Pelicula;

	constructor(private _peliculasService:PeliculasService, 
		private _router:Router,
		private _routerParams:RouteParams) {

	}

	
	onSubmit() {

		this._peliculasService.insertPelicula(this.nuevaPelicula);

		this._router.navigate(["Peliculas"]);

	}

	ngOnInit():any {

		this.TituloPelicula = this._routerParams.get("titulo");
		console.log("TituloPelicula: "+this.TituloPelicula);

		this.nuevaPelicula = new Pelicula(
			0,
			this._routerParams.get("titulo"),
			this._routerParams.get("director"),
			parseInt(this._routerParams.get("anio")));
	}


}