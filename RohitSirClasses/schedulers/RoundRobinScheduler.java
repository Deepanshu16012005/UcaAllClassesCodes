import java.util.*;

public class RoundRobinScheduler implements Scheduler {
    int timeQuantum;
    Map<Task, Thread> taskThreadMap;

    public RoundRobinScheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
        this.taskThreadMap = new HashMap<>();
    }

    @Override
    public void execute(List<Task> taskList) throws InterruptedException {
        Queue<Task> queue = new LinkedList<>();
        for (Task t : taskList) {
            Thread thread = new Thread(t);
            thread.start();
            taskThreadMap.put(t, thread);
        }

        int currentTime = 0;
        int completed = 0;
        int idx = 0;

        while (completed < taskList.size()) {

            // adding in the queue , the task that has arrived
            while (idx < taskList.size() && taskList.get(idx).getArrivalTime() <= currentTime) {
                queue.offer(taskList.get(idx));
                idx++;
            }

            if (queue.isEmpty()) {
                currentTime++;
                Thread.sleep(100);
                continue;
            }

            Task currentTask = queue.poll();
            int units = 0;

            while (units < timeQuantum && !currentTask.isCompleted()) {
                currentTask.resume(); // Executre one unit
                Thread.sleep(105);
                currentTime++;
                units++;

                // Other Task Might Arrive in the same Time
                while (idx < taskList.size() && taskList.get(idx).getArrivalTime() <= currentTime) {
                    queue.offer(taskList.get(idx));
                    idx++;
                }
            }

            if (currentTask.isCompleted()) {
                currentTask.calculateTimes(currentTime);
                completed++;
                System.out.printf("Task %d is completed at this time %d", currentTask.getTaskId(), currentTime);
            } else {
                // task is paused
                queue.offer(currentTask);
            }
        }

        // Make sure threads join back
        for (Thread t : taskThreadMap.values()) {
            t.join();
        }
        printStatistic(taskList);
    }

    private void printStatistic(List<Task> taskList) {
        System.out.println("\nTask | Completion | Turnaround | Waiting");
        for (Task t : taskList) {
            System.out.printf(" %2d  |     %2d     |     %2d     |    %2d\n",
                    t.getTaskId(), t.getCompletionTime(),
                    t.getTurnaroundTime(), t.getWaitingTime());
        }
    }
}

