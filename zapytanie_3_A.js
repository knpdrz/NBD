printjson(db.people.aggregate([
    {
        $project: {
            "job": 1
        }
    },
    {
        $group: {
            _id: "$job",
            count: {$sum: 1}
        }
    },
    {
        $match: {
            count: {$eq: 1}
        }
    },
    {$sort: {_id: 1}}
]).toArray().map(j => j._id));
print("returned only unique jobs == ones that only one person performs");
