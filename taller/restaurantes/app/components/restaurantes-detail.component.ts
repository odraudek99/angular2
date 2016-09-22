import {Component, OnInit} from "angular2/core";


import {RouteParams, Router} from "angular2/router";

import {RestauranteService} from "../services/restaurante.service";

import {Restaurante} from "../model/restaurante";



@Component({
	selector: "restaurantes-detail",

	templateUrl: "app/view/restaurante-detail.html",

	providers: [RestauranteService]
})



export class RestaurantesDetailComponent implements OnInit  {

	public parametro;
	public restaurante: Restaurante[];
	public errorMessage: string;
	public status: string;

	constructor(private _routerParams:RouteParams,
		private _restauranteService : RestauranteService,
		private _router: Router) {
	}

	ngOnInit() {

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
}