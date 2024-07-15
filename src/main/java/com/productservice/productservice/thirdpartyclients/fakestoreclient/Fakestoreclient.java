package com.productservice.productservice.thirdpartyclients.fakestoreclient;

import com.productservice.productservice.dto.FakeStoreProductDTO;
import com.productservice.productservice.dto.GenericProductDTO;
import com.productservice.productservice.exception.ProductException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Fakestoreclient {
    private RestTemplateBuilder restTemplateBuilder;

    private String getProductUrl = "https://fakestoreapi.com/products/{id}";

    private String getAllProductsUrl = "https://fakestoreapi.com/products";

    public Fakestoreclient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<FakeStoreProductDTO> getProducts() {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> res = restTemplate.getForEntity(getAllProductsUrl, FakeStoreProductDTO[].class);
        FakeStoreProductDTO[] fakeStoreProductDTOs = res.getBody();
        return List.of(fakeStoreProductDTOs);
    }

    public FakeStoreProductDTO getProductById(Long id) throws ProductException {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> res = restTemplate.getForEntity(getProductUrl, FakeStoreProductDTO.class, id);
        FakeStoreProductDTO fakeStoreProductDTO = res.getBody();
        if(fakeStoreProductDTO == null){
            throw new ProductException("Product with id "+id+" not found");
        }
        return fakeStoreProductDTO;
    }

    public FakeStoreProductDTO createProduct(GenericProductDTO product) {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.postForEntity(getAllProductsUrl, product, FakeStoreProductDTO.class);
        return responseEntity.getBody();
    }

    public FakeStoreProductDTO deleteProduct(Long id) {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.execute(getProductUrl, HttpMethod.GET, requestCallback, responseExtractor, id);
        return responseEntity.getBody();
    }

    public FakeStoreProductDTO updateProductById(Long id, GenericProductDTO product) {
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.execute(getProductUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);
        return responseEntity.getBody();
    }
}
