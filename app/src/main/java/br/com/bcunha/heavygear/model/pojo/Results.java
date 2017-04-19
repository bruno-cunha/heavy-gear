package br.com.bcunha.heavygear.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bruno on 18/04/17.
 */

public class Results {
    public Results(List<Quote> quote) {
        this.quote = quote;
    }

    @SerializedName("quote")
    public List<Quote> getQuote() {
        return this.quote;
    }

    public void setQuote(List<Quote> quote) {
        this.quote = quote;
    }

    List<Quote> quote;
}