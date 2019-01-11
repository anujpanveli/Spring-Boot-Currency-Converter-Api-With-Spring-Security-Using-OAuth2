package com.example.demo.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Quotes {

    @JsonProperty("quotes")
    private BigDecimal quotes;

    @JsonProperty("quotes")
	public BigDecimal getQuotes() {
		return quotes;
	}

    @JsonProperty("quotes")
	public void setQuotes(BigDecimal quotes) {
		this.quotes = quotes;
	}
}
