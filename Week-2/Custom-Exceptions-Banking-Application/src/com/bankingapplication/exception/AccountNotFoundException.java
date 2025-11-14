package com.bankingapplication.exception;
//رقم الحساب غير موجود
public class AccountNotFoundException extends RuntimeException{
    private final String accountIdentifier;
    public AccountNotFoundException(String accountIdentifier, String message){
        super(message);
        this.accountIdentifier=accountIdentifier;
    }

    public String getAccountIdentifier() {
        return accountIdentifier;
    }
}
