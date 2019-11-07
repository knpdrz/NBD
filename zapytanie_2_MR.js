let mapFun = function () {
    this.credit.forEach(function(credit){
       emit(credit.currency, parseFloat(credit.balance))
    });
};

let reduceFun = function (currency, values) {
    return Array.sum(values);
};

db.people.mapReduce(
    mapFun,
    reduceFun,
    {
        out: "balance_by_currency"
    }
)
;

printjson(db.balance_by_currency.find().toArray());

