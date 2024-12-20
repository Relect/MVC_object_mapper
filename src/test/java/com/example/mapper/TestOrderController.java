package com.example.mapper;

import com.example.mapper.controller.OrderController;
import com.example.mapper.dto.OrderDto;
import com.example.mapper.enums.Status;
import com.example.mapper.model.Customer;
import com.example.mapper.model.Order;
import com.example.mapper.model.Product;
import com.example.mapper.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestOrderController {

    @Mock
    private OrderService service;
    @InjectMocks
    private OrderController controller;
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getOrderByIdTest() throws Exception {
        Order order = new Order();
        Customer customer = new Customer(1L, "Ivan", "Ivanov", "Ivan@email.ru", "+7999-123-45-67");
        order.setCustomerId(customer);
        Product product = new Product(1L, "product1", "good product", 10.0, 2);
        order.setProducts(List.of(product));
        order.setOrderDate(LocalDateTime.now());
        order.setShippingAddress("rollstreet, house 2");
        order.setTotalPrice(10.0);
        order.setOrderStatus(Status.COMPLETED);
        Mockito.when(service.getOrder(1L)).thenReturn(order);

        mockMvc.perform(MockMvcRequestBuilders.get("/order/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId.customerId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[0].productId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products[0].name").value("product1"));
    }

    @Test
    public void addOrderTest() throws Exception {
        OrderDto orderDto = new OrderDto();
        mockMvc.perform(MockMvcRequestBuilders.post("/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDto)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
