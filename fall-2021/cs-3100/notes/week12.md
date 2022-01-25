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





















