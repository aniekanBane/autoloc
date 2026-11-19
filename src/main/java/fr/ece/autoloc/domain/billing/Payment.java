package fr.ece.autoloc.domain.billing;

import java.math.BigDecimal;
import java.time.Instant;

import fr.ece.autoloc.domain.rental.Contract;
import fr.ece.autoloc.domain.shared.BaseEntity;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Payment extends BaseEntity {

    @Column(nullable = false, updatable = false, precision = 10, scale = 2, 
        check = @CheckConstraint(constraint = "amount >= 0"))
    private BigDecimal amount;

    @Column(nullable = false, updatable = false)
    private Instant paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false, length = 20)
    private PaymentMethod paymentMethod;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Contract contract;

    public static Payment create(
        BigDecimal amount, 
        Instant paymentDate, 
        PaymentMethod paymentMethod, 
        Contract contract) {
        
        return new Payment(amount, paymentDate, paymentMethod, contract);
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
