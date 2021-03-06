프로그램의 진입점 역할을 하는 함수와 그 역할을 올바르게 설명한 것은?

- main() 함수는 프로그램의 진입점 역할을 하며 최상위 함수 입니다.

함수가 호출될 때마다 함수의 정보는 어떤 메모리에 쌓이며 무엇으로 부를 수 있나요?

- 스택메모리
- 프레임정보

다음과 같은 함수를 간략화 하였을때의 결과

```kotlin
fun test(a: Int, b: Int): Int {
    return a + b
}

fun test(a: Int, b: Int) = a + b
```

함수에서 하나 이상의 매개변수를 받아들이려고 합니다. 이때 사용하는 키워드는?

- vararg

람다식의 개념과 의미를 올바르게 설명한 것을 고르세요.

- 람다식은 화살표기호와 함께 람다 함수로 표현되며 익명함수의 일종이다.

일반 변수에 두개의 매개변수를 가지는 람다식을 통해 값을 곱하고 반환할 수 있도록 선언하고 할당하려고 합니다. 올바른 문법은?

`val multi = { x: Int, y: Int -> x * y }`

다음과 같은 함수 선언을 사용하려고 합니다. 올바른 호출 방법은?

```kotlin
fun more(out: (String) -> String) {
    println(out("Hello"))
}

more {"$it Kotlin"}
```

인라인 함수의 특징

- 인라인 함수는 inline으로 정의되며 함수 내용을 복사합니다.
- 분기가 없으므로 처리 성능이 증가합니다.ㅁ
- noinline을 사용하면 인라인을 금지합니다.

어떤 요소에 추가적인 기능을 구현하려고 합니다. 원본은 그대로 두고 추가기능을 구현할 수 있는 함수 기법은 무엇인가요?

- 확장 함수