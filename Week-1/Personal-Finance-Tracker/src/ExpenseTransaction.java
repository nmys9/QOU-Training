public class ExpenseTransaction extends Transaction{

    private String paymentMethod;

    ExpenseTransaction(){}

    ExpenseTransaction(double amount,Category category,String paymentMethod){
        super(amount,category);
        this.paymentMethod=paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public String toString() {
        return String.format("----- Transaction Details -----\nType: Expense \nPayment Method: %s \n%s ",
                this.paymentMethod,super.toString());

    }
}
