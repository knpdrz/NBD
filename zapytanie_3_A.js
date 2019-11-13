printjson(db.people.aggregate([
    {
        $project: {
            "job": 1
        }
    },
    {
        $group: {
            _id: "$job"
        }
    },
    {$sort: {_id: 1}}
]).toArray().map(j => j._id));
