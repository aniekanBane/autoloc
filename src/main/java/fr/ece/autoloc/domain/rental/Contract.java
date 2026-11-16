package fr.ece.autoloc.domain.rental;

import java.math.BigDecimal;
import java.time.LocalDate;

import fr.ece.autoloc.domain.shared.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contracts")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class Contract extends BaseEntity {

    @Column(nullable = false)
    private LocalDate dateSigned;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    private boolean isValid;

    public static Contract create(
        LocalDate dateSigned, 
        BigDecimal totalAmount, 
        boolean isValid) {
        
        return new Contract(dateSigned, totalAmount, isValid);
    }
}
