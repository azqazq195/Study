let someArray = [1, "string", false];

for (let entry of someArray) {
  console.log(entry); // 1, "string", false
}

let list = [4, 5, 6];

// in => index
console.log(list);
for (let i in list) {
  console.log(i); // "0", "1", "2"
}

// of => value
for (let i of list) {
  console.log(i); // "4", "5", "6"
}

let pets = new Set(["Cat", "Dog", "Hamster"]);
console.log(pets);

pets["species"] = "mammals";
console.log(pets);

for (let pet in pets) {
  console.log(pet); // "species"
}

var numbers = [1, 2, 3];
for (var _i = 0; _i < numbers.length; _i++) {
  var num = numbers[_i];
  console.log(num);
}
