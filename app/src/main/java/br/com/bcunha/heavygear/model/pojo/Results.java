package br.com.bcunha.heavygear.model.pojo;

/**
 * Created by BRUNO on 09/10/2016.
 */

public class Results
{
    private Quote quote;

    public Quote getQuote ()
    {
        return quote;
    }

    public void setQuote (Quote quote)
    {
        this.quote = quote;
    }

    @Override
    public String toString()
    {
        return "Classe Results [quote = "+quote+"]";
    }
}