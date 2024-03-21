// 객체 생성
let person = {
    name : 'nox',
    age : 43
};
console.log(person);

// 객체 리터럴 - 빈 객체 생성
let person2 = {};

console.log(person2.age);   // undefined

// method
let counter = {
    num : 0,
    increase : function(){
        this.num++;
    }
};
console.log(counter.num);

// 생성자
function Person(name, age) {
    this.name = name;
    this.age = age;
}

const newPerson = new Person('nox',234);
console.log(newPerson);


// key = name, age
for(key in newPerson ){
    console.log(key);
}

// Object 복사하기
const newPerson2= {};
for(key in newPerson){
    newPerson2[key] = newPerson[key];
}

console.log("name:" + newPerson2.name);     // nox
console.log("age:" + newPerson2.age);       // 234

const newPerson3 = Object.assign({}, newPerson2);
console.log("name:" + newPerson3.name);     // nox
console.log("age:" + newPerson3.age);       // 234