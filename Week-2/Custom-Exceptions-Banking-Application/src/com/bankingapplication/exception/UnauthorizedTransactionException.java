package com.bankingapplication.exception;
//عملية غير مصرح بها
public class UnauthorizedTransactionException extends SecurityException{
    private final String attemptingUser;
    private final String attemptedAction;

    public UnauthorizedTransactionException(String attemptingUser ,String attemptedAction){
        super("User " + attemptingUser + " is not authorized to perform the action: " + attemptedAction);
        this.attemptingUser=attemptingUser;
        this.attemptedAction=attemptedAction;
    }

    public String getAttemptingUser() {
        return attemptingUser;
    }

    public String getAttemptedAction() {
        return attemptedAction;
    }
}
