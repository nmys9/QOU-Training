package com.bankingapplication.exception;
//حساب مجمّد
public class FrozenAccountException extends IllegalStateException{
    private final String accountId;

    public FrozenAccountException(String accountId,String reason){
        super("Account " + accountId + " is currently frozen. Reason: " + reason);
        this.accountId=accountId;
    }

    public String getAccountId() {
        return accountId;
    }
}
