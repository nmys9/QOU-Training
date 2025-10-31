
public class Main {
    public static void main(String[] args) {

        char opCode=args[1].charAt(0);
        MathOperation mathOperation=MathOperation.fromSymbol(opCode);
        MathEquation equation=new MathEquation(mathOperation,Double.parseDouble(args[0]),Double.parseDouble(args[2]));
        equation.execute();
        System.out.println(equation);

    }
}

