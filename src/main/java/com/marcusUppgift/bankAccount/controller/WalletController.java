package com.marcusUppgift.bankAccount.controller;

import com.marcusUppgift.bankAccount.models.Transaction;
import com.marcusUppgift.bankAccount.service.WalletService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    
    @Autowired
    private WalletService walletService;
    
    @GetMapping("/balance/{accountId}")
    public ResponseEntity<Double> getBalance(@PathVariable String accountId) {
        double balance = walletService.getBalance(accountId);
        return ResponseEntity.ok(balance);
    }
    
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(
            @RequestParam String accountId,
            @RequestParam double amount) {
        String message = walletService.transfer(accountId, amount);
        return ResponseEntity.ok(message);
    }
    
    @GetMapping("/transactions/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable String accountId) {
        List<Transaction> transactions = walletService.getTransactions(accountId);
        return ResponseEntity.ok(transactions);
    }
    
    @PostMapping("/create-account")
    public ResponseEntity<String> createAccount(@RequestParam String accountId) {
        String message = walletService.createAccount(accountId);
        return ResponseEntity.ok(message);
    }
    
    @GetMapping("/test")
    public String testar(){
        return "hello world!";
    }
}
