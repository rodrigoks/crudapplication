package br.com.jbusiness.crudapplication.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jbusiness.crudapplication.domain.product.Product;
import br.com.jbusiness.crudapplication.domain.product.ProductRepository;
import br.com.jbusiness.crudapplication.domain.product.RequestProduct;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        var allProducts = repository.findAll();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity<Product> registerProduct(@RequestBody @Valid RequestProduct data) {
        Product newProduct = new Product(data);
        newProduct = repository.save(newProduct);
        return ResponseEntity.ok(newProduct);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid RequestProduct data) {
        Optional<Product> optionalProduct = this.repository.findById(data.id());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(data.name());
            product.setPriceInCents(data.priceInCents());
            this.repository.save(product);
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteProduct(@PathVariable String id) {
        Optional<Product> optionalProduct = this.repository.findById(id);
        if (optionalProduct.isPresent()) {
            this.repository.delete(optionalProduct.get());
            return ResponseEntity.ok("delete de produto realizado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
