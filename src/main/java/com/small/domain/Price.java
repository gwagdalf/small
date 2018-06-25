package com.small.domain;

import java.math.BigDecimal;
import javax.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class Price {
    private String currency;
    private BigDecimal amount;

    public Price(Long amount) {
        this.currency = "KRW";
        this.amount = new BigDecimal(amount);
    }

    public Price(BigDecimal amount) {
        this.currency = "KRW";
        this.amount = amount;
    }

    public Price(String currency, BigDecimal amount) throws UnsupportedCurrencyException {
        switch (currency.toUpperCase()) {
            case "USD":
            case "JPY":
            case "KRW":
                break;
            default:
                throw new UnsupportedCurrencyException(currency);
        }

        this.currency = currency.toUpperCase();
        this.amount = amount;
    }
}
