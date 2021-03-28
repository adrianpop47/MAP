package threads;

import java.util.concurrent.*;

class Crescator implements Runnable{
    @Override
    public void run() {
        for(int i = 0; i < 10; i+= 2){
            System.out.println(i+" ");
            try{
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class Desrescator implements Runnable{
    @Override
    public void run() {
        for(int i = 9; i > 0; i-= 2){
            System.out.println(i+" ");
            try{
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}


public class Thread1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        Thread t1 = new Thread(new Crescator());
//        Thread t2 = new Thread(new Desrescator());
//
//        t1.start();
//        t2.start();
//
//        t1.join();
//        t2.join();

//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        executorService.execute(new Crescator());
//        executorService.execute(new Desrescator());
//        executorService.shutdown();


        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> result1 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int suma = 0;
                for(int i = 9; i > 0; i-= 2){
                    suma += i;
                    try{
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return suma;
            }
        });
        Future<Integer> result2 = executorService.submit(() ->{
                int suma = 0;
                for(int i = 0; i < 10; i+= 2){
                    suma += i;
                    try{
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return suma;
        });

        System.out.println(result1.get() * result2.get());
        executorService.shutdown();
    }
}
