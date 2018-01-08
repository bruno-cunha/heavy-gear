
package br.com.bcunha.heavygear.model.pojo.alphavantage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespostaStock {

    @SerializedName("Meta Data")
    @Expose
    private MetaData metaData;
    @SerializedName("Time Series (1min)")
    @Expose
    private TimeSeries1min timeSeries1min;

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public TimeSeries1min getTimeSeries1min() {
        return timeSeries1min;
    }

    public void setTimeSeries1min(TimeSeries1min timeSeries1min) {
        this.timeSeries1min = timeSeries1min;
    }

}
