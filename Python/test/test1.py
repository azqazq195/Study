class Person:
    def __init__(self):
        self.age = 1


def update(person):
    person.age = 10


def update2(person):
    person = Person()
    person.age = 20


def main():
    person = Person()
    print(person.age)
    update(person)
    print(person.age)

    update2(person)
    print(person.age)


if __name__ == "__main__":
    main()
