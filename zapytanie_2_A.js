db.people.find().forEach(function(person){
   person.credit.forEach(function(credit){
       credit.balance = parseFloat(credit.balance);
   });
   db.people.save(person);

});

printjson(db.people.aggregate([
    {
        $project: {
            "credit": 1
        }
    },
    {$unwind: "$credit"},
     {
         $group:
             {
                 _id: "$credit.currency",
                 amount: {$sum: "$credit.balance"}

             }
     }
]).toArray());