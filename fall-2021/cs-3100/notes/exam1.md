# September 1, 2021

## Computing System Categories

#### Hardware

CPU, Memory, I/O

#### OS

Coordinates the use of hardware

Provides an interface for users/applications

#### Applications

Way system used to solve problems

#### Users

People, other machines

## How the components interact

User <-> Application Programs <-> Operating System <-> Hardware

## User View

#### What are users concerned with when they use a machine

- Ease of use
- Speed
- Battery

## System View

- Resource Allocation
- Managing conflicts
- Efficiency

## What is an Operating System? 

Kernel - Part of OS that is always running. 

Some see the OS as 'Everything that ships with my computer'

The point is that the definition of an OS is vauge. 

Our Definition: An OS is a Resource allocator and controls programs. 

## Computer System Organization

Basic Components: 
- CPUs
- Devices/Device Controllers (Driver)
	- Controller - software interface
- Shared Memory
- Common bus

## Computer System Capabilities

Note: Concurrency gives the appearance of two things happening at the same time. Parallelism is when things are actually doing multiple things at once. 

Concurrent Execution: CPU and device controllers. When there is a shared bus, only one thing can be happening at once but we can get the appearance of multiple things happening at the same time
 
Device controllers do have a buffer that can be used to store instruction. 

CPU Moves data between devices

Interrupt Driven

## Interrupts

An analogy might be that a CPU is like a parent. If they are in the middle of a task and their baby starts crazy, they may step what they are doing to tend to them, depending on its priority. Some cries are more concerning than others. Some may be able to wait. 

Implemtnatation:
- Interrupt Vector (IV): Array of addresses services routines 
- Interrupt Service Routines (ISR): Instructions executed for a specific interrupt. 
- Interrupt Request Line: CPU checks this after every instruction (Very fast, but example of overhead). CPU needs to process the interrupt if there is a request. 

Before sending an interrupt on the bus, you need permission from the CPU. There is a wire from the devices directly to the CPU that allows it send interrupt. 


-----

# September 8, 2021

## Groovy

Gradle uses Groovy. 

Groovy offers a `doFirst` and a `doLast method`, as they are aptly name, blocks of code under the doFirst method will execute before your main block and doLast will execute after. 

```task base

base.doFirst {
	println 'base: doFirst'
}

task main (dependsOn:base)
main.doFirst {
	println 'main: doFirst'
}
```

Define `build.gradle` file. 

Define plugins

```
plugins {
	id 'java'
	id 'application' /* Needed for mainClassName property, also brings in the 'run' task */
}
```

Define source sets: (Defines where your source code is). Code will be under a directory called 'src'

```
sourceSets {
	main {
		java {
			srcDirs = ['src']
		}
	}
}
```

Define main class name

```
mainClassName = 'AssnXMain'
```

Define a jar (so it can be packaged to be run later)

```
jar {
	manifest {
		attributes 'Main-Class'
	}
}
```

Define jar file name

```
archivesBaseName = "AssnX"
```

All together: 

```java
plugins {
	id 'java'
	id 'application' /* Needed for mainClassName property, also brings in the 'run' task */
}

sourceSets {
	main {
		java {
			srcDirs = ['src']
		}
	}
}

mainClassName = 'AssnXMain'

jar {
	manifest {
		attributes 'Main-Class': 'AssnXMain'
	}
}

archivesBaseName = "AssnX"
```

You can remove your build folder at any time and it will not be a problem. It can be rebuilt. 

You can also run `gradle clean`

---

## Architecture (CPU)

### Multi-Processor System/Parallel System

#### Two Types of Systems

Asymmetric Multiprocessing

- Boss-Worker Model // There will be a question about this
	- One CPU that runs the kernel
	- The "boss" assigns tasks to other CPUs
	
Symmetric Multiprocessing (SMP)

- Each Processor can run any task, including OS tasks
- This tasks advantage of shared memory. 
	- This means also using a Shared cache. 

## Clustered Systems

Multi-CPU

Usually a compilation of computing systems. 

Shared Storage
	- Storage Area Network (SAN)
	
Uses:
	- High performance computing
	- fault tolerance/High Availability

## OS Operations

### Bootstrapping

Initial steps to starting a computer

Bootloader/boostrapper

Load the OS and needed drivers (for devices)

### Multiprogramming

Divide programs up into jobs/tasks/processes

### Multitasking (time sharing)	

 Switch between jobs very quickly (Such as interrupts)
 
### Dual-mode operation

User mode
	- Some instruction cannot be executed
	- Running user processes

kernel mode
	- all instructions may be executed
	- When running OS level processes


# September 10, 2021

---

User Process: User process executing -> Calls system call -> Execute system call (kernel) -> Return from system call (back to user Process)

Overhead
	- Expense that we occurr to accomplish a task
	- Processed using an interrupt (to change the mode)
	- Cost is 1000's of cycles on the CPU (quite extensive, takes a bit of time). 
	
	
## CPU Timer

Countdown for the current process (What is running on the CPU)

Operating system determines when a processes CPU time is done, not the program itself. 

When process timer ends, trigger an interrupt to switch processes



## Resource Management

### Process Management

Process - Program in execution (not necessarily on the CPU). AKA: task, job

What we are referring to here is managing resources that the process needs. 
	- CPU
	- Memory
	- I/O
	- Files
	- etc. 

Multi-CPU system
	- Synchronize resource management between CPUs
	
OS Responsibilities
	- Create and Delete
	- Schedule and Resume
	- Process communication
	- Process synchronization



### Memory Management

What should be in memory and where

How much memory a process gets

### File System Management

### Mass Storage Management

OS Responsibilities
	- Mounting and unmounting storage devices
	- Free space management
	- Disk scheduling
	- Partitioning
	- Protection
	
### Cache Management

Remember, Cache is temporary storage

Magnetic Disk -> Main Memory -> Cache -> Hardware register

### I/O Subsystem Management

Hide device communication needs from user

Memory management
	- Buffering
	- Spooling - mixed processes
	- Caching
	
	
## Protection and Security 

Protection - any mechanism for controlling access to processes or OS resources

Security - Defense against internal and external attacks

Security Triad - CIA 
	- Confidentiality - Keep data private
	- Integrity - Ensuring data is correct, authentic, and reliable. 
	- Availability - Data is accessible to authorized users at authorized times
	
## Computing Environments

### Traditional Computing

Evolves over time

Workstations

Thin Client 

Remote work

### Mobile Computing

Primary way for many

Email, Messaging, Word Processing

Web/social

### Distributed Systems

- Physically separate systems working together (networkd together)
- Shared file system
- Connected via
	- LAN - Local area network
	- WAN - Wide area network
	- MAN - Metropolitan area network

### Client Server computing

- Client generates requests
- Server processes and returns answer
- WWW

### Peer-to-peer

Nodes in the system are not distinguished from each other

As peers, each node can perform client or server like operations. 

### Cloud computing

SaaS (Software as a service)
	- Google office, Office 365
	- Many many more

Platform as a Service (Paas)
	- AWS - amazon web services
	
### Real-Time Embedded Systems

Something like a stop light. Something that performs actions but without much interaction. It is just embetted. 

Time Critical

Internet of things




-----

# September 13, 2021

---

## OS System Services

### Common OS Service Categories

#### User/ Developer View

#####  Interface

Three cores: 
- Command line (CMD)
	- terminal, shell
	- Popular
		- c-shell, bourne shell
		- bourne Again Shell (bash)
		- Korn Shell
		- z shell (zsh)
	- Often considered a special program. May need some other service to allow the use of a terminal
	- Main Purpose
		- Get and execute the next command
- GUI - Graphical User Interface
	- Very user friendly
	- Mouse, keyboard, monitor
	- Quick history
		- Xerox had the first GUI (Xerox PARC)
		- Apple Macintosh (1984) was the first computer to use GUI with mouse. 
- Touch
	- Relatively new
	- Mobile devices
	
	

Program execution

i/o operations

filesystem manipulation

communication

Error detection

#### System View

Resource allocation

Logging/statistics

Protection and security


### System Calls

Interface between running process and OS
	- API (Application Programming Interface)
	- Set of functions provided to a developer
Common
	- Windows API
	- POSIX (UNIX, Linux, MacOS)
	- Java
	- .NET
Example
	- fork - Create a child process
	- read - Read from a file descriptor

#### Implementation

One function call = many system calls

`cp in.txt out.txt`

Example system call sequence: Acquire input file name -> Write prompt to screen - Accept input -> etc

#### Passing Data

Passing parameters from a user mode called to a kernel mode function

Three Ways
	- Registers
		- Major limitation - registers are very small and very few
	- Blocks or tables
		- Pass an address to a register
		- Pro - Unlimited space for parameters
		- Slow 
	- Use a stack
		- User program push data
		- kernel service pop data
		
		
#### Types of System Calls

Process Control

File Management

Device Management
	- Request/release devices
	- read/write/reposition
	- attach/detach devices

Information management (About the system)
	- system data
	- get/set time
	- get/set process information
	
Communication Management
	- Creating/termination connections
	
Protection
	- Get/set permissions
	
## System Services

aka: System Programs, system utilities

Many times, its a wrapper for a system call

Categories
	- File manipulation
		- Create/delete/modify
		- Simple text editors
		- Search content
	- Status information
		- Getting system status
	- Programming/dev support
		- Compilers/Assemblers/Interpreters
	- Communication
	- Program loading and execution
	- Background services
		- Launched at boot time
		- Scheduling, disk checking
		- ak : services, subsystems, daemon
	- Interrupts
		- /proc/interrupts
		- `watch -n 0.1 -d cat /proc/interrupts`
		
## Why are apps OS specific? 

Different machine languages

Different system call structures
	- GUI is a big deal
	
Interpreted/VM
	- Bytecode > machine code
	
OS Design and Implentation
	- This is not solved. SO many different needs
	- GOals
		- User
			- Convenient
			- Easy to learn
			- reliable
		- System (Developer)
			- Easy to design
			- Easy to implement and maintain
			Flexible
			Error free, reliable
			Efficient
	- Conflicts
		- User wants fast > 
		
---

# September 17, 2021

---

### Mechanisms and Policies

Mechanism - How something will be done

Policy - What will be done (How mechanism will be used)

Example: 
	-Timer
		- Proect the CPU
		- Enables multi-programming

Anything with resource allocation


### Implementation 

How are we going to write it? 

Early operating systems were written in Assembly. Doesnt really have a compiler. 
	- Benefits of Assembly
		- Incredibly fast if used appropriately. 

High Level Languages
	- C/C++
	- Easier to port
	- Easier to maintain
	- Data structures/Algorithm for efficiency
	
### OS Structure

Just like any program. Properly organizing its content can make management easier. 

Monolithic Structure: Something that comes from one original location
	- Simplest Structure
	- All functionality is in a single addres sspace/single binary file
	- Single address space: 
		- Very Fast
		- Little overhead
	- Tightly Coupled System
		- Hard to maintain
		- Unexpected negative impact after changes
	- Pro - little overhead - very fast
	- Cons 
		- Difficult to maintain
		- Memory space overhead (unused drivers)
		- Difficult to implement
	

### Layered Structure


Loosely couple system - Separate into smaller components
	- Abstraction/encapsulation
	- Layer 0: Hardware, Layer n = user interface
	- Pro 
		- Simplicity of implementation (layer level) 
		- Debugging
	- Con 
		- Defining Layers is difficult
		- Communication overhead is huge
	- Not a common approach for OS.
	- Very common in network communication
		- TCP/IP stack. Has 7 layers. 




-----


# September 20, 2021

---

## Microkernel

Developed in the mid 1980's

Organization called 'Mach' wanted to take some aspects out of the kernel and minimize it. Some things like 'Application Program', 'File system', and Device Driver. Kernel is then limited to
	- Introprocess Communication
	- Memory Management
	- CPU schedulding
	
Pros: 
	- Very fast (for things inside the kernel) and very small
	- Extending the kernel is very easy. 
	- Increased security
	- Easy to port

Cons:
	- Poor performance when communicating with things outside of the kernel. 
	- Context switching
	
Microkernel example: MacOS (formerly OS X). It is based on the Mach microkernel. 

## Modules

Linux often uses Loadable Kernel Modules (LKM)

Modules are loaded at boot time

Ex - Can change up the file system

	
## Hybrids

Most OS's use some type of hybrid aproach and pick and choose features. 

Linux uses this
- Monolithic - kernel is in a single address space
- Modular - functionality is dynamically added to the kernel. 

## Bootup 

2.9.2

BIOS - Basic Input Output System
	- Small program stored on a non-volatile chip
	- Lods the bootstrap program
	- MBR
		- Lives the hard drive
		- Stores the bootstrap program
	- Bootstrap pulls the OS from HD into memory
	- Limitations
		- 16-bit mode (Very limited on the amount of data it can hold)
		- Text-based interface
		- HD limitation of 2.1TB
		- Initialize one HW device at a time
		- Multi-step processes
		- Security Concerns

UEFI - Unified Extensible Firmware Interface
	- 32 or 64-bit mode (Much more space to do things)
	- GUI interface
		- Mouse
	- Initialize multiple HW devices at once
	- Networking - remote troubleshooting 
	- Larger HD (xetabytes)
	- Checks the OS for validity
	- GUID PT - Replaces the MBR
		- Globally Unique ID partition table. 
		- Boot process is contained throughout the HD
		
		
## Process Concept

History
	- Processes were called 'jobs' previously. 
		- Batch systems
	- Time Shared Systems were introduced.
		- Multiple programs could run on the same system. 
		- context switching
		- During this period, processes were called 'tasks'
	- Most recently, activities on a computer switch between user and kernel and are called 'processes'
		- Job scheduling - realy process scheduling
		
Terms
	- Program - Passive. An executable sitting in storage
	- Process - Active. Running in memory. Using or waiting for memory
	
Organization
	- What is a process? Its a data structure
	- Stack | -> <- | Heap |  data | text |
		- Text - executable code
		- Data - global variables/resources
		- Heap - Dynamically allocated memory
			- Stores things like Objects
		- Stack - Temporary storage of activation records created with a function callf
		
	- There is a user and kernel mode stack
		- Kernel stack executes kernel mode instructions for the process. 
		- kernel is very secure

## Process States

New: Proces is created

Ready: Process is waiting for the CPU for assignment


## September 24, 2021

---


	

		
-----

# September 27, 2021

---

## Android Process Hierarchy

### Priorities

Forground processes - currently visible. 

Visible Processes - Not entirely visible. Used by a foreground process
    - EX: GPS, Excelorameter. 

Service Process - Performing activity apparent to the user. Not doing something for the foreground process, but it is something the user notices, such as streaming audio. 

Background Process - Performing activity that is not apparent to the user. 

Empty Process - No active components associuated with any application. 
    - Ex. Process to decide what other processes to kill

## Interprocess Communication (IPC)

Two types of processes
- Independent - Not affected by other processes, does not affect other processes
- Cooperating - Can affect, and be affected by other processes

Reasons
- Information sharing
- Synchonization
- Modularity - Simplicity of maintenance/implementation

Two approaches
- Shared Memory
    - A region of memory accessible by multiple processes
    - Procudcer/Consumer Paradign
        - Producer - creates data
        - Consumer - uses data
        - Can sometimes go both ways
    - Model
        - Shared buffer/array
        - Bounded capacity (Bounded buffer). aka: Limited amount of space we can use
            - Producer cannot add when full. Producer must wait
            - Consumer must wait when empty
        - Unbounded capacity/buffer
            - Producer can always add
            - Consumer must wait when empty
            - Network has something like unbounded capacity. 
        - Neither producer or consumer communicate directly
    - Concerns
        - Simultaneous access
- Message Passing
    - Send/Receive
    - Implementation Issues 
        - Physical/Hardware Support
            - Can actually be done through Shared memory
            - Hardware bus
            - Network
        - Logical Components
            - Buffer
            - Message Types
            - Passing Model (Direct/Indirect)
    - Issues/Concerns
        - How is the link established?
        - Are there more than two processes? 
        - How many links between processes?
        - Capacity/Bandwidh
        - Fixed or variable size messages
        - Uni or bi directional concerns
        - Buffered or non buffered
        - Blocking or non-blocking
 
## Direct vs Indirect Message Passing

### Direct

Processes know about each other

#### Properties

Single link established automatically

Exactly one pair of processes
    - Link between each pair

Typically bi-directional, can be unidirectional

#### Operations

Send(P, message)

Receive(Q, message)

#### Major Issue

Hard coding of process id information

### Indirect

#### Properties

Messages are sent/received to/from "mailboxes" or ports

Each Mailbox is uniquely identified

Processes can communicate if they share a mailbox. 

One linke/mailbox can be shared between many processes

Uni or bi-directional

#### Operations

send(A, message)
receive(A, message)

#### Major Issues

How is a receive handled? 

##  Sychronization

The behavior of send/receive actions

### Blocking Behavior

Synchronous behavior

Blocking send
- Sender is blocked until message is received
- Similar to a singing telegram

Blocking recieve
- Receiver is blocked until a message is available
- Similar to a bus stop

### Non-blocking behavior

Asynchronous

Non-blocking send
- Sender sends message and continues on
- Similar to dropping a letter in a blue mailbox

Non-blocking receive
- Receiver gets a message or null message
- Similar to blue mailbox pickup



-----




Name of the part of the operating system that is always running: kernel

NOT a basic component of a Computer System: Network Adapter

Iterrupt Service Routine: Name for the instructions that are executed when a certain event happens such as a keyboard key being pressed.

Memory is NOT consided non-volatile

Data Storage DCevices in order from fastest to slowest: 
- Registers
- Main Memory
- Hard Disk
- Tape

Fault tolerance IS NOT one of the key advantages of a single processor system over a multi-processor system

In Symmetric Multiprocessing (SMP), each processor can run any task, including OS (not user level) processes. 

Multi-core processor configuration makes a shared cache on the CPU possible. 

Bootstrapping: The startup process to get the OS running when a computer is turned on

Resource management responsibilities of the OS: 
- Cache Managmenet
- Process Management
- I/O Subsystem Management
- Mass Storage Management
- Memory Management
- File System Management

Integrity - Ensuring that data is correct, authentic and reliable

pwd - print working directory path

Voice is NOT one of the three main categories of User Interfaces for an OS.

System Call - Interface between a running process and the OS

Using a register to pass data from a user mode process to the OS running in kernel mode IS possible on modern computer systems

Protection is NOT a category of system services

It is NOT best if Mechanisms and Policies are combined in the implementation of a computer system for efficiency and speed. 

Monolithic - Name of a type of structure of an OS where the kernel is in a single static binary file and is executes in a single address space

BIOS does not run with a GUI interface and allows for much larger sized HDDs than earlier system start processes. 

The terms Program and Process do not mean the same thing

Section of memory with dynamic sizes: 
- Heap
- Stack

Ready - State that a process must be before moving to a ready state. 

The Process Control Block contains information to enable a process to be loaded back to the CPU to begin executing where it left off. 

Context Switch - The term which describes the process of changing the process that is running on the CPU

Changing processes on the CPU is NOT an incredibly fast process (10's of cpu cycles) which results in very little computation overhead. 

Fork - Name of the system call to create a new child process. 

RMI - Example of Message Passing

In the Shared Memory model, the shared memory is not required to be located within the kernel memory space. 

The comuser Producer model is NOT outdated

Number of Procudes - Not necessary to keep track of in a Bounded Buffer model

In Indirect Communication it is never necessary for processes to know about other processes. 

Zero Capacity Buffer - Something the Sender always block on the receiver

Ordinary Pipes are uni-directional

It is false that threads share process instructions, data, files, register values, but each thread has its own stack

Parallelism is NOT one of the main benefits of multi-threaded programs

Data Parallelism is NOT focused on distributing subsets of data across multiple cores, and performing unique operations on each core. 

Amdahl's Law estimates the maximum speedup of a program can achieve ased on the percentage of a program that must be serial

In all thread models, kernel threads are necessary 

In the One-to-One thread model, a system call must be made by one thread will never block the other thread

In an asynchronous thread execution strategy, The parent creates the chidl threads and then continues execution

PThreads are NOT an implementation of a standard design

Java threads can be created by creating a Runnable object and passing it to a Thread constructor

Implicit Threading - Describes pushing creation and management of threads to run-time libraries and compilers, instead of requiring a developer to manage it. 

Fork Join - Threading approach that is similar to divide and conquer recursion solution

Thread Pool - Threading approach that is used by Java Executor service. 


Completely Sure: 
---
1
2
4
5
6
7

Pretty Sure: 
---
3
8
10

Probably, not not confident: 
---
9
12

No Idea
---





