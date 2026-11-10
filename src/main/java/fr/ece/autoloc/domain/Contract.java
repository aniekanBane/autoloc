package fr.ece.autoloc.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contracts")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
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
        
        var contract = new Contract();

        contract.dateSigned = dateSigned;
        contract.totalAmount = totalAmount;
        contract.isValid = isValid;

        return contract;
    }
}
