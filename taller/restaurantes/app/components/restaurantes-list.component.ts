import {Component, OnInit} from "angular2/core";


import {ROUTER_DIRECTIVES, RouteConfig, Router} from "angular2/router";

import {RestauranteService} from "../services/restaurante.service";

import {Restaurante} from "../model/restaurante";



@Component({
	selector: "restaurantes-list",

	templateUrl: "app/view/restaurantes-list.html",

	providers: [RestauranteService],
	directives: [ROUTER_DIRECTIVES]
})



export class RestaurantesListComponent implements OnInit  {
	
	public titulo:string = "Listado de restaurantes";	
	public restaurantes: Restaurante[];
	public status: string;
	public errorMessage:string;

 	public loading2;
 	public confirmado;

	constructor(private _restauranteService: RestauranteService) {
	}

	ngOnInit() {
		this.loading2 ='show';
		console.log("restaurantes-list component cargado");
		this.getRestaurantes();
	}


	getRestaurantes() {

		let box_restaurantes = <HTMLElement>document.
			querySelector("#restaurantes-list .loading");

		box_restaurantes.style.visibility = "visible";

		this._restauranteService.getRestaurantes().
			subscribe(
				result => {
					//this.restaurantes = result;
					this.status= result.status;
					console.log("this.status: "+this.status);
					this.restaurantes =result.data;
					if (this.status !== "success") {
						alert("Error en el servidor");
					}

					box_restaurantes.style.display = "none";
					 this.loading2 = 'hide';
				}, error => {
					this.errorMessage = <any>error;
					console.log("this.errorMessage: "+this.errorMessage);
					if (this.errorMessage !== null) {
						console.log("error en la petición");
					}
				}
			);
	}


	onBorrarConfirm(id) {
		this.confirmado = id;
	}

	onCancelarConfirm() {
		this.confirmado = null;
	}

	onCambiarPelicula(id) {

		console.log("onBorrarRestaurante1: "+id);

		this._restauranteService.deleteRestaurante(id).
			subscribe(
				result => {
					console.log("this.result: "+result);
					this.status= result.status;
					
					this.restaurantes =result.data;
					if (this.status !== "success") {
						alert("Error en el servidor");
					}
					this.getRestaurantes();

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