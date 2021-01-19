## 중위 함수
### 중위 표현법(infix notation)
- 클래스의 멤버 호출 시 사용하는 점(.)을 생략하고 함수 이름 뒤에
소괄호를 생략해 직관적인 이름을 사용할 수 있는 표현법
  
#### 중위 함수의 조건
- 멤버 메서드 또는 확장 함수여야 합니다.
- 하나의 매개변수를 가져야 합니다.
- infix 키워드를 사용하여 정의합니다.

```kotlin
fun main() {
    // 일반 표현법
    // val multi = 3.multiply(10)

    // 중위 표현법
    val multi = 3 multiply 10
    println("multi: $multi")
}

// Int 를 확장해서 multiply() 함수가 하나 더 추가되었음
// infix 로 선언되므로 중위 함수
infix fun Int.multiply(x: Int): Int {
    return this * x
}
```