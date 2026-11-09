package fr.ece.autoloc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
public abstract class Person extends BaseEntity {

    @Column(nullable = false, length = 32)
    protected @Getter String lastName;

    @Column(nullable = false, length = 32)
    protected @Getter String firstName;

    protected Person() {
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void updateName(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }
}
