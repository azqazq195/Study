# 함수

## 함수의 선언

```kotlin
fun sum(a: Int, b: Int): Int {
    var sum = a + b
    return sum
}
```

> fun 함수 이름 ([변수 이름: 자료형, 변수 이름: 자료형]): [반환값의 자료형]{
> 표현식...
> [return 반환값]

## 간략하게 표현하기

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