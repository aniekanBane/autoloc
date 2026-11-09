package fr.ece.autoloc.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicles")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
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
    private VehicleStatus status = VehicleStatus.AVAILABLE;

    public static Vehicle create(
        String registrationNumber, 
        String make, 
        String model,
        VehicleCategory category,
        BigDecimal dailyRate) {
        
        var vehicle = new Vehicle();

        vehicle.registrationNumber = registrationNumber;
        vehicle.make = make;
        vehicle.model = model;
        vehicle.category = category;
        vehicle.dailyRate = dailyRate;

        return vehicle;
    }
}
