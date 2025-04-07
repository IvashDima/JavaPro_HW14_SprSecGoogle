package org.example.springbank.services;

import org.example.springbank.models.Account;
import org.example.springbank.models.Transaction;
import org.example.springbank.repositories.AccountRepository;
import org.example.springbank.repositories.TransactionRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository){
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void addTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }

    @Transactional
    public void deposit(Transaction transaction){
        transactionRepository.save(transaction);

        transaction.getReceiver().deposit(transaction.getAmount());
        accountRepository.save(transaction.getReceiver());
    }

    @Transactional
    public void transfer(Transaction transaction){
        transactionRepository.save(transaction);

        transaction.getSender().withdraw(transaction.getAmount());
        accountRepository.save(transaction.getReceiver());

        transaction.getReceiver().deposit(transaction.getAmount());
        accountRepository.save(transaction.getReceiver());
    }

    @Transactional(readOnly=true)
    public List<Account> findAccounts() {
        return accountRepository.findAll();
    }

    @Transactional(readOnly=true)
    public Account findAccount(long id) {
        return accountRepository.findById(id).get();
    }

    @Transactional(readOnly=true)
    public List<Transaction> findAll(Pageable pageable) {
        return transactionRepository.findAll(pageable).getContent();
    }

    @Transactional(readOnly=true)
    public List<Transaction> findByPattern(String pattern, Pageable pageable) {
        return transactionRepository.findByPattern(pattern, pageable);
    }

    @Transactional(readOnly=true)
    public List<Transaction> findByAnyAccount(Account account, Pageable pageable) {
        return transactionRepository.findByAnyAccount(account, pageable);
    }

    @Transactional(readOnly=true)
    public List<Transaction> findBySenderAccount(Account account, Pageable pageable) {
        return transactionRepository.findBySenderAccount(account, pageable);
    }

    @Transactional(readOnly=true)
    public List<Transaction> findByReceiverAccount(Account account, Pageable pageable) {
        return transactionRepository.findByReceiverAccount(account, pageable);
    }

    @Transactional(readOnly = true)
    public long countBySenderAccount(Account account) {
        return transactionRepository.countBySenderAccount(account);
    }

    @Transactional(readOnly = true)
    public long countByReceiverAccount(Account account) {
        return transactionRepository.countByReceiverAccount(account);
    }

    @Transactional(readOnly = true)
    public long count() {
        return transactionRepository.count();
    }

    public void deleteAllTransactions() {
        transactionRepository.deleteAll();
    }
}
