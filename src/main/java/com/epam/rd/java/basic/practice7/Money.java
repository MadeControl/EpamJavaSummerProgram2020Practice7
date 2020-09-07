package com.epam.rd.java.basic.practice7;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "currency",
    "amount",
})
@XmlRootElement(name = "com.epam.rd.java.basic.practice7.Money",
        namespace = "https://rep.lab.epam.com/api_client/tytykvou-task7")
public class Money {

    @XmlElement(name = "Currency", required = true)
    protected String currency;
    @XmlElement(name = "Amount")
    protected double amount;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String value) {
        this.currency = value;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double value) {
        this.amount = value;
    }

}

