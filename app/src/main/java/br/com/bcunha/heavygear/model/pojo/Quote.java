package br.com.bcunha.heavygear.model.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bruno on 18/04/17.
 */

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
        return this.Symbol.substring(0, 5);
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