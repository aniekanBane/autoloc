package fr.ece.autoloc.domain.rental;

import java.time.LocalDate;

import org.jspecify.annotations.Nullable;

import fr.ece.autoloc.domain.customer.Customer;
import fr.ece.autoloc.domain.inventory.Vehicle;
import fr.ece.autoloc.domain.shared.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservations", 
    check = {@CheckConstraint(constraint = "end_date >= start_date")})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Reservation extends BaseEntity {

    @Column (nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ReservationStatus status;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Customer customer;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Vehicle vehicle;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private @Nullable Contract contract;

    public static Reservation create(
        LocalDate startDate, 
        LocalDate endDate, 
        Customer customer,
        Vehicle vehicle) {
        
        return new Reservation(startDate, endDate, ReservationStatus.PENDING, customer, vehicle, 
            null);
    }

    public void confirm() {
        this.status = ReservationStatus.CONFIRMED;
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELLED;
    }

    public void complete() {
        this.status = ReservationStatus.COMPLETED;
    }
}
