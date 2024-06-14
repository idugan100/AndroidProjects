package com.example.coffeeorder;

public  class OrderCalculator {
    private int quantity=0;


    public int getQuantity() {
        return quantity;
    }

    public void  incrementQty(){
        quantity++;
    }

    public void  decrementQty(){
        if(quantity>0){
            quantity--;
        }
    }
    private double cacluateCost(boolean cream, boolean chocolate){
        double perCupCost = 4.0;
        if(cream){
            perCupCost+=.5;
        }
        if(chocolate){
            perCupCost+=1.0;
        }
        double q = (double) quantity;
        return (perCupCost*q);
    }

    public String getOrderSummary(boolean cream, boolean chocolate ){
        String output;
        double cost = cacluateCost(cream,chocolate);
        output = "Add whipped cream? ";
        output += cream ? "yes\n":"no\n";
        output += "Add chocolate? ";
        output += chocolate ? "yes\n":"no\n";
        output += "Quantity: " + String.valueOf(quantity)+"\n\n";
        output += "Cost: $"+String.format("%.2f", cost)+"\n";
        output +="THANK YOU!";
        return output;
    }

}
