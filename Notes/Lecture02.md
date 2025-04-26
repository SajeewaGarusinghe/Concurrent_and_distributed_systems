 ---

# 🧵 **  Concurrent and Distributed Systems**  
## 📘 Lecture 02 – Thread Life Cycle & Java Locking Mechanisms

---

## 🧩 PART 1: Java Locking Mechanisms

### 🔐 1. What is a Lock?

A **lock** is a concurrency control mechanism used to **restrict access** to shared resources (like objects or variables) so that **only one thread** can access a **critical section** at a time.

---

## 🔒 2. Types of Locks in Java

| Lock Type | Description | Package/Class |
|-----------|-------------|----------------|
| **Synchronized** (Intrinsic Lock) | Built-in locking mechanism | `synchronized` keyword |
| **ReentrantLock** (Extrinsic Lock) | Explicit, flexible locking | `java.util.concurrent.locks.ReentrantLock` |
| **ReentrantReadWriteLock** | Enables multiple reads and exclusive writes | `java.util.concurrent.locks.ReentrantReadWriteLock` |

---

### ✅ **Synchronized (Intrinsic Lock)**

```java
public synchronized void deposit(double amount) {
    balance += amount;
}
```

- Automatically applied at the method or block level.
- One thread at a time can enter the synchronized method or block.

---

### ✅ **ReentrantLock (Extrinsic Lock)**

```java
ReentrantLock lock = new ReentrantLock();

lock.lock();
try {
    // critical section
} finally {
    lock.unlock(); // Always release the lock
}
```

- **Manual control** over locking and unlocking.
- More powerful than `synchronized`:
  - Can attempt a timed lock (`tryLock()`).
  - Can be interrupted while waiting for lock.

---

### 🤔 **What is Reentrancy?**

> A lock is **reentrant** if a thread that already holds the lock can reacquire it again **without getting blocked**.

### 💡 Example:

```java
public synchronized void withdraw() {
    // Acquires lock
    getBalance(); // Also synchronized
}
```

- Thread calls `withdraw()`, which internally calls `getBalance()`.
- Even though both methods are synchronized, the thread **won’t be blocked** when calling `getBalance()` — because it's the **same thread** re-entering the lock.

Hence: **“Reentrant”** lock.

---

### ✅ **ReentrantReadWriteLock**

#### 🔧 Key Features:
- Designed for **Reader-Writer scenarios**.
- Allows **multiple threads to read** if there is **no writer**.
- Allows **only one writer**, and **no readers** during write.

```java
ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
ReadLock readLock = lock.readLock();
WriteLock writeLock = lock.writeLock();
```

#### 🔍 Behavior Table:

| State                | Allowed?  |
|----------------------|-----------|
| Multiple readers     | ✅ Yes     |
| One writer           | ✅ Yes     |
| Writer + readers     | ❌ No      |
| Multiple writers     | ❌ No      |

---

## 🧩 PART 2: **Thread Life Cycle**

A Java thread goes through **six main states** during its lifetime.

---

### 🔹 **1. NEW**

- Thread is **created but not started**.
```java
Thread cmwThread = new Thread(cmw, "Career Minded Wife");
```

At this point, thread is in the **NEW** state.

---

### 🔹 **2. RUNNABLE**

- Thread moves to this state after calling `.start()`.
```java
cmwThread.start();
```

- The thread is now **ready to run** but may not be running **immediately**.

#### 🧠 Substates:
- **READY**: Waiting for CPU allocation
- **RUNNING**: Actually executing on CPU

Note: In a **single-core system**, only one thread will run at a time (time slicing used).

---

### 🔹 **3. BLOCKED**

- A thread is **blocked** if it tries to access a resource that is **locked by another thread**.
- It stays here until the lock is released.

#### 🔁 Transition:
- From `RUNNABLE` ➡️ `BLOCKED`
- Then back to `RUNNABLE` once lock is acquired

---

### 🔹 **4. WAITING**

A thread enters WAITING when it **waits for another thread’s signal**.

#### Triggered by:
- `wait()`
- `join()`

#### Exits when:
- Another thread calls `notify()` or `notifyAll()`.

> ⚠️ This state has **no timeout** — it will wait indefinitely.

---

### 🔹 **5. TIMED_WAITING**

Same as WAITING, but with a **time limit**.

#### Triggered by:
- `sleep(milliseconds)`
- `join(milliseconds)`
- `wait(milliseconds)`

```java
Thread.sleep(100); // Pauses thread for 100ms
```

Once time expires → back to **RUNNABLE**

---

### 🔹 **6. TERMINATED (DEAD)**

When `run()` completes, the thread moves to **TERMINATED** state.

> It cannot be restarted.

---

## 🔁 Thread Life Cycle Summary Diagram (Text-Based)

```
NEW --> RUNNABLE --> RUNNING --> TERMINATED
         |   ↑         ↓
         ↓   |       BLOCKED
      WAITING / TIMED_WAITING
```

---

## 🛠️ Thread Lifecycle Example

```java
Runnable cmw = new CMWRunnable();
Thread cmwThread = new Thread(cmw, "Career Minded Wife");

cmwThread.start(); // NEW -> RUNNABLE

// Inside run():
Thread.sleep(100); // RUNNABLE -> TIMED_WAITING

// Waiting for HBH to finish:
hbhThread.join(); // RUNNABLE -> WAITING

// Uses synchronized method:
account.deposit(); // May go to BLOCKED if lock not available

// run() completes:
TERMINATED
```

---

## 🧠 Summary Table: Lock Types

| Lock Type             | Supports Reentrancy | Allows Multiple Readers | Requires Manual Unlock |
|------------------------|---------------------|--------------------------|-------------------------|
| `synchronized`         | ✅ Yes               | ❌ No                    | ❌ No                   |
| `ReentrantLock`        | ✅ Yes               | ❌ No                    | ✅ Yes                  |
| `ReentrantReadWriteLock` | ✅ Yes            | ✅ Yes (ReadLock)        | ✅ Yes                  |

---

## ✅ Final Key Takeaways

- Java provides **intrinsic (`synchronized`)** and **explicit (`ReentrantLock`)** locking.
- Use `ReentrantReadWriteLock` for **reader-writer problems**.
- Threads go through six main **lifecycle states**.
- Use **locks wisely** to prevent race conditions and ensure data consistency.
- Always **release locks** in a `finally` block when using `ReentrantLock`.

---
 
