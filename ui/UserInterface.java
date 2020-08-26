package com.company.ui;

import com.company.customTypes.CurrencyException;
import com.company.customTypes.ItemID;
import com.company.customTypes.Money;
import com.company.domain.Register;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Register register;
    private Scanner input = new Scanner(System.in);

    public UserInterface(Register register) {
        this.register = register;
        processSale();
    }

    private void processSale(){
        while (true){
            askToCreateNewSale();
            askToEnterItems();
            endSale();
            Money cashTendered= askForPayment();
            makePayment(cashTendered);
            System.out.println("\n\n----SALE SUMMARY AFTER PAYMENT----");
            getSummary();
            register.saveSale();
        }
    }

    private void askToCreateNewSale() {
        System.out.println("Please type YES to start a new sale, NO to exit");
        String answer = input.nextLine();
        if (answer.toUpperCase().equals("YES")) {
            makeNewSale();
        }
        else if(answer.toUpperCase().equals("NO")){
            System.exit(0);
        }
        else {
            askToCreateNewSale();
        }
    }

    public void makeNewSale() {
        register.makeNewSale();
    }

    public void askToEnterItems() {
        boolean another;
        do {
            another = enterItem();
        } while (another);
    }

    public boolean enterItem() {
        ArrayList<String> productIds = register.getProductIds();
        System.out.println("Store Items:" + productIds);
        System.out.println("Please enter an ID of the product: ");
        String answer = input.nextLine();

        ItemID id = new ItemID(answer);
        System.out.println("Please enter quantity of the product \"" + answer + "\":");
        int quantity = input.nextInt();
        input.nextLine(); //throw away the \n not consumed by nextInt()

        try{
            System.out.println(register.enterItem(id, quantity));
        }
        catch (CurrencyException e){
            System.out.println("Product could not be added to sale.");
            System.out.println("CurrencyException:" + e.getMessage() +"\n");
        }

        System.out.println("Do you want to add another item? (YES to continue, else end sale)");
        String another = input.nextLine();
        if (another.toUpperCase().equals("YES")) {
            return true;
        } else {
            return false;
        }
    }

    public void endSale() {
        register.endSale();
        System.out.println("\n\n----SALE SUMMARY----");
        getSummary();
    }

    public Money askForPayment() {
        System.out.println("Please enter the payment:");
        double payment = input.nextDouble();
        input.nextLine(); //throw away the \n not consumed by nextDouble()
        Money cashTendered = new Money(payment);
        return cashTendered;
    }

    private void makePayment(Money cashTendered) {
        register.makePayment(cashTendered);
    }

    private void getSummary(){
        System.out.println(register.getSummary());
    }

}
