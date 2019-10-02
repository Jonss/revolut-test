package application.services;

import domain.exceptions.BalanceException;
import domain.models.Account;
import domain.models.Balance;
import infrastructure.repositories.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import stubs.AccountStub;

import static domain.models.Currency.BRL;
import static java.time.LocalDateTime.now;
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
        Account origin = new AccountStub().regularAccount().get();
        Account destiny = new AccountStub().regularAccount().get();

        when(balanceService.findBalance(origin, BRL)).thenReturn(new Balance(3000L, now(), origin, BRL));

        assertThrows(BalanceException.class, () ->
                transactionService.transfer(3001L, origin, destiny, BRL)
        );

    }


}