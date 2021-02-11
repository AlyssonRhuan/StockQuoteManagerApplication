package com.arcs.stock.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockManagerRegister {

    private String host;
    private String port;
}
