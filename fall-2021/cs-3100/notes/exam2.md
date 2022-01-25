## In the ___________________________ thread model, a system call made by one thread will never block another thread

-  One-to-One


## In an Asynchronous thread execution strategy, which of the following is true?

-  The parent creates child threads then continues execution

# PThreads are an implementation of a standard design.

-  False

# Java threads can be created by creating a Runnable object and passing it to a Thread constructor.

-  True

# What is the term describing pushing creation and management of threads to run-time libraries and compilers, instead of requiring a developer to manage it?

-    Implicit Threading

# Which Threading approach is similar to a divide and conquer recursion solution?

-  Fork Join



# Which Threading approach is used by the Java Executor service?

   Thread Pool

# What best describes a CPU burst?

- The amount of cpu time a process needs before voluntarily stopping

# In practice, the distribution of cpu burst durations is pretty even with the number of short and long cpu bursts being somewhat equal.

-  False

# What term describes the case where a process is only released from the cpu when it voluntarily does so?

-  Non-preemptive

# What term best describes the time between one process finishing/stopping execution and another one starting?

-  Dispatch Latency

# What term describes a metric of scheduling performance that measures the amount of time from when process is created until it is completed?

- Turnaround Time

# What is the average wait time of the following Shortest Remaining Time First scheduling algorithm that uses the following processes?

P1 6 0
P2 3 2
P3 2 5
P4 4 7

-  2.25

3 2 4 0 9/4 2 1/4

# Note, this is the same chart as the previous question.

- P1

# Note, this is the same chart as the previous question.

# How many context switches are there? Only count when one ends and another begins, do not count the first (where only one begins) or the last (where only one ends).

- 4

# What scheduling algorithm is provably the best in terms of Average Wait Time, but cannot realistically be implemented?

- Shorted Job First

# What is an important consideration when creating a priority-based scheduling algorithm? Pick the answer that is most unique to a priority system.

- Starvation

# An OS actually schedules hardware threads, not kernel threads.

-  False

# Push and Pull migration is a solution to load balance processes in a symmetric multi-processing system where each processor has its own ready queue.

-  True

# A race condition is the result of an event where the output is wrong because instructions were not executed in the proper order.

- False

# A critical section is a shared piece of code that only one process can enter at a time.

- False

# Which of the following is NOT a section of code that needs to be considered in the Critical Section Problem?

-  Shared Section

# Choose all that are required for a solution to the Critical Section Problem. You must be exactly right to get full credit.

- Progress
- Mutual Exclusion
- Bounded Waiting

# What is the term for the hardware support solution that forces all loads and stores to complete before a new load or store can begin?

-  Memory Barrier

# What term describes a hardware solution where a variable value can be modified as if it happens all at once (as if it is a single instruction)?

-  Atomic Variable

# A key difference between a Mutex and a Semaphore is that a Mutex works on boolean values while a Semaphore uses integer values.

- True

# What is the term that describes a case where a set of processes are unable to progress because they each hold a lock, but are waiting for another in the set to release a lock?

- Deadlock

# Java provides a Mutex class and Semaphore class to implement each type of lock for shared resource synchronization.

- False

# The java synchronized keyword is an example of what kind of synchronization tool?

- Monitor

# Semaphores as a shared resource synchronization tool has an advantage over other methods in that it does not required each Thread/Process to implement a specific protocol/steps to access the shared resource.

- False

# The Readers Writers problem has multiple versions. The first two versions may suffer from what problem?

- Starvation

# What is the major problem the Dining Philosophers may face?

- Deadlock

# All questions are based on the following information:

# Process Size: 245 bytes

# Page Size: 16 bytes

# For each answer just enter the number. This is auto graded.

    # How many pages are needed? (Process size / page size --> round up if answer % page size == 0)

    - 16

    # How many bytes of internal fragmentation are there? (Page size - remainder)

    - 11

    # What is the size of the logical address bits? (2^m where page page size = 2^n and n = m + 1)

    - 8

    # What size in bits of the offset portion of the logical address ( I believe this is n ^ x to get to the number of pages needed. )

    -4 

# Pure Demand Paging starts by automatically writing only a small number of pages into memory when a program starts up.

False

# Which of the following is NOT one of the benefits of virtual memory and page swapping?

- Elimination of internal fragmentation 

# With demand paging, the valid/invalid bit in the page table has more than two distinct meanings. A page that is invalid may mean the entry in the table is not valid, or____________________.

- The required page is read only 

# What terms describes when a virtual memory address is used, but an associated physical memory location cannot be accessed because the necessary data is not loaded into memory?

- Page Fault 

# Thanks to the speed of modern computing systems, the penalty for swapping a page into memory because it was not already loaded is extremely small in terms of computation time.

- False

# What term describes a process whereby after a fork() call, two processes share all pages of the original process. When a process makes a write to a page, then the page is duplicated and the new page is written to?

Copy on Write 

# First-In-First-Out is the most commonly used page replacement algorithm.

False 

# What term describes a policy where a page may replace any other page in a frame in user space (memory)?

 Global 

# Which of the following is NOT true about thrashing?

CPU utilization is so high the system slows down 

# October 4, 2021

## Thread Libraries

Dev APIs

### Two approaches: 

User Space: 
- No OS support
- All good and data structures in user space

Kernel Space:
- OS support
- Kernel space for management

### Strategies

Asynchronous
- Parent creates child and moves on

Synchoronous
- Parent creates child and waits on child

### PThreads
- POSIX Threads
- POSIX is a design spec, not an implementation
- Can beu user or kernel threads
- *NIX, MacOS

### Windows Threads

- Kernel Threads
- WaitFor MultipleObjects
     - Group join

### Java Threads
- Managed by JVM
- JVM uses the underlying OS approach
- Two approaches
    - Esxtend the thread class
    - Implement the Runnable class
        - Create an instance and pass to a Thread constructor
- Manual threads
    - Create, run, and manage thread lifecycle
- Java executor Framework

### Implicit Threading

Compilers and run-time libraries

Reduces developer responsibilities for management

### Thread Pools

Create a number of threads at start-up

Assign thread to instructions to execute (assi)


---

# October 6, 2021

## Fork Join

Creating threads and waiting for them

Task(problem)

```
If problem is small enough
    solve problem
else
    subtask1 = fork(new Task(subset of problem))
    subtask2 = fork(new Task(subset of problem))

    result1 = join(subtask1)
    result2 = join(subtask2)

    return combined results
```

### Java class

RecursiveTask
    - Threads forking threads

Each thread tracks the threads it created

Threads can balance workload by passing tasks

## OpenMP

Set of compiler directive and an API in C, C++, FORTRAN

Compiler directive
- Extra tags/code in course code that gives instructions to compiler

Works in shared memory

Notes: 
    - No explicit thread creation call
    - No explicit join
    - Compiler takes care of everything


## Grant Central Dispatch

Apple iOS and MacOS specific

Combination
- Runtime Library
- API


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
    

# October 27, 2021

## The Critical Section Problem

System of n processes/threads.
- Have some common resource: Shared memory, file, hardware, data

Each Process/thread has a critical section
- Changing/accessing the shared resource
- No other process can be in its critical section at the same time

Solution
- Design a protocol
- Identify key parts
    - Entry section
        - Request to enter the critical section
    - Critical Section
    - Exit section
        - Annouce the process is done with the critical section
    - Remainder Section
        - Code not related to the critical section

Requirements (Full definitions on page 260)
    - Mutual Exclusion
        - Only one process in its critical section at a time
    - Progress
        - The selection cannot be postponed indefinitely
    - Bounded Waiting - How long a process can wait before getting a turn
        - A process can't be kept waiting indefinitely


## Kernel Level Critical Sections

Fork process
- Protential to have two processes with the same id

Solutions
- Non-preemptive kernel
    - Provide Synchronization


## Section 6.3 - Peterson's Solution

Software based solution

## Hardware Support

Require locking

Can be used directly or as building blocks for other tools

Memory Barrier
- Guarantee the state of memory - Everything that should be there, is there.
- Two Types
    - Strongly Ordered
        - A memory modification on processor is immediately available to all processors
    - Weakly ordered
        - A memory modication on a processor may not be immediately available to all processors

- Varies by processor
- Developers (OS or application) can enforce a strongly ordered type. 
- All loads and stores are completed before any new load or store starts. 

Example


## Hardware Insturctions

Test and set
- Test and modify a variable(word) atomically (as if it happened all at once. )

```
boolean test_and_set(boolean* target) {
    boolean rv = *target;
    *target = true;
    return rv;    
}

Happens atomically

lock is false

```
do {
    while(test_and_set(&lock))
        ; //busy wait
    ...critical section
    lock = false
    ...remainder section
}while(true);
```

# October 29, 2021

---

## Compare and Swap

```
    Int compare_and_swap(int* value, int expected_value, int new_value) {
        Int temp = *value
        if (*value == expected) {
            *value = new_value
        }
        return temp;
    }
```

Usage: 

```
do {
    while(compare_and_wait(&lock,0,1) != 0) {
        ; // do nothing
    }
    ..critical section
    Lock = 0
    ..remainder section
}
```

Why an Int? -> Shared resource may have multiple resources

## Atomic Variables

```
void (int* val) {
    int temp;
    do {
        temp = *val
    } while (temp != compare_and_swap(val, temp, temp + 1))
}
```

## Developer Slutions

### Mutex Lock

Mutal Exclusion Lock

Methods 

- Acquire()
    - Keep tyring until we receive the lock

- Release()
    - Give upt he lock to another process

Code

```
Acquire() {
    While(!available) {
        ; // busy wait
    }
    Available = false
}
```

```
Release() {
    Available = true
}
```

```
Acquire()
...critical section
Release()
...remainder section
```

## Semaphores

Is an integer value

Methods: 
- Wait(S)
- Signal(S)

Mutex = Boolean
Semaphore = Integer

Code

```
Wait(S) {
    while (S <= 0) {
        ; // busy wait
    }
    S--
}
```
```
Signal(S) {
    S++;
}
```

```
Wait(S)
..critical section
Signal(S)
..remainder section
```

## Monitor

Mutex & Semaphore
- Depends on developer following protocol

Synchronization is provided by the resource/data type

Monitor manages the data and functions/methods to provide synchronization

## Liveness

Circumstances where progress can be made

Deadlock
- Process 1
    - Wait(S)
    - Wait(Q)
    - ..critical section
    - Signal(S)
    - Signal(Q)

- Process 2
    - Wait(Q)
    - Wait(S)
    ..critical section
    - Signal(Q)
    - Signal(S)

Locks on two processes cause a cirular wait. 

Priority Inversion
- Lower Priority process holds back a higher priority process
- Lower priority process hasa lock that the higher priority needs
- This can happen in two ways
    - Lower priority process is not scheduled because the higher priority is
    - Lower priority is scheduled, but is preempted by the higher priority



# November 1, 2021

When doing priority queues. Dont track priority yourself. Use the priority queue in java. 

---

Java Atomic Varialbes
- Java.util.concurrent
- incrementAndGet()
- decrementAndGet()
- addAndGet(long val)
- compareAndSet(ExpectedValue, NewValue)
    - Return true if it worked
    - return false if atomic long and expected are not equal

Java Mutex - Use Semaphores
    - Does not exist
    - Volatile keyboard
        - Forcing a read from memory

Java Synchronized
    - Monitor
    - Java Object
    - Use on a method
        - Monitor is 'this'
    - Monitor only allows one synchonized method to execute at a time. 
    - Use ona  block
        - Synchronize

Syncrhonized BLock gives much more control than using Synchornized method. 

# November 10, 2021


## Dining Philosophers Problem

They alternate between eating (rice) and thinking. 

Chopstick on each side of you but shared with adjacent philosopher. 

There is a concern of creating deadlock if each philospher picks up one chopstick and waits for the other to become available. 

Solution: ONly let philospher eat when both chopstics are available. 

Best solution: Monitor with Atomic solution (Check if both are available) 


# November 15, 2021


# November 17, 2021

## Performance of Demand Paging 

Effective Access Time EAT) - Time from request to having data available in memory

Memory Access Time = EAT if no page fault happens - 200ns

Probability of Page Fault (PF)
    - [0,1]
    - We'll caculate a reasonable level

Page Fault Time (PFT)
    - This is really slow
    - 8ms = 8,000,000 ns

EAT = ( 1 - Proability)(Memory Access Time) + Probability * Page Fault Time --> (1-P)MA + P * PFT

Page Fault steps:
- Trap to OS
- Save process registers state
- Determine the location of the page on disk
- Issue a read from the disk to the free frame
    - Wait in the queue for the read to be serviced
    - Latency to seek the location on disk
    - Begin transfer of the page
- While that's happening --> Allocate CPU to another process
- Receive and I/O complete interrupt
- Save REgisters and state for current process
- Process the I/O Interrupt
- Update the page table and Process Control Block (PCB) table to indicate a page is in memory
- Wait for the process to be allocated the CPU again
- Restore register values and state to the CPU

TlDR: Page fault time == HUGE

Need to minimize hte number of page faults (or rate)

Example:

MA = 200ns

PFT = 8ms = 8,000,000 ns
EAT = (1 - P)200ns + P * 8,000,000 ns -> 200ns + 7,999,800 ns * P

Let P 1/1000 -> P = .001 -> EAT = 8200 ns . Factor of 40. 

Say we want degredation < 10% (220 ns)

220 > 200 + 7,999,800 * P -> P < 0.000025 -> 1/400,000 memory accesses. 

## Solutions

Reading from backing store is slightly faster than the filesystem
    - Key is to maximize swap space usage

Copy the entire process into swap space
    - This doesnt happen too much anymore, as it takes time

Demand page from the file system and then swap to swap space. 
    - First access is slow (would have had to do this with second solution anyway)
    - Second access is faster

Read Only Pages
    - Do not swap, just overwrite
    - Re-read the filesystem

Copy on Write (COW)
    - This happens when we have a Fork()
        - When we have a fork, a process is duplicated
        - When duplicated, we can share pages at first
        - This is very fast, assuming we are just reading data

        When a process writes
            - Duplicate the page
            - Write to the new page

    - If there is an Exec()
        - Implements the solution we talked about weeks ago where we dont actually do the duplication

## Page Replacement

With virtual memory, we can also use more memory than we actually have but you run the risk of processings demanding excessive pages and running out of memory

OS to pick a 'Victim' page and OS swaps the desired page into its place, and swaps the victim page out. 

### Performance

2 Page writes

Very expensive. 

### Solution

If a page hasn't been modified, does it exist somewhere else? (If it is read only, it exists in the filesystem)

We can use a Dirty Bit (or a Modify bit)
    - Set if a page is modified

### Algorithmic Solutions

Page replacement -> Which page should I choose as victim?

Frame allocation - How do I allocate frames to a process? 

Goal: Minimize the page fault rate

Number of page faults should decrease as the number of frames increases

### Evaluation

Reference string -> The pages needed in order: 1, 5, 2, 6, 1, 2, 6, 3. 

## Page Replacement

Example input string: 7 0 1 2 0 3 0 4 2 3 0 3 2 1 2 0 1 7 0 1

### First in First Out (FIFO)

Let there be three frames for each process

Anytime we write to a column, its a PF

First Frame: 7  <- PF (7)

Second Frame: 0 <- PF (70)

Third Frame: 1 <- PF (701)

All frames are full now

Fourth Frame: (7) -> 2 <- PF (201)

Fith Frame: (0) -> (0) <- No change necessary, no page fault (201)

Sixth Frame: (0) -> (3) <- PF (231) <- Note, we did not move on to the third frame as the second frame was never changed. 

So on

Also note, Current input does not need to be the current frame, just needs to be the same value as any of the three existing frames to avoid a page fault. Only replace if input does not exist in frames. This is why there are less page faults as there are more frames. 

In total, 15 page faults. 

### Optimal Page Replacement

Replace the page that won't be used for the longest period. 

This one is impossible as we would need to look into the future. 

First Frame: 7 (7)
Second Frame: 0 (70)
Third Frame 1 (701)

In our input, 7 will not be used for the longest, so it would be the first to be swapped when a swap is necessary. 

9 Page faults 

### Least Recently Used

Repalce the page that hasn't been used for the longest time. 

Keep track of replacement list

---

First Frame: 7 (7)
Second Frame: 0 (70)
Third Frame 1 (701)
Fourth Frame: 2 -> Replace the 7, as it was the last recently used -> (201)
Fifth frame: 0 (in frame)
Sixth Frame: 3 -> Replace the 1, as it was the least recently used -> (203)

12 Page Faults

### Revisit FIFO 

FIFO has an interesting behavior in that you can have more page faults with more frames than with processing with less frames. 

For example, with the input '1 2 3 4 1 2 5 1 2 3 4 5'. Using three frames results in nine page faults. Using four frames results in ten page faults. 

Typically, more frames means fewer page faults

This is called Belady's Anomaly
    - Adding frames increases page faults


### Allocation

Fixed amount of memory

Some number of processes

#### Approaches

Equal
    - Each process gets the same number of frames
    - Lots of wasted memory on small processes
    - Large processes do a lot of swapping

Proportional
    - Allocate based on the logical address space needs, such as each process gets x% of process needs
    - Small processes may do a lot of swapping

Priority
    - Allocate frames based on process priority
    - Include size as part of the priority 
    - Large low priority processes may or may not have more frames than high priority small processes. 

Global vs Local
    - Local
        - Frames are only associated with a single process. 
        - Which means that page swapping only happens within a process
        - Benefit: This approach provides consistent performance
        - Issue: May have underutilized memory
    Global
        - Frames can be swapped for any process
        - Process can lose/gain total frames
        - Inconsistent performance - If you run the same program several times, it may perform differently with each attempt as it is influenced by the needs of other processes. 
        - It does provide higher system throughput
        - This is the common approach. 

## Thrashing

Thrashing is not good. Its not a feature, it is the result of an unoptimal procedure involving paging

Process does not have some minimum number of frames for its "active" pages. 

Have Page fault for a page that was just swapped out. 

Cycle: 
    - WHen there is a lot of paging, there is a lot of I/O
    - When there is a lot of I/O, CPU utilization decreases
    - When CPU utilization decreases, the OS tries to increase multiprogramming
    - When there is an increase in multiprogramming, there are more processes running
    - More processes running means more paging. 

System spends more time paging than executing processes

### Thrasing: Solution

Setting some acceptable page fault rate

## Other Condierations

Pre-paging
    - Load some number of pages at startup
    - Reduces page faults at start up
    - Faster to loa dmany pages at once than one at a time. 
        - Few context switches
        - Less overhead
    - Wasted I/O

Page Size
    We've talked about this

TLB Reach - The amount of memory accessible by the TLB
    - Reach = TLB entries x Page Size
    - Ideally - Working set (set of pages actively being used) fits in the TLB Reach

I/O Interlock
    - Lock a page in memory while I/O is being performed
    - 























