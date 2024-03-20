// api server uri
const SERVER_URL = "http://133.186.241.167:8100";

window.addEventListener("DOMContentLoaded",function(){
    'use strict'; // ->

    const loginForm = document.querySelector("#login-form");

    // vlidation id, password check;

    const formValidator = function(form){
        if(form["userId"].value == ''){
            alert("userID를 입력해 주세요!");
            form["userId"].focus();
            return false;
        } else if(form["userPassword"].value==''){
            alert("userPassword를 입력해 주세요!!")
            form["userPassword"].focus();
            return false;
        }
        return true;
    }
    
    loginForm.addEventListener("submit", function(event){
        // 버튼 submit - 전송되는 중간에 하는거라 뭔가 안해주면 전송을 끝맞쳐서 preventDefault 해줘야한다
        event.preventDefault();
        if(formValidator(loginForm)==false) {
            return;
        }
        
        // loginFprm['userId']로 적어도 사용가능
        // const userId = document.getElementById("userId").value; -> 아이디 값 지정안해서 안됨
        // const userPassword = document.querySelector("#userPassword").value;
        const userId = loginForm['userId'].value;
        const userPassword = loginForm['userPassword'].value;
        
        //login api 호출
        doLogin(userId,userPassword);
    });
    
    function doLogin(userId, userPassword) {
        const xhr = new XMLHttpRequest();
        const requestUrl = SERVER_URL + "/api/users/login";
        
        // const user = {
            //     'userId' : userId,
            //     'userPassword': userPassword0
            // }
            // 요거ㅓ 대신
            
            const user = {
                userId,
                userPassword
            }
            
            // 성공적으로 다 끝나면 실행
            xhr.addEventListener("load", function(){
                if(this.status === 200) {
                    // 로그인 성공 -> json -> Object 순서로 변환
                    // 초기화 때 json으로 받는다해서 변경해줘야한다
                    const userInfo = this.response;
                    
                    console.log("userInfo", JSON.stringify(userInfo));
                    
                }
            });
            
            // 초기화
            xhr.open("POST",requestUrl);
            xhr.setRequestHeader("Content-Type","application/json");
            xhr.responseType = "json";
            
            // 전송 -> api 호출
            xhr.send(JSON.stringify(user)); // json 문자열로 전송
        }
    });