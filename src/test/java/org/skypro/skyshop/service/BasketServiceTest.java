package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.basket.UserBasket;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.product.Product;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    private UUID productId;
    private Product product;

    @BeforeEach
    public void setUp() {
        productId = UUID.randomUUID();
        product = new Product(productId, "Книга hello", 10.99, false);
    }

    @Test
    public void testAddNonExistingProductThrowsException() {
        when(storageService.getProductById(productId)).thenReturn(Optional.empty());
        assertThrows(NoSuchProductException.class, () -> basketService.addProductToBasket(productId));
    }

    @Test
    public void testAddExistingProductCallsAddProduct() {
        when(storageService.getProductById(productId)).thenReturn(Optional.of(product));
        basketService.addProductToBasket(productId);
        verify(productBasket).addProduct(productId);
    }

    @Test
    public void testGetUserBasketReturnsEmptyBasket() {
        when(productBasket.getAllProducts()).thenReturn(Collections.emptyMap());
        UserBasket userBasket = basketService.getUserBasket();
        assertTrue(userBasket.getItems().isEmpty());
    }

    @Test
    public void testGetUserBasketReturnsBasketWithProducts() {
        Map<UUID, Integer> productMap = new HashMap<>();
        productMap.put(productId, 1);
        when(productBasket.getAllProducts()).thenReturn(productMap);
        when(storageService.getProductById(productId)).thenReturn(Optional.of(product));

        UserBasket userBasket = basketService.getUserBasket();
        assertFalse(userBasket.getItems().isEmpty());
        assertEquals(1, userBasket.getItems().size());
        assertEquals(product, userBasket.getItems().get(0).getProduct());
    }
}