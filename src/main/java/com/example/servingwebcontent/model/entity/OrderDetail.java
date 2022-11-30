package com.example.servingwebcontent.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * @author Oksana Borisenko
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "order_detail")
//возможно понадобиться tostring.exlude
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "*Quantity has to be non negative number")
    private int totalQuantity;

    @DecimalMin(value = "0.00", message = "*Price has to be non negative number")
    @Column(name = "total_price", columnDefinition = "Decimal(10,2) default '0.00'")
    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
