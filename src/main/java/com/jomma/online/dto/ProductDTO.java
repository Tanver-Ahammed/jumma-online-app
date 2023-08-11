package com.jomma.online.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private long product_id;

    private String product_name;

    private String product_code;

    private int unit_price;

    private int gain_loss;

    private String year_to_date;

    private int active_status;

}
