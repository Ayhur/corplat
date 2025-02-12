package com.inditex.core.platform.corplat.model.Catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;


/**
 * Catalog Serach Request class
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatalogSearchRequest {
    /**
     * The ID
     */
    private BigInteger idProduct;
    /**
     * The brand of product
     */
    private BigInteger brand;
    /**
     * The start date
     */
    private LocalDateTime date;
}
