package com.marcusUppgift.bankAccount.service;

import com.marcusUppgift.bankAccount.Repository.AccountRepository;
import com.marcusUppgift.bankAccount.Repository.TransactionRepository;
import com.marcusUppgift.bankAccount.models.Account;
import com.marcusUppgift.bankAccount.models.Transaction;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
        
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Account getAccount(String accountId) {
        return accountRepository.findByAccountId(accountId).orElse(null);
    }

    public double getBalance(String accountId) {
        Account account = getAccount(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        return account.getBalance();
    }
    
    public String transfer(String accountId, double amount) {
        Account account = getAccount(accountId);

        if (account == null && amount < 0) {
            return "Cannot create account with negative balance";
        }
        
        else if (account == null && amount > 0) {
            account = new Account(accountId, amount);
            accountRepository.save(account); 

            Transaction transaction = new Transaction(account, amount, LocalDateTime.now(),
                    amount > 0 ? "DEPOSIT" : "WITHDRAWAL", account.getBalance());
            transactionRepository.save(transaction);

            return "Created new account and transferred amount";
        } 
        
        if ((account.getBalance() + amount) < 0) {
            return "Cannot withdraw more than account has in balance";
        }

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction(account, amount, LocalDateTime.now(),
                amount > 0 ? "DEPOSIT" : "WITHDRAWAL", account.getBalance());
        transactionRepository.save(transaction);

        return "Transfer successful";
    }

    public List<Transaction> getTransactions(String accountId) {
        return transactionRepository.findByAccount_AccountId(accountId);
    }
    
    public String createAccount(String accountId) {
        if (getAccount(accountId) != null) {
            return "Account already exists";
        }

        Account newAccount = new Account(accountId, 0.0);
        accountRepository.save(newAccount);
        return "Account created successfully";
    }
}
