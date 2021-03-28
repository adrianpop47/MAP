public class DivisionExpression extends ComplexExpression{

    public DivisionExpression(Operation operation, NumarComplex[] args) {
        super(operation, args);
    }

    @Override
    public NumarComplex executeOneOperation(NumarComplex c1, NumarComplex c2) {
        return c1.impartire(c2);
    }
}
