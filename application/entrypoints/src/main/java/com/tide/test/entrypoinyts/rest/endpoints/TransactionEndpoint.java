package com.tide.test.entrypoinyts.rest.endpoints;


import com.tide.test.core.entity.AccountNumber;
import com.tide.test.core.entity.Amount;
import com.tide.test.core.entity.Transaction;
import com.tide.test.core.usercase.DoIntraAccountTransfer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
public class TransactionEndpoint {

    private DoIntraAccountTransfer doIntraAccountTransfer;

    public TransactionEndpoint(DoIntraAccountTransfer doIntraAccountTransfer) {
        this.doIntraAccountTransfer = doIntraAccountTransfer;
    }

    @PostMapping(value = "transactions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> doTransfer(@RequestBody Map<String, Object> body) {
        // can add various validation here
        Transaction transaction = doIntraAccountTransfer.doTransfer(new AccountNumber(new Long(body.get("fromAccountNumber").toString())), new AccountNumber(new Long(body.get("toAccountNumber").toString())), new Amount(new BigDecimal(body.get("amount").toString())));
        // the whole transaction object is returned for now .. this can be restricted further and only reelvant fields can be sent
        return new ResponseEntity<>(transaction,HttpStatus.CREATED);
    }

}

