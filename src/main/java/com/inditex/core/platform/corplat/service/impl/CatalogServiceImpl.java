package com.inditex.core.platform.corplat.service.impl;

import com.inditex.core.platform.corplat.model.Catalog.CatalogSearchRequest;
import com.inditex.core.platform.corplat.model.Price;
import com.inditex.core.platform.corplat.repository.PRICESRepository;
import com.inditex.core.platform.corplat.service.CatalogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The service implementation of Catalog Service.
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    /**
     * The PRINCES Repository.
     */
    private PRICESRepository pricesRepository;

    /**
     * The constructor.
     * @param pricesRepository
     */
    public CatalogServiceImpl(PRICESRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    /**
     * Method that return a List with the resources of the search.
     * @param catalogSearchRequest
     * @return list of Price
     */
    @Override
    public List<Price> findByIdAndBrandAndDate(CatalogSearchRequest catalogSearchRequest) {
        return pricesRepository.findByProductId(catalogSearchRequest.getIdProduct(), catalogSearchRequest.getBrand(),
                catalogSearchRequest.getDate());
    }
}
