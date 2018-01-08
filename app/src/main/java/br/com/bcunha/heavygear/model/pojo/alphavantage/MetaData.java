
package br.com.bcunha.heavygear.model.pojo.alphavantage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetaData {

    @SerializedName("1. Information")
    @Expose
    private String _1Information;
    @SerializedName("2. Symbol")
    @Expose
    private String _2Symbol;
    @SerializedName("3. Last Refreshed")
    @Expose
    private String _3LastRefreshed;
    @SerializedName("4. Interval")
    @Expose
    private String _4Interval;
    @SerializedName("5. Output Size")
    @Expose
    private String _5OutputSize;
    @SerializedName("6. Time Zone")
    @Expose
    private String _6TimeZone;

    public String get1Information() {
        return _1Information;
    }

    public void set1Information(String _1Information) {
        this._1Information = _1Information;
    }

    public String get2Symbol() {
        return _2Symbol;
    }

    public void set2Symbol(String _2Symbol) {
        this._2Symbol = _2Symbol;
    }

    public String get3LastRefreshed() {
        return _3LastRefreshed;
    }

    public void set3LastRefreshed(String _3LastRefreshed) {
        this._3LastRefreshed = _3LastRefreshed;
    }

    public String get4Interval() {
        return _4Interval;
    }

    public void set4Interval(String _4Interval) {
        this._4Interval = _4Interval;
    }

    public String get5OutputSize() {
        return _5OutputSize;
    }

    public void set5OutputSize(String _5OutputSize) {
        this._5OutputSize = _5OutputSize;
    }

    public String get6TimeZone() {
        return _6TimeZone;
    }

    public void set6TimeZone(String _6TimeZone) {
        this._6TimeZone = _6TimeZone;
    }

}
