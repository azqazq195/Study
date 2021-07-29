const dog = { type : 'dog', name: '귤', owner: { name : "문성하"}};

const logging = function logging(json) {
    console.log('[LOG]', "json");
    console.log(json.get);
}

logging(dog);