package com.bankingapplication.exception;
//إيداع سالب
public class NegativeDepositException extends IllegalArgumentException{
    private final double depositAmount;

    public NegativeDepositException(double depositAmount){
        super("Deposit amount cannot be negative: "+ depositAmount);
        this.depositAmount=depositAmount;
    }

    public double getDepositAmount() {
        return depositAmount;
    }
}
