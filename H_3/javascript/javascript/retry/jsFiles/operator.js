// equality == - 피연산자가 같으면 true
console.log(3 == '3')               // true

// equality === 피연산자, 값 모두 같아야 true
console.log(1===1);                 // true
console.log('hello' === 'hello');   // true
console.log('1' === 1);             // false
console.log(0 === false);           // false

// 조건 삼항 연산자
let score = 69;
let result = score >= 60 ? "합격" : "불합격"; // 합격
console.log(result);