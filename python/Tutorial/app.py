# print('Hello')
# print("*" * 10)

# price = 10
# print(price)

# name = input('What is your name? ')
# favorite_color = input('What is your favorite color? ')
# print(name + ' likes ' + favorite_color)

# 입력은 str 형식이므로 int() 를 사용하여 int 로 형변환
# birth_year = input('Birth year: ')
# print(type(birth_year))
# age = 2021 - int(birth_year) + 1
# print(type(age))
# print(age)

# weight_lbs = input('Weight (lbs): ')
# weight_kg = int(weight_lbs) * 0.45
# print(weight_kg)

# course = '''
# Hi John,
#
# Here is out first email to you.
#
# Thank you,
# The support team.
#
# '''
# print(course)

# course = 'Python for Beginners'
# print(course)
# print(course[0])
# print(course[1])
# print(course[-1])
# print(course[1:])
# print(course[1:5])
# print(course[:5])
# print(course[:])

# name = 'Jennifer'
# print(name[1:-1])

# first = 'John'
# last = 'Smith'
# message = first + ' [' + last + '] is a coder'
# print(message)
# message2 = f'{first} [{last}] is a coder'
# print(message2)

# course = 'Python for Beginners'
# print(course.upper())
# print(course)
# print(len(course))
# print(course.find('P'))
# print(course.find('p'))
# print(course.replace('Beginners', 'Absolute Beginners'))
# print('Python' in course)
# print('python' in course)

# import math
# print(round(2.9))
# print(abs(-2))
# print(math.ceil(2.9))
# print(math.floor(2.9))

# is_hot = False
# is_cold = True
#
# if is_hot:
#     print("It's a hot day")
# elif is_cold:
#     print("It's a cold day")
# else:
#     print("It's a lovely day")
#
# print('Enjoy your day')

# price = 1000000
# has_good_credit = False
#
# if has_good_credit:
#     down_payment = 0.1 * price
# else:
#     down_payment = 0.2 * price
# print(f"Down payment: ${down_payment}")

# has_high_income = True
# has_good_credit = False
#
# if has_high_income and has_good_credit:
#     print("Enable")
# if has_high_income and not has_good_credit:
#     print("Disable")

# temperature = 35
#
# if temperature > 30:
#     print("It's a hot day")
# else:
#     print("It's not a hot day")

# name = "J"
#
# if len(name) < 3:
#     print("Name must be at least 3 character")
# elif len(name) > 50:
#     print("Name must be a maximum of 50 character")
# else:
#     print("Name looks good!")

# weight = int(input('Weight: '))
# unit = input('(L)bs or (K)g: ')
#
# if unit.upper() == "L":
#     converted = weight * 0.45
#     print(f"You are {converted} kilos")
# elif unit.upper() == "K":
#     converted = weight / 0.45
#     print(f"You are {converted} pounds")

# i = 1
# while i <= 5:
#     print('*' * i)
#     i += 1
# print('Done')

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
