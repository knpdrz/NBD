let mapFun = function () {
    emit(this.job, 1);
};

let reduceFun = function (job, values) {
    return Array.sum(values);
};

db.people.mapReduce(
    mapFun,
    reduceFun,
    {
        out: "unique_jobs"
    }
)
;

printjson(db.unique_jobs.find().toArray());

