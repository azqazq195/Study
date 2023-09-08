## Call by Reference, Call by Value

함수의 호출 방법에는 크게 두가지가 있다.

- Call By Value (값에 의한 호출)

- Call By Reference (참조에 의한 호출)

각각의 동작 방식을 알아보고, Java는 어디에 해당하는지 알아보자.

## Call by Value

- 함수가 호출될 때 메모리 공간 안에서**임시의 공간**이 생성된다. 함수가 종료될 때 해당 공간은 사라진다.
- 함수 호출시 전달되는 변수의 값을**복사하여 함수의 인자로 전달한다.**
- 복사된 인자는 함수 안에서**지역적으로 사용**하는 변수이다.

장점

- 복사하여 처리하기 때문에 안전하다. 원래의 값이 보존된다.

단점

- 복사를 하기 때문에 메모리 사용량이 늘어난다.

## Call by Reference

- 함수가 호출될 때, 메모리 공간 안에서는 함수를 위한**임시의 공간**이 생성된다. (함수 종료시 사라짐)
- call by refrenece 참조에 의한 호출방식은 함수 호출시 인자로 전달되는**변수의 레퍼런스를 전달한다.**(해당 변수를 가르킨다)
- 함수 안에서 인자의 값이 변경되면, 함수 호출시에 있던 변수들도 값이 바뀐다.

장점

- 복사하지 않고 직접 참조를 하기에 빠르다.
- 메모리 사용량이 늘어나지 않는다.

단점

- 직접 참조를 하기에 원래 값이 영향을 받는다.

## Java의 동작방식

### Primitive Type

먼저 기본 자료형 (primitive) 변수를 살펴보자.

1. `main`에서`primitive`에 5를 할당한다.
2. `modifyPrimitive`메서드 호출 시`primitive`값을 복사하여 전달한다.
3. `modifyPrimitive`의 stack 메모리에 새로운`primitive`값이 생성된다.
4. `modifyPrimitive`에서`primitive`값을 변경한다.
5. `main`에서`primitive`의 값을 확인하면`modifyPrimitive`의 변화된 값과 관계없이`5`를 출력한다.

```java
public static void main(String[]args){
    int primitive=5;
    modifyPrimitive(primitive);
    // 원시 타입은 변경되지 않습니다.
    System.out.println(primitive); // 5
    }

public static void modifyPrimitive(int primitive){
    primitive=10;
    }

```

![](../../../../assets/callby/call%20by%20value%20(primitive).png)

### Reference Type

아마 이미 Reference Type 변수를 전달했을 때 값이 바뀌는 것을 알고 있을 것이다.

참조형 변수는 어떻게 동작하는지 살펴보자.

1. `main`에서`myObject`를 생성하고`value`에 5를 할당한다.
2. `modifyReference`메서드 호출 시`myObject`의 주소값을 복사하여 전달한다.
3. `modifyReference`의 stack 메모리에 새로운`myObject`의 주소값이 생성되지만, 참조 값은`main`의`myObject`와 같은 값이다.
4. `modifyReference`에서`myObject`의`value`값을 수정한다.
5. `main`에서`myObject`의`value`값을 확인하면`modifyReference`에서 수정된`value`를 확인할 수 있다.이는`modifyReference`
   와`main`이 같은`MyObject`를 참조하기 때문이다.

```java
public static void main(String[]args){
    MyObject myObject=new MyObject();
    myObject.value=5;
    modifyReference(myObject);
    // 객체의 멤버는 변경되었습니다.
    System.out.println(myObject.value); // 10
    }

public static void modifyReference(MyObject myObject){
    myObject.value=10;
    }
```

![](../../../../assets/callby/call%20by%20value%20(referenece).png)

위의 예시를 보면 Reference Type은 Call by Reference가 아닌가? 라는 생각이든다.

하지만 참조 자체를 넘기는 것이 아닌 참조 값을 복사해서 넘긴다는 것에 차이가 있다.

아래 예시를 추가로 살펴보자.

1. `main`에서`myObject`를 생성하고`value`에 5를 할당한다.
2. `changeReference`메서드 호출 시`myObject`의 주소값을 복사하여 전달한다.
3. `changeReference`의 stack 메모리에 새로운`myObject`의 주소값이 생성되지만, 참조 값은`main`의`myObject`와 같은 값이다.
4. `changeReference`에서 새로운`MyObject`객체를 생성한다. 이제`changeReference`의`myObject`는 새로운 객체를 참조한다.
5. `changeReference`에서`myObject`의`value`에 5를 할당한다. 이는 새로운 객체로`main`에서 참조하는`myObject`와는 다른 객체이다.
6. `main`에서`myObject`의`value`값을 확인하면`changeReference`의 변화된 값과 관계없이`5`를 출력한다.`changeReference`에서
   사용한`MyObject`의 객체는`changeReference`에서 사용 후 소멸하는 객체로,`main`이 참조하는`myObject`와는 다른 객체이며 함수 종료시 GC
   대상이 된다.

```java
public static void main(String[]args){
    MyObject myObject=new MyObject();
    myObject.value=5;
    changeReference(myObject);
    // 참조 자체는 변경되지 않았습니다.
    System.out.println(myObject.value); // 5
    }

public static void changeReference(MyObject obj){
    obj=new MyObject();
    obj.value=20;
    }

```

![](../../../../assets/callby/call%20by%20value%20(referenece%20new%20object).png)

결국 자바는 Call by Value 형식으로 값을 넘긴다고 정의할 수 있다.

참조:

[https://devlog-wjdrbs96.tistory.com/44](https://devlog-wjdrbs96.tistory.com/44)

[https://bcp0109.tistory.com/360](https://bcp0109.tistory.com/360)