
package br.com.bcunha.heavygear.model.pojo.alphavantage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespostaMoeda {

    @SerializedName("Meta Data")
    @Expose
    private MetaDataMoeda metaDataMoeda;
    @SerializedName("Time Series (Digital Currency Intraday)")
    @Expose
    private TimeSeries timeSeries;

    public MetaDataMoeda getMetaDataAcao() {
        return metaDataMoeda;
    }

    public void setMetaDataMoeda(MetaDataMoeda metaDataMoeda) {
        this.metaDataMoeda = metaDataMoeda;
    }

    public TimeSeries getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(TimeSeries timeSeries) {
        this.timeSeries = timeSeries;
    }

}
