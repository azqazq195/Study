interface Square {
  kind: "square";
  size: number;
}
interface Rectangle {
  kind: "rectangle";
  width: number;
  height: number;
}
interface Circle {
  kind: "circle";
  radius: number;
}
interface Triangle {
  kind: "triangle";
  width: number;
  height: number;
}

function assertNever(x: never): never {
  throw new Error("Unexpected object: " + x);
}
type Shape = Square | Rectangle | Circle | Triangle;

function area(s: Shape) {
  switch (s.kind) {
    case "square":
      return s.size * s.size;
    case "rectangle":
      return s.height * s.width;
    case "circle":
      return Math.PI * s.radius ** 2;
    default:
      return assertNever(s); // 빠진 케이스가 있다면 여기서 오류 발생
  }
}

const square: Square = {
  kind: "square",
  size: 3,
};

const rectangle: Rectangle = {
  kind: "rectangle",
  width: 3,
  height: 3,
};

const circle: Circle = {
  kind: "circle",
  radius: 3,
};

const triangle: Triangle = {
  kind: "triangle",
  width: 3,
  height: 3,
};

console.log(area(square));
console.log(area(rectangle));
console.log(area(circle));
console.log(area(triangle));

function neverFunc(arg: never) {
  throw new Error("error");
}

neverFunc("sd");
