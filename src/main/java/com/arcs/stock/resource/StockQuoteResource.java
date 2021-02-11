package com.arcs.stock.resource;

import com.arcs.stock.domain.StockQuote;
import com.arcs.stock.service.StockQuoteService;
import com.arcs.stock.service.exceptions.ObjectNotFoundException;
import com.arcs.stock.service.exceptions.ObjectNotRegistredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StockQuoteResource {

    @Autowired
    private StockQuoteService stockQuoteService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<StockQuote> save(@RequestBody StockQuote stockQuote) throws ObjectNotRegistredException {
        stockQuote = stockQuoteService.save(stockQuote);
        return ResponseEntity.ok(stockQuote);
    }

    @RequestMapping(value = "/{stockQuoteId}", method = RequestMethod.GET)
    public ResponseEntity<StockQuote> getById(@PathVariable String stockQuoteId) throws ObjectNotFoundException {
        StockQuote stockQuote = stockQuoteService.getById(stockQuoteId);
        return ResponseEntity.ok(stockQuote);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StockQuote>> getAll() throws Exception {
        List<StockQuote> stockQuotes = stockQuoteService.getAll();
        return ResponseEntity.ok(stockQuotes);
    }
}
