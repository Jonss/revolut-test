package application.services;

import domain.exceptions.BalanceException;
import domain.models.Account;
import domain.models.Balance;
import domain.models.Transaction;
import infrastructure.repositories.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import stubs.AccountStub;

import java.util.List;

import static domain.models.Currency.BRL;
import static domain.models.Operation.TRANSFER;
import static domain.models.Operation.WITHDRAWAL;
import static java.time.LocalDateTime.now;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountService accountService;

    @Mock
    private BalanceService balanceService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldThrowExceptionWhenBalanceIsBelowAmount() {
        Account origin = new AccountStub().build().get();
        Account destiny = new AccountStub().build().get();

        when(balanceService.findBalance(origin, BRL)).thenReturn(new Balance(3000L, now(), origin.getId(), BRL));

        assertThrows(BalanceException.class, () ->
                transactionService.transfer(3001L, origin, destiny, BRL)
        );
    }

    @Test
    public void shouldTransferToDestinyAccountWhenAccountHasBalance() {
        Account origin = new AccountStub().build().get();
        Account destiny = new AccountStub().build().get();

        when(balanceService.findBalance(origin, BRL)).thenReturn(new Balance(3000L, now(), origin.getId(), BRL));

        List<Transaction> transactions = transactionService.transfer(1000L, origin, destiny, BRL);

        assertEquals(BRL, transactions.get(0).getCurrency());
        assertEquals(WITHDRAWAL, transactions.get(0).getOperation());
        assertEquals(Long.valueOf(-1000), transactions.get(0).getAmount());
        assertEquals(BRL, transactions.get(1).getCurrency());
        assertEquals(TRANSFER, transactions.get(1).getOperation());
        assertEquals(Long.valueOf(1000), transactions.get(1).getAmount());
    }

}