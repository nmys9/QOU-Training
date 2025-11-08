package com.noor.financetracker.model;

public class ExpenseTransaction extends Transaction{

    private String paymentMethod;

    public ExpenseTransaction(){}

    public ExpenseTransaction(double amount, Category category, String paymentMethod){
        super(amount,category);
        this.paymentMethod=paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public String toString() {
        return String.format("----- com.noor.financetracker.model.Transaction Details -----\nType: Expense \nPayment Method: %s \n%s",
                this.paymentMethod,super.toString());

    }
}
