package com.jomma.online.controller;

import com.jomma.online.dto.ProductDTO;
import com.jomma.online.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/product")
@CrossOrigin("http://localhost:4200/")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping(path = "/add")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        productDTO = this.productService.addProduct(productDTO);
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> allProducts = this.productService.findAllProducts();
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/id/{productId}")
    public ResponseEntity<ProductDTO> getSingleUserById(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(this.productService.getSingleProductById(productId));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteUser(@PathVariable("productId") Long productId) {
        this.productService.deleteProduct(productId);
        return new ResponseEntity<>("Product deleted successfully.",
                HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    private ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO,
                                                     @PathVariable("productId") Long productId) {
        ProductDTO updateProduct = this.productService.updateProduct(productDTO, productId);
        return ResponseEntity.ok(updateProduct);
    }

}
