void main() {
  String name = "Moseoh";
  print(name);
  print("Hi, $name, what's up?");

  List number = List.empty(growable: true);
  number.add(32);
  number.add("dd");
  number.add(7.4);
  number.add(false);
  print(number);

  var number2 = [];
  number2.add(30);
  print(number2);

  List<int> list = List.empty(growable: true);
  list.add(5);
  printNumber(list);
}

void printNumber(List<int> list) {
  print(list);
}
