package systemDesign.concurrency;

public class Counter {
    int val;
    public Counter(){
        val = 0;
    }
    public void increase(){
        val++;
    }
    public void decrease(){
        val--;
    }
}
