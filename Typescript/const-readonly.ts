// const, readonly
const a = [1, 2, 3];
console.log(a);
a.push(102);
console.log(a);
a[0] = 101;
console.log(a);
// 불가 a = 3;
// array 임이 변하지 않은 상태에서 참조 값을 변경할 수 있지만
// a = 3 은 데이터가 바뀜

interface PrivateData {
  name: string;
  readonly age: number;
  readonly cash: number;
}

let privateData: PrivateData = {
  name: "문성하",
  age: 29,
  cash: 30_000,
};

console.log(privateData);
privateData.name = "이재욱";
console.log(privateData);
// 불가 privateData.age = 20;
// readonly

interface PublicData {
  name: string;
  age: number;
  cash: number;
}

let publicData: Readonly<PublicData> = {
  name: "문성하",
  age: 29,
  cash: 30_000,
};

// 불가 publicData.name = "이재욱";
// 불가 privateData.age = 20;
