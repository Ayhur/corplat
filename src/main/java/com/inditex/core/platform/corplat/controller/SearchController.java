package com.inditex.core.platform.corplat.controller;

import com.inditex.core.platform.corplat.model.Catalog.CatalogSearchRequest;
import com.inditex.core.platform.corplat.model.Price;
import com.inditex.core.platform.corplat.service.CatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class SearchController {
    /**
     * The Catalog Service
     */
    private CatalogService catalogService;

    /**
     * The constructor.
     *
     * @param catalogService
     */
    SearchController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    /**
     * Service that provides a list of product between a dates.
     * 1.- No params are required in the request for search products.
     *
     * @param product
     * @param brand
     * @param date
     * @return list CatalogSearchResponse
     */
    @Operation(
            summary = "Buscar productos por filtros de busqueda",
            description = """
                    Obtiene una lista de productos disponibles por producto y marca entre las fechas proporcionadas.
                    A tener en cuenta las siguientes condiciones:
                    1) Ninguno de los campos de entrada serán obligatorios para realizar la busqueda.
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de productos encontrada",
                    content = @Content(
                            schema = @Schema(implementation = Price.class),
                            examples = @ExampleObject(
                                    name = "Example of a successful response",
                                    value = """
                    [
                        {
                            "id": 1,
                            "brand": 123,
                            "idProduct": 456,
                            "price": 99.99,
                            "currency": "EUR",
                            "startDate": "2024-02-01T00:00:00",
                            "endDate": "2024-02-10T23:59:59"
                        }
                    ]
                    """,
                                    summary = "Example response - HttpStatus 200 OK"
                            )
                    )
            ),
            @ApiResponse(responseCode = "204", description = "No hay ninguna coincidencia"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/search")
    public ResponseEntity<?> catalogSearch(@RequestParam(required = false) BigInteger product,
                                           @RequestParam(required = false) BigInteger brand,
                                           @RequestParam(required = false) String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        LocalDateTime parseDate = LocalDateTime.parse(date.trim(), formatter);

        CatalogSearchRequest catalogSearchRequest = CatalogSearchRequest.builder().brand(brand)
                .idProduct(product).date(parseDate).build();
        List<Price> prices = catalogService.findByIdAndBrandAndDate(catalogSearchRequest);
        return prices.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay ninguna coincidencia")
                : ResponseEntity.ok(prices);
    }

}
