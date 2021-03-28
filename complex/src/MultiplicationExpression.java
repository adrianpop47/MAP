public class MultiplicationExpression extends ComplexExpression{

    public MultiplicationExpression(Operation operation, NumarComplex[] args) {
        super(operation, args);
    }

    @Override
    public NumarComplex executeOneOperation(NumarComplex c1, NumarComplex c2) {
        return c1.inmultire(c2);
    }
}
