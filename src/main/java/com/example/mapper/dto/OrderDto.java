package com.example.mapper.dto;

import com.example.mapper.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    @NotNull(message = "id покупателя дожен быть указан")
    private Long customer_id;
    @Size(min = 1, message = "список продуктов не должен быть пустым")
    private List<Long> products;
    @NotBlank(message = "адрес не должен быть пустым")
    private String shippingAddress;
    @NotNull(message = "укажите статус заказа")
    private Status orderStatus;

}
