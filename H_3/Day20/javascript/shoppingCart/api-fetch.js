// api server uri
const SERVER_URL = "http://133.186.241.167:8100";

window.addEventListener("DOMContentLoaded",function(){
    'use strict'; 

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
    
    loginForm.addEventListener("submit", async function(event){
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
        
        let userInfo;

        try{
            userInfo = await doLogin(userId, userPassword);
            // ui 처리 할 수 있다
            console.log("userInfo", JSON.stringify(userInfo));
            displayLogin(userInfo);

        } catch(error) {
            alert(error);
            return ;    // 다음 블럭 실행 시킬 이유가 없으니 return해버린다
        }

        try {
            const items = await getCartItems(userInfo.userId, userInfo.cartId);

            displayItems(items);
        } catch(error) {
            alert(error);
            return ;
        }

        // promise가 성공하면 
        doLogin(userId, userPassword).then((userInfo)=> {
            

            // 다음 then에서 사용할 수 있도록 return 시켜준다
            return userInfo;

        }).catch((error)=>{ // 여기의 catch는 doLogin에 대한 catch이다
            alert(error);
            // then 메소드를 통해 체이닝 할 수 있다
        }).then((userInfo)=>{   // catch 오고 then으로 올 수 있는데 대신 undefined로 값이 들어감
                                // catch하고 안 멈추고 계속 진행하기 때문에 throw를 던져줘야하는데 대신 그거를 잡을 catch 또 만들어야한다
            return getCartItems(userInfo.userId, userInfo.cartId);   
            // 장바구니 정보
        }).then((items)=> { // 성공하면 then이라는 애를 불러옴 근데 실패하면 error 발생하고 이걸 catch로 잡아라
            
        })
        .catch((error) =>{ // 예외 처리
            alert(error);
        });
    });

    async function getCartItems(userId, cartId) {
        const xhr = new XMLHttpRequest();
        const requestUrl = `${SERVER_URL}/api/nhnmart/shopping-cart/${cartId}`;
            
        
        const options = {
            method : "GET",
            headers : {
                "content-type" : "application/json",
                "X-USER-ID" : userId
            }
        };

        const response = await fetch(requestUrl, options);

        if(!response.ok) {
            throw new Error("getCartItems Api Error");
        }

        return await response.json();

        //요거 생략 가능
        /* const executor = function(resolve, reject) {
            // 이렇게 작성시 굳이 +를 붙여가며 사용하지 않아도 된다
        

            xhr.addEventListener("load",function(){
                if(this.status===200) {
                    console.log(JSON.stringify(this.response));
                    // 모든 걸 한번에 받음
                    const items = this.response;
                
                    resolve(items);
                }
                // 에러를 잡고 싶다면 이렇게 작성하면 되는데 실상 잡기 힘들다
                // getCartItems이라는 collback 함수를 사용하기때문에
                //  또한, 비동기 방식으로 돌아가기 때문에 잡기 어렵다
                else {
                    reject(new Error(this.status));
                }
            });
        
            xhr.addEventListener("error", function(){
                // throw new Error("error"); -> 이렇게 잡기 어렵다
                // error 처리 ui(user interface) 만들어야한다
            });
        
            // 초기화
            xhr.open("GET", requestUrl);
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader("X-USER-ID",userId);
            xhr.responseType="json";
        
            // 호출
            xhr.send('');
        }
        const promise  = new Promise(executor);
        return promise; */

    }

    // 로그인 호출 -> 서버 호출 + ui 처리까지 하고 있다
    async function doLogin(userId, userPassword ) {
        const xhr = new XMLHttpRequest();
        const requestUrl = SERVER_URL + "/api/users/login";

        const user = {
            userId,
            userPassword
        }

        const options = {
            method : "POST",
            headers : {
                "content-type" : "application/json"
            },
            body : JSON.stringify(user)
        }

        const response = await fetch(requestUrl,options);

        if(!response.ok) {
            throw new Error("login fail");
        }

        return await response.json();
        // await를 붙이면 실제 return 되는건 dmdekq wkcpfmf json을 만들어서 보내버림
        // 게다가 비동기 방식이 끝날때까지 기다림

        // executor라는 이름있는 함수로 정의

        // 이 밑이 필요 없어져서 되게 간결해짐
        /* const executor = function(resolve, reject){
            const xhr = new XMLHttpRequest();
            const requestUrl = SERVER_URL + "/api/users/login";


            // 성공적으로 다 끝나면 실행
            xhr.addEventListener("load", function(){
                if(this.status === 200) {
                    // 로그인 성공 -> json -> Object 순서로 변환
                    // 초기화 때 json으로 받는다해서 변경해줘야한다
                    const userInfo = this.response;
                    resolve(userInfo)
                }
                else {
                    reject(new Error("login Fail"));
                }
            });

            // 초기화
            xhr.open("POST",requestUrl);
            xhr.setRequestHeader("Content-Type","application/json");
            xhr.responseType = "json";

            // 전송 -> api 호출
            xhr.send(JSON.stringify(user)); // json 문자열로 전송
            
        }
        const promise = new Promise(executor);
        return promise;
         */
    }
});

// executor 함수는 resolve, reject를 가지고 resolve는 성공할때, reject는 실패할때로
// resolve를 사용하면 then으로 빠지고
// reject를 사용하면 catch로 빠진다

// catch는 바로 위에 있는 then에 대한 reject만 catch한다



function displayLogin(userInfo) {
    //display:block -> display:none;
            // block이 보여주는 거고 none이 아무것도 안 보여주는거
            const loginWrapper = document.querySelector('#login-wrapper');
            loginWrapper.setAttribute("style", "display:none;");
    
            //display:none -> display:block;
            const loginSuccess = document.querySelector("#login-success");
            loginSuccess.setAttribute("style", "display:block;");
            
            // 로그인 성공 후 값 표시
            // div - ul - li 속 id값을 넣으면 된다 
            const loginUserId = document.getElementById("login-userId");
            const loginUserName = document.getElementById("login-userName");   
            const loginCartId = document.getElementById("login-cartId");
            
            // span 안에 값 넣기 설정
            loginUserId.innerText = userInfo.userId;
            loginUserName.innerText = userInfo.userName;
            loginCartId.innerText = userInfo.cartId;
}

function displayItems(items) {
    // ui 처리
    const cartTable = document.querySelector("#cart-table");
    const tbody = cartTable.getElementsByTagName("tbody")[0];
        // 이렇게 해도 되고
        // for(let i = 0; i<items.length; i++) {}

        //이런 방식으로 해도 된다
        for(let item of items) {
            // html tag를 만든 것이다
            const tr = document.createElement("tr");
            const td1 = document.createElement("td");
            const td2 = document.createElement("td");
            const td3 = document.createElement("td");
            const td4 = document.createElement("td");
            const td5 = document.createElement("td");

            td1.innerText = item.productId;
            td2.innerText = item.name;
            td3.innerText = item.price;
            td4.innerText = item.amount;
            td5.innerText = item.totalPrice;

            // 만든 tr, td를 tbody > tr > td 순으로 넣어준다
            tr.append(td1, td2, td3, td4, td5);
            tbody.append(tr);
        }
}