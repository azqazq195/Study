# 반복문

## for 문

### for 문의 선언

```kotlin
for (요소 변수 in 컬렉션 혹은 범위){
    반복할 본문
}
```

```kotlin
// 코틀린의 in 과 범위 지정을 활용한 루프
for (x in 1..5) {
    println(x)
}

// 한줄에 표현하는 경우
for (x in 1..5) println(x)
```

### 하행 반복 - downTo

- 5, 4, 3, 2, 1

```kotlin
for (i in 5 downTo 1) print(i)

// 잘못된 사용법. 출력되지 않는다.
// for (i in 5..1) print(i)
```

- 필요한 단계 증가 - step

```kotlin
for (i in 1..5 step 2) print(i)
```

- 혼합 사용

```kotlin
for (i in 5 downTo 1 step 2) print(i)
```

- 추가
```kotlin
 for (i in 0..(input-1))
     
 for (i in 0 until input)
```

until -> input 전까지