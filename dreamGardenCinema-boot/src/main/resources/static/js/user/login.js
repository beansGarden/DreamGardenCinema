function getCookie(key){
    const cookies = document.cookie;

    // saveId=user01@kh.or.kr; test=가나다; aaa=100
    // 배열.map() : 모든 배열 순차 접근 함수 수행 후 
    //              수행 결과를 이용해 새로운 배열을 만드는 함수
    const cookieList = cookies.split("; ").map(cookie => cookie.split("="));

    const obj = {}; // 빈 객체 생성

    for(let i=0; i < cookieList.length; i++){
        obj[cookieList[i][0]] = cookieList[i][1];
    }
    return obj[key];

}

// 쿠키에 saveId가 있을 경우
if(document.querySelector("input[name='userId']") != null){
    // 화면에 memberEmail 입력박스가 있을 경우

    const saveId = getCookie("saveId");
    // 있으면 이메일, 없으면 undefined

    if(saveId != undefined){ // 쿠키에 저장된 이메일 있을 때
        document.querySelector("input[name='userId']").value = saveId;

        document.querySelector("input[name='saveId']").checked = true;

    }
}