void main() {
  var circleSlot = new Slot<Circle>();
  circleSlot.insert(new Circle());

  var squareSlot = new Slot<Square>();
  squareSlot.insert(new Square());
}

class Circle {}

class Square {}

class Slot<T> {
  insert(T shape) {}
}
