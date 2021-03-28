package lab9;

public interface MyInterface {
    int X= 10;
    int y = 0;
    int Z= 20;

    default int x() {return 0;}

    abstract void foo();

    int f(int x);
}
