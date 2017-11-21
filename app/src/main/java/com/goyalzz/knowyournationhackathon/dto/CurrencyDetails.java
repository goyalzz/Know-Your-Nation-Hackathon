package com.goyalzz.knowyournationhackathon.dto;

import java.io.Serializable;

/**
 * Created by ankush on 20/11/17.
 */

public class CurrencyDetails implements Serializable {

    private String code;

    private String name;

    private String symbol;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Currency Code = '" + code + '\n' +
                "Currency Name = '" + name + '\n' +
                "Currency Symbol = '" + symbol + '\n';
    }
}
