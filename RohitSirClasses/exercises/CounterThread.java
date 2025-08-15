import java.util.*;
class CounterThread implements Runnable{
    private final Object lock = new Object();
    private boolean running=true;
    private boolean isPaused=false;
    private int count=1;
    public void run(){
	while(running){
	    synchronized(lock){
                while(isPaused){
		    try{
		        lock.wait();
		    }catch(InterruptedException e){
		        running=false;
		    }
                }
		if(!running){
		    break;
		}
		System.out.println("count:- "+count);
                count++;
            }
	    try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                running = false;
            }
        }
        System.out.println("Counter has finished");
    }
    public void pause(){    
        synchronized(lock){
	    isPaused=true;
	}
    }
    public void startCount(){
        synchronized(lock){
	    isPaused=false;
	    lock.notify();
	}
    }
    public void exit(){
        synchronized(lock){
	    running=false;
	    lock.notify();
	}
    }
}

