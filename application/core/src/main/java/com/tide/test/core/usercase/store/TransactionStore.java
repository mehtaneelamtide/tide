package com.tide.test.core.usercase.store;

import com.tide.test.core.entity.Account;
import com.tide.test.core.entity.Amount;
import com.tide.test.core.entity.Transaction;

public interface TransactionStore {

    Transaction transfer(Account fromAccount , Account toAccount ,Amount newFromAccountBalance ,Amount newToAccountBalance,Transaction creditTransaction);
}
