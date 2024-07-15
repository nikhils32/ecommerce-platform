package com.productservice.productservice.controller;

import com.productservice.productservice.dto.GenericProductDTO;
import com.productservice.productservice.exception.ProductException;
import com.productservice.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    // @Autowired --> optional in new versions of Spring
    ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id) throws ProductException {
        return this.productService.getProductById(id);
    }

    @GetMapping("/")
    public List<GenericProductDTO> getAllProducts() {
        return this.productService.getProducts();
    }

    @DeleteMapping("/{id}")
    public GenericProductDTO deleteProductById(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }

    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO genericProductDTO) {
        return productService.createProduct(genericProductDTO);
    }

    @PutMapping("/{id}")
    public GenericProductDTO updateProduct(@PathVariable("id") Long id, @RequestBody GenericProductDTO genericProductDTO) {
        return productService.updateProductById(id, genericProductDTO);
    }

//    @ExceptionHandler
//    private ResponseEntity<ExceptionDTO> handleProductException(ProductException e) {
//        ExceptionDTO exceptionDTO = new ExceptionDTO();
//        exceptionDTO.setHttpStatus(HttpStatus.NOT_FOUND);
//        exceptionDTO.setMessage(e.getMessage());
//        //return exceptionDTO;
//        return new ResponseEntity<ExceptionDTO>(
//                exceptionDTO,
//                HttpStatus.NOT_FOUND
//                );
//    }
}
