import com.noor.financetracker.service.Budget;
import java.util.Scanner;

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
            System.out.println("1 - Add new transaction");
            System.out.println("2 - View transactions");
            System.out.println("3 - View Transaction based on Category");
            System.out.println("4 - View remaining amount");
            System.out.println("5 - Exit");

            int choice=Integer.parseInt(input.nextLine());

            switch (choice){
                case 1:
                    System.out.println("What type of your transaction ?\n1 - Income\n2 - Expense");
                    int type=Integer.parseInt(input.nextLine());
                    if(type == 1){
                        myBudget.addIncomeTransactio();
                    }else if(type==2) {
                        myBudget.addExpenseTransaction();
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
                    myBudget.displayTransactionsByType(choice);
                    break;
                case 3:
                    myBudget.viewCategories();
                    myBudget.viewTransactionBasedOnCategory();
                    break;
                case 4:
                    System.out.println("Total Amount: "+myBudget.getTotalAmount());
                    break;
                case 5:
                    running=false;
                    break;
                default:
                    System.out.println("Please enter number between 1 -4");

            }

        }

    }
}