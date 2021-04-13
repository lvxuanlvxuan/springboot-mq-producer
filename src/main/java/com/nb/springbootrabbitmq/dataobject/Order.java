package com.nb.springbootrabbitmq.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author: nb
 * @Date: 2021/3/20 21:07
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String id;
    private String productName;
    private BigDecimal price;
    private Integer num;
    private BigDecimal totalAmt;
}
