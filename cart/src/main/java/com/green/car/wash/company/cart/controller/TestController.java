package com.green.car.wash.company.cart.controller;

import com.green.car.wash.company.cart.model.Cart;
import com.green.car.wash.company.cart.model.Items;
import com.green.car.wash.company.cart.model.WashPacks;
import com.green.car.wash.company.cart.repository.CartRepository;
import com.green.car.wash.company.cart.service.CartService;
import com.green.car.wash.company.cart.service.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CartControllerTest {

    @Mock
    private CartService cartService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CartServiceImpl cartServiceImpl;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllWP() {
        WashPacks[] washPacks = {new WashPacks(), new WashPacks()};
        when(restTemplate.getForObject(eq("http://admin/admins/all/findWP"), eq(WashPacks[].class)))
                .thenReturn(washPacks);

        List<WashPacks> result = cartController.getAllWP();

        assertEquals(Arrays.asList(washPacks), result);
        verify(restTemplate).getForObject(eq("http://admin/admins/all/findWP"), eq(WashPacks[].class));
    }

    @Test
    void testGetAllCarts() {
        List<Cart> expectedCarts = new ArrayList<>();
        when(cartServiceImpl.getallcarts()).thenReturn(expectedCarts);

        ResponseEntity<List<Cart>> result = cartController.getAllCarts();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedCarts, result.getBody());
        verify(cartServiceImpl).getallcarts();
    }

}