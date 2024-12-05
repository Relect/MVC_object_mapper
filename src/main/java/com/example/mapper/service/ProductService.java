package com.example.mapper.service;

import com.example.mapper.model.Product;
import com.example.mapper.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getById(long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Product not found by id:" + id));
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product udpate(long id, Product product) {
        Product newProduct = getById(product.getProductId());
        if (product.getName() != null) newProduct.setName(product.getName());
        if (product.getDescription() != null) newProduct.setDescription(product.getDescription());
        newProduct.setQuantityInStock(product.getQuantityInStock());
        return productRepository.save(newProduct);
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }
}
