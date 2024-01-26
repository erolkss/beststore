package br.com.boostmytool.beststore.controllers;

import br.com.boostmytool.beststore.models.Product;
import br.com.boostmytool.beststore.models.ProductDto;
import br.com.boostmytool.beststore.services.ProductsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping({"", "/"})
    public String showProductsList(Model model) {
        List<Product> products = productsRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        // If order By desc add in findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("products", products);
        return "products/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "products/CreateProduct";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute ProductDto productDto, BindingResult result) {
        if (productDto.getImageFile().isEmpty()) {
            result.addError(new FieldError("productDto", "imageFile", "The image file is required"));
        }
        if (result.hasErrors()) {
            return "products/CreateProduct";
        }

        // Save image file

        MultipartFile image = productDto.getImageFile();
        Date createAt = new Date();
        String storageFileName = createAt.getTime() + "_" + image.getOriginalFilename();
        try {
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception exception) {
            System.out.println("Exception: " + exception.getMessage());
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCreateAt(createAt);
        product.setImageFileName(storageFileName);

        productsRepository.save(product);

        return "redirect:/products";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam int id) {

        try {
            Product product = productsRepository.findById(id).get();
            model.addAttribute("product", product);

            ProductDto productDto = new ProductDto();
            productDto.setName(product.getName());
            productDto.setBrand(product.getBrand());
            productDto.setCategory(product.getCategory());
            productDto.setPrice(product.getPrice());
            productDto.setDescription(product.getDescription());

            model.addAttribute("productDto", productDto);

        } catch (Exception exception) {
            System.out.println("Exception: " + exception.getMessage());
            return "redirect:/products";
        }

        return "products/EditProduct.html";

    }

    @PostMapping("/edit")
    public String updateProduct(Model model, @RequestParam int id, @Valid @ModelAttribute ProductDto productDto, BindingResult result) {

        try {
            Product product = productsRepository.findById(id).get();
            model.addAttribute("product", product);

            if (result.hasErrors()) {
                return "products/EditProduct";
            }

            if (!productDto.getImageFile().isEmpty()) {
                //delete old image
                String uploadDir = "public/images";
                Path oldImagePath = Paths.get(uploadDir + product.getImageFileName());

                try {
                    Files.delete(oldImagePath);
                } catch (Exception exception) {
                    System.out.println("Exception: " + exception.getMessage());
                }

                //Save new image file
                MultipartFile image = productDto.getImageFile();
                Date createAt = new Date();
                String storageFileName = createAt.getTime() + "_" + image.getOriginalFilename();

                try (InputStream inputStream = image.getInputStream()) {
                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
                }
                product.setImageFileName(storageFileName);
            }

            product.setName(productDto.getName());
            product.setBrand(productDto.getBrand());
            product.setCategory(productDto.getCategory());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());

            productsRepository.save(product);

        } catch (Exception exception) {
            System.out.println("Exception: " + exception.getMessage());
        }

        return "redirect:/products";

    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam int id) {

        try {
            Product product = productsRepository.findById(id).get();

            //delete image product
            Path imagePath = Paths.get("public/images/" + product.getImageFileName());
            try {
                Files.delete(imagePath);
            } catch (Exception exception) {
                System.out.println("Exception: " + exception.getMessage());
            }

            productsRepository.delete(product);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception.getMessage());
        }
        return "redirect:/products";

    }

}
