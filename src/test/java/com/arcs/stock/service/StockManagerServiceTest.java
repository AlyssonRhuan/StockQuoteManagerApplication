package com.arcs.stock.service;

import com.arcs.stock.cache.CacheKeys;
import com.arcs.stock.cache.GenericCache;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class StockManagerServiceTest {

    @InjectMocks
    private StockManagerService stockManagerService;

    private GenericCache mockCache;
    private RestTemplate mockRestTemplate;
    private List<String> stockList;

    private String STOCK_KEY = "petr3";
    private String HOST = "HOST";
    private String PORT = "PORT";
    private String LINK_NOTIFY = "LINK_NOTIFY";

    public StockManagerServiceTest(){
        MockitoAnnotations.openMocks(this);
        mockRestTemplate = mock(RestTemplate.class);
        mockCache = mock(GenericCache.class);
        stockManagerService = new StockManagerService(mockRestTemplate, mockCache);

        stockList = new ArrayList<>();
        stockList.add(STOCK_KEY);
    }

    @Test
    public void getStockManagerListSucess() {
        Mockito.when(mockCache.containsCache(CacheKeys.STOCK_KEY.name())).thenReturn(true);
        Mockito.when(mockCache.getAll(CacheKeys.STOCK_KEY.name())).thenReturn(stockList);
        List<String> stockManagerResponse = stockManagerService.getStockManagerList();
        assertEquals(stockList, stockManagerResponse);
    }
}
