## while 문

### 선언

```kotlin
// 조건식이 true ㄴ인 동안 본문의 무한 반복
while (조건식) {
    본문
    ...
}
```

```kotlin
var i = 1

// 계속 반복하다 5이상으로 넘어갈 때 false가 되어 탈출
while (i <= 5) {
    println("$i")
    ++i
}
```