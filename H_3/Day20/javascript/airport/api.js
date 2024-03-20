// flightScheleApi = 함수를 갖다쓸때 쓸수 있는 method가 serach, getAirportList 등등등이 있는데 
// 밖에 있는 displaySearchResult 같은 경우 쓸 수가 없다
const flightScheduleApi = (function(){
    'use strict';
    const SERVICE_KEY = "szUb3GP6ynYeWUTJtSIEtlqBTEitiF6gkVbWIfVUX%2BsgO3%2FD%2F1nxS1J%2Fp575Shu5QalOY7HG6n0zYTFT0pii6A%3D%3D";

    //운행스케줄 api
    const api = new Object();
    // const api = {getAirportList : function(){}} 
    // api 선언하고 api.searchr같은 것과 윗줄은 동일하게 사용하는 것이다
    // return api하면 어케되는가 -> java에서 private로 만든것과 동일하다 
    // object를 return하면서 공개/비공개를 범위를 딴진 것

    
    async function getAirlineList(){
        let url = 'http://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getAirmanList'; /*URL*/
        let queryParams = '?' + encodeURIComponent('serviceKey') + '='+SERVICE_KEY; /*Service Key*/
        queryParams += '&' + encodeURIComponent('_type') + '=' + encodeURIComponent('json'); /**/
        url+=queryParams;
        //TODO#1 항공사 리스트 구하기
        /* const options = {
            method : "",    // 아무것도 안쓰면 기본이 GET
            headers : {
                "content-type" : "application/json"
            }
        } */
        const response = await fetch(url);
        const json = await response.json();
        console.log(json);
        if(!response.ok) {
            throw new Error("airline api Error");
        }
        return json.response.body.items.item;

    }

    api.getAirportList = async function(){
        let url = "http://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getArprtList";
        let queryParams = '?' + encodeURIComponent('serviceKey') + '='+SERVICE_KEY; /*Service Key*/
            queryParams += '&' + encodeURIComponent('_type') + '=' + encodeURIComponent('json'); /**/
            url+=queryParams;

            //TODO#2 공항리스트 구하기
            const response = await fetch(url);
            const responseJson = await response.json();
            console.log("response : ", response);
            console.log("jason : ",responseJson);

            if(!response.ok) {
                throw new Error("airport api Error");
            }
            return responseJson.response.body.items.item;

    }

    /* 
        * @param {*} depAirportId  출발공항 아이디
        * @param {*} arrAirportId  도착공항 아이디
        * @param {*} depPlandTime  출발시간 : 20230321
        * @param {*} airlineId     항공사 아이디
    */
    //getFlightSchedule("NAARKJJ","NAARKPC","20201202","AAR");
    async function getFlightSchedule(depAirportId,arrAirportId,depPlandTime,airlineId){
        let url = "http://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getFlightOpratInfoList";
        let queryParams = "?serviceKey="  + encodeURIComponent(SERVICE_KEY);
        queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
        queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); /**/
        queryParams += '&' + encodeURIComponent('_type') + '=' + encodeURIComponent('json'); /**/
        queryParams += '&' + encodeURIComponent('depAirportId') + '=' + encodeURIComponent(depAirportId); /**/
        queryParams += '&' + encodeURIComponent('arrAirportId') + '=' + encodeURIComponent(arrAirportId); /**/
        queryParams += '&' + encodeURIComponent('depPlandTime') + '=' + encodeURIComponent(depPlandTime); /**/
        queryParams += '&' + encodeURIComponent('airlineId') + '=' + encodeURIComponent(airlineId); /**/
        url = url + queryParams;
        console.log(url);

        const response = await fetch(url);
        const json = await response.json();


        console.log(depAirportId,arrAirportId,depPlandTime,airlineId);
        console.log("FlightSchedule json", json);

        //TODO#3 항공운항정보 조회
        const a = json.response.body.items.item;

        if(response.ok) {

            console.log("dfdfdf", a);
            if(a== null) {
                return [];
            }
            return a;
        } else {
            throw new Error("airport api Error");
        }


        // console.log("FlightSchedule", json.response.body);
        // return json.response.body.items;

        
    }

    api.search=async function(depAirportId,arrAirportId,depPlandTime){
        const airlineList = await getAirlineList();
        console.log("airlist-------------",airlineList);

        //조회로직 실행
        depPlandTime = depPlandTime.replaceAll("-","");
        const promiseList = [];

        /* {
            "airlineId": "AAR",
            "airlineNm": "아시아나항공"
          }, */
        for (const airline of airlineList) {
            console.log("airline-----------------------------------------", airline);
            const promise = await getFlightSchedule(depAirportId,arrAirportId,depPlandTime,airline.airlineId);
            //TODO#4 항공사별 운항정보를 얻어서 하나의 리스트로 리턴

            promiseList.push(... promise);
        }

        return promiseList;
    }

    return api;
})();

window.addEventListener("DOMContentLoaded",async function(){
    'use strict'
    
    const departureId = document.getElementById("departureId");
    const arrivalId = document.getElementById("arrivalId");
    
    //비행날짜
    const plandDate = document.getElementById("plandDate");
    //TODO#5 기본 날짜를 오늘로 설정
    plandDate.value = new Date().toISOString().substring(0,10);
    
    //FIXME #6 공항리스트 호출.
    const airportList = await flightScheduleApi.getAirportList();

    /* {
        "airportId": "NAARKSS",
        "airportNm": "김포"
    } */

    // for(let airport of airportJson) {
    //     /* const departureIdOption = document.createElement("option");
    //     departureId.append(departureIdOption);
    //     departureIdOption.innerText = airport.airportNm;
    //     const arrivalIdOption = document.createElement("option");
    //     arrivalId.append(arrivalIdOption);
    //     arrivalIdOption.innerText = airport.airportNm; */
    //     airportList.push(airport);
    // }
    
    for (const item of airportList) {
        //TODO#7  selectBox (departureId,arrivalId)에 공항리스트 할당
        const option = document.createElement("option");
        option.value=item.airportId;
        option.textContent=item.airportNm;
        
        departureId.appendChild(option.cloneNode(true));
        arrivalId.appendChild(option);
        
    }
    console.log("airportList", airportList);

    const validateForm = function(form){
        const departureId = form["departureId"];    // 폼 Id가 departureId인 것들 다 접근
        const arrivalId = form["arrivalId"];
        const departureIdValue  = departureId.options[departureId.selectedIndex].value;
        const arrivalIdValue  = arrivalId.options[arrivalId.selectedIndex].value;   // arrivalId.selectedIndex - 선택박스 몇번째인가 
        //TODO#8 form validation
        // departureId, arrivalId 선택여부 체크
        // 출발(공항) == 도착(공항) retun false
        console.log("departureIdValue", departureIdValue);
        if(departureIdValue == arrivalIdValue) {
            return false;
        }
        return true;
    };

    const findForm = document.getElementById("find-form");
    
    findForm.addEventListener("submit",async function(event){
        event.preventDefault();
        if(validateForm(event.target)==false){
            return;
        }

        //schedule 조회
        try{
            const depPlandTime = document.getElementById("plandDate").value;
            const items = await flightScheduleApi.search(departureId.value,arrivalId.value,depPlandTime);
            searchResult(items);

        }catch(e){
            alert(e);
        }
    });

});

function searchResult(items){

    const scheduleTbl = document.getElementById("schedule-tbl");
    const tbody = scheduleTbl.getElementsByTagName("tbody")[0];

    while(tbody.firstChild){
       //TODO#9tbody에 담겨있는 모든 <tr> 삭제
       tbody.firstChild.remove();
    }

    for(let i=0; i<items.length; i++){
        /* {
            "airlineNm": "티웨이항공",
            "arrAirportNm": "제주",
            "arrPlandTime": 202303221605,
            "depAirportNm": "광주",
            "depPlandTime": 202303221510,
            "vihicleId": "TW9903",
            "economyCharge": "",
            "prestigeCharge": ""
          } */
        const tr = document.createElement("tr");
        //TODO#10 tbdoy에 <tr><td>...</td> ... </tr> 만들어서 넣기
        const td1 = document.createElement("td");
        const td2 = document.createElement("td");
        const td3 = document.createElement("td");
        const td4 = document.createElement("td");
        const td5 = document.createElement("td");
        const td6 = document.createElement("td");
        const td7 = document.createElement("td");
        const td8 = document.createElement("td");
        tr.append(td1, td2, td3, td4, td5, td6, td7, td8);
        // td1.innerText = items[i].vihicleId;
        // td2.innerText = items[i].arrAirportNm;
        // td3.innerText = items[i].depPlandTime;
        // td4.innerText = items[i].arrPlandTime;
        // td5.innerText = items[i].prestigeCharge;
        // td6.innerText = items[i].economyCharge;
        // td7.innerText = items[i].depAirportNm;
        // td8.innerText = items[i].arrAirportNm;
        td1.innerText = items[i].vihicleId;
        td2.innerText = items[i].airlineNm;
        td3.innerText = convertDate(items[i].depPlandTime);
        td4.innerText = convertDate(items[i].arrPlandTime);
        td5.innerText = new Intl.NumberFormat().format(items[i].economyCharge);
        td6.innerText = new Intl.NumberFormat().format(items[i].prestigeCharge);
        td7.innerText = items[i].depAirportNm;
        td8.innerText = items[i].arrAirportNm;
        
        //숫자 서식 관려해서는 다음을 참고하기
        //https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Intl/NumberFormat 
        //날짜 변환에 대해서는 convertDate(str) 함수를 이용해주세요

        tbody.append(tr);
    }
}

function convertDate(str){
    str = str.toString();
    //202303221125 -> 2023 03 22 11:25
    return str.substring(0,4) 
            + "-" + str.substring(4,6)
            + "-" + str.substring(6,8) 
            + " " + str.substring(8,10) 
            + ":" + str.substring(10,12);
}