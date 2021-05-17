function square(number) {
  console.log(arguments)
  console.log(this)

  return number * number
}

console.log(square(2))

console.log("\n\n ################# \n\n")

let foo = function () {
  console.dir(this)
}
foo()
let obj = {foo: foo}
obj.foo()
let instance = new foo()
let bar = {name: 'bar'}
foo.call(bar)
foo.apply(bar)
foo.bind(bar)()

console.log("\n\n ################# \n\n")


