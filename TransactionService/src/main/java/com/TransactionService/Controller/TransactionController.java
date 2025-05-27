package com.TransactionService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TransactionService.DTO.TransactionRequest;
import com.TransactionService.DTO.TransactionResponse;
import com.TransactionService.Model.Transaction;
import com.TransactionService.Service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	@Autowired
    private TransactionService transactionService;


    @PostMapping("/make")
    public ResponseEntity<Transaction> makeTransaction(@RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.makeTransaction(request);
        return ResponseEntity.ok(transaction);
    }
    
    @GetMapping("/history/email/{email}")
    public ResponseEntity<List<TransactionResponse>> getUserHistoryByEmail(@PathVariable("email") String email) {
        List<TransactionResponse> transactions = transactionService.getUserHistoryByEmail(email);
        return ResponseEntity.ok(transactions);
    }
}
