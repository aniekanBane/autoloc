package fr.ece.autoloc.domain.organization;

import fr.ece.autoloc.domain.shared.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "agencies")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class Agency extends BaseEntity {

    @Column(nullable = false, unique = true, length = 128)
    private String name;

    @Column(nullable = false, length = 64)
    private String city;

    @Column(nullable = false)
    private String address;

    @Column(length = 25)
    private String contactNumber;

    public static Agency create(
        String name, 
        String city, 
        String address, 
        String contactNumber) {

        return new Agency(name, city, address, contactNumber);
    }
}
