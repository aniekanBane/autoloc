package fr.ece.autoloc.domain.organization;

import java.util.ArrayList;
import java.util.List;

import fr.ece.autoloc.domain.inventory.Vehicle;
import fr.ece.autoloc.domain.shared.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "agencies")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Agency extends BaseEntity {

    @Column(nullable = false, unique = true, length = 128)
    private String name;

    @Column(nullable = false, length = 64)
    private String city;

    @Column(nullable = false)
    private String address;

    @Column(length = 25)
    private String contactNumber;

    @OneToMany(mappedBy = "agency", fetch = FetchType.LAZY)
    private List<Vehicle> vehicles = new ArrayList<>();

    @OneToMany(mappedBy = "agency", fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();

    public static Agency create(
        String name, 
        String city, 
        String address, 
        String contactNumber) {

        return new Agency(name, city, address, contactNumber, new ArrayList<>(), 
            new ArrayList<>());
    }
}
