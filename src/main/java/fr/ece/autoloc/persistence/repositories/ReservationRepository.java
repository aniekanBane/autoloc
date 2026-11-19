package fr.ece.autoloc.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import fr.ece.autoloc.domain.rental.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

}
