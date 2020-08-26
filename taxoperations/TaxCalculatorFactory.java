/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.taxoperations;

import com.company.domain.Sale;
import com.company.domain.TaxLineItem;

/**
 *
 * @author Berke
 */
public class TaxCalculatorFactory {
        public TaxCalculatorFactory(){
            
        }
    public static ITaxCalculator GetForItem(TaxLineItem t)
        {
            String type=t.getType();
            switch (type)
            {
                case "alcohol":
                    return new AlcohoolTaxCalculator();
                case "softDrink":
                    return new SoftDrinksTaxCalculator();
                case "food":
                    return new FoodTaxCalculator();
                default :
                    return new defaultTaxCalculator();
            }
        }
}
