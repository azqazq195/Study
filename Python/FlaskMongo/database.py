import pymongo

try:
    connection = pymongo.MongoClient(
        host="localhost",
        port=27017,
        serverSelectionTimeoutMS=1000
    )
    db = connection.brandi
    connection.server_info()  # trigger exception if cannot connect to db
    print("******************")
    print("Database connected")
    print("******************\n")
except:
    print("******************")
    print("ERROR - Cannot connect to db")
    print("******************\n")

