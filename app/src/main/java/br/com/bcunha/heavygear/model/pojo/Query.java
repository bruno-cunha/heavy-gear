package br.com.bcunha.heavygear.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by bruno on 18/04/17.
 */

public class Query {
    public Query(int count, Results results) {
        this.count = count;
        this.results = results;
    }

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
