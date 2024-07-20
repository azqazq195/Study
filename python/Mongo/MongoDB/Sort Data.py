# Sorting Data
from pymongo import MongoClient

demoClient = MongoClient()
myClient = MongoClient('localhost', 27017)

myDB = myClient["demo"]  # DB name
myColl = myDB["dbTable"]  # Collection name

# Sorting Data in alphabetical order
print("ascending")
myDoc = myColl.find().sort('name', 1)
for x in myDoc:
    print(x)
print("\n=============================\n")

print("descending")
myDoc = myColl.find().sort('name', -1)
for x in myDoc:
    print(x)
print("\n=============================\n")
