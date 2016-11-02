/**
 * Created by archus on 10/12/16.
 */

import java.util.*;
/*
 * Creating class to hold attributes of each process
 * Arrival Time, Burst Time, Process ID, Wait Time
 * TurnaroundTime
 */
class Processes {

    private int at;
    private int bt;
    private int pid;
    private int wt;
    private int tat;

    public int getAt() {
        return at;
    }

    public void setAt(int at) {
        this.at = at;
    }

    public int getBt() {
        return bt;
    }

    public void setBt(int bt) {
        this.bt = bt;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getWt() {
        return wt;
    }

    public void setWt(int wt) {
        this.wt = wt;
    }

    public int getTat() {
        return tat;
    }

    public void setTat(int tat) {
        this.tat = tat;
    }
}
/*
 * Overloading Comparator to compare processes based on Shortest Burst Time
 */

class ArrivalTimeComparator implements Comparator<Processes> {
    public int compare(Processes p1, Processes p2) {
        Integer AT1 = p1.getBt();
        Integer AT2 = p2.getBt();
        return AT1.compareTo(AT2);
    }
}

public class Scheduler {
/*
 * Scheduler function to ensure scheduling according to FCFS or SJF
 * depending on the Queue passes as parameter
 */
    private static void schedulerFunction(List pList, Queue<Processes> readyQueue) {

        Processes temp = null;
        Queue<Processes> finalQueue = new LinkedList<Processes>();

        int currTime = 0;
        int targetTime = 0;
        int cpuIdleTime = 0;

        while(!pList.isEmpty() || !readyQueue.isEmpty()) {

            //Find elements whose arrivalTime = currTime
            //Put them in the ready queue
            Iterator<Processes> iterator = pList.iterator();
            while(iterator.hasNext()) {
                temp = iterator.next();
                if(temp.getAt() == currTime) {
                    readyQueue.add(temp);
                    iterator.remove();
                }
            }
            //Consider all processes arrived before the Current Time
            if(currTime >= targetTime && !readyQueue.isEmpty()) {
                Processes currProcess = readyQueue.poll();
                System.out.println("Currently executing process: P" + currProcess.getPid());
                targetTime += currProcess.getBt();
                //Wait time is currentTime - arrivalTime
                currProcess.setWt(currTime - currProcess.getAt());
                //turnAroundTime = waitTime + burstTime
                currProcess.setTat(currProcess.getWt() + currProcess.getBt());
                System.out.println("Waiting time: " + currProcess.getWt()
                        + ", Turn-around-time: " + currProcess.getTat());
                finalQueue.add(currProcess);
            } else if(readyQueue.isEmpty()) {
                //If ready queue is empty the CPU is idle
                cpuIdleTime++;
            }
            currTime++;
        }


        //Report Stats
        int numOfProcesses = finalQueue.size();
        int totalTime = currTime;
        int totalWaitTime = 0;
        int totalTurnAroundTime = 0;

        for(Processes p : finalQueue) {
            totalWaitTime += p.getWt();
            totalTurnAroundTime += p.getTat();
        }

        System.out.println("\n======== Statistics ========\n");
        System.out.println("CPU Idle Time: " + cpuIdleTime);
        System.out.println("Average wait time: " + ((float)totalWaitTime) / numOfProcesses + "ms");
        System.out.println("Average turn-around time: " + ((float) totalTurnAroundTime) / numOfProcesses + "ms");
        System.out.println("CPU utilization: " + ((float)totalTime - cpuIdleTime) * 100 / totalTime + "%");
        System.out.println("Throughput: " + ((float) numOfProcesses * 1000) / totalTime + " processes / sec");
    }

    public static void main(String[] args) {

        Random rand1 = new Random(5);
        Random rand2 = new Random(5);

        List<Processes> FCFSList = new ArrayList<Processes>();
        List<Processes> SJFList = new ArrayList<Processes>();

        for (int i = 0; i < 5; i++) {

            Processes p = new Processes();
            p.setPid(i);
            //Randomly generating Arrival Time and Burst Time for processes.
            //Arrival time between 0 - 9
            //Burst Time between 2 - 42
            p.setAt(rand1.nextInt(10));
            p.setBt(rand2.nextInt(40) + 2);

            FCFSList.add(p);
            SJFList.add(p);
        }

        System.out.println("PID <AT , BT>");
        for(Processes tp : FCFSList) {
            System.out.println("P" + tp.getPid() + "  <" + tp.getAt() + " , " + tp.getBt() + ">");
        }

        Queue<Processes> readyQueueFCFS = new LinkedList<Processes>();
        System.out.println("\n========== First-Come-First-Serve ==========");
        schedulerFunction(FCFSList, readyQueueFCFS);

        PriorityQueue<Processes> readyQueueSJF = new PriorityQueue<Processes>(8, new ArrivalTimeComparator());
        System.out.println("\n========== Shortest-Job-First ==========");
        schedulerFunction(SJFList, readyQueueSJF);

        }
}