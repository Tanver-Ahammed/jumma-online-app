package com.jomma.online.controller;

import com.jomma.online.dto.TransactionDTO;
import com.jomma.online.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/transaction")
@CrossOrigin("http://localhost:4200/")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(path = "/{productId}/add")
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody TransactionDTO transactionDTO,
                                                         @PathVariable("productId") Long productId) {
        transactionDTO = this.transactionService.addTransaction(transactionDTO, productId);
        return new ResponseEntity<>(transactionDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> allTransactions = this.transactionService.findAllTransactions();
        return ResponseEntity.ok(allTransactions);
    }

    @GetMapping("/id/{transactionId}")
    public ResponseEntity<TransactionDTO> getSingleUserById(@PathVariable("transactionId") Long transactionId) {
        return ResponseEntity.ok(this.transactionService.getSingleTransactionById(transactionId));
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<String> deleteUser(@PathVariable("transactionId") Long transactionId) {
        this.transactionService.deleteTransaction(transactionId);
        return new ResponseEntity<>("Transaction deleted successfully.",
                HttpStatus.OK);
    }

    @PutMapping("/{transactionId}")
    private ResponseEntity<TransactionDTO> updateTransaction(@RequestBody TransactionDTO transactionDTO,
                                                             @PathVariable("transactionId") Long transactionId) {
        TransactionDTO updateTransaction = this.transactionService.updateTransaction(transactionDTO, transactionId);
        return ResponseEntity.ok(updateTransaction);
    }

}
