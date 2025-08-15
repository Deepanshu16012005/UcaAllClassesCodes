// create a func which prints countimg unitl i tell it to stop and again start prints if i tell it to start and on saying exot it should exit the program

import java.util.*;
class ThreadingExercise{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        CounterThread count = new CounterThread();
	Thread t1 = new Thread(count);
	System.out.println("Counter will start soon. Enter commands:");
        System.out.println("  'start' - to resume counting");
        System.out.println("  'stop'  - to pause counting");
        System.out.println("  'exit'  - to close the program");
        System.out.println("------------------------------------");
	t1.start();
	while(true){
	    String command = sc.nextLine().trim().toLowerCase();
	    switch(command){
	        case "start":
		    count.startCount();
		    break;
		case "stop":
		    count.pause();
		    break;
		case "exit":
		    count.exit();
		    return;
		default:
		    System.out.println("unknown command");
		    break;    
	    }
	}
    }

}

