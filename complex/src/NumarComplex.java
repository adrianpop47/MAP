public class NumarComplex {
    private double re;
    private double im;

    public NumarComplex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public void setRe(double re) {
        this.re = re;
    }

    public double getIm() {
        return im;
    }

    public void setIm(double im) {
        this.im = im;
    }

    public NumarComplex adunare(NumarComplex other){
        this.re += other.re;
        this.im += other.im;
        return this;
    }

    public NumarComplex scadere(NumarComplex other){
        this.re -= other.re;
        this.im -= other.im;
        return this;
    }

    public NumarComplex inmultire(NumarComplex other)
    {
        double a = this.re;
        double b = this.im;
        double c = other.re;
        double d = other.im;

        this.re = a * c - b * d;
        this.im = a * d + b * c;

        return this;
    }

    public NumarComplex impartire(NumarComplex other)
    {
        double a = this.re;
        double b = this.im;
        double c = other.re;
        double d = other.im;

        this.re = (a * c + b * d) / (c*c + d*d);
        this.im = (b * c - a * d) / (c*c + d*d);

        return this;
    }

    public NumarComplex conjugat()
    {
        this.im *= -1;
        return this;
    }
}
