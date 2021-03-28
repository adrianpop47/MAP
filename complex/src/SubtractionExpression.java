public class SubtractionExpression extends ComplexExpression {

    public SubtractionExpression(Operation operation, NumarComplex[] args) {
        super(operation, args);
    }

    @Override
    public NumarComplex executeOneOperation(NumarComplex c1, NumarComplex c2) {
        return c1.scadere(c2);
    }
}
