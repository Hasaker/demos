from pymongo import MongoClient
from datetime import datetime

client = MongoClient()

db = client.test

result = db.restaurants.insert_one(
    {
        "address": {
            "street": "2 Avenue",
            "zipcode": "10075",
            "building": "1480",
            "coord": [-73.9557413, 40.7720266]
        },
        "borough": "Manhattan",
        "cuisine": "Italian",
        "grades": [
            {
                "date": datetime.strptime("2014-10-01", "%Y-%m-%d"),
                "grade": "A",
                "score": 11
            },
            {
                "date": datetime.strptime("2014-01-16", "%Y-%m-%d"),
                "grade": "B",
                "score": 17
            }
        ],
        "name": "Vella",
        "restaurant_id": "41704620"

    }
)

cursor = db.restaurants.find()
for document in cursor:
	print(document)

cursorB = db.restaurants.find({"grades.grade": "B"})
for document in cursorB:
    print(document)

print(db.restaurants.isCapped())

# Capped Collection

# client.test.createCollection('capped_collection', { capped: true, size: 4096,
# max:100 })
# capped_col = client.capped_collection
# capped_col.insert_one(
#     {
#         "address": {
#             "street": "2 Avenue",
#             "zipcode": "10075",
#             "building": "1480",
#             "coord": [-73.9557413, 40.7720266]
#         }
#     })