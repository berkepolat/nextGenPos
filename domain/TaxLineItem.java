/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.domain;

import com.company.customTypes.Money;

/**
 *
 * @author Berke
 */
public class TaxLineItem {
    ProductDescription description;
    int quantity;
    String productType;
    public TaxLineItem(ProductDescription desc,int quant){
        int id = Integer.parseInt(desc.getITemID().getId());
            description=desc;
            quantity=quant;
        switch(id){
            case 1: case 2: case 3:
                productType="alcohol";
                break;
            case 4: case 5: case 6:
                productType="food";
                break;
            case 7: case 8: case 9: case 10:
                productType="softDrink";
                break;
            default:
                productType="default";
        }
    }
    public String getType(){
        return productType;
    }
    public Money getSubTotal(){
        return description.getPrice().times(quantity);
    }
}
