package fr.ece.autoloc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "agencies")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
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
        var agency = new Agency();

        agency.name = name;
        agency.city = city;
        agency.address = address;
        agency.contactNumber = contactNumber;

        return agency;
    }
}
