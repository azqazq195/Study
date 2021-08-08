function getClosure() {
  const text = 'variable 1'
  return function () {
    return text
  }
}

const closure = getClosure()
console.log(closure());

const base = 'Hello, '

function sayHelloTo(name) {
  const text = base + name
  return function () {
    console.log(text)
  }
}

const hello1 = sayHelloTo('승민')
const hello2 = sayHelloTo('현섭')
const hello3 = sayHelloTo('유근')

hello1()
hello2()
hello3()


console.log('\n\n### 클로저를 통한 은닉화 ###\n')

function Hello(name) {
  this._name = name
}

Hello.prototype.say = function () {
  console.log('Hello, ' + this._name)
}

const hello4 = new Hello('승민')
const hello5 = new Hello('현섭')
const hello6 = new Hello('유근')

hello4.say()
hello5.say()
hello6.say()

hello4._name = 'anonymous'
hello4.say()

console.log('\n\n### 반복문 클로저 ###\n')
for (let i = 0; i < 10; i++) {
  setTimeout(function () {
    console.log(i)
  }, 1000)
}
