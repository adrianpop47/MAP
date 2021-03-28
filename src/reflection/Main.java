package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class A<T> {
    private T entity;

    public A(T entity) {
        this.entity = entity;
    }

    private void f()
    {
        System.out.println(entity.getClass());
    }
}

class B<T> extends A{

    public B(T field) {
        super(field);
    }
}


public class Main {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        A<Integer> a = new A<>(1);
        B<A> b = new B<>(a);

        Method print = A.class.getMethod("f");
//        print.setAccessible(true);
        print.invoke(b);
    }
}

//class java.lang.Integer
//class reflection.A
//class reflection.B
