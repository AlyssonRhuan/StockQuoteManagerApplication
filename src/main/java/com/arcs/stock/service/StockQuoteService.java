package com.arcs.stock.service;

import com.arcs.stock.domain.StockQuote;
import com.arcs.stock.repository.StockQuoteRepository;
import com.arcs.stock.service.exceptions.ObjectNotFoundException;
import com.arcs.stock.service.exceptions.ObjectNotRegistredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockQuoteService {

    @Autowired
    private StockQuoteRepository stockQuoteRepository;

    @Autowired
    private StockManagerService stockManagerService;

    public StockQuote save(StockQuote stockQuote) throws ObjectNotRegistredException {
        validateStock(stockQuote);
        return stockQuoteRepository.save(stockQuote);
    }

    private void validateStock(StockQuote stockQuote) throws ObjectNotRegistredException {
        List<String> stocks = stockManagerService.getStockManagerList();
        if (!stocks.contains(stockQuote.getId())) {
            throw new ObjectNotRegistredException(stockQuote.getId()
                    + " is not registered on Stock Quote Manager!");
        }
    }

    public StockQuote getById(String stockQuoteId) throws ObjectNotFoundException {
        Optional<StockQuote> stockQuote = stockQuoteRepository.findById(stockQuoteId);
        return stockQuote.orElseThrow(() -> new ObjectNotFoundException(
                "Stock Quote not found!"));
    }

    public List<StockQuote> getAll() throws Exception {
        return stockQuoteRepository.findAll();
    }
}
