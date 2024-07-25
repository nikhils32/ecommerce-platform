package com.productservice.productservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productservice.productservice.dto.GenericProductDTO;
import com.productservice.productservice.repositories.CategoryRepository;
import com.productservice.productservice.repositories.PriceRepository;
import com.productservice.productservice.repositories.ProductRepository;
import com.productservice.productservice.service.ProductService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//It won't initialize the unnecessary dependencies.
@WebMvcTest(ProductController.class)
public class ProductControllerWebMVCTest {

    @MockBean
    private ProductService productService;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private PriceRepository priceRepository;

    @Inject
    private MockMvc mockMvc;

    @Inject
    private ObjectMapper objectMapper;

    @Test
    void testGetAllProductsReturnsEmptyList() throws Exception {
        when(productService.getProducts())
                .thenReturn(new ArrayList<>());

        mockMvc.perform(get("/products/"))
                .andExpect(status().is(200))
                .andExpect(content().string("[]"));
    }

    @Test
    void testGetAllProductsReturnsValidList() throws Exception {
        List<GenericProductDTO> genericProductDtos = new ArrayList<>();
        genericProductDtos.add(new GenericProductDTO());
        genericProductDtos.add(new GenericProductDTO());
        genericProductDtos.add(new GenericProductDTO());

        when(productService.getProducts())
                .thenReturn(genericProductDtos);

        mockMvc.perform(get("/products/"))
                .andExpect(status().is(200))
                .andExpect(content().string(objectMapper.writeValueAsString(genericProductDtos)));
    }


    @Test
    void createProductShouldCreateAValidNewProduct() throws Exception {
        GenericProductDTO productToCreateDto = new GenericProductDTO();
        productToCreateDto.setTitle("Macbook");
        productToCreateDto.setPrice(200000);
        productToCreateDto.setDescription("Fastest mac ever");
        productToCreateDto.setCategory("Laptop");
        productToCreateDto.setId(1000L);

        GenericProductDTO outputGenericProductDto = new GenericProductDTO();
        outputGenericProductDto.setCategory(productToCreateDto.getCategory());
        outputGenericProductDto.setTitle(productToCreateDto.getTitle());
        outputGenericProductDto.setPrice(productToCreateDto.getPrice());
        outputGenericProductDto.setDescription(productToCreateDto.getDescription());
        outputGenericProductDto.setId(1000L);

        when(productService.createProduct(any()))
                .thenReturn(outputGenericProductDto);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productToCreateDto))
                )
                .andExpect(status().is(200))
                .andExpect(
                        content().string(objectMapper.writeValueAsString(outputGenericProductDto))
                ).andExpect(jsonPath("$.title", is("Macbook")))
                .andExpect(jsonPath("$.price", is(200000)));
    }

}
