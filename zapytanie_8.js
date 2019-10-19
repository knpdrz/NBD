print("before update");
printjson("number of people with city Moscow = " + db.people.count({"location.city":"Moscow"}));
printjson("number of people with city Moskwa = " + db.people.count({"location.city":"Moskwa"}));
db.people.update({"location.city": "Moscow"}, {$set: {"location.city": "Moskwa"}}, false, true);
print("after update");
printjson("number of people with city Moscow = " + db.people.count({"location.city":"Moscow"}));
printjson("number of people with city Moskwa = " + db.people.count({"location.city":"Moskwa"}));