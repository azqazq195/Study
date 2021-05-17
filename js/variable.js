// JavaScript is very flexible
// flexible === dangerous
// added ECMAScript 5
// "use strict" 를 선언 하면
// let a = 5 에러 없음
// a = 5 에러 발생
"use strict"

// Variable
let globalName = 'global name'

{
    let name = 'moseoh'
    console.log(name)
    name = 'hello'
    console.log(name)
}

console.log(name);
console.log(globalName);

const count = 17
const size = 17.1
console.log(`value: ${count}, type: ${typeof count}`);
console.log(`value: ${size}, type: ${typeof size}`);

const infinity = 1/0
const negativeInfinity = -1/0
const nAn = 'not a number' / 2
console.log(infinity);
console.log(negativeInfinity);
console.log(nAn);

const bigInt = 123456789012345678901234567890123456789n // bigInt
console.log(`value: ${bigInt}, type: ${typeof bigInt}`);

// string
const char = 'c'
const brendan = 'brendan'
const greeting = 'hello ' + brendan
const helloBob = `hi ${brendan}~`
console.log(`value: ${char}, type: ${typeof char}`);
console.log(`value: ${greeting}, type: ${typeof greeting}`);
console.log(`value: ${helloBob}, type: ${typeof helloBob}`);

// boolean
// false: 0, null, undefined, Nan, ''
// true: any other value
const canRead = true;
const test = 3 < 1;
console.log(`value: ${canRead}, type: ${typeof canRead}`);
console.log(`value: ${test}, type: ${typeof test}`);

// null
let nothing = null;
console.log(`value: ${nothing}, type: ${typeof nothing}`);

// undefined
let x;
console.log(`value: ${x}, type: ${typeof x}`);

// symbol, create unique identifiers for objects
const symbol1 = Symbol('id');
const symbol2 = Symbol('id');
console.log(symbol1)
console.log(symbol2)
console.log(symbol1 === symbol2)
const gSymbol1 = Symbol.for('id');
const gSymbol2 = Symbol.for('id');
console.log(gSymbol1)
console.log(gSymbol2)
console.log(gSymbol1 === gSymbol2)
console.log(`value: ${gSymbol1.description}, type: ${typeof gSymbol1}`);

// dynamic typing: dynamically typed language
let text = 'hello';
console.log(`value: ${text}, type: ${typeof text}`);
text = 1;
console.log(`value: ${text}, type: ${typeof text}`);
text = '7' + 5;
console.log(`value: ${text}, type: ${typeof text}`);
text = '8' / '2';
console.log(`value: ${text}, type: ${typeof text}`);

// object, real-life object, data structure
const moseoh = {name: "moseoh", age: 28}
console.log(moseoh);
