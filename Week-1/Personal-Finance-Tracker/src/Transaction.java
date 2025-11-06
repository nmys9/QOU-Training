import java.time.LocalDateTime;
import java.util.Date;

public class Transaction {
    private double amount;
    private LocalDateTime dateTime;
    private Category category;


    public Transaction(){}

    public  Transaction(double amount,Category category){
        this.dateTime=LocalDateTime.now();
        this.amount=amount;
        this.category=category;
    }

    public LocalDateTime getDateTime() {return dateTime;}
    public double getAmount() {return amount;}
    public Category getCategory() {return category;}
    public void setCategory(Category category) {this.category = category;}



    @Override
    public String toString() {
        return String.format("%s \namount: %.2f \nDate %s",
                this.category,this.amount,this.dateTime);
    }
}
