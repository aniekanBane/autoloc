package fr.ece.autoloc.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import fr.ece.autoloc.domain.customer.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
