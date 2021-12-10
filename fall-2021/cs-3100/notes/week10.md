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