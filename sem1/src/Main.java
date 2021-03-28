import domain.MessageTask;
import domain.SortingStrategy;
import domain.SortingTask;
import factory.Strategy;
import runners.DelayTaskRunner;
import runners.PrinterTaskRunner;
import runners.StrategyTaskRunner;

import java.time.LocalDateTime;

public class Main {


    public static MessageTask[] createMessageTaskArray(){
        MessageTask t1=new MessageTask("1","Feedback lab1",
                "Ai obtinut 9.60","Gigi", "Ana", LocalDateTime.now());
        MessageTask t2=new MessageTask("2","Feedback lab1",
                "Ai obtinut 5.60","Gigi", "Ana", LocalDateTime.now());
        MessageTask t3=new MessageTask("3","Feedback lab3",
                "Ai obtinut 10","Gigi", "Ana", LocalDateTime.now());
        return new MessageTask[]{t1,t2,t3};
    }

    public static void main(String[] args) {
        System.out.println(args[0] + " " + args[1]);
        var v = createMessageTaskArray();
//        for (MessageTask el : v)
//            System.out.println(el.toString());
//        StrategyTaskRunner runner = new StrategyTaskRunner(Strategy.FIFO);
//        runner.addTask(v[0]);
//        runner.addTask(v[1]);
//        runner.addTask(v[2]);
//        PrinterTaskRunner printerRunner = new PrinterTaskRunner(runner);
//        printerRunner.executeAll();


        StrategyTaskRunner runner = new StrategyTaskRunner(Strategy.FIFO);
        runner.addTask(v[0]);
        runner.addTask(v[1]);
        runner.addTask(v[2]);
        DelayTaskRunner delayRunner = new DelayTaskRunner(runner);
        PrinterTaskRunner printerRunner = new PrinterTaskRunner(delayRunner);
        printerRunner.executeAll();

//        int[] arr = new int[3];
//        arr[0]=1;
//        arr[1]=5;
//        arr[2]=3;
//        SortingTask t = new SortingTask("1","asd",arr, SortingStrategy.QUICK);
//        t.execute();
    }

}
