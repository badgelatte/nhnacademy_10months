// 기명 함수 - 이름 있는 함수, 호이스팅 영향 O
console.log(square(4));

function square(number) {
    return number * number;
}


// 익명 함수 - 이름 없는 함수, 호이스팅 영향 X
let square2 = function(number) {
    return number * number;
}

let x = square(4);
console.log(x);

// 즉시 실행 함수
(function(number =4){
    return number * number;
})();

// 함수를 다른 함수에 인수로 제공한다
function sayHello(){
    return "Hello, ";
}

// 함수 greeting - 파라미터로 (함수, 이름(string))
function greeting(helloMessage, name) {
    console.log(helloMessage() + name);
}

greeting(sayHello, "dfdf");     // Hello, dfdf

// 함수가 함수를 반환 가능
function sayHello() {
    return function() {
        console.log("Helloooo");
    }
}

// 결과
// function() {
//     console.log("Helloooo");
// }dfdf

const foo = function() {
    console.log("foobar");
}

foo();          // foobar
