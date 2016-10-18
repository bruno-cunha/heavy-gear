package br.com.bcunha.heavygear.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;


/**
 * Created by BRUNO on 16/10/2016.
 */

public class RespostaValorLista {
    @SerializedName("query")
    public Query getQuery() {
        return this.query;
    }
    public void setQuery(Query query) {
        this.query = query;
    }
    Query query;

    public class Query {
        @SerializedName("count")
        public int getCount() {
            return this.count;
        }
        public void setCount(int count) {
            this.count = count;
        }
        int count;

        @SerializedName("created")
        public Date getCreated() {
            return this.created;
        }
        public void setCreated(Date created) {
            this.created = created;
        }
        Date created;

        @SerializedName("lang")
        public String getLang() {
            return this.lang;
        }
        public void setLang(String lang) {
            this.lang = lang;
        }
        String lang;

        @SerializedName("results")
        public Results getResults() {
            return this.results;
        }
        public void setResults(Results results) {
            this.results = results;
        }
        Results results;
    }

    public class Results {
        @SerializedName("quote")
        public List<Quote> getQuote() {
            return this.quote;
        }
        public void setQuote(List<Quote> quote) {
            this.quote = quote;
        }
        List<Quote> quote;
    }

    public class Quote {
        @SerializedName("symbol")
        public String getsymbol() {
            return this.symbol;
        }
        public void setsymbol(String symbol) {
            this.symbol = symbol;
        }
        String symbol;

        @SerializedName("AverageDailyVolume")
        public String getAverageDailyVolume() {
            return this.AverageDailyVolume;
        }
        public void setAverageDailyVolume(String averageDailyVolume) {
            this.AverageDailyVolume = averageDailyVolume;
        }
        String AverageDailyVolume;

        @SerializedName("Change")
        public String getChange() {
            return this.Change;
        }
        public void setChange(String change) {
            this.Change = change;
        }
        String Change;

        @SerializedName("DaysLow")
        public String getDaysLow() {
            return this.DaysLow;
        }
        public void setDaysLow(String daysLow) {
            this.DaysLow = daysLow;
        }
        String DaysLow;

        @SerializedName("DaysHigh")
        public String getDaysHigh() {
            return this.DaysHigh;
        }
        public void setDaysHigh(String daysHigh) {
            this.DaysHigh = daysHigh;
        }
        String DaysHigh;

        @SerializedName("YearLow")
        public String getYearLow() {
            return this.YearLow;
        }
        public void setYearLow(String yearLow) {
            this.YearLow = yearLow;
        }
        String YearLow;

        @SerializedName("YearHigh")
        public String getYearHigh() {
            return this.YearHigh;
        }
        public void setYearHigh(String yearHigh) {
            this.YearHigh = yearHigh;
        }
        String YearHigh;

        @SerializedName("MarketCapitalization")
        public String getMarketCapitalization() {
            return this.MarketCapitalization;
        }
        public void setMarketCapitalization(String marketCapitalization) {
            this.MarketCapitalization = marketCapitalization;
        }
        String MarketCapitalization;

        @SerializedName("LastTradePriceOnly")
        public String getLastTradePriceOnly() {
            return this.LastTradePriceOnly;
        }
        public void setLastTradePriceOnly(String lastTradePriceOnly) {
            this.LastTradePriceOnly = lastTradePriceOnly;
        }
        String LastTradePriceOnly;

        @SerializedName("DaysRange")
        public String getDaysRange() {
            return this.DaysRange;
        }
        public void setDaysRange(String daysRange) {
            this.DaysRange = daysRange;
        }
        String DaysRange;

        @SerializedName("Name")
        public String getName() {
            return this.Name;
        }
        public void setName(String name) {
            this.Name = name;
        }
        String Name;

        @SerializedName("Symbol")
        public String getSymbol() {
            return this.Symbol;
        }
        public void setSymbol(String symbol) {
            this.Symbol = symbol;
        }
        String Symbol;

        @SerializedName("Volume")
        public String getVolume() {
            return this.Volume;
        }
        public void setVolume(String volume) {
            this.Volume = volume;
        }
        String Volume;

        @SerializedName("StockExchange")
        public String getStockExchange() {
            return this.StockExchange;
        }
        public void setStockExchange(String stockExchange) {
            this.StockExchange = stockExchange;
        }
        String StockExchange;
    }
}


