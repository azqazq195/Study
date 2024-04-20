class Person:
    def __init__(self):
        self.age = 1


if __name__ == "__main__":
    # -5부터 256 까지 정수 객체를 사전에 생성
    a = 1
    b = 1
    print(id(a))
    print(id(b))
    print()

    c = 1000
    d = 1000
    print(id(c))
    print(id(b))
    print()

    e = "long long long long long long long long long long long long long long long "
    f = "long long long long long long long long long long long long long long long "
    print(id(e))
    print(id(f))
    print()

    g = Person()
    h = Person()
    print(id(g))
    print(id(h))
    print()
