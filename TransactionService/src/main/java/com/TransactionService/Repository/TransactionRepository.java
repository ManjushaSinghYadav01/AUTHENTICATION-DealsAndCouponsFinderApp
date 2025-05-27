package com.TransactionService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TransactionService.DTO.TransactionResponse;
import com.TransactionService.Model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

	List<TransactionResponse> findByEmail(String email);

	
}
