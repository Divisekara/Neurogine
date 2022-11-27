package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;

@Getter
@Setter
@ToString
public class ProductInfo {
    private long id;
    private String name;
    private Double price;
    private int stock;
    private HashMap<String, String> attributes;
}
