let mapFun = function () {
    this.credit.forEach(function(credit){
        emit(credit.currency, {
            balance: parseFloat(credit.balance),
            count: 1
        })
    });
};

let reduceFun = function (currency, values) {
    result = {balance: 0, count: 0};
    for (var i = 0; i < values.length; i++) {
        result.balance += values[i].balance;
        result.count += values[i].count;
    }
    return result;
};

let finalizeFun = function (key, reducedVal) {
    return {
        avg_balance: reducedVal.balance / reducedVal.count,
        balance_sum: reducedVal.balance
    };
};

db.people.mapReduce(
    mapFun,
    reduceFun,
    {
        out: "female_polish_money",
        query: {sex: "Female", nationality: "Poland"},
        finalize: finalizeFun
    }
)
;
printjson(db.female_polish_money.find().toArray());
