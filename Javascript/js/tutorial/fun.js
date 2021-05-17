// 파라미터 기본값 설정
function sayHello(name = "friend") {
  const msg = `Hello, ${name}`
  console.log(msg)
}

sayHello()
sayHello("Jane")

console.log(`
#######################
`)

function add(num1, num2) {
  return num1 + num2
}

const result = add(2,3)
console.log(result)

console.log(`
#######################
`)

const add2 = (num1, num2) => num1 + num2;

const result2 = add2(2,3)
console.log(result2)

console.log(`
#######################
`)
