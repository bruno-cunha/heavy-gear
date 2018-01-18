
package br.com.bcunha.heavygear.model.pojo.alphavantage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespostaAcao{

    @SerializedName("Meta Data")
    @Expose
    private MetaDataAcao metaDataAcao;
    @SerializedName("Time Series (Daily)")
    @Expose
    private TimeSeries timeSeries;

    public MetaDataAcao getMetaDataAcao() {
        return metaDataAcao;
    }

    public void setMetaDataAcao(MetaDataAcao metaDataAcao) {
        this.metaDataAcao = metaDataAcao;
    }

    public TimeSeries getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(TimeSeries timeSeries) {
        this.timeSeries = timeSeries;
    }

}
