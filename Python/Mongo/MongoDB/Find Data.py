# Finding data
from pymongo import MongoClient

demoClient = MongoClient()
myClient = MongoClient('localhost', 27017)

myDB = myClient["demo"]  # DB name
myColl = myDB["dbTable"]  # Collection name

print("find_one")
# for finding single occurrences in collection/table
x = myColl.find_one()
print(x)
print("\n=============================\n")

print("find_all")
# for finding all occurrences in collection
for x in myColl.find():
    print(x)
print("\n=============================\n")

print("find_query")
# normal query
myQuery = {"name": "John"}
myDoc = myColl.find(myQuery)
for x in myDoc:
    print(x)
print("\n=============================\n")

print("find_query")
# query search using regex
myQuery = {"name": {"$regex": "^S"}}
myDoc = myColl.find(myQuery)
for x in myDoc:
    print(x)
print("\n=============================\n")
