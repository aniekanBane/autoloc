package fr.ece.autoloc.domain;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers", indexes = {@Index(columnList = "lastName, firstName")})
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Customer extends Person {

    @Column(nullable = false, unique = true, length = 254)
    private String email;

    @Column(nullable = false, length = 25)
    private String phoneNumber;

    @Column(nullable = false, length = 20)
    private String permitNumber;

    @Column(nullable = false, updatable = false)
    private Instant registrationDate;

    public static Customer create(
        String lastName, 
        String firstName, 
        String email, 
        String phoneNumber, 
        String permitNumber) {
        
        var customer = new Customer();

        customer.lastName = lastName;
        customer.firstName = firstName;
        customer.email = email;
        customer.phoneNumber = phoneNumber;
        customer.permitNumber = permitNumber;
        customer.registrationDate = Instant.now();

        return customer;
    }
}
