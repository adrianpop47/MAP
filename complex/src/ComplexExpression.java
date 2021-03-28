public abstract class ComplexExpression {
    private Operation operation;
    private NumarComplex[] args;

    public ComplexExpression(Operation operation, NumarComplex[] args) {
        this.operation = operation;
        this.args = args;
    }

    public abstract NumarComplex executeOneOperation(NumarComplex c1, NumarComplex c2);

    public NumarComplex execute(){
        NumarComplex c = new NumarComplex(args[0].getRe(), args[0].getIm());
        for(int i = 1; i < args.length; i++)
        {
            c = executeOneOperation(c, args[i]);
        }
        return c;
    }
}
