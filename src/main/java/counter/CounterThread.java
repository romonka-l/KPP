package counter;

public class CounterThread extends Thread{
    public CounterThread(String name){
        super(name);
    }

    @Override
    public void run(){
        RequestCounter.inc();
    }
}
