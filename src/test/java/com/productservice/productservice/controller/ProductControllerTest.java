package com.productservice.productservice.controller;

import com.productservice.productservice.exception.ProductException;
import com.productservice.productservice.thirdpartyclients.fakestoreclient.Fakestoreclient;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ProductControllerTest {

    @Inject
    private ProductController productController;

    @Inject
    private Fakestoreclient fakestoreclient;

    @Test
    @DisplayName("Testing 1 + 1 is 2")
    void testOnePlusOneIsTwoOrNot() {
        //assert(11, 1 + 1, "1 + 1 should be 2");
        assertEquals(11, 1+1, "1+1 should be 2");
        //assertTrue(2 == 2);
        //Checks the expected value (2) vs the output of the expression.
        //If assert is TRUE, test case succeeds, else fails.
    }

    @Test
    void testGetProductByIdNegativeTC() throws ProductException {
        assertThrows(ProductException.class, () -> productController.getProductById(123L));
        //assertNull(fakeStoreClient.getProductById(1L));
    }

}
