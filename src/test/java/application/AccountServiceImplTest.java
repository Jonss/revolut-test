package application;

import application.exceptions.EntityNotFoundException;
import application.services.AccountServiceImpl;
import domain.models.Account;
import infrastructure.repositories.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import stubs.AccountStub;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


public class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindAccountByEmail() {
        when(accountRepository.findAccountByEmail("email@email.com"))
                .thenReturn(new AccountStub().regularAccount());

        Optional<Account> optionalAccount = accountService.findAccount("email@email.com");
        assertTrue(optionalAccount.isPresent());

        Account account = optionalAccount.get();

        assertThat(account.getEmail(), is("email@email.com"));
        assertThat(account.getFullName(), is("My FullName"));
        assertThat(account.getNickName(), is("full"));
        assertThat(account.getPhoneNumber(), is("+55 11 999999999"));
    }


    @Test
    public void shouldThrowExceptionWhenAccountIsNotFound() {
        when(accountService.findAccount(Mockito.any())).thenThrow(EntityNotFoundException.class);

        assertThrows(EntityNotFoundException.class,
                () -> accountService.findAccount("email@email.com")
        );
    }

}
