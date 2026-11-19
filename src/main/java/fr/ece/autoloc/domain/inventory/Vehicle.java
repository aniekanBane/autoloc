package fr.ece.autoloc.domain.inventory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jspecify.annotations.Nullable;

import fr.ece.autoloc.domain.organization.Agency;
import fr.ece.autoloc.domain.shared.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicles")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    @Column(nullable = false, precision = 10, scale = 2, 
        check = @CheckConstraint(constraint = "daily_rate > 0"))
    private BigDecimal dailyRate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private VehicleStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    private @Nullable Agency agency;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.PERSIST, 
        fetch = FetchType.LAZY)
    private List<Maintenance> maintenanceRecords = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vehicle_equipment")
    private Set<Equipment> equipments = new HashSet<>();

    public static Vehicle create(
        String registrationNumber, 
        String make, 
        String model,
        VehicleCategory category,
        BigDecimal dailyRate,
        @Nullable Agency agency) {
        
        return new Vehicle(registrationNumber, make, model, category, dailyRate, 
            VehicleStatus.AVAILABLE, agency, new ArrayList<>(), new HashSet<>());
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

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public void addMaintenanceRecord(Maintenance maintenance) {
        maintenance.setVehicle(this);
        maintenanceRecords.add(maintenance);
    }

    public void addEquipment(Equipment equipment) {
        equipments.add(equipment);
    }

    public void removeEquipment(Equipment equipment) {
        equipments.remove(equipment);
    }
}
