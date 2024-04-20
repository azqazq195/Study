## 재귀 함수
### 재귀(recursion)란
- 자기 자신을 다시 참조
- 재귀 함수는 자기 자신을 계속 호출하는 특징

#### 재귀 함수의 필수 조건
- 무한 호출에 빠지지 않도록 탈출 조건을 만들어 둔다.
- 스택 영역을 이용하므로 호출 횟수를 무리하게 많이 지정해 연산하지 않는다.
- 코드를 복잡하지 않게 한다.

```kotlin
fun main() {
    val number = 4
    val result: Long = factorial(number)

    println("Factorial: $number -> $result")
}

fun factorial(n: Int): Long {
    return if (n == 1) n.toLong() else n * factorial(n - 1)
}
```

## 꼬리 재귀 함수(tail recursive function)
- 스택에 계속 쌓이는 방식이 함수가 계속 씌워지는 꼬리를 무는 형태
- 코틀린 고유의 tailrec 키워드를 사용해 선언

```kotlin
fun main() {
    val number = 5
    println("Factorial: $number -> ${factorial(number)}")
}

// 스택을 사용하지 않음
tailrec fun factorial(n: Int, run: Int = 1): Long {
    return if (n == 1) run.toLong() else factorial(n - 1, run * n)
}
```