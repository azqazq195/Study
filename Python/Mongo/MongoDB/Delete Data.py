# Deleting data
from pymongo import MongoClient

demoClient = MongoClient()
myClient = MongoClient('localhost', 27017)

myDB = myClient["demo"]  # DB name
myColl = myDB["dbTable"]  # Collection name

print("delete_one")
myQuery = {"name": "Sandy"}
myDoc = myColl.delete_one(myQuery)
for x in myColl.find():
    print(x)
print("\n=============================\n")

print("delete_many")
myQuery = {"address": {"$regex": "^O"}}
myDoc = myColl.delete_many(myQuery)
for x in myColl.find():
    print(x)
print("\n=============================\n")
