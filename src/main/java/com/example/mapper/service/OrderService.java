package com.example.mapper.service;

import com.example.mapper.dto.OrderDto;
import com.example.mapper.enums.Status;
import com.example.mapper.exception.ProductEmptyInStock;
import com.example.mapper.model.Customer;
import com.example.mapper.model.Order;
import com.example.mapper.model.Product;
import com.example.mapper.repository.CustomerRepository;
import com.example.mapper.repository.OrderRepository;
import com.example.mapper.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public Order createOrder(OrderDto orderDto) {
        Customer customer = customerRepository.findById(orderDto.getCustomer_id())
                .orElseThrow(()-> new EntityNotFoundException("customer not found id:" + orderDto.getCustomer_id()));

        List<Product> productList = productRepository.findAllById(orderDto.getProducts());
        if (productList.isEmpty()) throw new EntityNotFoundException("Product list not found by id:" + orderDto.getProducts());
        for (Product product: productList) {
            if (product.getQuantityInStock() == 0) {
                throw new ProductEmptyInStock("На складе не хватает товара" + product.getName());
            }
            product.setQuantityInStock(product.getQuantityInStock()-1);
        }
        Order order = new Order();
        order.setCustomerId(customer);
        order.setProducts(productList);
        order.setOrderDate(LocalDateTime.now());
        order.setShippingAddress(orderDto.getShippingAddress());
        order.setTotalPrice(calculateTotalPrice(productList));
        order.setOrderStatus(orderDto.getOrderStatus());

        return orderRepository.save(order);
    }

    private double calculateTotalPrice(List<Product> products) {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public Order getOrder(long id) {
        return orderRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Order not found id:"+id));
    }
}
