package com.company.domain;

import com.company.fileOperations.SaleFileOperator;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private ProductCatalog catalog = new ProductCatalog();
    private Register register = new Register(catalog, this);
    private List<Sale> completedSales = new ArrayList<>();
    SaleFileOperator sfo = new SaleFileOperator("..\\NextGenPOS\\Sales.txt");

    public Register getRegister(){
        return register;
    }

    public void addSale(Sale s){
        completedSales.add(s);
        saveSale(s);
    }

    public void saveSale(Sale s){
        sfo.saveSale(s);
    }
}
