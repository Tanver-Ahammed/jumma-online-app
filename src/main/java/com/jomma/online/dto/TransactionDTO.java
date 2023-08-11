package com.jomma.online.dto;

import com.jomma.online.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private long transaction_id;

    private int user_id;

    private Product product;

    private int order_amount;

    private Date created_on;

}
