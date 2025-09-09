import java.util.*;
import java.lang.*;
import java.io.*;


class Message {
    public String msg;
    public int timeStamp;
    public Message(String msg, int timeStamp) {
        this.msg = msg;
        this.timeStamp = timeStamp;
    }
}

interface MessageTracker {
    Message getMessage();    
}

class RobotTracker {
    MessageTracker messageTracker;
    HashMap<String,Integer>map;
    public RobotTracker(MessageTracker messageTracker) {
        map= new HashMap<>();
	this.messageTracker = messageTracker;
    }
    
    public void pollMessage() {
        while (true) {
            Message message = messageTracker.getMessage();
            shouldPrintMessage(message);
        }
    }
    /*
        print msg if not comed in previous 10 seconds
        [1, "Hello"] , [2, "Hello"], [8, "Bye"], [12, "Hello"], [13, "Bye"]
        Answer is : [1, "Hello"], [8, "Bye"], [12, "Hello"]
    */
    
    public void shouldPrintMessage(Message message) {
        if(map.isEmpty()){
	  map.put(message.msg,message.timeStamp);
	  System.out.println("["+message.timeStamp+", "+message.msg+"]");
	  return;
	}
	List<String> list = new ArrayList<>();
	int time=message.timeStamp;
        for(String keys:map.keySet()){
	    if(map.get(keys)+10<=time){
	        list.add(keys);
	    }else{
	        if(message.msg.equals(keys)){
		  map.remove(keys);
		  map.put(message.msg,time);
		  return;
		}  
	    }
	}
	for(String keys : list){
	    map.remove(keys);
	}
	map.put(message.msg,time);
        System.out.println("["+message.timeStamp+", "+message.msg+"]");	
        // Add your implementation here
    }
    
    public static void main(String args[]) {
        RobotTracker robotTracker = new RobotTracker(null);
        robotTracker.shouldPrintMessage(new Message("hello" , 1)); // print this
        robotTracker.shouldPrintMessage(new Message("hello" , 2));
        robotTracker.shouldPrintMessage(new Message("bye" , 8)); //print this
        robotTracker.shouldPrintMessage(new Message("hello" , 12)); //print this
        robotTracker.shouldPrintMessage(new Message("bye" , 13));
    }
}

