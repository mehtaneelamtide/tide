package com.tide.test.dataproviders.database;

import com.tide.test.core.entity.Account;
import com.tide.test.core.entity.Amount;
import com.tide.test.core.entity.Transaction;
import com.tide.test.core.usercase.store.AccountStore;
import com.tide.test.core.usercase.store.TransactionStore;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class TransactionStoreImpl implements TransactionStore {
    private JdbcTemplate jdbcTemplate;
    private AccountStore accountStore;

    public TransactionStoreImpl(JdbcTemplate jdbcTemplate, AccountStore accountStore) {
        this.jdbcTemplate = jdbcTemplate;
        this.accountStore = accountStore;
    }

    @Override
    @Transactional
    public Transaction transfer(Account fromAccount, Account toAccount, Amount newFromAccountBalance, Amount newToAccountBalance, Transaction transaction) {

        accountStore.updateBalance(fromAccount.getAccountNumber(),newFromAccountBalance);
        accountStore.updateBalance(toAccount.getAccountNumber(), newToAccountBalance);
        Transaction savedTransaction = addTransaction(transaction);
        return savedTransaction;
    }

    private Transaction addTransaction(Transaction transaction) {
        String sql = "INSERT INTO PAYMENT_SYSTEM.TRANSACTION (ID, FROM_ACCOUNT_ID,TO_ACCOUNT_ID, AMOUNT,OLD_BALANCE_FROM_ACCOUNT,NEW_BALANCE_FROM_ACCOUNT,OLD_BALANCE_TO_ACCOUNT,NEW_BALANCE_TO_ACCOUNT,DONE_ON)" +
                " VALUES (PAYMENT_SYSTEM.TRANSACTION_ID_SEQ.nextval, ?,  ?, ?,?,?,?,?,?);";
        KeyHolder key = new GeneratedKeyHolder();

        jdbcTemplate.update((connection) -> {
            final PreparedStatement ps = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, transaction.getFromAccount().getAccountNumber().getId());
            ps.setLong(2, transaction.getToAccount().getAccountNumber().getId());
            ps.setBigDecimal(3, transaction.getAmountTransferred().getAmount());
            ps.setBigDecimal(4, transaction.getOldBalanceFromAccount().getAmount());
            ps.setBigDecimal(5, transaction.getNewBalanceFromAccount().getAmount());
            ps.setBigDecimal(6, transaction.getOldBalanceToAccount().getAmount());
            ps.setBigDecimal(7, transaction.getNewBalanceToAccount().getAmount());
            ps.setDate(8, new java.sql.Date(transaction.getDoneOnDate().getTime()));
            return ps;
        }, key);
        // it is a short cut taken
        transaction.setId(key.getKey().longValue());
        return transaction;
    }
}
