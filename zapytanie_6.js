db.people.insert(
    {"_id":"mrz", "sex":"Female","first_name":"Monika","last_name":"Ruszczyk","job":"Software Developer",
        "email":"s20107@pjwstk.edu.pl","location":{"city":"Warsaw","address":{"streetname":"Koszykowa","streetnumber":"656"}},
        "description":"sanktificetur nomen tuum","height":"162","weight":"78.1","birth_date":"1995-08-22T15:30:12Z",
        "nationality":"Poland","credit":[
            {"type":"mastercard","number":"545443454345","currency":"PLN","balance":"606.6"},
            {"type":"visa","number":"2222222222222222","currency":"PLN","balance":"1.99"}]}
);
printjson(db.people.findOne({"_id" : "mrz"}));