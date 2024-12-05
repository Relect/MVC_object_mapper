package com.example.mapper.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotBlank(message = "название продукта не должно быть пустым")
    private String name;
    @NotBlank(message = "описание не должно быть пустым")
    private String description;
    @NotNull(message = "цена должна быть указана")
    @Positive(message = "цена должна быть положительной")
    private Double price;
    @NotNull(message = "количество товара должно быть указано")
    @Positive(message = "количество товара должно быть положительным")
    private volatile Integer quantityInStock;

}
