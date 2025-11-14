package com.bankingapplication.exception;
//رقم حساب غير صالح
public class InvalidAccountNumberException extends IllegalArgumentException{
    private final String invalidNumber;

    public InvalidAccountNumberException(String invalidNumber, String message){
        super(message);
        this.invalidNumber=invalidNumber;
    }

    public String getInvalidNumber() {
        return invalidNumber;
    }
}
