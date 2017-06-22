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


	public filesToUpload: Array<File>;

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


	public resultUpload;
	fileChangeEvent(fileInput: any) {

		console.log('fileChangeEvent...');

		this.filesToUpload = <Array<File>>fileInput.target.files;

		this.makeFileRequest("http://localhost:8080/restaurantes/upload-restaurante/1", [], this.filesToUpload)
			.then((result)=>{

				console.log(result);
				this.resultUpload=result;
				this.restaurante.imagen = this.resultUpload.filename;
				console.log(this.resultUpload.filename);
			}, (error) => {
				console.log(error);
			});
	}


	makeFileRequest(url: string, params:Array<string>, files : Array<File>) {

		console.log('makeFileRequest...');

		return new Promise((resolve, reject) => {

			var formData:any = new FormData();
			var xhr = new XMLHttpRequest();

			for (var i = 0; i<files.length; i++) {
				formData.append("uploads[]", files[i], files[i].name);
			}

			xhr.onreadystatechange = function() {
				if(xhr.readyState == 4) {
					if (xhr.status == 200) {
						resolve(JSON.parse(xhr.response));
					} else {
						reject(xhr.response);
					}
				}
			}

			xhr.open("POST", url, true);
			xhr.send(FormData);
			});
	}

}
