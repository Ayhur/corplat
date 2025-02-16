package com.inditex.core.platform.corplat.integration;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("integration")
@SpringBootTest
@AutoConfigureMockMvc
public class SearchControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void catalogSearchTestOne() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/search")
                        .param("product", "35455")
                        .param("brand", "1")
                        .param("date", "2020-06-14-10.00.00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            [
                                {
                                    "id": 1,
                                    "brandId": 1,
                                    "startDate": "2020-06-14T00:00:00",
                                    "endDate": "2020-12-31T23:59:59",
                                    "priceList": 1,
                                    "productId": 35455,
                                    "priority": 0,
                                    "price": 35.50,
                                    "currency": "EUR"
                                }
                            ]
                        """));
    }

    @Test
    public void catalogSearchTestTwo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/search")
                        .param("product", "35455")
                        .param("brand", "1")
                        .param("date", "2020-06-14-16.00.00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            [
                                {
                                    "id": 1,
                                    "brandId": 1,
                                    "startDate": "2020-06-14T00:00:00",
                                    "endDate": "2020-12-31T23:59:59",
                                    "priceList": 1,
                                    "productId": 35455,
                                    "priority": 0,
                                    "price": 35.50,
                                    "currency": "EUR"
                                },
                                {
                                    "id": 2,
                                    "brandId": 1,
                                    "startDate": "2020-06-14T15:00:00",
                                    "endDate": "2020-06-14T18:30:00",
                                    "priceList": 2,
                                    "productId": 35455,
                                    "priority": 1,
                                    "price": 25.45,
                                    "currency": "EUR"
                                }
                            ]
                        """));

    }

    @Test
    public void catalogSearchTestThree() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/search")
                        .param("product", "35455")
                        .param("brand", "1")
                        .param("date", "2020-06-14-21.00.00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            [
                                {
                                    "id": 1,
                                    "brandId": 1,
                                    "startDate": "2020-06-14T00:00:00",
                                    "endDate": "2020-12-31T23:59:59",
                                    "priceList": 1,
                                    "productId": 35455,
                                    "priority": 0,
                                    "price": 35.50,
                                    "currency": "EUR"
                                }
                            ]
                        """));

    }

    @Test
    public void catalogSearchTestFour() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/search")
                        .param("product", "35455")
                        .param("brand", "1")
                        .param("date", "2020-06-15-10.00.00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            [
                                {
                                    "id":1,
                                    "brandId":1,
                                    "startDate":"2020-06-14T00:00:00",
                                    "endDate":"2020-12-31T23:59:59",
                                    "priceList":1,
                                    "productId":35455,
                                    "priority":0,
                                    "price":35.50,
                                    "currency":"EUR"
                                },
                                {
                                    "id":3,
                                    "brandId":1,
                                    "startDate":"2020-06-15T00:00:00",
                                    "endDate":"2020-06-15T11:00:00",
                                    "priceList":3,
                                    "productId":35455,
                                    "priority":1,
                                    "price":30.50,
                                    "currency":"EUR"
                                }
                            ]
                        """));
    }


    @Test
    public void catalogSearchTestFive() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/search")
                        .param("product", "35455")
                        .param("brand", "1")
                        .param("date", "2020-06-16-21.00.00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            [
                                {
                                    "id": 1,
                                    "brandId": 1,
                                    "startDate": "2020-06-14T00:00:00",
                                    "endDate": "2020-12-31T23:59:59",
                                    "priceList": 1,
                                    "productId": 35455,
                                    "priority": 0,
                                    "price": 35.50,
                                    "currency": "EUR"
                                },
                                {
                                    "id": 4,
                                    "brandId": 1,
                                    "startDate": "2020-06-15T16:00:00",
                                    "endDate": "2020-12-31T23:59:59",
                                    "priceList": 4,
                                    "productId": 35455,
                                    "priority": 1,
                                    "price": 38.95,
                                    "currency": "EUR"
                                }
                            ]
                        """));

    }


}
