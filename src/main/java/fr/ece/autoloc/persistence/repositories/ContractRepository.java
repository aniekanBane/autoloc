package fr.ece.autoloc.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import fr.ece.autoloc.domain.rental.Contract;

public interface ContractRepository extends CrudRepository<Contract, Long>  {

}
