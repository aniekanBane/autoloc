package fr.ece.autoloc.domain.inventory;

import fr.ece.autoloc.domain.shared.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipments")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class Equipment extends BaseEntity {

    @Column(nullable = false, unique = true, length = 64)
    private String label;

    public static Equipment create(String label) {
        return new Equipment(label);
    }
}
