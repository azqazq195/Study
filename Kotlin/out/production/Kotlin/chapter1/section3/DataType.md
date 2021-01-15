# 자료형 비교, 검사, 변환

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

## 자료형의 검사

### is 키워드를 사용한 검사

```kotlin
val num = 256

if (num is Int) {
    // num 이 Int 형이 아닐 때
    print(num)
} else if (num !is Int) {
    // num 이 Int 형이 아닐 때, 
    // !(num is Int) 와 동일 
    print("Not a Int")
}
```

## 묵시적 변환

### 스마트 캐스트

스마트 캐스트로 자료형을 알아서 바꿔줄 수 있다. Any > Number > Int, Long 등

#### Any

- 자료형이 정해지지 않은 겨웅
- 모든 클래스의 뿌리
- Any 는 언제든 필요한 자료형으로 자동 변환(스마트 캐스트)

```kotlin
fun chapter1.section1.chapter1.section2.chapter1.section3.chapter1.section4.main() {
    checkArg("Hello")   // 문자열을 인자로 넣음
    checkArg(5)         // 숫자를 인자로 넣음
}

fun checkArg(x: Any) {    // 인자를 Any 형으로 받음
    if (x is String) {
        println("x is String: $x")
    }
    if (x is Int) {
        println("x is Int: $x")
    }
}
```

#### Number

```kotlin
// 12.2 에 의해 test 는 Float 형으로 스마트 캐스트
var test: NUmber = 12.2

test = 12       // Int 형으로 스마트 캐스트
test = 120L     // Long 형으로 스마트 캐스트
test += 12.0f   // Float 형으로 스마트 캐스트
```