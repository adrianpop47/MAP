import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/// TOATE PROBLEMELE SUNT APROXIMATIV CU CE AM PRIMIT, CUMULAT DE PE MAI MULTE RANDURI
/// UNELE AFISARI AU FOST MODIFICIATE PENTRU SIMPLITATE (de la "Hello" la "Metoda <x> din clasa <y>")

class AA{
    static int x, y; {x=y=1010;}
    void setX(int val) {x=val;}
    void setY(int val) {y=val;}
    void m1(){System.out.println("m1 din AA");}
    static void m2(){System.out.println("A" + (x + y));}
}

class BB extends AA{
    void m1(){System.out.println("m1 din BB");}
    static void m2(){System.out.println("B" + x + y);}
}

class Generic<E, T>{
    E e;
    T t;
    void setE(E val) {e=val;}
    void setT(T val) {t=val;}
    E getE(){return this.e;}
    T getT(){return this.t;}
}


public class Main {

    /*
    // --------------------- Problema 1 ---------------------
    // Cu colectoare
    public static void main(String[] args) {
        List<String> list= new ArrayList<String>();
        list.add("abc");
        list.add("acd");
        list.add("cde");
        var rez =list.stream()
                .filter(s -> {System.out.println(s); return s.contains("a");})
                .map(x -> {System.out.println(x); return  x.toUpperCase();})
                .collect(Collectors.toList());

        rez.forEach(System.out::println);
    }
    */

    /*
    // --------------------- Problema 2 ---------------------
    // Fara colectoare
    public static void main(String[] args) {
        List<String> list= new ArrayList<String>();
        list.add("abc");
        list.add("acd");
        list.add("cde");
        list.stream()
        .filter(s -> {System.out.println(s); return s.contains("a");})
        .map(x -> {System.out.println(x); return  x.toUpperCase();})
        .forEach(System.out::println);

    }
    */

    /*
    // --------------------- Problema 3 ---------------------
    public static void main(String[] args) {
        AA a = new BB();
        a.setX(100);
        a.setY(10);
        a.m1();
        a.m2();
    }
    */

    /*
    // --------------------- Problema 4 ---------------------
    public static void main (String[] args){
        Generic<Integer, String> generic = new Generic<>();
        generic.setE(5000);
        generic.setE("abcd"); // Eroare de compilare
        System.out.println(generic.getE());
        System.out.println(generic.getT());
    }
    */

    // EXERCITII INCOMPLETE

    /*
    // --------------------- Problema 5 ---------------------
    public static void main (String[] args){
        String[] strings = new String[]{"hello", "world"};
        String[] obj = strings; // Aici se facea referinta la strings, nu se copia

        // Era un for aici care tot adauga hello la capatul listei de fiecare data cand il gasea pe "hello" in lista
    }
    */
}
