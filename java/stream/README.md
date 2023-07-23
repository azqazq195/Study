# JAVA Stream API

---

## Stream 이란?

- Java 8에서 도입된 데이터 처리 도구
- 컬렉션, 배열 등의 데이터 처리를 간결하게 해줌
- 데이터를 나중에 처리하도록 (lazy) 설정 가능

## 사용 이유

1. **함수형 프로그래밍**
    - 코드를 간결하게 작성하고 불변성을 유지
2. **효율성**
    - 필요할 때만 데이터를 처리 (lazy)
3. **병렬 처리**
    - 여러 코어/쓰레드에서 데이터 처리 가능
    - 멀티코어 프로세서의 장점을 취할 수 있다.
4. **데이터 처리 파이프라인**
    - 필터링, 매핑, 집계 등의 연산을 연결하여 가독성 높은 코드 작성

### 병렬 처리

Fork / Join Framework 를 사용하여 멀티 스레드 환경에서 동작한다.

1. 쓰레드가 parallelStream()를 호출
2. Fork / Join Framework 를 사용하여 입력된 데이터 분할
3. 분할된 작업들을 수행하기 위해 ForkJoinPool에서 ForkJoinTask를 생성
4. ForkJoinPool의 쓰레드들이 ForkJoinTask 작업을 병렬 수행
5. 모든 ForkJoinTask가 완료되면 ForkJoinPool이 모든 결과를 취합
6. 취합된 결과를 `parallelStream()`를 호출한 쓰레드에게 반환

멀티 코어환경이라도 프로세스가 parallelStream만 처리하는 것이 아니기 때문에, 대용량 데이터가 아니라면 적합하지 않겠다.

## 연산

1. **필터링(filter)**
    - 스트림의 요소 중 일부를 선택
2. **매핑(map)**
    - 스트림의 각 요소에 함수를 적용하여 새로운 스트림 생성
3. **리듀싱(reduce)**
    - 스트림의 모든 요소를 처리하여 단일 값으로 합침
4. **정렬(sorted)**
    - 스트림의 요소를 정렬
5. **매칭(anyMatch, allMatch, noneMatch)**
    - 스트림의 요소가 특정 조건에 맞는지 검사

### 추가

프로그래머스에서 코딩 테스트를 진행했을 때, Stream을 사용하니 수행시간이 10배로 나왔었다. 그 뒤로 Kotlin의 함수형 프로그래밍을 애용했더라도 Java에서는 Stream을 일부러 안써봤는데 테스트 결과 성능 저하가 별로 없는 것 같다.\
물론 약간의 오버헤드는 발생할 것.

테스트 결과는 다음과 같다.

```text
Benchmark Mode Cnt Score Error Units
ServiceTest.useNotStream avgt 5  0.002 ±  0.001 s / op
ServiceTest.useStream avgt 5  0.002 ±  0.001 s / op
```

Spring Framework에서 흔히 사용되는 Dto 전환을 Stream으로 사용해보았다.\
10만개의 Entity class를 만들고 Dto로 전환하는데, Stream을 사용한 것과 for문으로 작성한 것의 시간을 비교해 보았다.

```java
public List<Entity> createEntities() {
    List<Entity> result = new ArrayList<>();
    for (int i = 0; i < 100000; i++) {
        result.add(new Entity(i, "name" + i));
    }
    return result;
}
```

```java
@Benchmark
@BenchmarkMode(Mode.AverageTime)
public void useStream() {
    Entity[] entityArray = service.createEntities().toArray(Entity[]::new);
    List<Dto> dtoList = Arrays.stream(entityArray).map(Dto::new).toList();
}

@Benchmark
@BenchmarkMode(Mode.AverageTime)
public void useNotStream() {
    Entity[] entityArray = service.createEntities().toArray(Entity[]::new);
    List<Dto> dtoList = new ArrayList<>();
    for (Entity entity : entityArray) {
        dtoList.add(new Dto(entity));
    }
}
```

테스트를 위한 dependency

```kotlin

plugins {
    \\ ..
    id("me.champeau.jmh") version "0.7.1"
}

dependencies {
    // me.champeau.jmh plugin에서 사용할 jmh 버전을 명시
    jmh("org.openjdk.jmh:jmh-core:1.36")
    // 위와 별개로 해당 프로젝트에서 사용할 jmp
    testImplementation("org.openjdk.jmh:jmh-core:1.36")
}


jmh {
    // fork.set(1): 벤치마크를 실행하는 JVM 프로세스의 수를 설정합니다.
    // 설정된 값만큼 각 벤치마크가 별도의 JVM 프로세스에서 독립적으로 실행됩니다.
    // 이렇게 하면 각각의 벤치마크 실행이 서로 영향을 미치지 않도록 할 수 있습니다.
    fork.set(1)

    // iterations.set(1): 실제 벤치마크를 반복 실행하는 횟수를 설정합니다.
    // 설정된 값만큼 각 벤치마크가 반복해서 실행되며, 이때의 실행 결과를 기록합니다.
    // 여러 번 반복 실행하여 얻은 결과를 평균내거나 분석하여,
    // 벤치마크 대상 코드의 성능을 좀 더 정확하게 측정할 수 있습니다.
    iterations.set(5)

    // warmupIterations.set(1): "예열" 벤치마크를 반복 실행하는 횟수를 설정합니다.
    // 설정된 값만큼 각 벤치마크를 실행하기 전에 먼저 반복 실행합니다.
    // 이 예열 실행은 JVM이 최적화를 수행하게 하여 실제 벤치마크 실행에서는
    // 최적화된 코드가 실행되도록 하는 역할을 합니다.
    warmupIterations.set(2)

}

```
