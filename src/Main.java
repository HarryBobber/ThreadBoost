import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main implements Callable<Long> {

    int s;
    int n;

    public Main(){
        s = Integer.MAX_VALUE;
        n = 10;
        System.out.println("1 Thread: " + count());
        Future[] futures = new Future[n];
        ExecutorService executorService = Executors.newScheduledThreadPool(n);
        for(int i=0; i<n; i++){
            futures[i] = executorService.submit(this::call);
        }
        long sum = 0;
        try{
            for(int i=0; i<n; i++){
                sum += (long)(futures[i].get());
            }
            System.out.println(n + " Thread: " + sum/n);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        executorService.shutdown();
    }

    public long count(){
        long start = System.currentTimeMillis();
        int i=0;
        while(i<s){
            i++;
        }
        System.out.println(i);
        long end = System.currentTimeMillis();
        return (end-start);
    }

    @Override
    public Long call() throws Exception {
        long start = System.currentTimeMillis();
        int i=0;
        while(i<s/n){
            i++;
        }
        System.out.println(i);
        long end = System.currentTimeMillis();
        return (end-start);
    }

    public static void main(String[] args){
        Main test = new Main();
    }

}