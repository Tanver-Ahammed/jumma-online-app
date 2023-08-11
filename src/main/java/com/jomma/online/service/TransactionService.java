package com.jomma.online.service;

import com.jomma.online.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {

    TransactionDTO addTransaction(TransactionDTO transactionDTO, long productId);

    List<TransactionDTO> findAllTransactions();

    TransactionDTO getSingleTransactionById(Long transactionId);

    void deleteTransaction(Long transactionId);

    TransactionDTO updateTransaction(TransactionDTO transactionDTO, Long transactionId);

}
