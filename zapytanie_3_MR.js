let mapFun = function () {
    emit(this.job, 1);
};

let reduceFun = function (job, values) {
    return Array.sum(values);
};

let finalizeFun = function(job, count){
    return count;
};

db.people.mapReduce(
    mapFun,
    reduceFun,
    {
        out: "unique_jobs",
        finalize: finalizeFun
    }
)
;

printjson(db.unique_jobs.find({value: 1},{nationality:1}).toArray().map(j => j._id));
print("returned only unique jobs == ones that only one person performs");