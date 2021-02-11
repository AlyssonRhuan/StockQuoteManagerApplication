package com.arcs.stock.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "sqm_stock")
public class StockQuote implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @ElementCollection
    @CollectionTable(name = "sqn_quotes",
            joinColumns = {@JoinColumn(name = "stock_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "date")
    @Column(name = "value")
    public Map<String, String> quotes;
}
