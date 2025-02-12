package com.inditex.core.platform.corplat.controller;

import com.inditex.core.platform.corplat.model.Catalog.CatalogSearchRequest;
import com.inditex.core.platform.corplat.model.Price;
import com.inditex.core.platform.corplat.service.CatalogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SearchControllerTest {

    @Mock
    private CatalogService catalogService;

    @InjectMocks
    private SearchController searchController;

    private BigInteger product;
    private BigInteger brand;
    private String dateStr;
    private LocalDateTime date;
    private Price mockPrice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Datos de prueba
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        product = BigInteger.valueOf(35455);
        brand = BigInteger.valueOf(1);
        dateStr = "2020-06-14-00.00.00";
        date = LocalDateTime.parse("2020-06-14T00:00:00");

        mockPrice = new Price(1, LocalDateTime.parse("2020-06-14-00.00.00", formatter), LocalDateTime.parse("2020-12-31-23.59.59", formatter), new BigInteger("1"), new BigInteger("35455"), new BigInteger("0"), new BigDecimal("35.50"), "EUR");
    }

    @Test
    void catalogSearch_ReturnsPriceList_WhenDataExists() {
        // Configuración del mock
        when(catalogService.findByIdAndBrandAndDate(any(CatalogSearchRequest.class)))
                .thenReturn(List.of(mockPrice));

        // Llamada al controlador
        ResponseEntity<?> response = searchController.catalogSearch(product, brand, dateStr);

        // Validaciones
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof List);
        List<?> responseBody = (List<?>) response.getBody();
        assertFalse(responseBody.isEmpty());

        // Verificar que se llamó correctamente al servicio
        verify(catalogService, times(1)).findByIdAndBrandAndDate(any(CatalogSearchRequest.class));
    }

    @Test
    void catalogSearch_ReturnsNoContent_WhenNoDataFound() {
        // Configuración del mock
        when(catalogService.findByIdAndBrandAndDate(any(CatalogSearchRequest.class)))
                .thenReturn(Collections.emptyList());

        // Llamada al controlador
        ResponseEntity<?> response = searchController.catalogSearch(product, brand, dateStr);

        // Validaciones
        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        assertEquals("No hay ninguna coincidencia", response.getBody());

        // Verificar que se llamó correctamente al servicio
        verify(catalogService, times(1)).findByIdAndBrandAndDate(any(CatalogSearchRequest.class));
    }

    @Test
    void catalogSearch_ThrowsException_WhenDateFormatIsInvalid() {
        // Fecha inválida
        String invalidDateStr = "invalid-date";

        // Verificar que la excepción se lanza
        assertThrows(Exception.class, () -> searchController.catalogSearch(product, brand, invalidDateStr));
    }

    @Test
    void catalogSearch_HandlesNullValuesProperly() {
        // Configuración del mock cuando los parámetros son nulos
        when(catalogService.findByIdAndBrandAndDate(any(CatalogSearchRequest.class)))
                .thenReturn(Collections.emptyList());

        // Llamada al controlador con valores nulos
        ResponseEntity<?> response = searchController.catalogSearch(null, null, "2020-06-14-00.00.00");

        // Validaciones
        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        assertEquals("No hay ninguna coincidencia", response.getBody());

        // Verificar que se llamó correctamente al servicio
        verify(catalogService, times(1)).findByIdAndBrandAndDate(any(CatalogSearchRequest.class));
    }
}

