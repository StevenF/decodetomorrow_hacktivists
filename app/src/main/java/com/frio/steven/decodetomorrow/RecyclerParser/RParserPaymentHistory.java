package com.frio.steven.decodetomorrow.RecyclerParser;

/**
 * Created by JMM on 11/11/2018.
 */

public class RParserPaymentHistory {

    private String amount;
    private String date;
    private String time;
    private String penalty;

    public RParserPaymentHistory( String amount, String date, String time, String penalty) {
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.penalty = penalty;
    }


    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getPenalty() {
        return penalty;
    }




}
