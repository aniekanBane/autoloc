package fr.ece.autoloc.domain.inventory;

import java.time.LocalDate;

import org.jspecify.annotations.Nullable;

import fr.ece.autoloc.domain.shared.BaseEntity;

import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(check = {@CheckConstraint(constraint = "end_date IS NULL OR end_date >= start_date")})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Maintenance extends BaseEntity {

    @Column(nullable = false)
    private LocalDate startDate;

    private @Nullable LocalDate endDate;

    @Column(nullable = false, length = 1024)
    private String description;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Vehicle vehicle;

    public static Maintenance create(
        LocalDate startDate,
        @Nullable LocalDate endDate,
        String description,
        Vehicle vehicle) {

        return new Maintenance(startDate, endDate, description, vehicle);
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
