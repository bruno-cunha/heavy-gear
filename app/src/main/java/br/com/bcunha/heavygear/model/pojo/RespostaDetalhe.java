package br.com.bcunha.heavygear.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by BRUNO on 14/10/2016.
 */

public class RespostaDetalhe {
    @SerializedName("query")
    public Query getQuery() {
        return query;
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
        public Quote getQuote() {
            return this.quote;
        }
        public void setQuote(Quote quote) {
            this.quote = quote;
        }
        Quote quote;
    }

    public class Quote
    {
        @SerializedName("symbol")
        public String getsymbol() {
            return this.symbol;
        }
        public void setsymbol(String symbol) {
            this.symbol = symbol;
        }
        String symbol;

        @SerializedName("Ask")
        public String getAsk() {
            return this.Ask;
        }
        public void setAsk(String ask) {
            this.Ask = ask;
        }
        String Ask;

        @SerializedName("AverageDailyVolume")
        public int getAverageDailyVolume() {
            return this.AverageDailyVolume;
        }
        public void setAverageDailyVolume(int averageDailyVolume) {
            this.AverageDailyVolume = averageDailyVolume;
        }
        int AverageDailyVolume;

        @SerializedName("Bid")
        public double getBid() {
            return this.Bid;
        }
        public void setBid(double bid) {
            this.Bid = bid;
        }
        double Bid;

        @SerializedName("AskRealtime")
        public Date getAskRealtime() {
            return this.AskRealtime;
        }
        public void setAskRealtime(Date askRealtime) {
            this.AskRealtime = askRealtime;
        }
        Date AskRealtime;

        @SerializedName("BidRealtime")
        public Date getBidRealtime() {
            return this.BidRealtime;
        }
        public void setBidRealtime(Date bidRealtime) {
            this.BidRealtime = bidRealtime;
        }
        Date BidRealtime;

        @SerializedName("BookValue")
        public double getBookValue() {
            return this.BookValue;
        }
        public void setBookValue(double bookValue) {
            this.BookValue = bookValue;
        }
        double BookValue;

        @SerializedName("Change_PercentChange")
        public String getChange_PercentChange() {
            return this.Change_PercentChange;
        }
        public void setChange_PercentChange(String change_PercentChange) {
            this.Change_PercentChange = change_PercentChange;
        }
        String Change_PercentChange;

        @SerializedName("Change")
        public double getChange() {
            return this.Change;
        }
        public void setChange(double change) {
            this.Change = change;
        }
        double Change;

        @SerializedName("Commission")
        public String getCommission() {
            return this.Commission;
        }
        public void setCommission(String commission) {
            this.Commission = commission;
        }
        String Commission;

        @SerializedName("Currency")
        public String getCurrency() {
            return this.Currency;
        }
        public void setCurrency(String currency) {
            this.Currency = currency;
        }
        String Currency;

        @SerializedName("ChangeRealtime")
        public Date getChangeRealtime() {
            return this.ChangeRealtime;
        }
        public void setChangeRealtime(Date changeRealtime) {
            this.ChangeRealtime = changeRealtime;
        }
        Date ChangeRealtime;

        @SerializedName("AfterHoursChangeRealtime")
        public Date getAfterHoursChangeRealtime() {
            return this.AfterHoursChangeRealtime;
        }
        public void setAfterHoursChangeRealtime(Date afterHoursChangeRealtime) {
            this.AfterHoursChangeRealtime = afterHoursChangeRealtime;
        }
        Date AfterHoursChangeRealtime;

        @SerializedName("DividendShare")
        public String getDividendShare() {
            return this.DividendShare;
        }
        public void setDividendShare(String dividendShare) {
            this.DividendShare = dividendShare;
        }
        String DividendShare;

        @SerializedName("LastTradeDate")
        public Date getLastTradeDate() {
            return this.LastTradeDate;
        }
        public void setLastTradeDate(Date lastTradeDate) {
            this.LastTradeDate = lastTradeDate;
        }
        Date LastTradeDate;

        @SerializedName("TradeDate")
        public Date getTradeDate() {
            return this.TradeDate;
        }
        public void setTradeDate(Date tradeDate) {
            this.TradeDate = tradeDate;
        }
        Date TradeDate;

        @SerializedName("EarningsShare")
        public double getEarningsShare() {
            return this.EarningsShare;
        }
        public void setEarningsShare(double earningsShare) {
            this.EarningsShare = earningsShare;
        }
        double EarningsShare;

        @SerializedName("ErrorIndicationreturnedforsymbolchangedinvalid")
        public String getErrorIndicationreturnedforsymbolchangedinvalid() {
            return this.ErrorIndicationreturnedforsymbolchangedinvalid;
        }
        public void setErrorIndicationreturnedforsymbolchangedinvalid(String errorIndicationreturnedforsymbolchangedinvalid) {
            this.ErrorIndicationreturnedforsymbolchangedinvalid = errorIndicationreturnedforsymbolchangedinvalid;
        }
        String ErrorIndicationreturnedforsymbolchangedinvalid;

        @SerializedName("EPSEstimateCurrentYear")
        public double getEPSEstimateCurrentYear() {
            return this.EPSEstimateCurrentYear;
        }
        public void setEPSEstimateCurrentYear(double ePSEstimateCurrentYear) {
            this.EPSEstimateCurrentYear = ePSEstimateCurrentYear;
        }
        double EPSEstimateCurrentYear;

        @SerializedName("EPSEstimateNextYear")
        public double getEPSEstimateNextYear() {
            return this.EPSEstimateNextYear;
        }
        public void setEPSEstimateNextYear(double ePSEstimateNextYear) {
            this.EPSEstimateNextYear = ePSEstimateNextYear;
        }
        double EPSEstimateNextYear;

        @SerializedName("EPSEstimateNextQuarter")
        public double getEPSEstimateNextQuarter() {
            return this.EPSEstimateNextQuarter;
        }
        public void setEPSEstimateNextQuarter(double ePSEstimateNextQuarter) {
            this.EPSEstimateNextQuarter = ePSEstimateNextQuarter;
        }
        double EPSEstimateNextQuarter;

        @SerializedName("DaysLow")
        public double getDaysLow() {
            return this.DaysLow;
        }
        public void setDaysLow(double daysLow) {
            this.DaysLow = daysLow;
        }
        double DaysLow;

        @SerializedName("DaysHigh")
        public double getDaysHigh() {
            return this.DaysHigh;
        }
        public void setDaysHigh(double daysHigh) {
            this.DaysHigh = daysHigh;
        }
        double DaysHigh;

        @SerializedName("YearLow")
        public double getYearLow() {
            return this.YearLow;
        }
        public void setYearLow(double yearLow) {
            this.YearLow = yearLow;
        }
        double YearLow;

        @SerializedName("YearHigh")
        public double getYearHigh() {
            return this.YearHigh;
        }
        public void setYearHigh(double yearHigh) {
            this.YearHigh = yearHigh;
        }
        double YearHigh;

        @SerializedName("HoldingsGainPercent")
        public double getHoldingsGainPercent() {
            return this.HoldingsGainPercent;
        }
        public void setHoldingsGainPercent(double holdingsGainPercent) {
            this.HoldingsGainPercent = holdingsGainPercent;
        }
        double HoldingsGainPercent;

        @SerializedName("AnnualizedGain")
        public double getAnnualizedGain() {
            return this.AnnualizedGain;
        }
        public void setAnnualizedGain(double annualizedGain) {
            this.AnnualizedGain = annualizedGain;
        }
        double AnnualizedGain;

        @SerializedName("HoldingsGain")
        public double getHoldingsGain() {
            return this.HoldingsGain;
        }
        public void setHoldingsGain(double holdingsGain) {
            this.HoldingsGain = holdingsGain;
        }
        double HoldingsGain;

        @SerializedName("HoldingsGainPercentRealtime")
        public double getHoldingsGainPercentRealtime() {
            return this.HoldingsGainPercentRealtime;
        }
        public void setHoldingsGainPercentRealtime(double holdingsGainPercentRealtime) {
            this.HoldingsGainPercentRealtime = holdingsGainPercentRealtime;
        }
        double HoldingsGainPercentRealtime;

        @SerializedName("HoldingsGainRealtime")
        public double getHoldingsGainRealtime() {
            return this.HoldingsGainRealtime;
        }
        public void setHoldingsGainRealtime(double holdingsGainRealtime) {
            this.HoldingsGainRealtime = holdingsGainRealtime;
        }
        double HoldingsGainRealtime;

        @SerializedName("MoreInfo")
        public String getMoreInfo() {
            return this.MoreInfo;
        }
        public void setMoreInfo(String moreInfo) {
            this.MoreInfo = moreInfo;
        }
        String MoreInfo;

        @SerializedName("OrderBookRealtime")
        public String getOrderBookRealtime() {
            return this.OrderBookRealtime;
        }
        public void setOrderBookRealtime(String orderBookRealtime) {
            this.OrderBookRealtime = orderBookRealtime;
        }
        String OrderBookRealtime;

        @SerializedName("MarketCapitalization")
        public double getMarketCapitalization() {
            return this.MarketCapitalization;
        }
        public void setMarketCapitalization(double marketCapitalization) {
            this.MarketCapitalization = marketCapitalization;
        }
        double MarketCapitalization;

        @SerializedName("MarketCapRealtime")
        public String getMarketCapRealtime() {
            return this.MarketCapRealtime;
        }
        public void setMarketCapRealtime(String marketCapRealtime) {
            this.MarketCapRealtime = marketCapRealtime;
        }
        String MarketCapRealtime;

        @SerializedName("EBITDA")
        public double getEBITDA() {
            return this.EBITDA;
        }
        public void setEBITDA(double eBITDA) {
            this.EBITDA = eBITDA;
        }
        double EBITDA;

        @SerializedName("ChangeFromYearLow")
        public double getChangeFromYearLow() {
            return this.ChangeFromYearLow;
        }
        public void setChangeFromYearLow(double changeFromYearLow) {
            this.ChangeFromYearLow = changeFromYearLow;
        }
        double ChangeFromYearLow;

        @SerializedName("PercentChangeFromYearLow")
        public String getPercentChangeFromYearLow() {
            return this.PercentChangeFromYearLow;
        }
        public void setPercentChangeFromYearLow(String percentChangeFromYearLow) {
            this.PercentChangeFromYearLow = percentChangeFromYearLow;
        }
        String PercentChangeFromYearLow;

        @SerializedName("LastTradeRealtimeWithTime")
        public String getLastTradeRealtimeWithTime() {
            return this.LastTradeRealtimeWithTime;
        }
        public void setLastTradeRealtimeWithTime(String lastTradeRealtimeWithTime) {
            this.LastTradeRealtimeWithTime = lastTradeRealtimeWithTime;
        }
        String LastTradeRealtimeWithTime;

        @SerializedName("ChangePercentRealtime")
        public String getChangePercentRealtime() {
            return this.ChangePercentRealtime;
        }
        public void setChangePercentRealtime(String changePercentRealtime) {
            this.ChangePercentRealtime = changePercentRealtime;
        }
        String ChangePercentRealtime;

        @SerializedName("ChangeFromYearHigh")
        public double getChangeFromYearHigh() {
            return this.ChangeFromYearHigh;
        }
        public void setChangeFromYearHigh(double changeFromYearHigh) {
            this.ChangeFromYearHigh = changeFromYearHigh;
        }
        double ChangeFromYearHigh;

        @SerializedName("PercebtChangeFromYearHigh")
        public String getPercebtChangeFromYearHigh() {
            return this.PercebtChangeFromYearHigh;
        }
        public void setPercebtChangeFromYearHigh(String percebtChangeFromYearHigh) {
            this.PercebtChangeFromYearHigh = percebtChangeFromYearHigh;
        }
        String PercebtChangeFromYearHigh;

        @SerializedName("LastTradeWithTime")
        public String getLastTradeWithTime() {
            return this.LastTradeWithTime;
        }
        public void setLastTradeWithTime(String lastTradeWithTime) {
            this.LastTradeWithTime = lastTradeWithTime;
        }
        String LastTradeWithTime;

        @SerializedName("LastTradePriceOnly")
        public double getLastTradePriceOnly() {
            return this.LastTradePriceOnly;
        }
        public void setLastTradePriceOnly(double lastTradePriceOnly) {
            this.LastTradePriceOnly = lastTradePriceOnly;
        }
        double LastTradePriceOnly;

        @SerializedName("HighLimit")
        public String getHighLimit() {
            return this.HighLimit;
        }
        public void setHighLimit(String highLimit) {
            this.HighLimit = highLimit;
        }
        String HighLimit;

        @SerializedName("LowLimit")
        public String getLowLimit() {
            return this.LowLimit;
        }
        public void setLowLimit(String lowLimit) {
            this.LowLimit = lowLimit;
        }
        String LowLimit;

        @SerializedName("DaysRange")
        public String getDaysRange() {
            return this.DaysRange;
        }
        public void setDaysRange(String daysRange) {
            this.DaysRange = daysRange;
        }
        String DaysRange;

        @SerializedName("DaysRangeRealtime")
        public String getDaysRangeRealtime() {
            return this.DaysRangeRealtime;
        }
        public void setDaysRangeRealtime(String daysRangeRealtime) {
            this.DaysRangeRealtime = daysRangeRealtime;
        }
        String DaysRangeRealtime;

        @SerializedName("FiftydayMovingAverage")
        public double getFiftydayMovingAverage() {
            return this.FiftydayMovingAverage;
        }
        public void setFiftydayMovingAverage(double fiftydayMovingAverage) {
            this.FiftydayMovingAverage = fiftydayMovingAverage;
        }
        double FiftydayMovingAverage;

        @SerializedName("TwoHundreddayMovingAverage")
        public double getTwoHundreddayMovingAverage() {
            return this.TwoHundreddayMovingAverage;
        }
        public void setTwoHundreddayMovingAverage(double twoHundreddayMovingAverage) {
            this.TwoHundreddayMovingAverage = twoHundreddayMovingAverage;
        }
        double TwoHundreddayMovingAverage;

        @SerializedName("ChangeFromTwoHundreddayMovingAverage")
        public double getChangeFromTwoHundreddayMovingAverage() {
            return this.ChangeFromTwoHundreddayMovingAverage;
        }
        public void setChangeFromTwoHundreddayMovingAverage(double changeFromTwoHundreddayMovingAverage) {
            this.ChangeFromTwoHundreddayMovingAverage = changeFromTwoHundreddayMovingAverage;
        }
        double ChangeFromTwoHundreddayMovingAverage;

        @SerializedName("PercentChangeFromTwoHundreddayMovingAverage")
        public String getPercentChangeFromTwoHundreddayMovingAverage() {
            return this.PercentChangeFromTwoHundreddayMovingAverage;
        }
        public void setPercentChangeFromTwoHundreddayMovingAverage(String percentChangeFromTwoHundreddayMovingAverage) {
            this.PercentChangeFromTwoHundreddayMovingAverage = percentChangeFromTwoHundreddayMovingAverage;
        }
        String PercentChangeFromTwoHundreddayMovingAverage;

        @SerializedName("ChangeFromFiftydayMovingAverage")
        public double getChangeFromFiftydayMovingAverage() {
            return this.ChangeFromFiftydayMovingAverage;
        }
        public void setChangeFromFiftydayMovingAverage(double changeFromFiftydayMovingAverage) {
            this.ChangeFromFiftydayMovingAverage = changeFromFiftydayMovingAverage;
        }
        double ChangeFromFiftydayMovingAverage;

        @SerializedName("PercentChangeFromFiftydayMovingAverage")
        public String getPercentChangeFromFiftydayMovingAverage() {
            return this.PercentChangeFromFiftydayMovingAverage;
        }
        public void setPercentChangeFromFiftydayMovingAverage(String percentChangeFromFiftydayMovingAverage) {
            this.PercentChangeFromFiftydayMovingAverage = percentChangeFromFiftydayMovingAverage;
        }
        String PercentChangeFromFiftydayMovingAverage;

        @SerializedName("Name")
        public String getName() {
            return this.Name;
        }
        public void setName(String name) {
            this.Name = name;
        }
        String Name;

        @SerializedName("Notes")
        public String getNotes() {
            return this.Notes;
        }
        public void setNotes(String notes) {
            this.Notes = notes;
        }
        String Notes;

        @SerializedName("Open")
        public double getOpen() {
            return this.Open;
        }
        public void setOpen(double open) {
            this.Open = open;
        }
        double Open;

        @SerializedName("PreviousClose")
        public double getPreviousClose() {
            return this.PreviousClose;
        }
        public void setPreviousClose(double previousClose) {
            this.PreviousClose = previousClose;
        }
        double PreviousClose;

        @SerializedName("PricePaid")
        public double getPricePaid() {
            return this.PricePaid;
        }
        public void setPricePaid(double pricePaid) {
            this.PricePaid = pricePaid;
        }
        double PricePaid;

        @SerializedName("ChangeinPercent")
        public String getChangeinPercent() {
            return this.ChangeinPercent;
        }
        public void setChangeinPercent(String changeinPercent) {
            this.ChangeinPercent = changeinPercent;
        }
        String ChangeinPercent;

        @SerializedName("PriceSales")
        public double getPriceSales() {
            return this.PriceSales;
        }
        public void setPriceSales(double priceSales) {
            this.PriceSales = priceSales;
        }
        double PriceSales;

        @SerializedName("PriceBook")
        public double getPriceBook() {
            return this.PriceBook;
        }
        public void setPriceBook(double priceBook) {
            this.PriceBook = priceBook;
        }
        double PriceBook;

        @SerializedName("ExDividendDate")
        public Date getExDividendDate() {
            return this.ExDividendDate;
        }
        public void setExDividendDate(Date exDividendDate) {
            this.ExDividendDate = exDividendDate;
        }
        Date ExDividendDate;

        @SerializedName("PERatio")
        public double getPERatio() {
            return this.PERatio;
        }
        public void setPERatio(double pERatio) {
            this.PERatio = pERatio;
        }
        double PERatio;

        @SerializedName("DividendPayDate")
        public Date getDividendPayDate() {
            return this.DividendPayDate;
        }
        public void setDividendPayDate(Date dividendPayDate) {
            this.DividendPayDate = dividendPayDate;
        }
        Date DividendPayDate;

        @SerializedName("PERatioRealtime")
        public double getPERatioRealtime() {
            return this.PERatioRealtime;
        }
        public void setPERatioRealtime(double pERatioRealtime) {
            this.PERatioRealtime = pERatioRealtime;
        }
        double PERatioRealtime;

        @SerializedName("PEGRatio")
        public double getPEGRatio() {
            return this.PEGRatio;
        }
        public void setPEGRatio(double pEGRatio) {
            this.PEGRatio = pEGRatio;
        }
        double PEGRatio;

        @SerializedName("PriceEPSEstimateCurrentYear")
        public double getPriceEPSEstimateCurrentYear() {
            return this.PriceEPSEstimateCurrentYear;
        }
        public void setPriceEPSEstimateCurrentYear(double priceEPSEstimateCurrentYear) {
            this.PriceEPSEstimateCurrentYear = priceEPSEstimateCurrentYear;
        }
        double PriceEPSEstimateCurrentYear;

        @SerializedName("PriceEPSEstimateNextYear")
        public double getPriceEPSEstimateNextYear() {
            return this.PriceEPSEstimateNextYear;
        }
        public void setPriceEPSEstimateNextYear(double priceEPSEstimateNextYear) {
            this.PriceEPSEstimateNextYear = priceEPSEstimateNextYear;
        }
        double PriceEPSEstimateNextYear;

        @SerializedName("Symbol")
        public String getSymbol() {
            return this.Symbol;
        }
        public void setSymbol(String symbol) {
            this.Symbol = symbol;
        }
        String Symbol;

        @SerializedName("SharesOwned")
        public int getSharesOwned() {
            return this.SharesOwned;
        }
        public void setSharesOwned(int sharesOwned) {
            this.SharesOwned = sharesOwned;
        }
        int SharesOwned;

        @SerializedName("ShortRatio")
        public double getShortRatio() {
            return this.ShortRatio;
        }
        public void setShortRatio(double shortRatio) {
            this.ShortRatio = shortRatio;
        }
        double ShortRatio;

        @SerializedName("LastTradeTime")
        public String getLastTradeTime() {
            return this.LastTradeTime;
        }
        public void setLastTradeTime(String lastTradeTime) {
            this.LastTradeTime = lastTradeTime;
        }
        String LastTradeTime;

        @SerializedName("TickerTrend")
        public String getTickerTrend() {
            return this.TickerTrend;
        }
        public void setTickerTrend(String tickerTrend) {
            this.TickerTrend = tickerTrend;
        }
        String TickerTrend;

        @SerializedName("OneyrTargetPrice")
        public double getOneyrTargetPrice() {
            return this.OneyrTargetPrice;
        }
        public void setOneyrTargetPrice(double oneyrTargetPrice) {
            this.OneyrTargetPrice = oneyrTargetPrice;
        }
        double OneyrTargetPrice;

        @SerializedName("Volume")
        public int getVolume() {
            return this.Volume;
        }
        public void setVolume(int volume) {
            this.Volume = volume;
        }
        int Volume;

        @SerializedName("HoldingsValue")
        public double getHoldingsValue() {
            return this.HoldingsValue;
        }
        public void setHoldingsValue(double holdingsValue) {
            this.HoldingsValue = holdingsValue;
        }
        double HoldingsValue;

        @SerializedName("HoldingsValueRealtime")
        public String getHoldingsValueRealtime() {
            return this.HoldingsValueRealtime;
        }
        public void setHoldingsValueRealtime(String holdingsValueRealtime) {
            this.HoldingsValueRealtime = holdingsValueRealtime;
        }
        String HoldingsValueRealtime;

        @SerializedName("YearRange")
        public String getYearRange() {
            return this.YearRange;
        }
        public void setYearRange(String yearRange) {
            this.YearRange = yearRange;
        }
        String YearRange;

        @SerializedName("DaysValueChange")
        public String getDaysValueChange() {
            return this.DaysValueChange;
        }
        public void setDaysValueChange(String daysValueChange) {
            this.DaysValueChange = daysValueChange;
        }
        String DaysValueChange;

        @SerializedName("DaysValueChangeRealtime")
        public String getDaysValueChangeRealtime() {
            return this.DaysValueChangeRealtime;
        }
        public void setDaysValueChangeRealtime(String daysValueChangeRealtime) {
            this.DaysValueChangeRealtime = daysValueChangeRealtime;
        }
        String DaysValueChangeRealtime;

        @SerializedName("StockExchange")
        public String getStockExchange() {
            return this.StockExchange;
        }
        public void setStockExchange(String stockExchange) {
            this.StockExchange = stockExchange;
        }
        String StockExchange;

        @SerializedName("DividendYield")
        public String getDividendYield() {
            return this.DividendYield;
        }
        public void setDividendYield(String dividendYield) {
            this.DividendYield = dividendYield;
        }
        String DividendYield;

        @SerializedName("PercentChange")
        public String getPercentChange() {
            return this.PercentChange;
        }
        public void setPercentChange(String percentChange) {
            this.PercentChange = percentChange;
        }
        String PercentChange;
    }
}
