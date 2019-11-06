let reduceFun = function (sex, values) {
    result = {height: 0, weight: 0, count: 0};
    for (var i = 0; i < values.length; i++) {
        result.height += values[i].height;
        result.weight += values[i].weight;
        result.count += values[i].count;
    }
    return result;
};

let finalizeFun = function (sex, reducedVal) {
    return {"avg_height": reducedVal.height / reducedVal.count, avg_weight: reducedVal.weight / reducedVal.count};
};


let mapFun = function () {
    let value = {
        height: parseFloat(this.height),
        weight: parseFloat(this.weight),
        count: 1
    };
    emit(this.sex, value);
};

db.people.mapReduce(
    mapFun,
    reduceFun,
    {
        out: "height_and_weight_sum_by_sex",
        finalize: finalizeFun
    }
)
;

printjson(db.height_and_weight_sum_by_sex.find().toArray());

