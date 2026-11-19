package fr.ece.autoloc.domain.billing;

import java.math.BigDecimal;
import java.time.Instant;

import fr.ece.autoloc.domain.shared.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class Payment extends BaseEntity {

    @Column(nullable = false, updatable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, updatable = false)
    private Instant paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false, length = 20)
    private PaymentMethod paymentMethod;

    public static Payment create(
        BigDecimal amount, 
        Instant paymentDate, 
        PaymentMethod paymentMethod) {
        
        return new Payment(amount, paymentDate, paymentMethod);
    }
}
