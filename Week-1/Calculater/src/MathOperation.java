public enum MathOperation {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('*'),
    DIVIDE('/');

    private char symbol;
    public char getSymbol(){return symbol;}
    private MathOperation(char symbol){
        this.symbol=symbol;
    }

    public static MathOperation fromSymbol(char symbol){
        for(MathOperation op: values()){
            if(op.getSymbol()==symbol) return op;
        }
        throw new IllegalArgumentException("Invalid Symbol " +symbol);
    }
}
