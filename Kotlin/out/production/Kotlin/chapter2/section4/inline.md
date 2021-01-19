## 인라인 함수의 제한과 인라인 금지
### 인라인 함수의 단점
- 코드가 복사되므로 내용이 많은 함수에 사용하면 코드가 늘어남

### noinline 키워드
- 일부 람다식 함수를 인라인 되지 않게 하기 위해
```kotlin
inline fun sub(out1: () -> Unit, noinline out2: () -> Unit) {}
```