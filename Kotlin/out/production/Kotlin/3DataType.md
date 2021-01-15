# 자료형 비고, 검사, 변환

## 코틀린의 자료형 변환

- 기본형을 사용하지 않고, 참조형만 사용
- 서로 다른 자료형은 변환 과정을 거친 후 비교

```kotlin
val a: Int = 1      // 정수형 변수 a 선언, 1 할당
val b: Double = a   // 자료형 불일치 오류 발생
val c: Int = 1.1    // 자료형 불일치 오류 발생
```

- 변환 메서드의 이용

```kotlin
val b: Double = a.toDouble()
```

- 표현식에서 자료형의 자동 변환

```kotlin
val result = 1L + 3     // Long + Int -> result 는 Long
```

### 변환 메서드의 종류

- toByte: Byte
- toLong: Long
- toShort: Short
- toFloat: Float
- toInt: Int
- toDouble: Double
- toChar: Char

## 기본형과 참조형 자료형의 비교 원리

### 이중 등호(==)와 삼중 등호(===)의 사용

- == : 값만 비교하는 경우
- === : 값과 참조 주소를 비교할 때

```kotlin
val a: Int = 128
val b: Int = 128
println(a == b)   // true
println(a === b)  // true
```

```kotlin
val a: Int = 128
val b: Int? = 128
println(a == b)   // true   (기본형)
println(a === b)  // false  (객체)
```