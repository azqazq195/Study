# 함수

## 함수 기본

### 함수의 선언

```kotlin
fun sum(a: Int, b: Int): Int {
    var sum = a + b
    return sum
}
```

> fun 함수 이름 ([변수 이름: 자료형, 변수 이름: 자료형]): [반환값의 자료형]{
> 표현식...
> [return 반환값]

### 간략하게 표현하기

```kotlin
fun sum(a: Int, b: Int): Int {
    return a + b
}
```

위의 함수를

```kotlin
fun sum(a: Int, b: Int): Int = a + b
```

로 표현할 수 있고, a 와 b 가 Int 형이기 때문에 리턴타입도 Int 로 추론 가능하다.

```kotlin
fun sum(a: Int, b: Int) = a + b
```

### 가변인자

```kotlin
fun normalVarargs(vararg a: Int) {
    for (num in a) {
        print("$num ")
    }
}

fun main() {
    normalVarargs(1)
    normalVarargs(1, 2, 3, 4)
}
```

vararg 를 통해 여러개의 인자를 넣을 수 있다.

## 함수형 프로그래밍

### 코틀린은 다중 패러다임 언어

- 함수형 프로그래밍 (FP: Functional Programming)
- 객체 지향 프로그래밍 (OOP: Object-Oriented Programming)

### 함수형 프로그래밍

- 코드 간략, 테스트나 재사용성 증가
- 람다식, 고차 함수를 사용해 구성
- 순수 함수

## 순수 함수

### 순수 함수 (pure function) 이해

- 부작용(side-effect)이 없는 함수
    - 동일한 입력에 대해서는 항상 같은 결과를 출력 혹은 반호나 한다.
    - 값이 예측 가능해 결정적(deterministic)이다.

```kotlin
// 순수 함수의 예
fun sum(a: Int, b: Int): Int {
    return a + b;
}
// 동일한 인자인 a, b를 입력 받아 항상 a + b를 출력
```

- 순수 함수의 조건
    - 같은 인자에 대하여 항상 같은 값을 반환
    - 함수 외부의 어떤 상태도 바꾸지 않는다.

+ 순수함수가 아닌 것은?

```kotlin
fun check() {
    // check() 함수에 없는 외부의 User 객체 사용
    val test = User.grade()
    // 변수 test는 User.grade()의 실행 결과에 따라 달라짐
    if (test != null) process(test)
}
```

### 순수 함수를 왜 쓸까?

- 입력과 내용을 분리하고 모듈화 하므로 재사용성이 높아진다. 여러가지 함수들과 조합해도 부작용이 없다.
- 특정 상태에 영향을 주지 않으므로 병행 작업 시 안전하다.
- 함수의 값을 추적하고 예측 할 수 있기 때문에 테스트, 디버깅 등이 유리하다.

### 함수형 프로그래밍에 적용

- 함수를 매개변수, 인자에 혹은 반환값에 적용 (고차 함수)
- 함수를 변수나 데이터 구조에 저장
- 유연성 증가

## 람다식

### 람다식(Lambda Expression)이란?

- 익명 함수의 하나의 형태로 이름 없이 사용 및 실행이 가능

```kotlin
// 람다싱의 예 (이름이 없는 함수 형태)
{ x, y -> x + y }
```

### 람다식의 이용

- 람다식은 고차 함수에서 인자로 넘기거나 결과값으로 반환등을 할 수 있다.

### 표현식이 2줄 이상일 때

```kotlin
val multi: (Int, Int) -> Int = { x: Int, y: Int ->
    println("x * y")
    x * y // 마지막 표현식이 반환됨
}
```

### 자료형의 생략

```kotlin
val multi: (Int, Int) -> Int = { x: Int, y: Int -> x * y }  // 생략 x 전체 표현
val multi = { x: Int, y: Int -> x * y }                     // 선언 자료형 생략
val multi: (Int, Int) -> Int = { x, y -> x * y }            // 람다식 매개변수 자료형의 생략
val multi = { x, y -> x * y }   // 에러!! 추론 불가
// Unit 타입의 경우 추론이 가능하다
val greet = { println("Hello World!") }
```

### 반환 자료형이 없거나 매개변수가 하나 있을 때

```kotlin
val greet: () -> Unit = { println("Hello World!") }
val square: (Int) -> Int = { x -> x * x }
```

### 람다식 안에 람다식이 있는 경우

```kotlin
val nestedLambda: () -> () -> Unit = { { println("nested") } }
```

## 고차 함수

### 고차 함수(high-order function)란?

```kotlin
fun main() {
    // 람다 함수를 인자로 넘김
    println(highFunc({ x, y -> x + y }, 10, 20))
}

// sum 매개변수는 함수
fun highFunc(sum: (Int, Int) -> Int, a: Int, b: Int): Int = sum(a, b)
```

`{ x, y -> x + y }` 람다식을
`sum: (Int, Int) -> Int` 형태의 매개변수로 받을 수 있다.

## 인라인 함수
- 함수가 호출되는 곳에 내용을 모두 복사
- 함수의 분기 없이 처리 -> 성능 증가

---
Ref. <a href="https://www.boostcourse.org/" target="_blank">네이버 부스트코스</a>