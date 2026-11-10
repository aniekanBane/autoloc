package fr.ece.autoloc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipments")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Equipment extends BaseEntity {

    @Column(nullable = false, unique = true, length = 64)
    private String label;

    public static Equipment create(String label) {
        var equipment = new Equipment();

        equipment.label = label;

        return equipment;
    }
}
