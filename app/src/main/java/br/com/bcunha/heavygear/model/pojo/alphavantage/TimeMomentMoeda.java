
package br.com.bcunha.heavygear.model.pojo.alphavantage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeMomentMoeda {

    @SerializedName("1a. price (BRL)")
    @Expose
    private String _1aPriceBRL;
    @SerializedName("1b. price (USD)")
    @Expose
    private String _1bPriceUSD;
    @SerializedName("2. volume")
    @Expose
    private String _2Volume;
    @SerializedName("3. market cap (USD)")
    @Expose
    private String _3MarketCapUSD;

    public String get1aPriceBRL() {
        return _1aPriceBRL;
    }

    public void set1aPriceBRL(String _1aPriceBRL) {
        this._1aPriceBRL = _1aPriceBRL;
    }

    public String get1bPriceUSD() {
        return _1bPriceUSD;
    }

    public void set1bPriceUSD(String _1bPriceUSD) {
        this._1bPriceUSD = _1bPriceUSD;
    }

    public String get2Volume() {
        return _2Volume;
    }

    public void set2Volume(String _2Volume) {
        this._2Volume = _2Volume;
    }

    public String get3MarketCapUSD() {
        return _3MarketCapUSD;
    }

    public void set3MarketCapUSD(String _3MarketCapUSD) {
        this._3MarketCapUSD = _3MarketCapUSD;
    }

}
