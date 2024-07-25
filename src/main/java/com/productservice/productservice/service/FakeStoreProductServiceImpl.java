package com.productservice.productservice.service;

import com.productservice.productservice.dto.FakeStoreProductDTO;
import com.productservice.productservice.dto.GenericProductDTO;
import com.productservice.productservice.exception.ProductException;
import com.productservice.productservice.thirdpartyclients.fakestoreclient.Fakestoreclient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductServiceImpl implements ProductService{

//    private RestTemplateBuilder restTemplateBuilder;

    private Fakestoreclient fakestoreclient;

    public FakeStoreProductServiceImpl(Fakestoreclient fakestoreClient) {
        this.fakestoreclient = fakestoreClient;
    }

    @Override
    public List<GenericProductDTO> getProducts() {
//        RestTemplate restTemplate = this.restTemplateBuilder.build();
//        ResponseEntity<FakeStoreProductDTO[]> res = restTemplate.getForEntity(getAllProductsUrl, FakeStoreProductDTO[].class);
//        FakeStoreProductDTO[] fakeStoreProductDTOs = res.getBody();

        List<FakeStoreProductDTO> fakeStoreProductDTOs = this.fakestoreclient.getProducts();

        List<GenericProductDTO> products = new ArrayList<>();

        for (FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductDTOs) {
            products.add(convertToGenericProductDTO(fakeStoreProductDTO));
        }

        return products;
    }

    private static GenericProductDTO convertToGenericProductDTO(FakeStoreProductDTO fakeStoreProductDTO){
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId(fakeStoreProductDTO.getId());
        genericProductDTO.setTitle(fakeStoreProductDTO.getTitle());
        genericProductDTO.setDescription(fakeStoreProductDTO.getDescription());
        genericProductDTO.setPrice(fakeStoreProductDTO.getPrice());
        genericProductDTO.setCategory(fakeStoreProductDTO.getCategory());
        genericProductDTO.setImage(fakeStoreProductDTO.getImage());
        return genericProductDTO;
    }

    @Override
    public GenericProductDTO getProductById(Long id) throws ProductException {
//        RestTemplate restTemplate = this.restTemplateBuilder.build();
//        ResponseEntity<FakeStoreProductDTO> res = restTemplate.getForEntity(getProductUrl, FakeStoreProductDTO.class, id);
//        FakeStoreProductDTO fakeStoreProductDTO = res.getBody();
        FakeStoreProductDTO fakeStoreProductDTO = this.fakestoreclient.getProductById(id);
        if(fakeStoreProductDTO == null){
            throw new ProductException("Product with id "+id+" not found");
        }
        return convertToGenericProductDTO(fakeStoreProductDTO);
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO product) {
//        RestTemplate restTemplate = this.restTemplateBuilder.build();
//        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.postForEntity(getAllProductsUrl, product, FakeStoreProductDTO.class);
//        return convertToGenericProductDTO(responseEntity.getBody());
        return convertToGenericProductDTO(this.fakestoreclient.createProduct(product));
    }

    @Override
    public GenericProductDTO deleteProduct(Long id) {
//        RestTemplate restTemplate = this.restTemplateBuilder.build();
//        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
//        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
//        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.execute(getProductUrl, HttpMethod.GET, requestCallback, responseExtractor, id);
//        return convertToGenericProductDTO(responseEntity.getBody());
        return convertToGenericProductDTO(this.fakestoreclient.deleteProduct(id));
    }

    @Override
    public GenericProductDTO updateProductById(Long id, GenericProductDTO product) {
//        RestTemplate restTemplate = this.restTemplateBuilder.build();
//        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDTO.class);
//        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
//        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.execute(getProductUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);
//        return convertToGenericProductDTO(responseEntity.getBody());
        return convertToGenericProductDTO(this.fakestoreclient.updateProductById(id, product));
    }
}
