# Python

### 기본

```python
print('Hello')
print("*" * 10)

# Hello
# **********
```

### 변수

```python
price = 10
print(price)

# 10
```

### 입력

```python
name = input('What is your name? ')  # moseoh
favorite_color = input('What is your favorite color? ')  # blue
print(name + ' likes ' + favorite_color)

# moseoh likes blue
```

### 자료형

입력은 str 형식이므로 int() 를 사용하여 int 로 형변환

```python
birth_year = input('Birth year: ')
age = 2021 - int(birth_year) + 1
print(age)
```

int * float = float

```python
weight_lbs = input('Weight (lbs): ')
weight_kg = int(weight_lbs) * 0.45
print(weight_kg)
```

### 문자열

`'''` 사용시 문자열의 특수문자 포함 그대로 저장

```python
course = '''
Hi John,

Here is out first email to you.

Thank you,
The support team.

'''
print(course)
```

문자열 index

```python
course = 'Python for Beginners'
print(course)
print(course[0])
print(course[1])
print(course[-1])
print(course[1:])
print(course[1:5])
print(course[:5])
print(course[:])

name = 'Jennifer'
print(name[1:-1])

# Python for Beginners
# P
# y
# s
# ython for Beginners
# ytho
# Pytho
# Python for Beginners

# ennifer
```

문자열 f

```python
first = 'John'
last = 'Smith'
message = first + ' [' + last + '] is a coder'
print(message)
message2 = f'{first} [{last}] is a coder'
print(message2)

# John [Smith] is a coder
# John [Smith] is a coder
```

기타

```python
course = 'Python for Beginners'
print(course.upper())
print(course)
print(len(course))
print(course.find('P'))
print(course.find('p'))
print(course.replace('Beginners', 'Absolute Beginners'))
print('Python' in course)
print('python' in course)

# PYTHON FOR BEGINNERS
# Python for Beginners
# 20
# 0
# -1
# Python for Absolute Beginners
# True
# False
```

### 연산

여러가지 연산을 위한 함수

```python
print(10 / 3)
print(10 % 3)
print(10 * 3)
print(10 ** 3)  # 3 제곱

import math

print(round(2.9))
print(abs(-2))
print(math.ceil(2.9))
print(math.floor(2.9))

# 3.3333333333333335
# 1
# 30
# 1000

# 3
# 2
# 3
# 2
```

### 조건문

```python
is_hot = False
is_cold = True

if is_hot:
    print("It's a hot day")
elif is_cold:
    print("It's a cold day")
else:
    print("It's a lovely day")

print('Enjoy your day')

# It's a cold day
# Enjoy your day
```

```python
price = 1000000
has_good_credit = False

if has_good_credit:
    down_payment = 0.1 * price
else:
    down_payment = 0.2 * price
print(f"Down payment: ${down_payment}")

# Down payment: $200000.0
```

```python
has_high_income = True
has_good_credit = False

if has_high_income and has_good_credit:
    print("Enable")
if has_high_income and not has_good_credit:
    print("Disable")

# Disable
```

```python
temperature = 35

if temperature > 30:
    print("It's a hot day")
else:
    print("It's not a hot day")

# It's a hot day
```

```python
name = "J"

if len(name) < 3:
    print("Name must be at least 3 character")
elif len(name) > 50:
    print("Name must be a maximum of 50 character")
else:
    print("Name looks good!")

# Name must be at least 3 character
```

```python
weight = int(input('Weight: '))
unit = input('(L)bs or (K)g: ')

if unit.upper() == "L":
    converted = weight * 0.45
    print(f"You are {converted} kilos")
elif unit.upper() == "K":
    converted = weight / 0.45
    print(f"You are {converted} pounds")

# Weight: 160
# (L)bs or (K)g: l
# You are 72.0 kilos
```

### 반복문

```python
i = 1
while i <= 5:
    print(i)
    i += 1
print('Done')

# 1
# 2
# 3
# 4
# 5
# Done
```

```python
secret_number = 9
guess_count = 0
guess_limit = 3
while guess_count < guess_limit:
    guess = int(input('Guess: '))
    guess_count += 1
    if guess == secret_number:
        print('You won!')
        break
else:
    print('Sorry, you failed!')

# Guess: 3
# Guess: 9
# You won!
```