package com.inditex.core.platform.corplat.controller;

import com.inditex.core.platform.corplat.exception.NoContentException;
import com.inditex.core.platform.corplat.model.Catalog.CatalogSearchRequest;
import com.inditex.core.platform.corplat.model.Price;
import com.inditex.core.platform.corplat.service.CatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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
            summary = "Search products by search filter's.",
            description = """
                    Obtiene una lista de productos disponibles por producto y marca entre las fechas proporcionadas.
                    A tener en cuenta las siguientes condiciones:
                    1 - (Opcional) informar un ID de producto.
                    2 - (Opcional) informar una marca.
                    3 - (Opcional) informar un campo fecha sobre la que se realizará la consulta informativa.
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Product list",
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
            @ApiResponse(
                    responseCode = "204",
                    description = "No matches found",
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example of no content",
                                    value = """
                                            {
                                                "message": "No matches found"
                                            }
                                            """,
                                    summary = "Example response - HttpStatus 204 No Content"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example of bad request",
                                    value = """
                                            {
                                                "error": "Failed request.",
                                                "details": "Re-check search filter's information."
                                            }
                                            """,
                                    summary = "Example response - HttpStatus 400 Bad Request"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Interal server error",
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example of server error",
                                    value = """
                                            {
                                                "message": "Unnable service. Try later",
                                            }
                                            """,
                                    summary = "Example response - HttpStatus 500 Internal Server Error"
                            )
                    )
            )
    })
    @GetMapping("/search")
    public ResponseEntity<List<Price>> catalogSearch(@RequestParam(required = false) BigInteger product,
                                                     @RequestParam(required = false) BigInteger brand,
                                                     @RequestParam(required = false) String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        LocalDateTime parseDate = null;
        if (null != date) {
            parseDate = LocalDateTime.parse(date.trim(), formatter);
        }

        CatalogSearchRequest catalogSearchRequest = CatalogSearchRequest.builder().brand(brand)
                .idProduct(product).date(parseDate).build();
        List<Price> prices = Optional.ofNullable(catalogService.findByIdAndBrandAndDate(catalogSearchRequest))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new NoContentException("No matches found"));

        return ResponseEntity.ok(prices);
    }


}
