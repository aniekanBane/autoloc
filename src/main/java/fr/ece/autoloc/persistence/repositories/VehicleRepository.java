package fr.ece.autoloc.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ece.autoloc.domain.inventory.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
