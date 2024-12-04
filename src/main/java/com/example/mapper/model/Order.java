package com.example.mapper.model;

import com.example.mapper.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Customer customer;
    private List<Product> products;
    private LocalDateTime orderDate;
    private String shippingAddress;
    private double totalPrice;
    private Status orderStatus;
}
