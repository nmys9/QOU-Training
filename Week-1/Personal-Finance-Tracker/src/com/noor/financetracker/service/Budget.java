package com.noor.financetracker.service;
import com.noor.financetracker.model.*;

import java.util.*;

public class Budget {
    private double totalAmount;
    private Set<Transaction> transactions;
    private Map<Integer,Category> categories;
    private static Scanner input=new Scanner(System.in);


    public Budget(){}

    public Budget(double totalAmount){
        this.totalAmount=totalAmount;
        this.transactions=new HashSet<>();
        this.categories=new HashMap<>();
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

    public Category getOrCreateCategory(String name,String description){
        for(Category category:categories.values()){
            if(category.getName().equalsIgnoreCase(name))
                return category;
        }
        Category newCategory=new Category(name,description);
        categories.put(newCategory.getId(),newCategory);
        return newCategory;
    }

    public void addIncomeTransactio() {
        System.out.println("Enter category name:");
        String name=input.nextLine();

        System.out.println("Enter category description:");
        String description=input.nextLine();

        double amount;
        while (true){
            try{
                System.out.println("Amount :");
                amount=Double.parseDouble(input.nextLine());
                break;
            }catch (NumberFormatException e){
                System.err.println("Please Enter Number for Amount!!");
                continue;
            }
        }
        System.out.println("what of source: ");
        String source=input.nextLine();

        Category category=getOrCreateCategory(name,description);
        transactions.add(new IncomeTransaction(amount, category, source));
        System.out.println("Add com.noor.financetracker.model.Transaction Successfully");

    }


    public void addExpenseTransaction() {
        System.out.println("Enter category name:");
        String name=input.nextLine();

        System.out.println("Enter category description:");
        String description=input.nextLine();

        double amount;
        while (true){
            try{
                System.out.println("Amount :");
                amount=Double.parseDouble(input.nextLine());
                if(amount> totalAmount){
                    System.out.println("The amount is insufficient");
                    continue;
                }
                break;
            }catch (NumberFormatException e){
                System.err.println("Please Enter Number for Amount!!");
                continue;
            }
        }
        System.out.println("Payment Method:");
        String paymentMethod=input.nextLine();

        Category category=getOrCreateCategory(name,description);
        transactions.add(new ExpenseTransaction(amount, category, paymentMethod));
        System.out.println("Add com.noor.financetracker.model.Transaction Successfully");

    }

    public double calcTotalAmount(){
        if(transactions==null){
            return totalAmount;
        }
        transactions.forEach(transaction -> {
            if(transaction instanceof IncomeTransaction){
                totalAmount+= transaction.getAmount();
            }else if(transaction instanceof ExpenseTransaction){
                totalAmount-= transaction.getAmount();
            }
        });
        return totalAmount;
    }

    public void displayTransactionsByType(int choice){
        if(transactions.isEmpty()){
            System.out.println("No Transactions yet");
        }else{
            switch (choice){
                case 1:
                    transactions.forEach(transaction->{System.out.println(transaction);});
                    break;
                case 2:
                    transactions.forEach(transaction -> {
                        if(transaction instanceof ExpenseTransaction)
                            System.out.println(transaction);
                    });
                    break;
                case 3:
                    transactions.forEach(transaction-> {
                       if(transaction instanceof IncomeTransaction)
                           System.out.println(transaction);
                    });
                    break;
                default:
                    System.out.println("Please enter number between 1 - 3");
            }
        }
    }

    public void viewCategories(){
        if(categories.isEmpty()) {
            System.out.println("No categories yet");
            return;
        }else {
            categories.forEach((ID, category) -> {
                System.out.println(ID + " - " + category.getName());
            });
        }
    }

    public void viewTransactionBasedOnCategory(){
        double total=0.0d;
        System.out.println("Please Enter ID for com.noor.financetracker.model.Category you want to com.noor.financetracker.model.Transaction view :");
        int categoryID=Integer.parseInt(input.nextLine());
        if(categories.containsKey(categoryID)){
            for(Transaction transaction: transactions){
                if(transaction.getCategory().getId()==categoryID) {
                    total+=transaction.getAmount();
                    System.out.println(transaction);
                }
            }
            System.out.println("Total amount for this category : "+total);
        }else{
            System.out.println("The ID category not fount");
        }

    }
}
