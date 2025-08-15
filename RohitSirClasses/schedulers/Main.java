
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	int timeQuantum=4;    
        List<Task> taskList = new ArrayList<>();
        // Task(ID, Arrival Time, Burst Time, Priority)
        taskList.add(new Task(1, 0, 5, 1));
        taskList.add(new Task(2, 1, 3, 2));
        taskList.add(new Task(3, 2, 8, 1));
        taskList.add(new Task(4, 3, 6, 3));
        
        System.out.println("\n📋 Task List (sorted by arrival time):");
        for(Task t : taskList) {
            System.out.printf("  - Task %d: Arrival=%d, Burst=%d\n", t.getTaskId(), t.getArrivalTime(), t.getBurstTime());
        }

        // Instantiate the scheduler.
       // Scheduler scheduler = new RoundRobinScheduler(timeQuantum);
	Scheduler scheduler = new ShortestTimeFirst();
        System.out.printf("\n Running SRTF\n" );


        try {
            // Execute the scheduling algorithm.
            scheduler.execute(taskList);
        } catch (InterruptedException e) {
            System.err.println("Scheduler execution was interrupted.");
            Thread.currentThread().interrupt();
        }

        System.out.println("\n-------------------------------------------------");
        System.out.println(" Scheduler execution finished.");
    }
}
