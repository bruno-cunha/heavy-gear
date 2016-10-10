package br.com.bcunha.heavygear.model.pojo;

/**
 * Created by BRUNO on 09/10/2016.
 */

public class Query
{
    private Results results;

    private int count;

    private String created;

    private String lang;

    public Results getResults ()
    {
        return results;
    }

    public void setResults (Results results)
    {
        this.results = results;
    }

    public int getCount ()
    {
        return count;
    }

    public void setCount (int count)
    {
        this.count = count;
    }

    public String getCreated ()
    {
        return created;
    }

    public void setCreated (String created)
    {
        this.created = created;
    }

    public String getLang ()
    {
        return lang;
    }

    public void setLang (String lang)
    {
        this.lang = lang;
    }

    @Override
    public String toString()
    {
        return "Classe Query [results = "+results+", count = "+count+", created = "+created+", lang = "+lang+"]";
    }
}