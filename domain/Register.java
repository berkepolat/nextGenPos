package com.company.domain;

import com.company.customTypes.CurrencyException;
import com.company.customTypes.ItemID;
import com.company.customTypes.Money;

import java.util.ArrayList;

public class Register {

    private ProductCatalog catalog;
    private Sale currentSale;
    private Store store;

    public Register(ProductCatalog catalog, Store store){
        this.catalog=catalog;
        this.store =store;
    }

    public void endSale(){
        currentSale.becomeComplete();
    }

    public String enterItem(ItemID id, int quantity) throws CurrencyException {
        ProductDescription desc = catalog.getProductDescription(id);
        return currentSale.makeLineItem(desc,quantity);
    }

    public void makeNewSale(){
        currentSale = new Sale();
    }

    public void makePayment(Money cashTendered){
        currentSale.makePayment(cashTendered);
    }

    public ArrayList<String> getProductIds(){
        return catalog.getProductIds();
    }


    public String getSummary (){
        return currentSale.toString();
    }

    public void saveSale(){
        store.addSale(currentSale);
    }

}
