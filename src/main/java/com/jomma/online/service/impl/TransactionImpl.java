package com.jomma.online.service.impl;

import com.jomma.online.dto.TransactionDTO;
import com.jomma.online.entity.Product;
import com.jomma.online.entity.Transaction;
import com.jomma.online.repository.TransactionRepository;
import com.jomma.online.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductServiceImpl productService;

    @Override
    public TransactionDTO addTransaction(TransactionDTO transactionDTO, long productId) {
        Product product = this.productService.getProductById(productId);
        Transaction transaction = this.dtoToTransaction(transactionDTO);
        transaction.setProduct(product);
        return this.transactionToDTO(this.transactionRepository.save(transaction));
    }

    @Override
    public List<TransactionDTO> findAllTransactions() {
        return this.transactionRepository
                .findAll()
                .stream()
                .map(this::transactionToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDTO getSingleTransactionById(Long transactionId) {
        return this.transactionToDTO(this.getTransactionById(transactionId));
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        this.transactionRepository.delete(this.getTransactionById(transactionId));
    }

    @Override
    public TransactionDTO updateTransaction(TransactionDTO transactionDTO, Long transactionId) {
        // don't updated
        return null;
    }

    protected Transaction getTransactionById(Long TransactionId) {
        return this.transactionRepository.findById(TransactionId).orElseThrow(() ->
                new RuntimeException("Transaction is not found: " + TransactionId));
    }

    protected Transaction dtoToTransaction(TransactionDTO TransactionDTO) {
        return this.modelMapper.map(TransactionDTO, Transaction.class);
    }

    protected TransactionDTO transactionToDTO(Transaction Transaction) {
        return this.modelMapper.map(Transaction, TransactionDTO.class);
    }

}
