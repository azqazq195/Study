// 객체

const superman = {
  name: 'clark',
  age: 30,
}
console.log(superman.name)
console.log(superman['age'])

superman.hairColor = 'black'
superman['hobby'] = 'football'
console.log(superman.hairColor)
console.log(superman.hobby)

console.log(superman)
delete superman.age
console.log(superman)

// 객체, Object
function makeObject(name, age) {
  return {
    name: name,
    age: age,
    hobby: "football"
  }
}

const Mike = makeObject("Mike", 30)
console.log(Mike)

function makeObject2(name, age) {
  return {
    name,
    age,
    hobby: "football"
  }
}

const Mike2 = makeObject2("Mike", 30)
console.log(Mike2)

// 객체 in
console.log('age' in Mike2)       // true
console.log('birthday' in Mike2)  // false

function isAdult(user) {
  return user.age >= 20;
}

const Moon = {
  name: "Moon",
  age: 30
}

const Jane = {
  name: "Jane"
}

console.log(isAdult(Moon))
console.log(isAdult(Jane))

// 객체 for ... in
const Jang = {
  name: "Jang",
  age: 30
}

for(let x in Jang) {
  console.log(Jang[x])  // Jang['name']
}
