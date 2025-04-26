Got it — you want a **really good**, **fully structured**, **deeply explained**, **reader-friendly** note that reads like a well-prepared textbook chapter based on your material.

I'll organize, elaborate, and enrich your content carefully, while keeping it professional, clear, and detailed.

---

# 📘 7SENG007C – Concurrent and Distributed Systems (Part 1: Concurrent Programming)

---

## 📋 Module Structure

- **Part 1**: **Concurrent Programming** (Lecturer: Mr. Guhanathan P)
  - Duration: 6–7 weeks
  - Coursework Assignment: **50%**
- **Part 2**: **Distributed Systems** (Lecturer: Mr. Sajith)
  - Coursework Assignment: **50%**

*This note covers **Part 1: Concurrent Programming.***

---

## 🧵 1. Introduction to Concurrent Programming

### ➡️ What is Concurrent Programming?

Concurrent Programming enables a system to execute **multiple tasks simultaneously**.

> ⚡ **Sequential Programming**: Only one task at a time (Single-threaded by default in Java).
>  
> ⚡ **Concurrent Programming**: Multiple tasks operate at the same time, usually using **threads**.

---
  
### 📜 Basic Definitions

- **Program**: A set of instructions.
- **Process**: A program in execution, with its **own memory and resources**.
- **Thread**: A lightweight sub-process that shares the memory of its process.

**➡ In Java:**
- Every Java application starts with a **single process** (your program).
- Inside the process, you can create **multiple threads** to achieve concurrency.

---

### 🧠 Why Threads Instead of Processes?

| Process | Thread |
|:-------|:-------|
| Heavyweight | Lightweight |
| Separate memory space | Shared memory space |
| Expensive context switching | Cheap context switching |
| Harder communication | Easier communication |

---

### 🎓 Analogy to Understand

Imagine you are a lecturer accidentally scheduling two batches at the same time:

| Scenario | Analogy | Relates To |
|:--------|:--------|:----------|
| Two batches in separate rooms, running back and forth | High switching overhead | Separate Processes |
| Two batches in a large single room | Easy to manage | Threads |

---

## 👨‍💻 2. How to Create Threads in Java

There are three ways to create threads:

### ✅ 1. Extending `Thread` class

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running...");
    }
}
```

**Cons**:
- Java does **not support multiple inheritance**.
- You lose flexibility if you extend `Thread`.

---

### ✅ 2. Implementing `Runnable` interface

```java
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Runnable running...");
    }
}
```

Then, create a `Thread` object:

```java
Runnable r = new MyRunnable();
Thread t = new Thread(r);
t.start();
```

> ✅ **Recommended Approach** for better design flexibility.

---

### ✅ 3. Using Lambda Expressions (Java 8+)

```java
Thread t = new Thread(() -> {
    System.out.println("Lambda Thread running...");
});
t.start();
```

> ✅ Great for **one-time-use** threads.

---

## ⏳ 3. Thread Life Cycle

| Stage | Description |
|:------|:------------|
| New | Thread object created |
| Runnable | After `start()` called, ready to run |
| Running | Thread is executing |
| Blocked | Waiting for a lock/resource |
| Terminated | Finished execution |

**Important:**  
Calling `.run()` directly does not create a new thread — it just behaves like a normal method.

---

## 🕰️ 4. Thread Scheduling & Priority

Even on a **single-core CPU**, concurrency is achieved through **time slicing**.

### 🛠️ Key Points:

- **Round-Robin Scheduling**: Threads get time slices (quanta) one after another.
- **Priority-Based Preemption**:
  - Higher priority threads may preempt lower priority ones.
  - Java supports **fixed-priority preemptive scheduling**.

```java
t1.setPriority(Thread.MAX_PRIORITY);
```

> 🎯 **However, thread scheduling is not fully predictable.**

---

## ⚔️ 5. Race Condition and Critical Section

### ❓ What is a Race Condition?

When **multiple threads** try to access and modify **shared resources** **simultaneously** without proper synchronization.

### 📍 Example: Bank Account

Shared Object: **BankAccount**

Two threads:
- Career Minded Wife (CMW) deposits money.
- House Based Husband (HBH) withdraws money.

**Problem Scenario:**

| Step | CMW (Thread 1) | HBH (Thread 2) |
|:----|:--------------|:--------------|
| Read balance | 25000 | 25000 |
| Update | +25000 | -25000 |
| Write back | 50000 | 0 |

**Expected balance** = 25000  
**Actual balance** = 0 ❌

---

### 🎯 Critical Section

Any part of the code where shared data is accessed.

**Examples**:
- `getBalance()`
- `deposit()`
- `withdraw()`

**Solution**: Synchronize the critical section to **allow only one thread** at a time.

---

## 🔐 6. Synchronization (Locking)

**Java Synchronization** is achieved using the `synchronized` keyword.

```java
public synchronized void deposit(double amount) {
    balance += amount;
}
```

**What happens when synchronized?**

- Only one thread can access the method at a time.
- Other threads are **BLOCKED** until the lock is released.

---

### 🏛️ Reader-Writer Problem (Classical Concurrency Problem)

#### Rules:

| Condition | Action |
|:----------|:-------|
| No writers active | Multiple readers allowed |
| Writer active | No readers allowed |
| Only one writer allowed | At a time |

> ✅ Multiple readers are OK together.  
> ❌ Writer and readers cannot operate together.

---

## 💻 Java Example: `BankAccount` Class

```java
public class BankAccount {
    private double balance;
    private String accountID;

    public BankAccount(double balance, String accountID) {
        this.balance = balance;
        this.accountID = accountID;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized void deposit(double amount) {
        if (amount > 0) balance += amount;
        else throw new IllegalArgumentException("Invalid amount!");
    }

    public synchronized void withdraw(double amount) {
        if (amount > 0 && balance - amount >= 0) balance -= amount;
        else throw new IllegalArgumentException("Invalid or insufficient amount!");
    }
}
```

> 🏦 Here, `balance` is the **shared resource**.

---

## 🎯 Important Concepts Recap

| Concept          | Meaning |
|:----------------|:---------|
| Process         | Program in execution |
| Thread          | Unit of execution within a process |
| Critical Section| Shared code needing synchronization |
| Race Condition  | Concurrent access leading to data corruption |
| Synchronization | Locking mechanism to prevent race conditions |
| Time Slicing    | Dividing CPU time among threads |
| Preemptive Scheduling | Higher-priority thread preempts lower ones |

---

# 🔥 Key Takeaways

- Prefer **Runnable** over **Thread inheritance**.
- Always protect shared data using **synchronization**.
- Be cautious: **Thread execution order is non-deterministic**.
- Race conditions can cause serious **data corruption**.
- Threads share resources, **Processes do not**.

---

# 📖 Coming Up Later in the Module

| Topic | Summary |
|:------|:--------|
| Thread Groups | Managing multiple threads together |
| Monitor | Special mechanism for managing synchronization |
| Semaphores | Advanced locking and thread signaling tool |

---

---

**✅ Done: Full organized, elaborated, descriptive note as you requested.**

---

Would you like me to also prepare **diagram illustrations** (like Thread Life Cycle, Process vs Thread, Race Condition flow)?  
It would make your final notes even more complete! 🎯  
Shall I? 📚✨
