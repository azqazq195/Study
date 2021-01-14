# 변수
## 변수 선언

- val (value) - 불변형 (immutable)
- var (variable) - 가변형 (mutable)

```kotlin
val username: Stirng = "Kildong"
```

val : 선언 키워드
username : 변수 이름
String : 자료형
"Kildong" : 값

코틀린은 자료형을 따로 쓰지 않아도 명확한 데이터 값이 들어 갔을때 자료형을 알아서 추론한다.

```kotlin
val count = 3
// Int 자료형으로 추론된다.
```
IntelliJ에서 확인하는 방법
Window : ctrl + shift + p
Mac : 동

## 변수 선언 예
- val username = "moseoh"
  자료형을 추론하여 String으로 결정
- var username
  자료형을 지정하지 않은 변수는 사용할 수 없다.
- val init: Int
  사용전 혹은 생성자 시점에서 init변수를 초기화 해야함
- val number = 10 
  number 변수는 Int형으로 추론
  
### 주의점
- 변수 이름은 123abc 등 숫자로 시작하면 안된다.
- 변수 이름에는 while, if 문과 같이 코틀린 예약어는 사용할 수 없다.
- 변수 이름에는 의미있는 단어를 부여.
- 여러 단어일 경우 카멜 표기법 사용. (Camel Expression)

## 변수명
### 일반 변수, 함수명 등
단봉 낙타, 카멜 표기법
- camelCase

### 클래스, 인터페이스 등
쌍봉 낙타, 파스칼 표기법
- AnimalCategory

# 자료형
## 기본형 (Primitive data type)
- 가공되지 않은 순수한 자료형으로 프로그래밍 언어에 내장
- int, long, float, double 등

## 참조형 (Reference type)
- 동적 공간에 데이터를 둔 다음 이것을 참조하는 자료형
- Int, Long, Float, Double 등

```java
// java

int a = 77;
Person person = new Person();
```
77은 주소 공간에 직접 넣는다.
person은 객체 참조 주소를 넣고 데이터는 그 주소에 넣는데

>Kotlin은 참조형 자료형을 사용한다.

## 자료형 알아보기
### 정수 자료형

|자료형|크기|값의 범위|
|---|---|---|
|Long|8바이트(64비트)|$-2^{63}$ ~ $2^{63}-1$
|Int|4바이트(32비트)|$-2^{31}$ ~ $2^{31}-1$
|Short|2바이트(16비트)|$-2^{15}$ ~ $2^{15}-1$
|Byte|1바이트(8비트)|$-2^{7}$ ~ $2^{7}-1$

### 자료형 생략

```kotlin
val num1 = 127                  // Int 형
val num2 = -32768               // Int 형
val num3 = 2147483647           // Int 형
val num4 = 98498051565484984    // Long 형
```

num1 과 num2는 Byte, Short 형 으로 추론될 수 있지만 기본 Int 형으로 추론된다.
Short 과 Byte 를 사용하려면 명시적으로 자료형을 지정해야한다.

### 접미사 접두사 사용

```kotlin
val exp1 = 123          // Int 형으로 추론
val exp2 = 123L         // 접미사 L을 사용해 Long 형 추론
val exp3 = 0x0F         // 접두사 0x 을 사용해 16진 표기 Int 형 추론
val exp4 = 0B0001011    // 접두사 0b 를 사용해 2진 표기 Int 형 추
```

### 부호 없는 정수 자료형

```kotlin
val uint: UInt = 153u
// short, long, byte 도 동일
// long 은 접미사 ul
```

### 큰 수를 읽기 쉽게 하는 방법

```kotlin
val number = 1_000_000
```

큰 수를 읽기 쉽게 하기 위해 언더스코어(_)를 포함하여 표현
언더스코어의 위치는 마음대로 해도되고 숫자에 영향을 끼치지 않는다.

### 실 자료형

|자료형|크기|값의 범위|
|---|---|---|
|Double|8바이트(64비트)|$4.9E-324$ ~ $1.7E+308$
|Float|4바이트(32비트)|$1.4E-45$ ~ $3.4E+38$

```kotlin
val exp1 = 3.14     // Double 형으로 추론(기본)
val exp2 = 3.14F    // 식별자 F에 의해 Float 형으로 추론
```
