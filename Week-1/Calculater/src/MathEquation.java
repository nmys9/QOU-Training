public class MathEquation {
    double leftVal;
    double rightVal;
    MathOperation mathOperation;
    double result;

    public MathEquation(){}

    public MathEquation(MathOperation mathOperation){this.mathOperation=mathOperation;}

    public MathEquation(MathOperation mathOperation,double leftVal,double rightVal){
        this(mathOperation);
        this.leftVal=leftVal;
        this.rightVal=rightVal;
    }

    public MathEquation(MathOperation mathOperation,String leftVal,String rightVal){
        this(mathOperation,Double.parseDouble(leftVal),Double.parseDouble(rightVal));
    }

    @Override
    public String toString() {
        return String.format("%.2f %c %.2f = %.2f",leftVal,mathOperation.getSymbol(),rightVal,result);
//        return leftVal + " " + mathOperation.getSymbol() + " " + rightVal + " = " + result;
    }

    public void execute(){
        switch (mathOperation){
            case ADD -> result= leftVal+rightVal;
            case SUBTRACT -> result=leftVal-rightVal;
            case MULTIPLY -> result=leftVal* rightVal;
            case DIVIDE -> {
                if(rightVal ==0)result=0.0d;
                else result=leftVal/rightVal;
            }
            default -> result=0.0d;
        }
    }

    public void execute(double leftVal,double rightVal){
        this.leftVal=leftVal;
        this.rightVal=rightVal;

        execute();
    }

    public double getLeftVal(){return leftVal;}
    public void setLeftVal(double leftVal){this.leftVal=leftVal;}

    public double getRightVal(){return rightVal;}
    public void setRightVal(double rightVal){this.rightVal=rightVal;}

    public MathOperation getMathOperation(){return mathOperation;}
    public void setMathOperation(MathOperation mathOperation){this.mathOperation=mathOperation;}

    public double getResult(){return result;}
    public void setResult(double result){this.result=result;}

}
