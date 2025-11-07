import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

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

    public String getDateTime() {
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dateTime.format(formatter);
    }

    public double getAmount() {return amount;}
    public Category getCategory() {return category;}
    public void setCategory(Category category) {this.category = category;}


    @Override
    public String toString() {
        return String.format("%s \namount: %.2f \nDate %s",
                this.category,this.amount,getDateTime());
    }
}
