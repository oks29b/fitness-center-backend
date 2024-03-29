package com.example.servingwebcontent.model.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * @author Oksana Borisenko
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(name = "quantity")
    @Min(value = 0, message = "*Quantity has to be non negative number")
    private Integer currentQuantity;

    @DecimalMin(value = "0.00", message = "*Price has to be non negative number")
    private BigDecimal price;

}
