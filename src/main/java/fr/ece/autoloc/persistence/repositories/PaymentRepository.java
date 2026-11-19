package fr.ece.autoloc.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import fr.ece.autoloc.domain.billing.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

}
