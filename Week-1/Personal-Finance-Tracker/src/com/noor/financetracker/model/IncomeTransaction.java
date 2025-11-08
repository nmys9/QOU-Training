package com.noor.financetracker.model;

public class IncomeTransaction extends Transaction{

    private String source;

    public IncomeTransaction(){}
    public IncomeTransaction(double amount, Category category, String source){
        super(amount,category);
        this.source=source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    @Override
    public String toString() {
        return String.format("----- com.noor.financetracker.model.Transaction Details ----- \nType: Income \nSource: %s \n%s",
                this.source,super.toString());
    }
}

