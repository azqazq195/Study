function start(
  // string
  // string array
  // function return type string
  // string in object
  arg: string | string[] | (() => string) | { s: string }
): string {
  // JavaScript에서 아주 일반적입니다
  if (typeof arg === "string") {
    return commonCase(arg);
  } else if (Array.isArray(arg)) {
    return arg.map(commonCase).join(",");
  } else if (typeof arg === "function") {
    return commonCase(arg());
  } else {
    return commonCase(arg.s);
  }

  function commonCase(s: string): string {
    // 마지막으로, 다른 문자열로 변환합니다
    return s;
  }
}

const returnString = (str: string) => "func " + str;

console.log(start("str"));
console.log(start(["str1", "str2"]));
console.log(start({ s: "str" }));
console.log(start(returnString("str")));
