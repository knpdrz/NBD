printjson("number of people with height > 190 before remove = " + db.people.count({"height":{"$gt":"190"}}));
db.people.remove({"height":{"$gt":"190"}});
printjson("number of people with height > 190 after remove = " + db.people.count({"height":{"$gt":"190"}}));