package ro.fasttrackit.course20.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.course20.homework.model.Transaction;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Long> {
}
