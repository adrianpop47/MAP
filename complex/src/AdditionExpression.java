public class AdditionExpression extends ComplexExpression{

    public AdditionExpression(Operation operation, NumarComplex[] args) {
        super(operation, args);
    }

    @Override
    public NumarComplex executeOneOperation(NumarComplex c1, NumarComplex c2) {
        return c1.adunare(c2);
    }
}
