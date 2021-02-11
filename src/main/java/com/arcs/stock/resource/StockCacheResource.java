package com.arcs.stock.resource;

import com.arcs.stock.cache.CacheKeys;
import com.arcs.stock.cache.GenericCache;
import com.arcs.stock.service.exceptions.ObjectNotRegistredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/stockcache")
public class StockCacheResource {

    @Autowired
    private GenericCache genericCache;

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> clearCache() throws ObjectNotRegistredException {
        genericCache.clear(CacheKeys.STOCK_KEY.name());
        return ResponseEntity.ok("Cache cleared.");
    }
}
