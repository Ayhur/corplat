package com.inditex.core.platform.corplat.repository;

import com.inditex.core.platform.corplat.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PRICESRepository extends JpaRepository<Price, Integer> {

    @Query("""
            FROM Price p WHERE p.productId = ?1 AND p.brandId = ?2 
            AND ?3 BETWEEN p.startDate AND p.endDate
            """)
    List<Price> findByProductId (BigInteger productId, BigInteger brand, LocalDateTime date);



}
