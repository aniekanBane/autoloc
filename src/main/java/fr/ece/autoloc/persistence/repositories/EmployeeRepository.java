package fr.ece.autoloc.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import fr.ece.autoloc.domain.organization.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
