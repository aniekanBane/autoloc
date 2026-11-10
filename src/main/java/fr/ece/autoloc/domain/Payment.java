package fr.ece.autoloc.domain;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Payment extends BaseEntity {

    @Column(nullable = false, updatable = false, length = 64, unique = true)
    private String transactionReference;

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
        String transactionReference,
        PaymentMethod paymentMethod) {
        
        var payment = new Payment();

        payment.amount = amount;
        payment.paymentDate = paymentDate;
        payment.paymentMethod = paymentMethod;
        payment.transactionReference = transactionReference;

        return payment;
    }
}
