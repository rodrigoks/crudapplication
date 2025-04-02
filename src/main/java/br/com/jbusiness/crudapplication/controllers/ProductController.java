package br.com.jbusiness.crudapplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    public ResponseEntity getAllProducts() {
        return ResponseEntity.ok().build();
    }

}
