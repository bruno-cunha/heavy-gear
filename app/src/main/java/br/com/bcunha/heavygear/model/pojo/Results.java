package br.com.bcunha.heavygear.model.pojo;

/**
 * Created by BRUNO on 16/10/2016.
 */

import com.google.gson.annotations.SerializedName;

public class Results
{
    @SerializedName("quote")
    public Quote getQuote() {
        return this.quote;
    }
    public void setQuote(Quote quote) {
        this.quote = quote;
    }
    Quote quote;

}
