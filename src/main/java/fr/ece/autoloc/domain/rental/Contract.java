package fr.ece.autoloc.domain.rental;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.ece.autoloc.domain.billing.Payment;
import fr.ece.autoloc.domain.shared.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contracts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Contract extends BaseEntity {

    @Column(nullable = false)
    private LocalDate dateSigned;
    
    @Column(nullable = false, precision = 10, scale = 2, 
        check = @CheckConstraint(constraint = "total_amount >= 0"))
    private BigDecimal totalAmount;

    private boolean isValid;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = true)
    private Reservation reservation;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, 
        orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Payment> payments = new ArrayList<>(); 

    public static Contract create(
        LocalDate dateSigned, 
        BigDecimal totalAmount, 
        boolean isValid,
        Reservation reservation) {
        
        return new Contract(dateSigned, totalAmount, isValid, reservation, new ArrayList<>());
    }

    public void addPayment(Payment payment) {
        payment.setContract(this);
        payments.add(payment);
    }
}
