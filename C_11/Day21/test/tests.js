let num = 123;
num = 12.345678;
console.log(100 / 0); // 무한대
console.log(Infinity); //무한대   Infinity를 직접 참조할 수 있습니다.
console.log("Not a Number" / 100); // NaN , 문자열을 숫자로 나누면 NaN 오류가 발생 합니다.
console.log(9007199254740991n); // BigInt -> 정수 리터럴 끝에 n을 붙이면 만들 수 있습니다.

let array = [1, 2, 3, 4, 5];
console.log(array);
for (index in array) {
    console.log(array[index]);
}

for (value of array) {
    console.log(value);
}
