package fr.ece.autoloc.domain.organization;

import org.jspecify.annotations.Nullable;

import fr.ece.autoloc.domain.shared.Person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees", indexes = {@Index(columnList = "lastName, firstName")})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee extends Person {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private @Setter EmployeeRole role;

    @ManyToOne(fetch = FetchType.LAZY)
    private @Setter @Nullable Agency agency;

    private Employee(
        String lastName, 
        String firstName, 
        EmployeeRole role, 
        @Nullable Agency agency) {
        super(lastName, firstName);
        this.role = role;
        this.agency = agency;
    }

    public static Employee create(
        String lastName, 
        String firstName, 
        EmployeeRole role, 
        @Nullable Agency agency) {
        
        return new Employee(lastName, firstName, role, agency);
    }
}
