function f() {
  console.log("f(): evaluated");
  return function (
    target,
    propertyKey: string,
    descriptor: PropertyDescriptor
  ) {
    console.log("f(): called");
  };
}

function g() {
  console.log("g(): evaluated");
  return function (
    target,
    propertyKey: string,
    descriptor: PropertyDescriptor
  ) {
    console.log("g(): called");
  };
}

class C {
  @f()
  @g()
  method(): void {
    console.log("method");
  }
}

const cClass: C = new C();
cClass.method();
