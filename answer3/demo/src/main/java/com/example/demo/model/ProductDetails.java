package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;

@Entity
@Table(name="products")
@Getter
@Setter
@NoArgsConstructor
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private double price;
    private int stock;
    private Object attributes;

    public ProductDetails(String name, double price, int stock, Object attributes) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.attributes = attributes;
    }

}
