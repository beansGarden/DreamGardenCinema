const hamburgerBtn = document.querySelector(".hamburgerBtn");
const pizzaBtn = document.querySelector(".pizzaBtn");
const hamburger = document.querySelector(".hamburger");
const pizza = document.querySelector(".pizza");
hamburgerBtn.addEventListener("click", ()=>{  /* 햄버거 버튼 누르면 제목 리스트 나옴 */
    hamburger.style.display = "block";
    pizza.style.display = "none";
})
pizzaBtn.addEventListener("click", ()=>{  /* 피자 버튼 누르면 포스터 추가된 리스트 나옴 */
    pizza.style.display = "block";
    hamburger.style.display = "none";
})

// 날짜 설정
const swiperWrapper = document.querySelector(".swiper-wrapper");
const headerDate = document.querySelector(".headerDate");
let today = new Date();
for(let i=0;i<10;i++){
    let year = today.getFullYear();  /* 2023 */
    let month = today.getMonth()+1;   /* 1~12 */
    let date = today.getDate();   /* 1~31 */
    let day = today.getDay();   /* 0~6 */
    
    switch(day) {
        case 1: day = "월"; break; 
        case 2: day = "화"; break;
        case 3: day = "수"; break;
        case 4: day = "목"; break;
        case 5: day = "금"; break;
        case 6: day = "토"; break;
        case 0: day = "일"; break;
    }
    const li = document.createElement("li");
    li.classList.add("swiper-slide");
    li.classList.add("checkBtn");
    li.onclick = dateSet;
    const strong = document.createElement("strong");
    if(i==0||date==1){
        strong.innerText = month+"월";
    }
    const a = document.createElement("a");
    const label = document.createElement("label");
    label.setAttribute("for", `Date${i}`);
    const innerStrong = document.createElement("strong");
    innerStrong.innerText = date;
    const em = document.createElement("em");
    if(day=='토'){
        innerStrong.style.color = 'blue';
        em.style.color = 'blue';
    }
    if(day=='일'){
        innerStrong.style.color = 'red';
        em.style.color = 'red';
    }

    const input = document.createElement("input");
    input.type = "radio";
    input.id = `Date${i}`;
    input.name = "date";
    if(month<10){
        month = "0"+month;
    }
    if(date<10){
        date = "0"+date;
    }
    if(i==0){
        day = "오늘";
        headerDate.innerText = `${year}-${month}-${date}(${day})`;
    }

    if(i==0) {
        li.classList.add("dateActive");
        input.setAttribute("checked", true);
    } 
    em.innerText = day;

    input.value = `${today.getFullYear()}${month}${date} ${day}`;  /*  ${day} */
    label.append(input, innerStrong, em);
    a.append(label);
    li.append(strong, a);
    swiperWrapper.append(li);
    today.setDate(today.getDate()+1);

}

// 영화 선택 이벤트
const checkMovie = document.querySelectorAll(".MovieList>label");
let sameLabel;
for(let i=0;i<checkMovie.length;i++){
    checkMovie[i].addEventListener("click", e=>{
        for(let j=0;j<checkMovie.length;j++){
            checkMovie[j].classList.remove("saveMovie");
        }
        sameLabel = e.currentTarget.getAttribute("for");
        for(let j=0;j<checkMovie.length;j++){
            if(checkMovie[j].getAttribute("for") == sameLabel){
                checkMovie[j].classList.add("saveMovie");
            }
        }
    })
}

// 날짜 선택 이벤트

function dateSet(){
    const choiceDate = document.querySelectorAll(".swiper-slide");

    for(let j=0;j<choiceDate.length;j++){
        choiceDate[j].classList.remove("dateActive");
    }
    this.classList.add("dateActive");
    this.querySelector("input").checked = true;

    // 날짜 헤더 변경
    let date = this.querySelector("a>label>input").value;
    let day = this.querySelector("a>label>em").innerText;
    headerDate.innerText = `${date.slice(0,4)}-${date.slice(4,6)}-${date.slice(6,8)}(${day})`;
}

// 상영시간 / 상영관 AJAX
const checkBtn = document.querySelectorAll(".checkBtn");
for(let i=0;i<checkBtn.length;i++){
    checkBtn[i].addEventListener("click", e=>{
        theaterChoiceajax(e.currentTarget);
    });
};
// 페이지 로딩시 상영관 AJAX
document.addEventListener("DOMContentLoaded", function(){
    theaterChoiceajax(checkBtn[0]);
});

// 상영시간 / 상영관 AJAX
function theaterChoiceajax(e){
    let movieNo;
    let date;

    if(e.nodeName == 'LABEL'){
        movieNo = e.getAttribute('for');
        date = document.querySelector("input[name=date]:checked").value;
    }
    if(e.nodeName == 'LI'){
        movieNo = document.querySelector("input[name=movieNo]:checked").value;
        date = e.querySelector("a>label>input").value;
    }

    const data = {"movieNo" : movieNo, "date" : date};
    fetch("/ticketing/time", {
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(data)
    })
    .then(resp => resp.json())
    .then(timeList => {

        // 기존 정보 삭제
        const movieChoiceTitle = document.querySelector(".movieChoiceTitle");
        const timeSelect = document.querySelector(".timeSelect");
        timeSelect.innerHTML = '';
        movieChoiceTitle.innerHTML = '';

        const ul = document.createElement("ul");
        ul.classList.add("timelist");

        if(timeList.length==0){
            const div = document.createElement("div");
            div.classList.add("nonelist");
            div.innerText ="상영 중인 정보가 없습니다"
            timeSelect.append(div);
        } else {

            for(let i=0;i<timeList.length;i++){
                // 영화관, 시간 버튼 생성
                const li = document.createElement("li");
                const label = document.createElement("label");
                label.classList.add("btn");
                label.setAttribute("onclick", "movieTimeSub()");
                label.setAttribute("for", `${timeList[i].movieTheater},${timeList[i].movieTime}`);

                const input = document.createElement("input");
                input.type = "radio";
                input.name = "movieTime";
                input.id = `${timeList[i].movieTheater},${timeList[i].movieTime}`;
                input.value = `${timeList[i].movieTheater},${timeList[i].movieTime}`;

                const firstDiv = document.createElement("div");
                firstDiv.innerText = timeList[i].movieTheater + '관';
                const lastDiv = document.createElement("div");
                lastDiv.innerText = timeList[i].movieTime;
                label.append(input, firstDiv, lastDiv);
                li.append(label);
                ul.append(li);
            }
            timeSelect.append(ul);
        }
        // 영화 등급, 제목 바꾸기
        const img = document.createElement("img");
        img.setAttribute("src",document.querySelector(".saveMovie>img.rating").src);
        const span = document.createElement("span");
        span.innerText = document.querySelector(".saveMovie>span.movieTitle").innerText;
        movieChoiceTitle.append(img, span);
    })
    .catch(err => {
        console.log("예외 발생");
        console.log(err);
    })
}

// 영화시간 버튼 클릭 시 제출
function movieTimeSub(){
    if(loginUser == null){
        alert("로그인 후 이용해주시길 바랍니다.");
        document.location.href = "/user/login";
        return;
    }
    document.getElementById('timeFrm').submit();
}