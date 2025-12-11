package com.api.api.service;

import com.api.api.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public ProductService() {
        inicializarProductos();
    }

    private void inicializarProductos() {
        crear(new Product(null, "Laptop HP", "Laptop HP 15.6 pulgadas, 8GB RAM, 256GB SSD", new BigDecimal("899.99"), 15));
        crear(new Product(null, "Mouse Logitech", "Mouse inalámbrico Logitech MX Master 3", new BigDecimal("99.99"), 50));
        crear(new Product(null, "Teclado Mecánico", "Teclado mecánico RGB, switches blue", new BigDecimal("79.99"), 30));
        crear(new Product(null, "Monitor Samsung", "Monitor Samsung 27 pulgadas, 144Hz", new BigDecimal("299.99"), 20));
        crear(new Product(null, "Webcam HD", "Webcam 1080p con micrófono integrado", new BigDecimal("49.99"), 40));
    }

    public List<Product> obtenerTodos() {
        return new ArrayList<>(products);
    }

    public Optional<Product> obtenerPorId(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public Optional<Product> editarPrecio(Long id, BigDecimal nuevoPrecio) {
        return obtenerPorId(id).map(product -> {
            product.setPrecio(nuevoPrecio);
            return product;
        });
    }

    public boolean eliminar(Long id) {
        return products.removeIf(p -> p.getId().equals(id));
    }

    private Product crear(Product product) {
        product.setId(counter.incrementAndGet());
        products.add(product);
        return product;
    }
}
