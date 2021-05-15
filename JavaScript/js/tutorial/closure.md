# 클로저

클로저는 내부함수가 외부함수의 context 에 접근할 수 있는 것을 가르킨다.

## 내부함수

자바스크립트는 함수 안에서 또 다른 함수를 선언을 할 수 있다.

```javascript
function outer() {
  function inner() {
    let title = "coding everybody"
    console.log(title)
  }

  inner()
}

outer()
// coding everybody
```

위 코드에서 inner 가 내부 함수

```javascript
function outer() {
  let title = "coding everybody"

  function inner() {
    console.log(title)
  }

  inner()
}

outer()
// coding everybody
```

> 내부함수는 외부함수의 지역변수에 접근할 수 있다.

---

클로저는 내부함수와 밀접한 관계를 가지고 있는 주제다.
내부함수는 외부함수의 지역변수에 접근 할 수 있는데
외부함수의 실행이 끝나서 외부함수가 소멸된 이후에도
내부함수가 외부함수의 변수에 접근할 수 있다.

이러한 메커니즘을 클로저라고 한다.

```javascript
function outer() {
  let title = "coding everybody"
  return function () {
    console.log(title)
  }
}

inner = outer()
inner()
// coding everybody
```

`outer()` 함수를 실행시킴으로 써 `inner` 에 `outer()` 의 리턴값이 담긴다.

`inner` 에는 `console.log(title)` 만 담겨지게 되는데
`inner()` 를 실행 하였을때 `coding everybody` 가 출력되게 된다.

---

```javascript
function factory_movie(title) {
  return {
    get_title: function () {
      return title
    },
    set_title: function (_title) {
      if(typeof _title === "string"){
        title = _title
      } else {
        console.log("제목은 문자열이어야 합니다.")
      }
    }
  }
}

ghost = factory_movie("Ghost in the shell")
matrix = factory_movie("Matrix")

console.log(ghost.get_title())
ghost.set_title("Fixed")
console.log(ghost.get_title())
console.log(matrix.get_title())
ghost.set_title(1)
// Ghost in the shell
// Fixed
// Matrix
// 제목은 문자열이어야 합니다.
```
