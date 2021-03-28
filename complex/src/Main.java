public class Main {

    public static void main(String[] args) {
        // write your code here
        ExpressionParser parser = new ExpressionParser(args);
        parser.parse();
        ComplexExpression expression = ExpressionFactory.getInstance().createExpression(parser.getOperation(), parser.getComplexNumbers());
        NumarComplex rez = expression.execute();
        if(rez.getIm() < 0)
            System.out.println(rez.getRe() + " - " + -rez.getIm() + " * i");
        else
            if(rez.getIm() == 0)
                System.out.println(rez.getRe());
            else
                System.out.println(rez.getRe() + " + " + rez.getIm() + " * i");
    }
}
