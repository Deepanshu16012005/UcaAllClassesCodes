import java.util.*;
import java.lang.*;
import java.io.*;


class Message {
    public String msg;
    public int timeStamp;
    boolean shouldPrint;
    public Message(String msg, int timeStamp) {
        this.msg = msg;
	this.shouldPrint=true;
        this.timeStamp = timeStamp;
    }
}

interface MessageTracker {
    Message getMessage();    
}

class RobotTracker2 {
    MessageTracker messageTracker;
    HashMap<String,Message>map;
    Queue<Message> queue;
    public RobotTracker2(MessageTracker messageTracker) {
        map= new HashMap<>();
	queue=new LinkedList<>();
	this.messageTracker = messageTracker;
    }
    
    public void pollMessage() {
        while (true) {
            Message message = messageTracker.getMessage();
            shouldPrintMessage(message);
        }
    }
    /*
        print msg if not comed in previous 10 seconds and guranteed will not come in next 10 seconds
        [1, "Hello"] , [2, "Hello"], [8, "Bye"], [12, "Hello"], [13, "Bye"]
        Answer is : [8, "Bye"]
    */
    
    public void shouldPrintMessage(Message message) {    
	List<String> list = new ArrayList<>();
	int time=message.timeStamp;
	if(map.containsKey(message.msg)){
	  Message oldMessage = map.get(message.msg);
	  if(time-oldMessage.timeStamp<=10){
	    message.shouldPrint=false;
	    oldMessage.shouldPrint=false;
	  }
	}
        for(String keys:map.keySet()){
	    if(map.get(keys).timeStamp+10<=time){
	        list.add(keys);
	    }
	}
	for(String keys : list){
	    map.remove(keys);
	}
	map.put(message.msg,message);
	queue.add(message);

	while(!queue.isEmpty()&&queue.peek().timeStamp+10<=time){
	  Message oldMessage = queue.poll();
	  if(oldMessage.shouldPrint){
	   System.out.println("["+oldMessage.timeStamp+", "+oldMessage.msg+"]");
	  }
	}
        //System.out.println("["+message.timeStamp+", "+message.msg+"]");	
        // Add your implementation here
    }
    
    public static void main(String args[]) {
        RobotTracker2 robotTracker = new RobotTracker2(null);
        robotTracker.shouldPrintMessage(new Message("hello" , 1)); 
        robotTracker.shouldPrintMessage(new Message("hello" , 3));
        robotTracker.shouldPrintMessage(new Message("bye" , 8)); 
        robotTracker.shouldPrintMessage(new Message("yoo" , 10)); //print this
        robotTracker.shouldPrintMessage(new Message("hello" , 12)); 
        robotTracker.shouldPrintMessage(new Message("bye" , 13));
        robotTracker.shouldPrintMessage(new Message("bye" , 24));

    }

}
