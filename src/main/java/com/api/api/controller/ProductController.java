package com.api.api.controller;

import com.api.api.model.Product;
import com.api.api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
@Tag(name = "Productos", description = "API para gestión del catálogo de productos")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Listar productos",
               description = "Retorna la lista completa de productos del catálogo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<Product>> listarProductos() {
        return ResponseEntity.ok(productService.obtenerTodos());
    }

    @Operation(summary = "Obtener producto por ID",
               description = "Retorna el detalle de un producto específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Product> obtenerProducto(
            @Parameter(description = "ID del producto")
            @PathVariable Long id) {
        return productService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Editar precio de producto",
               description = "Actualiza el precio de un producto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Precio actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PatchMapping("/{id}/precio")
    public ResponseEntity<Product> editarPrecio(
            @Parameter(description = "ID del producto")
            @PathVariable Long id,
            @Parameter(description = "Nuevo precio del producto")
            @RequestBody Map<String, BigDecimal> precio) {
        return productService.editarPrecio(id, precio.get("precio"))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar producto",
               description = "Elimina un producto del catálogo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(
            @Parameter(description = "ID del producto a eliminar")
            @PathVariable Long id) {
        if (productService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
