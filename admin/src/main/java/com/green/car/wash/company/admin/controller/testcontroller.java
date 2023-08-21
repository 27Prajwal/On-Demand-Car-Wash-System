package com.green.car.wash.company.admin.controller;

import com.green.car.wash.company.admin.model.WashPacks;
import com.green.car.wash.company.admin.service.AdminService;
import com.green.car.wash.company.admin.service.WashPackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class testcontroller {

    @Mock
    private WashPackService washPackService;

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllWP() {
        List<WashPacks> expectedWashPacks = new ArrayList<>();
        when(washPackService.findallWP()).thenReturn(expectedWashPacks);

        List<WashPacks> result = adminController.findallWP();

        assertEquals(expectedWashPacks, result);
        verify(washPackService).findallWP();
    }

    @Test
    void testFindOneWP() {
        String id = "123";
        WashPacks expectedWashPack = new WashPacks();
        when(washPackService.findoneWP(eq(id))).thenReturn(ResponseEntity.ok(expectedWashPack));

        ResponseEntity<WashPacks> result = adminController.findoneWP(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedWashPack, result.getBody());
        verify(washPackService).findoneWP(id);
    }

    @Test
    void testAddWP() {
        WashPacks washPackToAdd = new WashPacks();
        WashPacks expectedWashPack = new WashPacks();
        when(washPackService.addWP(eq(washPackToAdd))).thenReturn(expectedWashPack);

        WashPacks result = adminController.addWP(washPackToAdd);

        assertEquals(expectedWashPack, result);
        verify(washPackService).addWP(washPackToAdd);
    }

    @Test
    void testDeleteWP() {
        String id = "123";
        Map<String, Boolean> expectedResponse = Map.of("success", true);
        when(washPackService.deleteWP(eq(id))).thenReturn(ResponseEntity.ok(expectedResponse));

        ResponseEntity<Map<String, Boolean>> result = adminController.deleteWP(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedResponse, result.getBody());
        verify(washPackService).deleteWP(id);
    }

    @Test
    void testUpdateWP() {
        String id = "123";
        WashPacks washPackToUpdate = new WashPacks();
        WashPacks expectedWashPack = new WashPacks();
        when(washPackService.updateWP(eq(id), eq(washPackToUpdate))).thenReturn(ResponseEntity.ok(expectedWashPack));

        ResponseEntity<WashPacks> result = adminController.updateWP(id, washPackToUpdate);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedWashPack, result.getBody());
        verify(washPackService).updateWP(id, washPackToUpdate);
    }

    // Add more test cases for the remaining methods in the AdminController class

}

