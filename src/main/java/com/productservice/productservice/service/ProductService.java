package com.productservice.productservice.service;

import com.productservice.productservice.dto.GenericProductDTO;

import java.util.List;

public interface ProductService {
    public List<GenericProductDTO> getProducts();
    public GenericProductDTO getProductById(Long id);
    public GenericProductDTO createProduct(GenericProductDTO product);
    public GenericProductDTO deleteProduct(Long id);
    public GenericProductDTO updateProductById(Long id, GenericProductDTO product);
}
