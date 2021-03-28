public class ExpressionFactory {
    private static ExpressionFactory instance = null;

    public ExpressionFactory() {
    }

    public static ExpressionFactory getInstance() {
        if(instance == null)
        {
            instance = new ExpressionFactory();
        }
        return instance;
    }

    public Complex createExpression(Operation operation, Complex[] args){
        switch (operation)
        {
            case ADDITION: return new AdditionExpression(args);
        }
    }
}
