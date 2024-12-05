package com.example.mapper.exception;

public class ProductEmptyInStock extends RuntimeException {
    public ProductEmptyInStock(String message) {
        super(message);
    }
}
