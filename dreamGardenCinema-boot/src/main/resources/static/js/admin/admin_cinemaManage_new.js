const selectCont = document.querySelector("#selectCont");
const content = document.querySelector("#content");
const opt = document.querySelectorAll("#opt > option");
const searchDate = document.querySelector("#date");
const theater = document.querySelector("#theater");

(()=>{
    const params = new URL(location.href).searchParams;

    const key = params.get("selectOpt");  // t, c, tc, w 중 하나
    const query = params.get("content");  // 검색어
    const date = params.get("date");  // 날짜
    const movieTheater = params.get("movieTheater");
    if(key != null){
        // 검색을 했을 때
        content.value = query;  // 검색어를 화면에 출력
        searchDate.value = date;
        theater.value = movieTheater;
        // option태그를 하나씩 순차 접근해서 value가 key랑 같으면
        // selected 속성 추가
        for(let op of opt){
            if(op.value == key){
                op.selected = true;
            }
        }
    }
    if(date != null){
        searchDate.value = date;
    }
})();

// 검색어 입력 없이 제출된 경우
selectCont.addEventListener("submit", e => {

    if(content.value.trim().length == 0 && searchDate.value.trim().length == 0 && theater.value.trim().length == 0){  // 검색어 미입력 시
        e.preventDefault();  // form 기본 이벤트 제거

        location.href = location.pathname;  // 해당 게시판 1페이지로 이동

        // location.pathname : 쿼리스트링을 제외한 실제 주소
    }

});

const movieTt = document.querySelectorAll("#movieTt");

for(let i=0;i<movieTt.length;i++){
    movieTt[i].addEventListener("click", e=>{
        console.log(e.target.innerText);
        console.log(e.target.parentNode.nextElementSibling.nextElementSibling.innerText);
        console.log(e.target.parentNode.previousElementSibling.innerText);

        const movieTitle = e.target.innerText;
        const movieDate = e.target.parentNode.nextElementSibling.nextElementSibling.innerText;
        const movieTheater = e.target.parentNode.previousElementSibling.innerText;

        const timeWrap = e.target.nextElementSibling;
        console.log(timeWrap);

        if(timeWrap == null || timeWrap.innerHTML == ''){
            fetch("/adminCinemaTimeSelect", {
                method : "POST",
                headers : {"Content-Type" : "application/json"},
                body : JSON.stringify({"movieTitle" : movieTitle, "movieDate" : movieDate, "movieTheater" : movieTheater})
            })
            .then(resp => resp.json())
            .then(timeList => {
                console.log(timeList);

                const parentDiv = document.createElement("div");
                parentDiv.classList.add("timeWrap");
                const div = document.createElement("div");
                div.innerText = '상영시간';
                const timeDiv = document.createElement("div");
                timeDiv.classList.add("timeDiv");
                for(let i=0;i<timeList.length;i++){
                    const div = document.createElement("div");
                    const span = document.createElement("span");
                    span.innerText = timeList[i];
                    const button = document.createElement("button");
                    button.classList.add("deleteBtn");
                    button.setAttribute("onclick", "deleteTime(this)");
                    button.innerText = 'x';
                    div.append(span, button);
                    timeDiv.append(div);
                }
                parentDiv.append(div, timeDiv);
                e.target.after(parentDiv);
            })
        } else {
            timeWrap.remove();
        }
    })
}

// const deleteBtn = document.querySelectorAll(".deleteBtn");
// for(let i=0;i<deleteBtn;i++){
//     deleteBtn[i].addEventListener("click", ()=> {
//         if(confirm("정말 삭제하시겠습니까?")){
    
//         }
//         console.log(e.target.parentNode.parentNode.parentNode.previousElementSibling.innerText);
//         console.log(e.target.parentNode.parentNode.parentNode.previousElementSibling.parentNode.nextElementSibling.nextElementSibling.innerText);
//         console.log(e.target.parentNode.parentNode.parentNode.previousElementSibling.parentNode.previousElementSibling.innerText);
//     });
// };

function deleteTime(e){
    if(confirm("정말 삭제하시겠습니까?")){
    
        const movieNo = e.parentNode.parentNode.parentNode.previousElementSibling.getAttribute("movieNo");
        const movieDate = e.parentNode.parentNode.parentNode.previousElementSibling.parentNode.nextElementSibling.nextElementSibling.innerText;
        const movieTheater = e.parentNode.parentNode.parentNode.previousElementSibling.parentNode.previousElementSibling.innerText;
        const movieTime = movieDate + e.previousElementSibling.innerText;
    
        const params = new URL(location.href).searchParams;

        const date = params.get("date");  // 날짜
        const theater = params.get("movieTheater");
        const selectOpt = params.get("selectOpt");  
        const content = params.get("content");  
        const cp = params.get("cp");

        console.log(movieNo);
        console.log(movieTheater);
        console.log(movieTime);
        let f = document.createElement('form');

        let obj;
        obj = document.createElement('input');
        obj.setAttribute('type', 'hidden');
        obj.setAttribute('name', 'movieNo');
        obj.setAttribute('value', movieNo);

        let obj1;
        obj1 = document.createElement('input');
        obj1.setAttribute('type', 'hidden');
        obj1.setAttribute('name', "movieTheater");
        obj1.setAttribute('value', movieTheater);

        let obj2;
        obj2 = document.createElement('input');
        obj2.setAttribute('type', 'hidden');
        obj2.setAttribute('name', 'movieTime');
        obj2.setAttribute('value', movieTime);
        
        let obj3;
        obj3 = document.createElement('input');
        obj3.setAttribute('type', 'hidden');
        obj3.setAttribute('name', 'cp');
        obj3.setAttribute('value', cp);

        let obj4;
        obj4 = document.createElement('input');
        obj4.setAttribute('type', 'hidden');
        obj4.setAttribute('name', 'selectOpt');
        obj4.setAttribute('value', selectOpt);

        let obj5;
        obj5 = document.createElement('input');
        obj5.setAttribute('type', 'hidden');
        obj5.setAttribute('name', 'content');
        obj5.setAttribute('value', content);

        let obj6;
        obj6 = document.createElement('input');
        obj6.setAttribute('type', 'hidden');
        obj6.setAttribute('name', 'date');
        obj6.setAttribute('value', date);

        let obj7;
        obj7 = document.createElement('input');
        obj7.setAttribute('type', 'hidden');
        obj7.setAttribute('name', 'theater');
        obj7.setAttribute('value', theater);

        f.append(obj, obj1, obj2, obj3, obj4, obj5, obj6, obj7);
        f.setAttribute('method', 'post');
        f.setAttribute('action', '/adminCinemaDeleteTime');
        document.body.appendChild(f);
        f.submit();
    }
}

const registBtn = document.getElementById("regist");
registBtn.addEventListener("click", () => {
    location.href="/adminCinemaRegister";
})

function cinemaSelectAll(cinemaSelectAll) { //온클릭 
    const checkboxes
      = document.querySelectorAll('input[type="checkbox"]'); //체크박스 전체 불러오기
  
    checkboxes.forEach((checkbox) => { //체크박스 전체 선택
      checkbox.checked = cinemaSelectAll.checked //체크박스누르면 
    })
  }