package com.inditex.core.platform.corplat.service;

import com.inditex.core.platform.corplat.model.Catalog.CatalogSearchRequest;
import com.inditex.core.platform.corplat.model.Price;

import java.util.List;

/**
 * The inteface of Catalog Service
 */
public interface CatalogService {
    /**
     * Method that return a List with the resources of the search.
     * @param catalogSearchRequest
     * @return list of Price
     */
    List<Price> findByIdAndBrandAndDate(CatalogSearchRequest catalogSearchRequest);
}
