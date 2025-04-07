package org.example.springbank.services;

import org.example.springbank.models.Account;
import org.example.springbank.models.Client;
import org.example.springbank.repositories.AccountRepository;
import org.example.springbank.repositories.ClientRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public AccountService(AccountRepository accountRepository, ClientRepository clientRepository){
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional
    public void addAccount(Account account){accountRepository.save(account);}

//    @Transactional
//    public void addClient(Client client){clientRepository.save(client);}

    @Transactional(readOnly=true)
    public List<Client> findClients() {
        return clientRepository.findAll();
    }

    @Transactional(readOnly=true)
    public List<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable).getContent();
    }

    @Transactional(readOnly=true)
    public Account findById(long id) {
        return accountRepository.findById(id).get();
    }

    @Transactional(readOnly=true)
    public List<Account> findByClient(Client client, Pageable pageable) {
        return accountRepository.findByClient(client, pageable);
    }

    @Transactional(readOnly=true)
    public List<Account> findByPattern(String pattern, Pageable pageable) {
        return accountRepository.findByPattern(pattern, pageable);
    }

    @Transactional(readOnly = true)
    public long countByClient(Client client) {
        return accountRepository.countByClient(client);
    }

    @Transactional(readOnly = true)
    public long count() {
        return accountRepository.count();
    }

    @Transactional(readOnly=true)
    public Client findClient(long id) {
        return clientRepository.findById(id).get();
    }

    public void deleteAllAccounts() {
        accountRepository.deleteAll();
    }
}
