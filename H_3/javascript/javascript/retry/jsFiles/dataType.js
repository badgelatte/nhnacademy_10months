// 문자형
let msg = "welcome!";
alert(typeof msg);          // string 
msg = 100;
alert(typeof msg);          // number
console.log( 100>200 );     // false

// 숫자형
let num = 123;
// alert(num);
num = 12.34567;
// alert(num);
console.log(100/0);                 // Infinity
console.log(Infinity);              // Infinity
console.log("Not a number" / 100);  // NaN - 숫자 아님
console.log(9007199254741n);        // 9007199254741n

// 문자형
let myName="nox";
let yourName="Ann";
let phrse = `My name is ${myName}`;
console.log(phrse);                     // My name is nox
console.log(` result = ${100+200}`);    // result = 300

// 형변환
let text = "marco";
console.log(text + " : " + typeof text);    // marco : string

text = 100;
console.log(text + " : " + typeof text);    // 100 : number

text = '100' + 10;
console.log(text + " : " + typeof text);    // 10010 : string

text = '100'/ '10';
console.log(text + " : " + typeof text);    // 10 : number

// 불린형
console.log( 100 > 200 );           // false
console.log( 100 < 200 );           // true
let hour = 5;
console.log(hour > 4 && hour < 6);  // true

// null - 어느 자료형에도 속하지 않는 값
let myName2 = null;
console.log(myName2);       // null

// undefined - 값이 되지 않는 상태
let aa;
console.log(aa);            // undefined

// Object (객체)
let oldPeople = new Object();
let youngPeople = {};
let people = {
    name : "marco",
    age : "40"
};
console.log(people.name);       // marco
console.log(people.age);        // 40
delete people.age;
console.log(people.age);        // undefined - 삭제했기때문에 찾을 수 없음

// Map
const map1 = new Map();
map1.set('a',1);
map1.set('b',2);
map1.set('c',3);

console.log(map1.get('a'));         // 1

console.log(map1.size);             // 3

map1.delete('b');

console.log(map1.size);             // 2