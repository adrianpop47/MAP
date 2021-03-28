public class ExpressionFactory {
    private static ExpressionFactory instance = null;

    private ExpressionFactory(){}

    public static ExpressionFactory getInstance(){
        if(instance == null)
        {
            instance = new ExpressionFactory();
        }
        return instance;
    }

    public ComplexExpression createExpression(Operation operation, NumarComplex[] args){
        switch (operation)
        {
            case ADDITION: return new AdditionExpression(operation, args);
            case SUBTRACTION: return new SubtractionExpression(operation, args);
            case MULTIPLICATION: return new MultiplicationExpression(operation, args);
            case DIVISION: return new DivisionExpression(operation, args);
        }
        return null;
    }
}
