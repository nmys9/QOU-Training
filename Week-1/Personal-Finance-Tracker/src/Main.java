import java.lang.invoke.CallSite;
import java.util.Scanner;
import java.util.SimpleTimeZone;

public class Main {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        boolean running=true;

        System.out.println("**** Welcome To Personal Finance Tracker ****");
        System.out.println("Please add your total amount : ");
        double totalAmount=Double.parseDouble(input.nextLine());

        Budget myBudget=new Budget(totalAmount);

        while (running){
            System.out.println("Please choose one of following options");
            System.out.println("1 - add new transaction");
            System.out.println("2 - view transactions");
            System.out.println("3 - view remaining amount");
            System.out.println("4 - Exit");

            String name,description,source,paymentMethod;
            double amount;
            int choice=Integer.parseInt(input.nextLine());

            switch (choice){
                case 1:
                    System.out.println("What type of your transaction ?\n1 - Income\n2 - Expense");
                    int type=Integer.parseInt(input.nextLine());
                    if(type == 1){
                        System.out.println("Enter category name:");
                        name=input.nextLine();

                        System.out.println("Enter category description:");
                        description=input.nextLine();

                        System.out.println("Amount of Income: ");
                        amount=Double.parseDouble(input.nextLine());

                        System.out.println("what of source: ");
                        source=input.nextLine();

                        Category category=new Category(name,description);
                        Transaction transaction=new IncomeTransaction(amount,category,source);
                        myBudget.addTransaction(transaction);

                    }else if(type==2) {
                        System.out.println("Enter category name:");
                        name=input.nextLine();

                        System.out.println("Enter category description:");
                        description=input.nextLine();

                        System.out.println("Amount of Expense: ");
                        amount=Double.parseDouble(input.nextLine());

                        System.out.println("Payment Method:");
                        paymentMethod=input.nextLine();

                        Category category=new Category(name,description);
                        Transaction transaction=new ExpenseTransaction(amount,category,paymentMethod);
                        myBudget.addTransaction(transaction);

                    }else {
                        System.out.println("Please Enter number 1 or 2");
                    }
                    break;
                case 2:
                    System.out.println("What do you want to show?");
                    System.out.println("1 - View All Transactions");
                    System.out.println("2 - View Expense Transaction");
                    System.out.println("3 - View Income Transaction");
                    choice=Integer.parseInt(input.nextLine());
                    switch (choice){
                        case 1:
                            myBudget.displayTransactions();
                            break;
                        case 2:
                            myBudget.displayExpenseTransactions();
                            break;
                        case 3:
                            myBudget.displayIncomeTransactions();
                            break;
                        default:
                            System.out.println("Please enter number between 1 - 3");
                    }
                    break;
                case 3:
                    System.out.println(myBudget.getTotalAmount());
                    break;
                case 4:
                    running=false;
                    break;
                default:
                    System.out.println("Please enter number between 1 -4");

            }

        }

    }
}