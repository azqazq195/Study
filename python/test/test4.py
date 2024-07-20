if __name__ == "__main__":
    immutable = 1000
    print(id(immutable))
    immutable += 1
    print(id(immutable))

    mutable = ["a", "b"]
    print(id(mutable))
    mutable.append("c")
    print(id(mutable))

