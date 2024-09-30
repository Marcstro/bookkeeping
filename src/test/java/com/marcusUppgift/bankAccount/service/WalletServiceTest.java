package com.marcusUppgift.bankAccount.service;

import com.marcusUppgift.bankAccount.Repository.AccountRepository;
import com.marcusUppgift.bankAccount.Repository.TransactionRepository;
import com.marcusUppgift.bankAccount.models.Account;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class WalletServiceTest {
    
    @Mock
    private AccountRepository accountRepository;
    
    @Mock
    private TransactionRepository transactionRepository;
   

    @InjectMocks
    private WalletService walletService;

    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void getAccount_withValidParameters_returnsAccount() {
        String accountId = "123";
        Account mockAccount = new Account(accountId, 100.0);
        when(accountRepository.findByAccountId(accountId)).thenReturn(Optional.of(mockAccount));

        Account result = walletService.getAccount(accountId);

        assertNotNull(result);
        assertEquals(accountId, result.getAccountId());
        assertEquals(100.0, result.getBalance());
    }
    
    @Test
    void getBalance_withValidParameters_returnsAccountBalance() {
        String accountId = "123";
        Account mockAccount = new Account(accountId, 100.0);
        when(accountRepository.findByAccountId(accountId)).thenReturn(Optional.of(mockAccount));

        double balance = walletService.getBalance(accountId);

        assertEquals(100.0, balance);
    }
    
    @Test
    void transfer_withNewAccountAndNegativeAmount_shouldNotMakeAnyTransaction(){
        String accountId = "1";
        Double negativeAmount = -50.0;
        when(accountRepository.findByAccountId(accountId)).thenReturn(Optional.empty());
        
        String response = walletService.transfer(accountId, negativeAmount);
        
        assertEquals(response, "Cannot create account with negative balance");
        verifyNoInteractions(transactionRepository);
    }
}
