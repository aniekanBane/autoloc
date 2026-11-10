package fr.ece.autoloc.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter Long id;

    protected BaseEntity() {
    }

    @Override
    public String toString() {
        return String.format("%s[id=%s]", this.getClass().getSimpleName(), id);
    }
}
