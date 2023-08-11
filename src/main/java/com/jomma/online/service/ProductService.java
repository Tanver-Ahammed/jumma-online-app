package com.jomma.online.service;

import com.jomma.online.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO addProduct(ProductDTO productDTO);

    List<ProductDTO> findAllProducts();

    ProductDTO getSingleProductById(Long productId);

    void deleteProduct(Long productId);

    ProductDTO updateProduct(ProductDTO productDTO, Long productId);

}
