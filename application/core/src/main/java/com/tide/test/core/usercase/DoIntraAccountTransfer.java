package com.tide.test.core.usercase;

import com.tide.test.core.entity.*;
import com.tide.test.core.usercase.exceptions.AccountNotFoundException;
import com.tide.test.core.usercase.exceptions.InsufficientBalanceException;
import com.tide.test.core.usercase.exceptions.InvalidAmountException;
import com.tide.test.core.usercase.store.AccountStore;
import com.tide.test.core.usercase.store.TransactionStore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

public class DoIntraAccountTransfer {


    private AccountStore accountStore;
    private TransactionStore transactionStore;

    public DoIntraAccountTransfer(AccountStore accountStore, TransactionStore transactionStore) {
        this.accountStore = accountStore;
        this.transactionStore = transactionStore;
    }

    public synchronized Transaction doTransfer(AccountNumber fromAccountNumber , AccountNumber toAccountNumber, Amount amount){

        Account fromAccount = Optional.ofNullable(accountStore.getAccountByAccountNumber(fromAccountNumber))
                .orElseThrow(AccountNotFoundException::new);

        Account toAccount = Optional.ofNullable(accountStore.getAccountByAccountNumber(toAccountNumber))
                .orElseThrow(AccountNotFoundException::new);

        if(amount.getAmount().compareTo(BigDecimal.ZERO) ==0){
            throw new InvalidAmountException();
        }

        Amount oldBalanceFromAccount = new Amount(new BigDecimal(fromAccount.getBalance().getAmount().toString()));
        BigDecimal newBalanceAsBigDecimal = oldBalanceFromAccount.getAmount().subtract(amount.getAmount());
        Amount newBalanceFromAccount;
        if(newBalanceAsBigDecimal.compareTo(BigDecimal.ZERO) < 0){
            throw new InsufficientBalanceException();
        }else{
             newBalanceFromAccount = new Amount (new BigDecimal(newBalanceAsBigDecimal.toString()));
        }

        Amount oldBalanceToAccount = new Amount(new BigDecimal(toAccount.getBalance().getAmount().toString()));
        Amount newBalanceToAccount = new Amount (new BigDecimal(oldBalanceToAccount.getAmount().add(amount.getAmount()).toString()));

        Transaction transaction = new Transaction(amount,fromAccount,toAccount,oldBalanceFromAccount,newBalanceFromAccount,oldBalanceToAccount,newBalanceToAccount,new Date());

        transaction = transactionStore.transfer(fromAccount,toAccount,newBalanceFromAccount,newBalanceToAccount,transaction);
        return transaction;
    }




}
