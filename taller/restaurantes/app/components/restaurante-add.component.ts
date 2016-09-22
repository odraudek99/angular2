import {Component, OnInit} from "angular2/core";


import {RouteParams, Router} from "angular2/router";

import {RestauranteService} from "../services/restaurante.service";

import {Restaurante} from "../model/restaurante";



@Component({
	selector: "restaurante-add",

	templateUrl: "app/view/restaurante-add.html",

	providers: [RestauranteService]
})



export class RestauranteAddComponent implements OnInit  {

	public titulo = "Crear nuevo restaurante";
	public parametro;
	public restaurante: Restaurante;
	public errorMessage: string;
	public status: string;

	constructor(private _routerParams:RouteParams,
		private _restauranteService : RestauranteService,
		private _router: Router) {
	}

	onSubmit() {
		console.log('onSubmit');
		this._restauranteService
			.addRestaurante(this.restaurante)
			.subscribe(
				response => {
					console.log('response: '+response);

					this.status = response.status;
					
					console.log('this.status: '+this.status);

					if (this.status !=="success") {
						alert("Error en el servidor");
					}
				}, error => {
					this.errorMessage = <any>error;
					console.log("this.errorMessage: "+this.errorMessage);
					if (this.errorMessage !== null) {
						console.log("error en la petición");
					}
				});
			this._router.navigate(["Home"]);
	}

	ngOnInit() {
		this.restaurante = new Restaurante(
					0,
					this._routerParams.get("nombre"),
					this._routerParams.get("direccion"),
					this._routerParams.get("descripcion"),
					null,
					this._routerParams.get("precio"));
		
	}


	callPrecio(value) {
		this.restaurante.precio = value;
	}

}