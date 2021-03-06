import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestApp {
    public static final int N = 100000;
    public static final int M = 100000;
    private static SharedObject sharedObject;
    private static List<ThreadInc> threadIncList;
    private static List<ThreadDec> threadDecList;

    public static void main(String[] args) {
        // фиксация текущего времени 1
        long m1 = System.currentTimeMillis();

        sharedObject = new SharedObject();
        threadIncList = new ArrayList<>();
        threadDecList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            ThreadInc threadInc = new ThreadInc(sharedObject);
            threadIncList.add(threadInc);
            threadInc.start();
            try {
                threadInc.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int j = 0; j < M; j++) {
            ThreadDec threadDec = new ThreadDec(sharedObject);
            threadDecList.add(threadDec);
            //threadDecList.add( new ThreadDec(sharedObject) );
            threadDec.start();
            try {
                threadDec.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // фиксация текущего времени 2
        long m2 = System.currentTimeMillis();
        System.out.println(sharedObject.getCounter());
        System.out.println(m2-m1/* время 2 - время 1 (перевести в часы, минуты, секунды) */);
    }
}
