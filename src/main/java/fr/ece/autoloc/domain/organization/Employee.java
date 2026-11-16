package fr.ece.autoloc.domain.organization;

import fr.ece.autoloc.domain.shared.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees", indexes = {@Index(columnList = "lastName, firstName")})
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Employee extends Person {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private @Setter EmployeeRole role;

    private Employee(String lastName, String firstName, EmployeeRole role) {
        super(lastName, firstName);
        this.role = role;
    }

    public static Employee create(
        String lastName, 
        String firstName, 
        EmployeeRole role) {
        
        return new Employee(lastName, firstName, role);
    }
}
