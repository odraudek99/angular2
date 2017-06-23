import {Component} from "angular2/core";




import {ROUTER_DIRECTIVES, RouteConfig, Router} from "angular2/router";

import {RestaurantesListComponent} from  "./components/restaurantes-list.component";
import {RestaurantesDetailComponent} from  "./components/restaurantes-detail.component";
import {RestauranteAddComponent} from "./components/restaurante-add.component";
import {RestauranteAdd2Component} from "./components/restaurante-add2.component";
import {RestauranteEditComponent} from "./components/restaurante-edit.component";



@Component({
	selector: "mi-app",

	templateUrl: "app/view/home.html",
	directives: [ROUTER_DIRECTIVES,
			RestaurantesListComponent]
})

@RouteConfig([
	{
		path: '/',
		name:"Home",
		component: RestaurantesListComponent,
		useAsDefault: true
	}, {
		path: '/restaurante/:id',
		name:"Restaurante",
		component: RestaurantesDetailComponent
	}, {
		path: '/crear-restaurante',
		name:"CrearRestaurante",
		component: RestauranteAddComponent
	},{
		path: '/crear-restaurante2',
		name:"CrearRestaurante2",
		component: RestauranteAdd2Component
	}, {
		path: '/edit-restaurante/:id',
		name:"EditarRestaurante",
		component: RestauranteEditComponent
	}, {
		path: '/donde-como-hoy/:random',
		name:"DondeComoHoy",
		component: RestaurantesDetailComponent
	}
])

export class AppComponent  {

	public titulo:string = "Restaurantes";


}
