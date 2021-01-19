## when 문으로 다양한 조건 처리

### 인자를 사용하는 when 문

- 기본

```kotlin
when (x) {
    1 -> print("x == 1")
    2 -> print("x == 2")
    else -> {
        // 블록 구문 사용 가능
        print("x는 1, 2 가 아닙니다.")
    }
}
```

- 일치되는 여러 조건

```kotlin
when (x) {
    0, 1 -> print("x == 0 or x == 1")
    else -> print("기타")
}
```

- 함수의 반환값 사용하기

```kotlin
when (x) {
    parseInt(s) -> print("일치함!")
    else -> print("기타")
}
```

- in 연산자와 범위 지정자 사용

```kotlin
when (x) {
    in 1..10 -> print("x는 1 이상 10 이하입니다.")
    !in 10..20 -> print("x는 10 이상 20 이하의 범위에 포함되지 않습니다.")
    else -> print("x는 어떤 범위에도 없습니다.")
}
```

- is 키워드 함께 사용하기

```kotlin
val str = "안녕하세요"
val result = when (str) {
    is String -> "문자열입니다."
    else -> false
}
```

### 인자가 없는 when

- 특정 인자에 제한하지 않고 다양한 조건을 구성 

```kotlin
when {
    조건[혹은 표현식] -> 실행문
    ...
}
```

