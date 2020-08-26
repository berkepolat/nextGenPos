package com.company.domain;

import com.company.customTypes.CurrencyException;
import com.company.customTypes.Money;
import com.company.taxoperations.TaxCalculatorFactory;

import java.util.*;

public class Sale {
    private List<SalesLineItem> lineItems = new ArrayList<>();
    private List<TaxLineItem> taxItems=new ArrayList<>();
    private Date date = new Date();
    private boolean isComplete = false;
    private Payment payment;
    private Tax tax;
    private TaxCalculatorFactory taxCalculator;

    public Money getBalance() {
        return payment.getAmount().minus(getTotal());
    }

    public void becomeComplete() {
        isComplete = true;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public String makeLineItem(ProductDescription desc, int quantity) throws CurrencyException {
        SalesLineItem sl = new SalesLineItem(desc, quantity);
        TaxLineItem t1 = new TaxLineItem(desc,quantity);
        String slString=sl.toString();
        if (lineItems.size() > 0) { //check if there is any product in the list
            Currency firstItemCurrency =  lineItems.get(0).getSubTotal().getCurrency();
            if (firstItemCurrency == desc.getPrice().getCurrency()) {//check to see if currencies are the same
                lineItems.add(sl);
                taxItems.add(t1);
            } else {
                throw new CurrencyException();
            }
        } else {
            taxItems.add(t1);
            lineItems.add(sl);
        }
        return slString;
    }

    public Money getTotal() {
        taxCalculator=new TaxCalculatorFactory();
        Money total = new Money();
        Money subTotal = null;

        for (SalesLineItem lineItem : lineItems) {
            subTotal = lineItem.getSubTotal();
            total = total.add(subTotal);
        }
        for(TaxLineItem taxItem : taxItems){
            tax = new Tax(taxCalculator.GetForItem(taxItem).GetTax());
            System.out.println(tax.getperc());
            tax.setTaxAmount(taxItem.getSubTotal());
            total=total.add(tax.getTaxAmount());
        }
        //Tax calculation

        return total;
    }

    public void makePayment(Money cashTendered) {
        payment = new Payment(cashTendered);
    }


    @Override
    public String toString() {
        Money totalWithoutTax = new Money();
        Money subTotal = null;
        String s = "Date: " + date.toString() + "\n";
        Iterator i = lineItems.iterator();
        while (i.hasNext()) {
            SalesLineItem sli = (SalesLineItem) i.next();
            s += sli.toString();
            subTotal = sli.getSubTotal();
            totalWithoutTax = totalWithoutTax.add(subTotal);
        }
        s += "\n";
        s += "Total (without tax): " + totalWithoutTax + "\n";
        s += "Total: " + getTotal() + "\n";
        s += "(Tax Amount:" + tax.getTaxAmount()+ ")\n";

        if(payment!=null){
            s += "Payment: " + payment.toString() + "\n";
            s += "Balance: " + getBalance() + "\n";
        }
        s += "--------------------------------------\n";
        return s;
    }

}
