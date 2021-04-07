import requests

BASE = "http://127.0.0.1:5000/"

data = [
    {"likes": 78, "name": "MOON", "views": 10000},
    {"likes": 4, "name": "How to make REST API", "views": 8888},
    {"likes": 12, "name": "Tim", "views": 2000},
    {"likes": 57, "name": "Designer", "views": 5164}
]

# response = requests.put(BASE + "video/3", {"likes": 10, "name": "Tim", "views": 10000})
# print(response.json())

for i in range(len(data)):
    response = requests.get(BASE + "video/" + str(i))
    print(response.json())

input()
for i in range(len(data)):
    response = requests.put(BASE + "video/" + str(i), data[i])
    print(response.json())

input()
response = requests.delete(BASE + "video/2")
print(response)

input()
response = requests.get(BASE + "video/3")
print(response.json())

input()
response = requests.patch(BASE + "video/2", {"views": 99})
print(response.json())

input()
response = requests.patch(BASE + "video/3", {"views": 99})
print(response.json())

