package com.tide.test.core.entity;


import java.util.Date;

public class Transaction {

    private Long id;
    private Amount amountTransferred;
    private Account fromAccount;
    private Account toAccount;
    private Amount newBalanceFromAccount;
    private Amount oldBalanceFromAccount;
    private Amount newBalanceToAccount;
    private Amount oldBalanceToAccount;
    private Date doneOnDate;



    public Transaction(Long id, Amount amountTransferred, Account fromAccount, Account toAccount, Amount newBalanceFromAccount, Amount oldBalanceFromAccount, Amount newBalanceToAccount, Amount oldBalanceToAccount, Date doneOnDate) {
        this.id = id;
        this.amountTransferred = amountTransferred;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.newBalanceFromAccount = newBalanceFromAccount;
        this.oldBalanceFromAccount = oldBalanceFromAccount;
        this.newBalanceToAccount = newBalanceToAccount;
        this.oldBalanceToAccount = oldBalanceToAccount;
        this.doneOnDate = doneOnDate;
    }

    public Transaction(Amount amountTransferred, Account fromAccount, Account toAccount, Amount newBalanceFromAccount, Amount oldBalanceFromAccount, Amount newBalanceToAccount, Amount oldBalanceToAccount, Date doneOnDate) {
        this.amountTransferred = amountTransferred;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.newBalanceFromAccount = newBalanceFromAccount;
        this.oldBalanceFromAccount = oldBalanceFromAccount;
        this.newBalanceToAccount = newBalanceToAccount;
        this.oldBalanceToAccount = oldBalanceToAccount;
        this.doneOnDate = doneOnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Amount getAmountTransferred() {
        return amountTransferred;
    }

    public void setAmountTransferred(Amount amountTransferred) {
        this.amountTransferred = amountTransferred;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    public Amount getNewBalanceFromAccount() {
        return newBalanceFromAccount;
    }

    public void setNewBalanceFromAccount(Amount newBalanceFromAccount) {
        this.newBalanceFromAccount = newBalanceFromAccount;
    }

    public Amount getOldBalanceFromAccount() {
        return oldBalanceFromAccount;
    }

    public void setOldBalanceFromAccount(Amount oldBalanceFromAccount) {
        this.oldBalanceFromAccount = oldBalanceFromAccount;
    }

    public Amount getNewBalanceToAccount() {
        return newBalanceToAccount;
    }

    public void setNewBalanceToAccount(Amount newBalanceToAccount) {
        this.newBalanceToAccount = newBalanceToAccount;
    }

    public Amount getOldBalanceToAccount() {
        return oldBalanceToAccount;
    }

    public void setOldBalanceToAccount(Amount oldBalanceToAccount) {
        this.oldBalanceToAccount = oldBalanceToAccount;
    }

    public Date getDoneOnDate() {
        return doneOnDate;
    }

    public void setDoneOnDate(Date doneOnDate) {
        this.doneOnDate = doneOnDate;
    }
}
