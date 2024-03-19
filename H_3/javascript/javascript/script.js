// alert("hello javascript!");

// --------------------------------------------------

// let loginBtn = document.getElementById("loginBtn");

// loginBtn.addEventListener("click", function() {
//     console.log("loginBtn click!");
// });

// --------------------------------------------------

// document.addEventListener("DOMContentLoaded",function(){
//     let loginBtn = document.getElementById("loginBtn");

//     loginBtn.addEventListener("click", function() {
//         console.log("loginBtn click!");
//     });

// });
// --------------------------------------------------

// var today = new Date();

// var a = 10;
// var a = new Date();
// a=20;

// 이렇게 선언하면 다른 객체로 바껴버림
// 그래서 나온게 const, let
// const는 불변일때 사용
// let은 할당은 되는데 중복 선언은 안됨

// var는 사용하면 안됨

// const b = 20;
// let c = 30;

// --------------------------------------------------

let userName="global";
{
    let userName="local";
    console.log("local",userName);
}
console.log("global:",userName);

console.log("before", color);
var color = "redColor";
console.log("after", color);