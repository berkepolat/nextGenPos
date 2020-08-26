package com.company.domain;

import com.company.customTypes.ItemID;
import com.company.customTypes.Money;


public class ProductDescription {
    private ItemID id;
    private Money price;
    private String description;

    public ProductDescription(ItemID id, Money price, String description){
        this.id=id;
        this.price =price;
        this.description =description;
    }

    public ItemID getITemID(){
        return id;
    }

    public Money getPrice(){
        return price;
    }

    public String getDescription(){
        return  description;
    }

    @Override
    public String toString(){
        return id.toString()+ " - "+ price.toString()+" - " + description;
    }



}
