package ro.fasttrackit.course20.homework.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.course20.homework.model.Transaction;
import ro.fasttrackit.course20.homework.model.TransactionType;
import ro.fasttrackit.course20.homework.repository.TransactionsRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private static long ID_COUNTER = 1L;
//    private List<Transaction> transactions = new ArrayList<>();

    private final TransactionsRepository repository;

//    @PostConstruct
//    void init(){
//        repository.saveAll(transactions.)
//    }

    public List<Transaction> filterProducts(String type, Double minAmount, Double maxAmount) {

        if (type == null && minAmount == null && maxAmount == null) {
            return repository.findAll();
        } else if (type != null && minAmount == null && maxAmount == null) {
            return filterByType(type);
        } else if (type == null && minAmount != null && maxAmount == null) {
            return filterByMinAmount(minAmount);
        } else if (type == null && minAmount == null && maxAmount != null) {
            return filterByMaxAmount(maxAmount);
        } else if (type != null && minAmount != null && maxAmount == null) {
            return filterByTypeAndMinAmount(type, minAmount);
        } else if (type != null && minAmount == null && maxAmount != null) {
            return filterByTypeAndMaxAmount(type, maxAmount);
        } else if (type == null && minAmount != null && maxAmount != null) {
            return filterByMinAmountAndMaxAmount(minAmount, maxAmount);
        } else {
            return filterByTypeAndMinAmountAndMaxAmount(type, minAmount, maxAmount);
        }
    }

    private List<Transaction> filterByType(String type) {
        return repository.findAll().stream()
                .filter(transaction -> transaction.type() == TransactionType.valueOf(type.toUpperCase()))
                .toList();
    }

    private List<Transaction> filterByMinAmount(Double minAmount) {
        return repository.findAll().stream()
                .filter(transaction -> transaction.amount() >= minAmount)
                .toList();
    }

    private List<Transaction> filterByMaxAmount(Double maxAmount) {
        return repository.findAll().stream()
                .filter(transaction -> transaction.amount() <= maxAmount)
                .toList();
    }

    private List<Transaction> filterByTypeAndMinAmount(String type, Double minAmount) {
        return repository.findAll().stream()
                .filter(transaction -> transaction.type() == TransactionType.valueOf(type.toUpperCase()))
                .filter(transaction -> transaction.amount() >= minAmount)
                .toList();
    }

    private List<Transaction> filterByTypeAndMaxAmount(String type, Double maxAmount) {
        return repository.findAll().stream()
                .filter(transaction -> transaction.type() == TransactionType.valueOf(type.toUpperCase()))
                .filter(transaction -> transaction.amount() <= maxAmount)
                .toList();
    }

    private List<Transaction> filterByMinAmountAndMaxAmount(Double minAmount, Double maxAmount) {
        return repository.findAll().stream()
                .filter(transaction -> transaction.amount() >= minAmount)
                .filter(transaction -> transaction.amount() <= maxAmount)
                .toList();
    }

    private List<Transaction> filterByTypeAndMinAmountAndMaxAmount(String type, Double minAmount, Double maxAmount) {
        return repository.findAll().stream()
                .filter(transaction -> transaction.type() == TransactionType.valueOf(type.toUpperCase()))
                .filter(transaction -> transaction.amount() >= minAmount)
                .filter(transaction -> transaction.amount() <= maxAmount)
                .toList();
    }

    public Optional<Transaction> getTransactionById(long id) {
        return repository.findAll().stream()
                .filter(filter -> filter.id() == id)
                .findFirst();
    }

    public Transaction addTransaction(Transaction trans) {
        return repository.save(trans);
    }

    public Transaction replaceTransaction(long id, Transaction toAdd) {
        Transaction transactionId = getOrThrow(id);
        return repository.save(toAdd.withId(transactionId.id()));
    }

    private Transaction getOrThrow(long id) {
        return getTransactionById(id)
                .orElseThrow(RuntimeException::new);
    }

    public Optional<Transaction> deleteTransaction(int id) {
        Optional<Transaction> transactionToDelete = getTransactionById(id);
        transactionToDelete.ifPresent((repository::delete));
        return transactionToDelete;
    }
}
