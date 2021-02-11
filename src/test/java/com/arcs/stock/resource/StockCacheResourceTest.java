package com.arcs.stock.resource;

import com.arcs.stock.cache.GenericCache;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockCacheResourceTest {

    @InjectMocks
    private StockCacheResource stockCacheResource;

    @Mock
    private GenericCache genericCache;

    private String CACHE_CLEARED = "Cache cleared.";

    public StockCacheResourceTest(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void clearCacheSucess() {
        ResponseEntity<String> responseEntity = stockCacheResource.clearCache();
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        String stockCacheResponse = (String) responseEntity.getBody();
        assertEquals(stockCacheResponse, CACHE_CLEARED);
    }
}
