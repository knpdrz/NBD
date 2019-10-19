print("before update");
printjson(db.people.find({"first_name":"Antonio"},{"first_name":1, "last_name":1, "hobby":1}).toArray());
db.people.update({"first_name": "Antonio"}, {$set: {"hobby": "pingpong"}}, false, true);
print("after update");
printjson(db.people.find({"first_name":"Antonio"},{"first_name":1, "last_name":1, "hobby":1}).toArray());
