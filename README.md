#Simulation and Performance Comparison of Schedulers
(Shortest Job First vs First Come First Served)

###Steps to Run
1. javac Scheduler.java
2. java Scheduler

###Sample Output
java Scheduler
PID <AT , BT>
P0  <7 , 9>
P1  <2 , 14>
P2  <4 , 36>
P3  <4 , 26>
P4  <6 , 28>

========== First-Come-First-Serve ==========
Currently executing process: P1
Waiting time: 0, Turn-around-time: 14
Currently executing process: P2
Waiting time: 10, Turn-around-time: 46
Currently executing process: P3
Waiting time: 46, Turn-around-time: 72
Currently executing process: P4
Waiting time: 70, Turn-around-time: 98
Currently executing process: P0
Waiting time: 97, Turn-around-time: 106

======== Statistics ========

CPU Idle Time: 3
Average wait time: 44.6ms
Average turn-around time: 67.2ms
CPU utilization: 97.14286%
Throughput: 47.61905 processes / sec

========== Shortest-Job-First ==========
Currently executing process: P1
Waiting time: 0, Turn-around-time: 14
Currently executing process: P0
Waiting time: 7, Turn-around-time: 16
Currently executing process: P3
Waiting time: 19, Turn-around-time: 45
Currently executing process: P4
Waiting time: 43, Turn-around-time: 71
Currently executing process: P2
Waiting time: 73, Turn-around-time: 109

======== Statistics ========

CPU Idle Time: 3
Average wait time: 28.4ms
Average turn-around time: 51.0ms
CPU utilization: 96.15385%
Throughput: 64.10256 processes / sec
