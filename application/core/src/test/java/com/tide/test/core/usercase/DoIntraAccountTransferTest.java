package com.tide.test.core.usercase;

import com.tide.test.core.entity.Account;
import com.tide.test.core.entity.AccountNumber;
import com.tide.test.core.entity.Amount;
import com.tide.test.core.usercase.exceptions.AccountNotFoundException;
import com.tide.test.core.usercase.exceptions.InsufficientBalanceException;
import com.tide.test.core.usercase.exceptions.InvalidAmountException;
import com.tide.test.core.usercase.store.AccountStore;
import com.tide.test.core.usercase.store.TransactionStore;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

public class DoIntraAccountTransferTest {

    private static AccountStore accountStore;
    private static TransactionStore transactionStore;
    private static DoIntraAccountTransfer doIntraAccountTransfer;

    @Before
    public  void setUp(){
        accountStore = Mockito.mock(AccountStore.class);
        transactionStore = Mockito.mock(TransactionStore.class);
        doIntraAccountTransfer = new DoIntraAccountTransfer(accountStore,transactionStore);
    }

    @Test(expected = AccountNotFoundException.class)
    public void givenFromAccountDoesNotExist_WhenTransferRequest_ThenException(){
        givenAccountDoesNotExist();
        doIntraAccountTransfer.doTransfer(new AccountNumber(1L), new AccountNumber(2l), new Amount(BigDecimal.TEN));
    }

    @Test(expected = InvalidAmountException.class)
    public void givenTransferAmountIsZero_WhenTransferRequest_ThenException(){
        givenFromAccountHasSomeBalance();
        doIntraAccountTransfer.doTransfer(new AccountNumber(3L), new AccountNumber(2l), new Amount(BigDecimal.ZERO));
    }

    @Test(expected = AccountNotFoundException.class)
    public void givenToAccountDoesNotExist_WhenTransferRequest_ThenException(){
        givenAccountDoesNotExist();
        doIntraAccountTransfer.doTransfer(new AccountNumber(2L), new AccountNumber(1l), new Amount(BigDecimal.TEN));
    }

    @Test(expected = InsufficientBalanceException.class)
    public void givenAccountHasInsufficientBalance__WhenTransferRequest_ThenException(){
        givenFromAccountHasSomeBalance();
        doIntraAccountTransfer.doTransfer(new AccountNumber(3L), new AccountNumber(2l), new Amount(BigDecimal.TEN));
    }

    @Test
    public void givenAccountHasBalance__WhenTransferRequest_ThenException(){
        givenFromAccountHasSomeBalance();
        doIntraAccountTransfer.doTransfer(new AccountNumber(3L), new AccountNumber(2l), new Amount(BigDecimal.ONE));
    }

    void givenAccountDoesNotExist(){
        when(accountStore.getAccountByAccountNumber(new AccountNumber(1L))).thenReturn(null);
        when(accountStore.getAccountByAccountNumber(new AccountNumber(2l))).thenReturn(new Account(new AccountNumber(2l),"user-2", new Amount(BigDecimal.TEN)));
    }


    void givenFromAccountHasSomeBalance(){
        when(accountStore.getAccountByAccountNumber(new AccountNumber(3l))).thenReturn(new Account(new AccountNumber(3l),"user-3", new Amount(BigDecimal.ONE)));
        when(accountStore.getAccountByAccountNumber(new AccountNumber(2l))).thenReturn(new Account(new AccountNumber(2l),"user-2", new Amount(BigDecimal.TEN)));
    }

}
