package fr.ece.autoloc.domain.inventory;

import java.math.BigDecimal;

import fr.ece.autoloc.domain.shared.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicles")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class Vehicle extends BaseEntity {

    @Column(nullable = false, unique = true, length = 20)
    private String registrationNumber;

    @Column(nullable = false, length = 50)
    private String make;

    @Column(nullable = false, length = 50)
    private String model;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private VehicleCategory category;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal dailyRate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private VehicleStatus status;

    public static Vehicle create(
        String registrationNumber, 
        String make, 
        String model,
        VehicleCategory category,
        BigDecimal dailyRate) {
        
        return new Vehicle(registrationNumber, make, model, category, dailyRate, 
            VehicleStatus.AVAILABLE);
    }

    public void markAsRented() {
        this.status = VehicleStatus.RENTED;
    }

    public void markAsUnderMaintenance() {
        this.status = VehicleStatus.MAINTENANCE;
    }

    public void markAsAvailable() {
        this.status = VehicleStatus.AVAILABLE;
    }
}
