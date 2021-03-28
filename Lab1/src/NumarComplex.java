import java.util.Objects;

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

    public NumarComplex adunare(NumarComplex other)
    {
        this.re += other.re;
        this.im += other.im;
        return this;
    }

    public NumarComplex scadere(NumarComplex other)
    {
        this.re -= other.re;
        this.im -= other.im;
        return this;
    }

}
