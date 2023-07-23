# Concurrency

---

## Concurrency 란?

- 병렬 처리를 가능하게 하는 프로그래밍 기법
- 여러 작업을 동시에 실행하여 효율을 높임
- 동시성 제어를 위해 동기화, 락, 데드락 방지 등의 중요한 개념들을 포함

## Concurrency 문제란?

Concurrency 문제는 동시에 여러 작업을 수행하는 과정에서 발생하는 문제

- Race Condition: 동시에 같은 데이터에 접근하면서 발생하는 데이터 불일치 문제
- Deadlock: 서로 다른 스레드가 점유하고 있는 자원을 기다리며 진행이 멈추는 상태
- Starvation: 일부 스레드가 자원을 영구히 점유할 수 없어 무한정 대기하는 상태
- Live Lock: 서로를 방해하며 진행이 안 되는 상태로, 데드락과 비슷하지만 스레드는 활동적

## Thread-Safe

- 'Thread Safe'는 여러 스레드가 동시에 데이터를 접근하더라도 안전하게 동작함을 의미
- 동시성을 보장하면서도 데이터 일관성을 유지하기 위한 개념

## Concurrency 해결방안

- Synchronization: 데이터를 접근하는 스레드의 순서를 제어
    - 단점: 스레드가 순차적으로 접근하도록 강제하기 때문에 성능 저하
- Locking: 한 번에 하나의 스레드만 데이터에 접근하도록 보장
    - 단점: 락을 기다리는 시간 발생, 락의 획득과 해제 과정도 오버헤드
- Atomic Operations: 이 연산은 중간에 방해를 받지 않으므로 race condition을 방지
    - 단점: 단순한 연산만을 제공, Atomic operations에 대한 높은 이해도가 필요
- Immutable data: 데이터를 변경 불가하도록 설정
    - 단점: 데이터 변경 불가하며 동시성 처리와는 다소 동떨어지는 느낌

## Synchronization VS Locking

synchronized도 결국 내부적으로 lock을 사용한다.\
하지만 이는 JVM이 관라하는 implicit lock 이라고 하며 개발자가 제어할 수 없다.

### Synchronization

- synchronized 키워드를 이용하여 동기화 구현
- 메서드 또는 블록 수준에서 동기화 가능
- Lock을 자동으로 관리, 개발자가 직접 해제할 필요 없음
- 여러 개의 lock을 사용하면서 Deadlock 발생 가능성 있음
- 세밀한 제어 불가능. 전체 메서드 또는 블록에만 적용 가능

### Locking

- java.util.concurrent.locks.Lock 인터페이스를 이용한 동기화
- 블록 수준에서만 동기화 가능
- 개발자가 직접 Lock을 관리해야 함. Lock을 얻고 반드시 해제해야 함
- 더 많은 제어를 할 수 있음. 공정성(Fairness), 재진입성(Reentrant), 조건(Condition) 등을 설정할 수 있음
- Lock을 미세하게 제어 가능하여 동기화 블록을 작게 만들어 성능 향상 가능
- Deadlock 발생 가능성 있음. 다만, lock을 보다 세밀하게 제어 가능하므로 방지 방안을 더 적용하기 쉬움

## 실험실

간단하게 동시성 제어의 데이터 일관성과 성능을 확인해 보고자 한다.

### Entiiy 설계

동시성 제어가 되지 않는 class\
Entity:

```java
public class Entity {
    private int num;

    public void increment() {
        this.num += 1;
    }
    //..
}
```

`synchronized`을 통해 동시성 제어를 한 class\
SynchronizedEntity:

```java
public class SynchronizedEntity extends Entity {
    //..

    @Override
    public synchronized void increment() {
        super.increment();
    }
}
```

`lock`을 통해 동시성 제어를 한 class\
일기 작업이 더 많은 경우에는 `ReentrantReadWriteLock`으로도 사용 가능하다.\
LockedEntity:

```java
public class LockedEntity extends Entity {
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void increment() {
        lock.lock();
        try {
            super.increment();
        } finally {
            lock.unlock();
        }
    }
}
```

`atomic operations`를 통해 동시성 제어를 한 class\
AtomicEntity:

```java
public class AtomicEntity {
    private final AtomicInteger num = new AtomicInteger(0);

    public void increment() {
        this.num.incrementAndGet();
    }

    //..
}
```

### 테스트 진행

테스트 진행은 10개의 쓰레드가 각 Entity의 number를 1씩 10000번 increment하도록 설정하였다.\
최종 number값은 100000의 결과를 반환할 것으로 예상된다.

```java
for(int i=0;i< 10;i++){
        executorService.submit(()->{
        for(int j=0;j< 10000;j++){
        entity.increment();
        }
        });
        }
```

### 일관성 확인

동시성 제어를 하지 않은 Entity는 일관성이 보장되지 않는 것을 확인할 수 있다.

```text
Entity: 18168
Entity: 24723
Entity: 26786
Entity: 95822
Entity: 93892

Synchronized: 100000
Synchronized: 100000
Synchronized: 100000
Synchronized: 100000
Synchronized: 100000

LockedEntity: 100000
LockedEntity: 100000
LockedEntity: 100000
LockedEntity: 100000
LockedEntity: 100000

AtomicEntity: 100000
AtomicEntity: 100000
AtomicEntity: 100000
AtomicEntity: 100000
AtomicEntity: 100000
```

### 성능 확인

간단한 테스트인점을 고려해 각각의 동시성 제어 기법에서는 수행시간의 큰 차이를 보이지 않는다.\
하지만 동시성을 제어함으로써 제어하지 않는 경우 보다는 성능이 떨어지는 것을 확인할 수 있다.

```text
Benchmark                    Mode  Cnt   Score    Error  Units
TestCode.entity              avgt    5  ≈ 10⁻³            s/op
TestCode.synchronizedEntity  avgt    5   0.001 ±  0.001   s/op
TestCode.lockedEntity        avgt    5   0.002 ±  0.001   s/op
TestCode.atomicEntity        avgt    5   0.002 ±  0.001   s/op
```

