package TryLeetCode.Period01;

public class MyThreadTest extends Thread{
    private int i=20;
    @Override
    public void run() {
        while (i>0){
            System.out.println(Thread.currentThread().getName()+":,i:"+i--);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
