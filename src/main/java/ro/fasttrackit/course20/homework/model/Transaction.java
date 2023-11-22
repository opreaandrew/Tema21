package ro.fasttrackit.course20.homework.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Builder
@Entity
@With
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String product;
    TransactionType type;
    double amount;

    public long id() {
        return id;
    }

    public String product() {
        return product;
    }

    public TransactionType type() {
        return type;
    }

    public double amount() {
        return amount;
    }
}
