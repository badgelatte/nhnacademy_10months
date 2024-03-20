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
        doLogin(userId,userPassword, function(userInfo){
            // login success 처리..
            console.log("userInfo", JSON.stringify(userInfo));
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
            const loginUserName = document.getElementById("login-userName");                    const loginCartId = document.getElementById("login-cartId");
            
            // span 안에 값 넣기 설정
            loginUserId.innerText = userInfo.userId;
            loginUserName.innerText = userInfo.userName;
            loginCartId.innerText = userInfo.cartId;

            // getCarts(userInfo.userId, userInfo.cartId);
            // 장바구니 호출 api
            getCartItems(userInfo.userId, userInfo.cartId, function(items){
                // table에 id 있으니까 찾아와서 그 안의 tbody를 byTagName으로 접근한다
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
            });
        });
    });

    function getCartItems(userId, cartId, viewItems) {
        const xhr = new XMLHttpRequest();

        // 이렇게 작성시 굳이 +를 붙여가며 사용하지 않아도 된다
        const requestUrl = `${SERVER_URL}/api/nhnmart/shopping-cart/${cartId}`;

        xhr.addEventListener("load",function(){
            if(this.status===200) {
                console.log(JSON.stringify(this.response));
                // 모든 걸 한번에 받음
                const items = this.response;

                // ui 처리
                viewItems(items);
            }
            // 에러를 잡고 싶다면 이렇게 작성하면 되는데 실상 잡기 힘들다
            // getCartItems이라는 collback 함수를 사용하기때문에
            //  또한, 비동기 방식으로 돌아가기 때문에 잡기 어렵다
            else {
                throw new Error(this.status);
            }
        });

        xhr.addEventListener("error", function(){
            // throw new Error("error"); -> 이렇게 잡기 어렵다
            // error 처리 ui 만들어야한다
        });

        // 초기화
        xhr.open("GET", requestUrl);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.setRequestHeader("X-USER-ID",userId);
        xhr.responseType="json";

        // 호출
        xhr.send();
    }

    // 로그인 호출 -> 서버 호출 + ui 처리까지 하고 있다
    function doLogin(userId, userPassword, loginSuccess ) {
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
                    loginSuccess(userInfo)

                    /* // login success 처리..
                    console.log("userInfo", JSON.stringify(userInfo));
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
                    loginCartId.innerText = userInfo.cartId; */
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