package br.com.boostmytool.beststore.controllers;

import br.com.boostmytool.beststore.models.Product;
import br.com.boostmytool.beststore.services.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping({"","/"})
    public String showProductsList(Model model) {
        List<Product> products = productsRepository.findAll();
        // If order By desc add in findAll(Sort.by(Sort.Direction.DESC, "id));
        model.addAttribute("products", products);
        return "products/index";
    }
}
