package com.tide.test.core.usercase.store;

import com.tide.test.core.entity.Account;
import com.tide.test.core.entity.AccountNumber;
import com.tide.test.core.entity.Amount;
import com.tide.test.core.entity.Transaction;

import java.util.List;

public interface AccountStore {

    Account getAccountByAccountNumber(AccountNumber accountNumber);

    Account updateBalance (AccountNumber accountNumber , Amount newBalance);


}
