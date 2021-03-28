package first;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

class ChristmasPresent{
    public boolean naughty;
    private boolean shiped;
    protected int elfTeam;


}


public class Main{
    public static void main(String[] args) throws InterruptedException, ExecutionException, ClassNotFoundException {
        Stream<String> ss = Stream.of("ee ", "xe ", "xe y ");
        var res = ss
                .filter(s -> {
                    System.out.println(s);
                    return s.contains("x");
                })
                .map((x) -> {
                    System.out.print(x);
                    return x.toUpperCase();
                })
                .reduce((x, y) -> x + y);
        res.ifPresent(System.out::println);
    }
}
