package com.example.mapper.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long costomerId;

    private String firstName;
    private String lastName;
    @Email(message = "Неверный формат почты")
    private String email;
    private String contactNumber;
}
