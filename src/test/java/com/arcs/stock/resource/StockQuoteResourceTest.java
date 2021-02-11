package com.arcs.stock.resource;

import com.arcs.stock.domain.StockQuote;
import com.arcs.stock.service.StockQuoteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockQuoteResourceTest {

    @InjectMocks
    private StockQuoteResource stockQuoteResource;

    @Mock
    private StockQuoteService stockQuoteService;

    private Map<String, String> quotes;
    private String STOCK_KEY = "petr3";
    private StockQuote stockQuote;

    public StockQuoteResourceTest(){
        MockitoAnnotations.openMocks(this);
        quotes = new HashMap<>();
        quotes.put("2019-01-04", "10");
        quotes.put("2019-01-05", "11");
        quotes.put("2019-01-06", "14");

        stockQuote = StockQuote.builder()
                .id(STOCK_KEY)
                .quotes(quotes)
                .build();
    }

    @Test
    public void saveSucess() {
        Mockito.when(stockQuoteService.save(stockQuote)).thenReturn(stockQuote);
        ResponseEntity<StockQuote> responseEntity = stockQuoteResource.save(stockQuote);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        StockQuote stockQuoteResponse = (StockQuote) responseEntity.getBody();
        assertEquals(stockQuoteResponse, stockQuote);
    }

    @Test
    public void getByIdSucess() {
        Mockito.when(stockQuoteService.getById(STOCK_KEY)).thenReturn(stockQuote);
        ResponseEntity<StockQuote> responseEntity = stockQuoteResource.getById(STOCK_KEY);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        StockQuote stockQuoteResponse = (StockQuote) responseEntity.getBody();
        assertEquals(stockQuoteResponse, stockQuote);
    }

    @Test
    public void getAllSucess() throws Exception {
        List<StockQuote> stockQuoteList = new ArrayList<>();
        stockQuoteList.add(stockQuote);

        Mockito.when(stockQuoteService.getAll()).thenReturn(stockQuoteList);
        ResponseEntity<List<StockQuote>> responseEntity = stockQuoteResource.getAll();
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        List<StockQuote> stockQuoteResponseList = (List<StockQuote>) responseEntity.getBody();
        assertEquals(stockQuoteResponseList, stockQuoteList);
    }
}
