package com.arcs.stock.domain;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StockManager {

    private String id;
    private String description;
    private Map<String, String> quotes;
}
