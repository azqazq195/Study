function outer() {
  function inner() {
    let title = "coding everybody"
    console.log(title)
  }

  inner()
}

outer()

function outer2() {
  let title = "coding everybody"

  function inner() {
    console.log(title)
  }

  inner()
}

outer2()

function outer3() {
  let title = "coding everybody"
  return function () {
    console.log(title)
  }
}

inner = outer3()
inner()

function factory_movie(title) {
  return {
    get_title: function () {
      return title
    },
    set_title: function (_title) {
      if (typeof _title === "string") {
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

let arr = []
// for (let i = 0; i < 5; i++) {
//   arr[i] = function () {
//     return i
//   }
// }
for (let i = 0; i < 5; i++) {
  arr[i] = function (id) {
    return function () {
      return id
    }
  }(i)
}

for (let i in arr) {
  console.log(arr[i]())
}
