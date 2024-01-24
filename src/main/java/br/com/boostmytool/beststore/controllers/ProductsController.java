package br.com.boostmytool.beststore.controllers;

import br.com.boostmytool.beststore.services.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductsRepository productsRepository;
}
