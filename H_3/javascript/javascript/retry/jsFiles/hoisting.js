console.log(redColor);          // undefined -> 호이스팅은 되는데 값을 주지는 않는다

// console.log(blueColor());    // 이름없는 메소드는 에러 처리가 된다

console.log(greenColor());      // green

var redColor = "red";

// 이름 없는 함수
var blueColor=function() {
    return "blue";
}

// 이름 있는 함수
function greenColor(){
    return "green";
}
// 호이스팅 = 선언된 함수나 변수의 선언문을 유효 번위의 최상단으로 끌어올리는 행위
// -> 변수 선언을 위에 해도 가능한 이유는 js파일을 script가 다 읽고 난 다음 실행에 옮기기 때문이다

// 결과

// 호이스팅으로 값을 보이고 싶으면 이름 있는 함수를 사용해라