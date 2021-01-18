## 코틀린 표준 라이브러리

### 코틀린 제공 표준 라이브러리 함수

- 람다식을 사용하는 코틀린의 표준 라이브러리에서 let(), apply(), with(), also(), run() 등 여러 가지 표준 함수를 제공하고 있다.
- 표준 함수들은 대략 확장 함수 형태의 람다식으로 구성되어 있다.

| 함수명 | 람다식의 접근 방법 | 반환 방법 |
|---|---|---|
|T.let|it|block 결과|
|T.also|it|T caller (it)|
|T.apply|this|T caller (this)|
|T.run 또는 run|this|block 결과|
|with|this|Unit|

<br>

---

<br>

## let() 활용

### let 동작

- 함수를 호출하는 객체 T를 이어지는 block 의 인자로 넘기고 block 의 결과값 R을 반환

```kotlin
public inline fun <T, R> T.let(block: (T) -> R): R {
    ... return block(this)
}
```

- 매개변수 block 은 T를 매개변수로 받아 R을 반환
- let() 함수 역시 R을 반환
- 본문의 this 는 객체 T를 가리키는데 람다식 결과 부분을 그대로 반환한다는 뜻
- 다른 메서드를 실행하거나 연산을 수행해야 하는 경우 사용
