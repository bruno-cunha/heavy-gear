package br.com.bcunha.heavygear.model.pojo.alphavantage;

/**
 * Created by Bruno on 17/01/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetaDataMoeda {

    @SerializedName("1. Information")
    @Expose
    private String _1Information;
    @SerializedName("2. Digital Currency Code")
    @Expose
    private String _2DigitalCurrencyCode;
    @SerializedName("3. Digital Currency Name")
    @Expose
    private String _3DigitalCurrencyName;
    @SerializedName("4. Market Code")
    @Expose
    private String _4MarketCode;
    @SerializedName("5. Market Name")
    @Expose
    private String _5MarketName;
    @SerializedName("6. Interval")
    @Expose
    private String _6Interval;
    @SerializedName("7. Last Refreshed")
    @Expose
    private String _7LastRefreshed;
    @SerializedName("8. Time Zone")
    @Expose
    private String _8TimeZone;

    public String get1Information() {
        return _1Information;
    }

    public void set1Information(String _1Information) {
        this._1Information = _1Information;
    }

    public String get2DigitalCurrencyCode() {
        return _2DigitalCurrencyCode;
    }

    public void set2DigitalCurrencyCode(String _2DigitalCurrencyCode) {
        this._2DigitalCurrencyCode = _2DigitalCurrencyCode;
    }

    public String get3DigitalCurrencyName() {
        return _3DigitalCurrencyName;
    }

    public void set3DigitalCurrencyName(String _3DigitalCurrencyName) {
        this._3DigitalCurrencyName = _3DigitalCurrencyName;
    }

    public String get4MarketCode() {
        return _4MarketCode;
    }

    public void set4MarketCode(String _4MarketCode) {
        this._4MarketCode = _4MarketCode;
    }

    public String get5MarketName() {
        return _5MarketName;
    }

    public void set5MarketName(String _5MarketName) {
        this._5MarketName = _5MarketName;
    }

    public String get6Interval() {
        return _6Interval;
    }

    public void set6Interval(String _6Interval) {
        this._6Interval = _6Interval;
    }

    public String get7LastRefreshed() {
        return _7LastRefreshed;
    }

    public void set7LastRefreshed(String _7LastRefreshed) {
        this._7LastRefreshed = _7LastRefreshed;
    }

    public String get8TimeZone() {
        return _8TimeZone;
    }

    public void set8TimeZone(String _8TimeZone) {
        this._8TimeZone = _8TimeZone;
    }

}