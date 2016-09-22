package mx.com.odraudek99.angular2.taller.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.com.odraudek99.angular2.taller.model.Restaurante;

@Repository

public interface RestauranteRepository extends CrudRepository<Restaurante, Integer> {

	
}


