const executorFunction = function(resolve, reject) {

};
// const promise = new Promise(executorFunction);

// 위의 하나의 함수와 하나의 객체가 아래처럼 하나로 축약할 수 있다

const promise = new Promise((resolve, reject) => {

});
