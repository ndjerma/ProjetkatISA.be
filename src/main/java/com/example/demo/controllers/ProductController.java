package com.example.demo.controllers;

import com.example.demo.models.ProductModel;
import com.example.demo.services.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
    private final IProductService productService;

    // Vraca LISTU svih filmova
    @GetMapping("get-list")
    public List<ProductModel> getList() { return productService.findAll(); }

    // Vraca film koji smo trazili PO NASLOVU
    @GetMapping("get-one")
    public ProductModel getOne(@RequestBody Map<String, String> body) {
        String title = body.get("title");
        return productService.findByTitle(title);
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody @Valid ProductModel product) {
        return ResponseEntity.ok(productService.create(product));
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody @Valid ProductModel product) {
        return ResponseEntity.ok(productService.update(product));
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(Integer productId) {
        productService.delete(productId);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
