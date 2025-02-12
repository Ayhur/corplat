package com.inditex.core.platform.corplat;

import com.inditex.core.platform.corplat.model.Price;
import com.inditex.core.platform.corplat.repository.PRICESRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Component for initializer a local data base.
 */
@Component
class DatabaseInitializer {
    /**
     * The PRICES repository.
     */
    private PRICESRepository pricesRepository;

    /**
     * The constructor
     * @param pricesRepository
     */
    public DatabaseInitializer(PRICESRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    /**
     * The initializer data base method.
     */
    @PostConstruct
    public void initializeDatabase() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        pricesRepository.save(new Price(1, LocalDateTime.parse("2020-06-14-00.00.00", formatter), LocalDateTime.parse("2020-12-31-23.59.59", formatter), new BigInteger("1"), new BigInteger("35455"), new BigInteger("0"), new BigDecimal("35.50"), "EUR"));
        pricesRepository.save(new Price(1, LocalDateTime.parse("2020-06-14-15.00.00", formatter), LocalDateTime.parse("2020-06-14-18.30.00", formatter), new BigInteger("2"), new BigInteger("35455"), new BigInteger("1"), new BigDecimal("25.45"), "EUR"));
        pricesRepository.save(new Price(1, LocalDateTime.parse("2020-06-15-00.00.00", formatter), LocalDateTime.parse("2020-06-15-11.00.00", formatter), new BigInteger("3"), new BigInteger("35455"), new BigInteger("1"), new BigDecimal("30.50"), "EUR"));
        pricesRepository.save(new Price(1, LocalDateTime.parse("2020-06-15-16.00.00", formatter), LocalDateTime.parse("2020-12-31-23.59.59", formatter), new BigInteger("4"), new BigInteger("35455"), new BigInteger("1"), new BigDecimal("38.95"), "EUR"));
    }
}
