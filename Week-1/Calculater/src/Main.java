
public class Main {
    public static void main(String[] args) {

        if(args.length!=3){
            System.out.println("Usage: java Main <leftVal> <operation> <rightVal> ");
            return;
        }else{
            char opCode=args[1].charAt(0);
            MathOperation mathOperation=MathOperation.fromتشSymbol(opCode);
            MathEquation equation=new MathEquation(mathOperation,args[0],args[2]);
            equation.execute();
            System.out.println(equation);
        }
    }
}

