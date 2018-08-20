package com.tide.test.dataproviders.database;

import com.tide.test.core.entity.AccountNumber;
import com.tide.test.core.entity.Amount;
import com.tide.test.core.entity.Account;
import com.tide.test.core.usercase.store.AccountStore;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.sql.ResultSet;

public class AccountStoreImpl implements AccountStore {

    private JdbcTemplate jdbcTemplate;

    public AccountStoreImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account getAccountByAccountNumber(AccountNumber accountNumber) {

        String sql = "SELECT * FROM ACCOUNT WHERE ID = ?";

        try{
            return jdbcTemplate.queryForObject(sql, (ResultSet rs, int rowNum)->{
                 return  new Account(new AccountNumber(rs.getLong("ID")), rs.getString("NAME"), new Amount( rs.getBigDecimal("AMOUNT")));
            }, accountNumber.getId());
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public Account updateBalance(AccountNumber accountNumber, Amount newBalance) {
        jdbcTemplate.update(
                "UPDATE PAYMENT_SYSTEM.ACCOUNT SET AMOUNT = ? where id = ?",
                newBalance.getAmount(), accountNumber.getId());
        return getAccountByAccountNumber(accountNumber);
    }
}
