print("before update");
printjson(db.people.find({"job":"Editor"},{"first_name":1, "job":1, "email":1}).toArray());
db.people.update({"job": "Editor"}, {$unset: {"email": 1}}, false, true);
print("after update");
printjson(db.people.find({"job":"Editor"},{"first_name":1, "job":1, "email":1}).toArray());