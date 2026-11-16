package fr.ece.autoloc.domain.customer;

import java.time.Instant;

import fr.ece.autoloc.domain.shared.Person;

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

    @Column(nullable = false, unique = true, length = 25)
    private String licenseNumber;

    @Column(nullable = false, updatable = false)
    private Instant registrationDate;

    private Customer(
        String lastName, 
        String firstName, 
        String email, 
        String phoneNumber, 
        String licenseNumber) {

        super(lastName, firstName);
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.licenseNumber = licenseNumber;
        this.registrationDate = Instant.now();
    }

    public static Customer create(
        String lastName, 
        String firstName, 
        String email, 
        String phoneNumber, 
        String licenseNumber) {
        
        return new Customer(lastName, firstName, email, phoneNumber, licenseNumber);
    }
}
