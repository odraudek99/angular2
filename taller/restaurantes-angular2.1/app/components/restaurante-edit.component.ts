import {Component, OnInit} from "angular2/core";


import {RouteParams, Router} from "angular2/router";

import {RestauranteService} from "../services/restaurante.service";

import {Restaurante} from "../model/restaurante";



@Component({
	selector: "restaurante-edit",

	templateUrl: "app/view/restaurante-add.html",

	providers: [RestauranteService]
})



export class RestauranteEditComponent implements OnInit  {

	public titulo = "Editar restaurante";
	public parametro;
	public restaurante: Restaurante;
	public errorMessage: string;
	public status: string;

	constructor(private _routerParams:RouteParams,
		private _restauranteService : RestauranteService,
		private _router: Router) {
	}

	onSubmit() {
		
		let id = this._routerParams.get("id");

		console.log('onSubmit');
		console.log('restaurante: '+this.restaurante);

		this._restauranteService
			.editRestaurante(id, this.restaurante)
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
					parseInt(this._routerParams.get("id")),
					this._routerParams.get("nombre"),
					this._routerParams.get("direccion"),
					this._routerParams.get("descripcion"),
					null,
					this._routerParams.get("precio"));

		this.getRestaurante();
		
	}


	getRestaurante() {
		let id = this._routerParams.get("id");
		this._restauranteService.getRestaurante(id)
			.subscribe(
				response => {
					this.restaurante = response.data;
					this.status =response.status;
					if (this.status !== "success") {
						//alert("Error en el servidor");
						this._router.navigate(["Home"]);
					}
					console.log('restaurante: '+this.restaurante);

				}, error => {
					this.errorMessage = <any>error;
					console.log("this.errorMessage: "+this.errorMessage);
					if (this.errorMessage !== null) {
						console.log("error en la petición");
					}
				}
				);
	}

	callPrecio(value) {
		this.restaurante.precio = value;
	}

}