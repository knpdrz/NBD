db.people.find().forEach(function (person) {
    person.credit.forEach(function (credit) {
        credit.balance = parseFloat(credit.balance);
    });
    db.people.save(person);

});

printjson(db.people.aggregate([
    {
        $match: {
            sex: "Female",
            nationality: "Poland"
        }
    },
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
                balance_sum: {$sum: "$credit.balance"},
                avg_balance: {$avg: "$credit.balance"}

            }
    },
    {$sort: {_id: 1}}
]).toArray());