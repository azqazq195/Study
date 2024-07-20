# NULL

코틀린은 변수를 사용할 때 반드시 값이 할당되어 있어야 한다는 원칙이 있습니다.
만약 값이 할당되지 않은 변수를 사용하면 코틀린에서 오류가 발생합니다.

## null 을 허용한 변수 검사
#### 코틀린의 변수 선언은 기본적으로 null을 허용하지 않는다.

```kotlin
val a: Int = 30
var b: String = "Hello"
```

### null 가능한 선언

```kotlin
val a: Int? = null
var b: String? = null
```

이제 위 a, b는 null 일 가능성이 존재하므로 null 을 체크하지 않고 쓴다면
**NPE (NullPointerException)** 이 발생할 수 있다.

>Kotlin 에서는 기본적으로 NotNull 이고 Nullable 표현에만 '?'가 사용된다.

```kotlin
fun set(a: String, b: String?){
    // a는 NotNull 인자, b는 Nullable 인자
}
```

```kotlin
var temp: String? = null
var size = -1
if(temp != null){
    size = temp.length
}

// or

var temp: String? = null
val size = if (temp != null) temp.length else -1
// or elvis 연산자
val size = temp?.length ?: -1
```

### nonNull

`println("length: ${str1!!.length}")`
!!. 넌널(nonNull) 단정 기호 => 앞의 변수가 널일리 없다. 즉 널이더라도 실행이 된다(오류 발생).
넌널 단정 기호는 쓰지 않는 것이 좋다.
---
Ref. <a href="https://www.boostcourse.org/" target="_blank">네이버 부스트코스</a>