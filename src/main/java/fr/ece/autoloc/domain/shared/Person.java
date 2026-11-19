package fr.ece.autoloc.domain.shared;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public abstract class Person extends BaseEntity {

    public static final int NAME_MAX_LENGTH = 32;

    @Column(nullable = false, length = NAME_MAX_LENGTH)
    private String lastName;

    @Column(nullable = false, length = NAME_MAX_LENGTH)
    private String firstName;

    protected Person(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setName(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }
}
