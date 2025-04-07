package org.example.springbank.controllers;

import org.example.springbank.enums.CurrencyType;
import org.example.springbank.enums.TransactionType;
import org.example.springbank.models.Account;
import org.example.springbank.models.Client;
import org.example.springbank.models.Transaction;
import org.example.springbank.services.AccountService;
import org.example.springbank.services.DemoDataService;
import org.example.springbank.services.TransactionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TransactionController {
    static final int ITEMS_PER_PAGE = 10;

    static final int DEFAULT_ACCOUNT_ID = -1;

    private final TransactionService transactionService;
    private final DemoDataService demoDataService;

    public TransactionController(TransactionService transactionService, DemoDataService demoDataService) {
        this.transactionService = transactionService;
        this.demoDataService = demoDataService;
    }

    @GetMapping("/transaction/")
    public String index(Model model,
            @RequestParam(required = false, defaultValue = "0") Integer page)
    {
        if (page < 0) page = 0;

        List<Transaction> transactions = transactionService
                .findAll(PageRequest.of(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));

        model.addAttribute("transactions", transactions);
        model.addAttribute("allPages", getPageCount());
        return "/transaction/index";
    }

    @GetMapping("/transaction/account/{id}")
    public String listTransaction(
            @PathVariable(value = "id") long accountId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Model model)
    {
        Account account = (accountId != DEFAULT_ACCOUNT_ID) ? transactionService.findAccount(accountId) : null;
        if (page < 0) page = 0;

        List<Transaction> transactions = transactionService
                .findByAnyAccount(account, PageRequest.of(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));

        model.addAttribute("accounts", transactionService.findAccounts());
        model.addAttribute("transactions", transactions);
        model.addAttribute("byAccountPages", getPageCount(account));
        model.addAttribute("accountId", accountId);

        return "transaction/index";
    }

    @GetMapping("/transaction/deposit_page/{id}")
    public String transactionDepositPage(Model model,
                                         @PathVariable(value = "id") long accountId) {
        model.addAttribute("accounts", transactionService.findAccounts());
        model.addAttribute("account", transactionService.findAccount(accountId));
        return "transaction/deposit_page";
    }

    @PostMapping(value="/transaction/deposit")
    public String transactionDeposit(@RequestParam(value = "fromaccount") long accountId,
                             @RequestParam double amount)
    {
        Account account = (accountId != DEFAULT_ACCOUNT_ID) ? transactionService.findAccount(accountId) : null;

        Transaction transaction = new Transaction(account, account, amount, TransactionType.deposit);
        transactionService.deposit(transaction);

        return "redirect:/transaction/account/" + accountId;
    }

    @GetMapping("/transaction/transfer_page/{id}")
    public String transactionTransferPage(Model model,
                                         @PathVariable(value = "id") long accountId) {
        model.addAttribute("accounts", transactionService.findAccounts());
        model.addAttribute("account", transactionService.findAccount(accountId));
        return "transaction/transfer_page";
    }

    @PostMapping(value="/transaction/transfer")
    public String transactionTransfer(@RequestParam(value = "fromaccount") long fromAccountId,
                                      @RequestParam(value = "toaccount") long toAccountId,
                                      @RequestParam double amount)
    {
        Account fromAccount = (fromAccountId != DEFAULT_ACCOUNT_ID) ? transactionService.findAccount(fromAccountId) : null;
        Account toAccount = (toAccountId != DEFAULT_ACCOUNT_ID) ? transactionService.findAccount(toAccountId) : null;

        Transaction transaction = new Transaction(fromAccount, toAccount, amount, TransactionType.transfer);
        transactionService.transfer(transaction);

        return "redirect:/transaction/account/" + fromAccountId;
    }

    @GetMapping("/transaction/reset")
    public String resetDemoData() {
        demoDataService.generateDemoData();
        return "redirect:/transaction/";
    }

    @PostMapping(value = "/transaction/search")
    public String search(@RequestParam String pattern, Model model) {
        model.addAttribute("accounts", transactionService.findAccounts());
        model.addAttribute("transactions", transactionService.findByPattern(pattern, null));

        return "transaction/index";
    }

    private long getPageCount() {
        long totalCount = transactionService.count();
        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

    private long getPageCount(Account account) {
        long totalCount = transactionService.countByReceiverAccount(account);
        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
    }
}
