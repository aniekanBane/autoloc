package fr.ece.autoloc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees", indexes = {@Index(columnList = "lastName, firstName")})
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Employee extends Person {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EmployeeRole role;

    public static Employee create(
        String lastName, 
        String firstName, 
        EmployeeRole role) {
        
        var employee = new Employee();

        employee.lastName = lastName;
        employee.firstName = firstName;
        employee.role = role;

        return employee;
    }
}
