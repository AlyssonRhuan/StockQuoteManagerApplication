package com.arcs.stock.repository;

import com.arcs.stock.domain.StockQuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockQuoteRepository extends JpaRepository<StockQuote, String> {

}
