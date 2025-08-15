public class Task implements Runnable {
    private int taskId;
    private int arrivalTime;
    private int burstTime;
    private int remainingTime;
    private int priority;
    private int completionTime;
    private int waitingTime;
    private int turnaroundTime;

    private final Object lock = new Object();
    private boolean isPaused = true;  // start paused


    public Task(int taskId, int arrivalTime, int burstTime, int priority) {
        this.taskId = taskId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
    }

    // Called by scheduler to resume this task for one time unit
    public void resume() {
        synchronized (lock) {
            isPaused = false;
            lock.notify();
        }
    }

    // The scheduler will pause the thread by setting isPaused = true
    public void pause() {
        synchronized (lock) {
            isPaused = true;
        }
    }

    // Simulate task execution for one time unit
    @Override
    public void run() {
        try {
            while (remainingTime > 0) {
                // Wait until scheduler resumes this task
                synchronized (lock) {
                    while (isPaused) {
                        lock.wait(); //
                    }
                }

                // “Execute” one time unit
                remainingTime--;
                System.out.printf("Task %d executing… Remaining=%d\n", taskId, remainingTime);
                Thread.sleep(100);      // 100 ms = one unit

                // After one unit, pause itself and notify scheduler
                pause();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean isCompleted() {
        return remainingTime == 0;
    }

    public void calculateTimes(int currentTime) {
        this.completionTime = currentTime;
        this.turnaroundTime = completionTime - arrivalTime;
        this.waitingTime = turnaroundTime - burstTime;
    }

    // Getters and setters
    public int getTaskId() { return taskId; }
    public int getArrivalTime() { return arrivalTime; }
    public int getBurstTime() { return burstTime; }
    public int getRemainingTime() { return remainingTime; }
    public int getPriority() { return priority; }
    public int getCompletionTime() { return completionTime; }
    public int getWaitingTime() { return waitingTime; }
    public int getTurnaroundTime() { return turnaroundTime; }

    @Override
    public String toString() {
        return String.format("Task %d: Arrival=%d, Burst=%d, Priority=%d, Completion=%d, Waiting=%d, Turnaround=%d",
                taskId, arrivalTime, burstTime, priority, completionTime, waitingTime, turnaroundTime);
    }
}

