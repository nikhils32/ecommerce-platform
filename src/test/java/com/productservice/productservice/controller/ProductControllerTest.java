package com.productservice.productservice.controller;

import com.productservice.productservice.dto.GenericProductDTO;
import com.productservice.productservice.exception.ProductException;
import com.productservice.productservice.service.FakeStoreProductServiceImpl;
import com.productservice.productservice.service.ProductService;
import com.productservice.productservice.thirdpartyclients.fakestoreclient.Fakestoreclient;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {

    @Inject
    private ProductController productController;

    @Inject
    private Fakestoreclient fakestoreclient;

    @MockBean
    private ProductService productService;

    @Captor
    private ArgumentCaptor<Long> argumentCaptor;

    @Test
    @DisplayName("Testing 1 + 1 is 2")
    void testOnePlusOneIsTwoOrNot() {
        //assert(11, 1 + 1, "1 + 1 should be 2");
        assertEquals(2, 1+1, "1+1 should be 2");
        //assertTrue(2 == 2);
        //Checks the expected value (2) vs the output of the expression.
        //If assert is TRUE, test case succeeds, else fails.
    }

    @Test
    void testGetProductByIdNegativeTC() throws ProductException {
        assertThrows(ProductException.class, () -> productController.getProductById(1000L));
        //assertNull(fakeStoreClient.getProductById(1L));
    }

    @Test
    void testGetProductByIdMockingTC() throws ProductException {
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        //when(productService.getProductById(1000L)).thenReturn(null);
        when(productService.getProductById(any(Long.class))).thenReturn(genericProductDTO);
        //assertThrows(ProductException.class, () -> productController.getProductById(100L));
        assertEquals(genericProductDTO, productController.getProductById(1000L));
    }

    @Test
    void testGetProductByIdMockingExceptionTC() throws ProductException {
        when(productService.getProductById(1000L)).thenThrow(new ProductException(""));
        assertThrows(ProductException.class, () -> productController.getProductById(1000L));
    }

    @Test
    void testGetProductByIdMockingPositiveTC2() throws ProductException {
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        when(productService.getProductById(1000L)).thenReturn(genericProductDTO);
        assertEquals(genericProductDTO, productController.getProductById(1000L));
    }

    @Test
    @DisplayName("testProductControllerCallsProductServiceWithSameProductIdAsInput")
    void testIfSameInput() throws ProductException {
        //This is the test case to check if productController is passing the same productId to the
        //productService as the input.
        Long id = 100L;

        when(productService.getProductById(id)).thenReturn(new GenericProductDTO());

        GenericProductDTO genericProductDto =  productController.getProductById(id);

        verify(productService).getProductById(argumentCaptor.capture());

        assertEquals(id, argumentCaptor.getValue());
    }

}
