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
