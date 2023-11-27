var myName = "nhn아카데미";
console.log(myName);

let num = 123;
console.log(num);

num = 12.345678;
console.log(num);

console.log(100/0);
console.log(Infinity);
console.log("Not a Number" /100);
console.log(900719925474091n);

let myNames = "marco";
let yourName = "Ann";
let phrse = `My name is ${myName}`;
console.log(phrse);
console.log(` result = ${100+200}`);

let text = "marco";
console.log(text + " : " + typeof text);
text = 100;
console.log(text + " : " + typeof text);
text = '100' + 10;
console.log(text + " : " + typeof text);
text = '100' / 10;
console.log(text + " : " + typeof text);
text = true;
console.log(text + " : " + typeof text);

let oldPeople = new Object();
let youngPeople = {};
let people = {
	name : "marco",
	age : 40
}

console.log(people.name);
console.log(people.age);
console.log(people);
delete people.age;
console.log(people.age);
console.log(people);
people.age2 = 1;
console.log(people);

const map1 = new Map();
map1.set('a',1);
map1.set('b',2);
map1.set('c',3);

console.log(map1.get('a'));

map1.set('a',97);
console.log(map1.get('a'));
console.log(map1.size);

map1.delete('b');
console.log(map1.size);
console.log(map1);

console.log(1 === 1);
console.log('hello' === 'hello');
console.log('1' === 1);
console.log(0 === false);

console.log('1' == 1);
console.log(0 == false);

console.log(redColor);
// Console.log(blueColor());
console.log(greenColor());

var redColor="red";
var blueColor=function(){
    return "blue";
}
function greenColor(){
    return "green";
}

function sayHello() {
	return "Hello, ";
}
function greeting(helloMessage, name) {
   	if(typeof helloMessage == "function"){
		console.log(typeof helloMessage);
		console.log(helloMessage(), name);
	}
}
greeting(sayHello, "JavaScript");

let person = {
	name : 'marco',
	age : 20
}
console.log(person);
console.log(person.name);
console.log(person['name']);

let person2 = {
	'0' : 'marco',
	'1' : 20
}
console.log(person2);
console.log(person2[0]);

let counter = {
	num : 0,
	increase : function() {
		this.num++;
	}
}

console.log(counter);
counter.increase();
console.log(counter);

function Person(name, age){
	this.name = name;
	this.age = age;
}

const newPerson = new Person('marco',48);
console.log(newPerson);

console.log('name' in newPerson );

for(key in newPerson) {
	console.log(key);
}
