# Updating data
from pymongo import MongoClient

demoClient = MongoClient()
myClient = MongoClient('localhost', 27017)

myDB = myClient["demo"]  # DB name
myColl = myDB["dbTable"]  # Collection name

# Update Query
print("update_one")
myQuery = {"name": "andrew"}
newValues = {"$set": {"name": "William's"}}
myColl.update_one(myQuery, newValues)
for x in myColl.find():
    print(x)
print("\n=============================\n")

# Update Query
print("update_one")
myQuery = {"address": {"$regex": "^M"}}
newValues = {"$set": {"address": "Mansion Street 105"}}
myColl.update_one(myQuery, newValues)
for x in myColl.find():
    print(x)
print("\n=============================\n")

# Update Query
print("update_many")
myQuery = {"address": {"$regex": "^M"}}
newValues = {"$set": {"address": "Mansion Street 105"}}
myColl.update_many(myQuery, newValues)
for x in myColl.find():
    print(x)
print("\n=============================\n")