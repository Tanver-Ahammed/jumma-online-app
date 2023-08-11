package com.jomma.online.service.impl;

import com.jomma.online.dto.ProductDTO;
import com.jomma.online.entity.Product;
import com.jomma.online.repository.ProductRepository;
import com.jomma.online.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = this.dtoToProduct(productDTO);
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currDate = sdf.format(currentDate);
        product.setYear_to_date(currDate);
        return this.productToDTO(this.productRepository.save(product));
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        return this.productRepository
                .findAll()
                .stream()
                .map(this::productToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getSingleProductById(Long productId) {
        return this.productToDTO(this.getProductById(productId));
    }

    @Override
    public void deleteProduct(Long productId) {
        this.productRepository.delete(this.getProductById(productId));
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Long productId) {
        Product product = this.getProductById(productId);
        product.setProduct_name(productDTO.getProduct_name());
        product.setProduct_code(productDTO.getProduct_code());
        product.setUnit_price(productDTO.getUnit_price());
        product.setGain_loss(productDTO.getGain_loss());
        product.setYear_to_date(productDTO.getYear_to_date());
        product.setActive_status(productDTO.getActive_status());
        return this.productToDTO(this.productRepository.save(product));
    }

    protected Product getProductById(Long productId) {
        return this.productRepository.findById(productId).orElseThrow(() ->
                new RuntimeException("Product is not found: " + productId));
    }

    protected Product dtoToProduct(ProductDTO productDTO) {
        return this.modelMapper.map(productDTO, Product.class);
    }

    protected ProductDTO productToDTO(Product product) {
        return this.modelMapper.map(product, ProductDTO.class);
    }

}
