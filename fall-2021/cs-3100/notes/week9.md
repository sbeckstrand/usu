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

