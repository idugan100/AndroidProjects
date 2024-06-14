package com.example.carloanapp;

public class Auto {
    static final double STATE_TAX = .07;
    static final double INTEREST_RATE = .09;

    private double price;
    private double downPayment;
    private int loanTerm;

    void setPrice(double p){
        price=p;
    }
    double getPrice(){
        return price;
    }
    void setDownPayment(double d){
        downPayment=d;
    }
    double getDownPayment(){
        return downPayment;
    }
    public void setLoanTerm(String term){
        if(term.contains("2")){
            loanTerm =2;
        }
        else if(term.contains("3")){
            loanTerm =3;
        }
        else {
            loanTerm=4;
        }
    }
    public int getLoanTerm(){
        return loanTerm;
    }

    public double taxAmount(){
        return STATE_TAX * price;
    }
    public double totalAmount(){
        return price + taxAmount();
    }
    public double borrowedAmount(){
        return totalAmount()-downPayment;
    }

    public double interestAmount(){

        return borrowedAmount()*INTEREST_RATE;
    }

    public double monthlyPayment(){
        return borrowedAmount()/(loanTerm*12);
    }
}
