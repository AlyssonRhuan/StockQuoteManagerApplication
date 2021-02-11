package com.arcs.stock.service;

import com.arcs.stock.cache.CacheKeys;
import com.arcs.stock.cache.GenericCache;
import com.arcs.stock.domain.StockManager;
import com.arcs.stock.domain.StockManagerRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockManagerService {

    @Value("${stock.manager.link.notify}")
    private String linkNotify;

    @Value("${stock.manager.link.stock}")
    private String linkStock;

    @Value("${server.port}")
    private String port;

    @Value("${server.host.url}")
    private String host;

    private RestTemplate restTemplate;

    @Autowired
    private GenericCache genericCache;

    public StockManagerService() {
        if (restTemplate == null) {
            this.restTemplate = new RestTemplate();
        }
    }

    public StockManagerService(RestTemplate restTemplate, GenericCache genericCache) {
        this.restTemplate = restTemplate;
        this.genericCache = genericCache;
    }

    public List<String> getStockManagerList() {
        return genericCache.containsCache(CacheKeys.STOCK_KEY.name())
                ? genericCache.getAll(CacheKeys.STOCK_KEY.name())
                : getFromStockManager();
    }

    private List<String> getFromStockManager() {
        StockManager[] result = this.restTemplate.getForObject(linkStock, StockManager[].class);
        List<StockManager> stockManagerList = Arrays.asList(result);
        List<String> stockIds = stockManagerList.stream().map(s -> s.getId()).collect(Collectors.toList());
        genericCache.post(CacheKeys.STOCK_KEY.name(), stockIds);
        return stockIds;
    }

    public void register(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        StockManagerRegister register = StockManagerRegister.builder().host(host).port(port).build();
        HttpEntity<StockManagerRegister> entity = new HttpEntity<StockManagerRegister>(register, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(
                linkNotify,
                HttpMethod.POST,
                entity,
                StockManagerRegister[].class
        );
    }
}
