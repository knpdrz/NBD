weightAndHeightToNumber = {
    $addFields: {
        numWeight: {$convert:{input:"$weight",to:"double"}},
        numHeight: {$convert:{input:"$height",to:"double"}}

    }
};

printjson(db.people.aggregate([
    weightAndHeightToNumber,
    {

        $group:
            {
                _id: "$sex",
                avg_height: {$avg: "$numHeight"},
                avg_weight: {$avg: "$numWeight"}
            }
    }
]).toArray());

