package com.company.domain;

import com.company.customTypes.Money;


public class Tax  {
    private final double TAX_PERCENTAGE_DEFAULT = 10;

    private final double taxPercentage;
    private Money taxAmount;

    public Tax(double taxPercentage){
        this.taxPercentage = taxPercentage;
    }

    public Tax(){
        taxPercentage =TAX_PERCENTAGE_DEFAULT;
    }

    public Money getTaxAmount(){
        return taxAmount;
    }

    public void setTaxAmount(Money total){
        double factor= taxPercentage;
        taxAmount= total.times(factor);
    }
    public double getperc(){
        return taxPercentage;
    }
}
