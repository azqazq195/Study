let items = [1, 2, 3];

// 추가 매개변수를 강제로 사용하지 마세요.
items.forEach((item, index, array) => console.log(item));

// 괜찮습니다!
items.forEach((item) => console.log(item));

items.forEach((value, index, items) => console.log(value));
