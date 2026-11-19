package fr.ece.autoloc.domain.inventory;

import java.time.LocalDate;

import org.jspecify.annotations.Nullable;

import fr.ece.autoloc.domain.shared.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
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

        return new Maintenance(startDate, endDate, description);
    }
}
