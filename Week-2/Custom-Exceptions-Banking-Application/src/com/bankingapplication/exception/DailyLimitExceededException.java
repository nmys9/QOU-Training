package com.bankingapplication.exception;
//تجاوز الحد اليومي
public class DailyLimitExceededException extends RuntimeException{
    private final double dailyLimit;
    private final double amountAttempted;
    private final double alreadyWithdrawn;

    public DailyLimitExceededException(String accountId,double dailyLimit,double amountAttempted,double alreadyWithdrawn){
        super("Daily withdrawal limit exceeded for account " + accountId + ". Limit: " + dailyLimit);
        this.dailyLimit=dailyLimit;
        this.amountAttempted=amountAttempted;
        this.alreadyWithdrawn=alreadyWithdrawn;
    }

    public double getDailyLimit() {
        return dailyLimit;
    }

    public double getAmountAttempted() {
        return amountAttempted;
    }

    public double getAlreadyWithdrawn() {
        return alreadyWithdrawn;
    }
}
