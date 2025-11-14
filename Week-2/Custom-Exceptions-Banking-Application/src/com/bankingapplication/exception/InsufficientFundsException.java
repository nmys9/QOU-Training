package com.bankingapplication.exception;
//عدم كفاية الرصيد
public class InsufficientFundsException extends RuntimeException{
    private final double currentBalance;
    private final double requestedAmount;

    public InsufficientFundsException(String accountId, double currentBalance,double requestedAmount){
        super("Account " + accountId + " has insufficient funds. Current: "+ currentBalance + ", Requested: " + requestedAmount);
        this.currentBalance=currentBalance;
        this.requestedAmount=requestedAmount;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }
}
