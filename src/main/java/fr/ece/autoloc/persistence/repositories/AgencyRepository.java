package fr.ece.autoloc.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import fr.ece.autoloc.domain.organization.Agency;

public interface AgencyRepository extends CrudRepository<Agency, Long> {

}
