package fr.ece.autoloc.domain;

import java.time.LocalDate;

import org.jspecify.annotations.Nullable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "maintenances")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Maintenance extends BaseEntity {

    @Column(nullable = false)
    private LocalDate startDate;

    private @Nullable LocalDate endDate;

    @Column(nullable = false, length = 1024)
    private String description;

    public static Maintenance create(
        LocalDate startDate, 
        @Nullable LocalDate endDate, 
        String description) {

        var maintenance = new Maintenance();

        maintenance.startDate = startDate;
        maintenance.endDate = endDate;
        maintenance.description = description;

        return maintenance;
    }
}
