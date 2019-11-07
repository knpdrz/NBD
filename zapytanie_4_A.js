weightAndHeightToNumber = {
    $addFields: {
        numWeight: {$convert: {input: "$weight", to: "double"}},
        numHeight: {$convert: {input: "$height", to: "double"}}

    }
};

calculateBMI = {
    $addFields: {
        bmi: {
            $multiply: [
                {$divide: ["$numWeight",
                    {$multiply: ["$numHeight", "$numHeight"]}]}
            , 10000]
        }

    }
};

printjson(db.people.aggregate([
    weightAndHeightToNumber,
    calculateBMI,
    {
        $group:
            {
                _id: "$nationality",
                avg_bmi: {$avg: "$bmi"},
                min_bmi: {$min: "$bmi"},
                max_bmi: {$max: "$bmi"}
            }
    }
]).toArray());