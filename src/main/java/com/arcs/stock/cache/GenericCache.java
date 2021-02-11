package com.arcs.stock.cache;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GenericCache {

    protected Map<String, List<String>> cacheMap;

    public GenericCache() {
        cacheMap = new HashMap<>();
    }

    public void clear(String key) {
        cacheMap.remove(key);
    }

    public List<String> getAll(String key) {
        return cacheMap.get(key);
    }

    public void post(String key, List<String> value) {
        cacheMap.put(key, value);
    }

    public boolean containsCache(String key) {
        return cacheMap.containsKey(key);
    }
}
