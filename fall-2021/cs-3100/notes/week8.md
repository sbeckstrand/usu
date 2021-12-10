# October 18, 2021

## First come first served (FCFS): 

Total Wait = Wait across all processes. 

You have three processes that all start at the same time (0). First process takes 24 cycles. That means process to waited from 0 -> 24 for a wait of 24 cycles. It finishes in three cycles so process three starts at 27 and then finishes at 30 cycles. The total wait is the addition of the wait time for all these processes (0 + 24 + 27)

In reverse order:

P3: -> 0:3
P2: -> 3:6
P1: -> 6:30

Total wait = 0 + 3 + 6 = 9

Throughput = number of processes. 

Average = Total Wait / Throughput

## ShortedJob First (SJF)

Best Performing

Select the process with the shortest task

Non-preemptive

Say you have the following processes: 

P1: Burst 6
P2: Burst 8
P3: Burst 7
P4: Burst 3

(0) P4  (3) -> (3) P1 (9) -> (9) P3 (16)-> (16) P8 (24)'

Total Time = 3 + 9 + 16  + 24 = 42

## Calculating Burst Times

Start with the default for Next Burst

Can track actual burst lengths

Exponential Averaging
    - Which part of history is most important

Basically, start with some default value. If the process is running for the first time, it is given that default value. Otherwise, if it has been run before, we keep a history of what its burst time was. 

## Shorted Remaining Time First (SRTF)

Preemptive

New process will receive cpu if it has the current smallest remaining burst

Say you have the following procsess: 

P1: 
- Time: 7
- Arrival: 0

P2: 
- Time: 4 
- Arrival: 2

P3: 
- Time: 1
- Arrival: 4

P4:
- Time: 4
- Arrival: 5

Process 1 is the only process to to start, so it is initially started. 

Two cycles in, a new process comes in (P2) and its time (4) is less thant he remaining time in P1 (5). P2 starts running and P3 comes in. P1 has 5 left, P2 has 2 left, P3 comes in with 1. P3 starts. P3 finishes and P4 comes in with 4. P2 finishes, P4 finishes, P1 finishes. 

P1 (2) -> P2 (4) -> P3 (5) -> P2(7) -> P4 (11) -> P1 (16)

## Round Robin (RR)

Similar to FCFS, but with preemption

Timer
- Time quantum = q
    - 10-100ms
- Can reset on I/O Call
- Process completes

Adjust Q
- Large -> FCFS
- Small - a lot of process changes


# October 20, 2021

## Priority Schedulding

Priority is an integer (typically)
- Low value is higher priority

SJF is a priority schedulding approach

Types
- Preemptive
- Non-preemptive

## Starvation

Low priority process never gets executed

Aging
- Gradually increase the priority of a process

## Equal Priority

Choose the older

RR (Round Robin) with those of equal priority

## Multi-level queue

O(n) search

Many queues of processes

Set priority to queues

Generalize
- Forground - visible to user
- Background -invisible to user

Each Queue can have its own scheduling algorithm
- Real Time - Priority
- Batch - FCFS

Starvation 
- Time Slices - Give each queue a certain amount of time to run

- Hypothetically, say we give Foregroup - 80%, Background - 20%. This should help to avoid starvation

## Thread Scheduling

We schedule threads, not processes

Each process has at least one thread

OS is aware of kernel level threads

Contention Scope

- Process Contention Scope
    - Unseen by the kernel
    - Many-to-One/Many-to-many model
        - Select among a user thread
        - Programmer may be able to set priority
- System Contention Scope
    - Seen by the kernel
    - All threading models
    - Which kernel thread is going to be scheduled

## Multi-processor scheduling

Environments

- Multi-core cpus
- Multiple cpus
- Multi-threaded cores - hyperthreading
- NUMA - non-uniform memory access
- Heterogeneous multiprocessing

Approaches
- Asymmetric Multiprocessing
    - One cpu is responsible for schexcduling
    




