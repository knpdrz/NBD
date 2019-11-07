let mapFun = function () {
    let height_in_meters = parseFloat(this.height)/100;
    let value = {
        bmi: parseFloat(this.weight) / (height_in_meters * height_in_meters),
        count: 1,
        min_bmi: parseFloat(this.weight) / (height_in_meters * height_in_meters),
        max_bmi: parseFloat(this.weight) / (height_in_meters * height_in_meters),
    };

    emit(this.nationality, value);
};

let reduceFun = function (nationality, values) {
    result = {bmi: 0, count: 0, min_bmi: values[0].min_bmi, max_bmi: values[0].max_bmi};
    for (var i = 0; i < values.length; i++) {
        result.bmi += values[i].bmi;
        result.count += values[i].count;
        result.min_bmi = Math.min(result.min_bmi, values[i].min_bmi);
        result.max_bmi = Math.max(result.max_bmi, values[i].max_bmi);
    }
    return result;
};

let finalizeFun = function (key, reducedVal) {
    return {
        avg_bmi: reducedVal.bmi / reducedVal.count,
        min_bmi: reducedVal.min_bmi,
        max_bmi: reducedVal.max_bmi
    };
};

db.people.mapReduce(
    mapFun,
    reduceFun,
    {
        out: "bmi",
        finalize: finalizeFun
    }
)
;

printjson(db.bmi.find().toArray());

