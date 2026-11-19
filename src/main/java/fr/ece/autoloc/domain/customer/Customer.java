package fr.ece.autoloc.domain.customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.ece.autoloc.domain.rental.Reservation;
import fr.ece.autoloc.domain.shared.Person;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers", indexes = {@Index(columnList = "lastName, firstName")})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends Person {

    @Column(nullable = false, unique = true, length = 254)
    private String email;

    @Column(nullable = false, length = 25)
    private String phoneNumber;

    @Column(nullable = false, unique = true, length = 25)
    private String licenseNumber;

    @Column(nullable = false, updatable = false)
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST, 
        fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>();

    private Customer(
        String lastName, 
        String firstName, 
        String email, 
        String phoneNumber, 
        String licenseNumber,
        LocalDate registrationDate,
        List<Reservation> reservations) {

        super(lastName, firstName);
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.licenseNumber = licenseNumber;
        this.registrationDate = registrationDate;
        this.reservations = reservations;
    }

    public static Customer create(
        String lastName, 
        String firstName, 
        String email, 
        String phoneNumber, 
        String licenseNumber,
        LocalDate registrationDate) {
        
        return new Customer(lastName, firstName, email, phoneNumber, licenseNumber, 
            registrationDate, new ArrayList<>());
    }
}
