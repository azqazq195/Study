// String concatenation
console.log('my' + ' cat');
console.log('1' + 2);
console.log(`sting literals:
''''
1 + 2 = ${1 + 2}`);

// Numeric operators
console.log(1 + 1);
console.log(1 - 1);
console.log(1 / 1);
console.log(1 * 1);
console.log(5 % 2);     // 나머지
console.log(2 ** 3);    // 제곱

// Increment and decrement operators
let counter = 2;
const preIncrement = ++counter;
console.log(`preIncrement: ${preIncrement}, counter: ${counter}`);
// counter = counter + 1;
// preIncrement = counter;
const postIncrement = counter++;
console.log(`postIncrement: ${postIncrement}, counter: ${counter}`);
// postIncrement = counter;
// counter = counter + 1;

// Assignment operators
let x = 3;
let y = 6;
x += y; // x = x + y;
x -= y;
x *+ y;
x /= y;

// Comparison operators
console.log(10 < 6);
console.log(10 <= 6);
console.log(10 > 6);
console.log(10 >= 6);

// Logical operators: || (or), && (and), ! (not)
const value1 = false;
const value2 = 4 < 2; // false

// || (or), find the first truthly value
// 앞에서 true가 나오면 곧바로 멈춘다.
console.log(`or: ${value1 || value2 || check()}`);

// && (and)
console.log(`or: ${value1 || value2 || check()}`);
 
function check() {
    for(let i = 0; i < 10; i++) {
        // wasting time
        console.log('^0^');
    }
    return true
}