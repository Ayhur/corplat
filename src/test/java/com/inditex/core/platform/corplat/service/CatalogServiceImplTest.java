package com.inditex.core.platform.corplat.service;

import com.inditex.core.platform.corplat.model.Catalog.CatalogSearchRequest;
import com.inditex.core.platform.corplat.model.Price;
import com.inditex.core.platform.corplat.repository.PRICESRepository;
import com.inditex.core.platform.corplat.service.impl.CatalogServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CatalogServiceImplTest {

    @Mock
    private PRICESRepository pricesRepository;

    @InjectMocks
    private CatalogServiceImpl catalogService;

    private CatalogSearchRequest searchRequest;
    private Price mockPrice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        searchRequest = new CatalogSearchRequest(BigInteger.valueOf(35455), BigInteger.valueOf(1), LocalDateTime.parse("2020-06-14T10:00:00"));
        mockPrice = new Price(1, LocalDateTime.parse("2020-06-14-00.00.00", formatter), LocalDateTime.parse("2020-12-31-23.59.59", formatter), new BigInteger("1"), new BigInteger("35455"), new BigInteger("0"), new BigDecimal("35.50"), "EUR");

    }

    @Test
    void findByIdAndBrandAndDate_ReturnsPriceList() {
        when(pricesRepository.findByProductId(searchRequest.getIdProduct(), searchRequest.getBrand(), searchRequest.getDate()))
                .thenReturn(Arrays.asList(mockPrice));

        List<Price> result = catalogService.findByIdAndBrandAndDate(searchRequest);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockPrice, result.get(0));
        verify(pricesRepository, times(1)).findByProductId(searchRequest.getIdProduct(), searchRequest.getBrand(), searchRequest.getDate());
    }

    @Test
    void findByIdAndBrandAndDate_ReturnsEmptyList_WhenNoMatch() {
        when(pricesRepository.findByProductId(searchRequest.getIdProduct(), searchRequest.getBrand(), searchRequest.getDate()))
                .thenReturn(List.of());

        List<Price> result = catalogService.findByIdAndBrandAndDate(searchRequest);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(pricesRepository, times(1)).findByProductId(searchRequest.getIdProduct(), searchRequest.getBrand(), searchRequest.getDate());
    }

    @Test
    void findByIdAndBrandAndDate_ThrowsException_WhenRepositoryFails() {
        when(pricesRepository.findByProductId(searchRequest.getIdProduct(), searchRequest.getBrand(), searchRequest.getDate()))
                .thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(RuntimeException.class, () ->
                catalogService.findByIdAndBrandAndDate(searchRequest));

        assertEquals("Database error", exception.getMessage());
        verify(pricesRepository, times(1)).findByProductId(searchRequest.getIdProduct(), searchRequest.getBrand(), searchRequest.getDate());
    }
}
