import java.util.HashSet;
import java.util.Set;

public class Budget {
    private double totalAmount;
    private Set<Transaction> transactions;

    Budget(){}

    Budget(double totalAmount){
        this.totalAmount=totalAmount;
        this.transactions=new HashSet<>();
    }

    public double getTotalAmount() {
        return calcTotalAmount();
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setTransactions(HashSet<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double calcTotalAmount(){
        if(transactions==null){
            return totalAmount;
        }
        for(Transaction transaction: transactions){
            if(transaction instanceof IncomeTransaction){
                totalAmount+= transaction.getAmount();
            }else if(transaction instanceof ExpenseTransaction){
                totalAmount-= transaction.getAmount();
            }
        }
        return totalAmount;
    }

    public void displayTransactions(){
        if(transactions.isEmpty()){
            System.out.println("No Transactions yet");
        }else{
            for(Transaction transaction:transactions){
                System.out.println(transaction);
            }
        }
    }

    public void displayExpenseTransactions(){
        if(transactions.isEmpty()){
            System.out.println("No Transactions yet");
        }else{
            for(Transaction transaction:transactions){
                if(transaction instanceof ExpenseTransaction)
                    System.out.println(transaction);
            }
        }
    }

    public void displayIncomeTransactions(){
        if(transactions.isEmpty()){
            System.out.println("No Transactions yet");
        }else{
            for(Transaction transaction:transactions){
                if(transaction instanceof IncomeTransaction)
                    System.out.println(transaction);
            }
        }
    }



    public void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
        System.out.println("Total Amount: "+calcTotalAmount());
    }

}
