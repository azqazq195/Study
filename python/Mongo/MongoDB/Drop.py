# Deleting data
from pymongo import MongoClient

demoClient = MongoClient()
myClient = MongoClient('localhost', 27017)

myDB = myClient["demo"]  # DB name
myColl = myDB["dbTable"]  # Collection name

myColl.drop()  # drop collection
demoClient.drop_database("demo")  # for dropping the DB
print(myClient.list_database_names())
