const hiddenRadio = document.querySelector(".hiddenRadio");
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
let checkMovie = document.querySelectorAll(".MovieList>label");
function movieChoice(){
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
let checkBtn = document.querySelectorAll(".checkBtn");
for(let i=0;i<checkBtn.length;i++){
    checkBtn[i].addEventListener("click", e=>{
        theaterChoiceajax(e.currentTarget);
    });
};
// 페이지 로딩시 상영관 AJAX
document.addEventListener("DOMContentLoaded", function(){
    theaterChoiceajax(document.querySelectorAll(".saveMovie")[0]);
    movieChoice();
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

// 예매순, 별점순 AJAX
const sortCont = document.getElementById("sortCont");
sortCont.addEventListener("change", e=>{
    const sortOption = document.querySelector("[name='sortOption']:checked").value;
    const movieNo = document.querySelector("input[name=movieNo]:checked").value;

    fetch("/ticketing/sort", {
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify({"sortOption" : sortOption, "movieNo" : movieNo})
    })
    .then(resp => resp.json())
    .then(map => {
        
        hiddenRadio.innerHTML = '';
        hamburger.innerHTML = '';
        pizza.innerHTML = '';

        for(let i=0;i<map.movieList.length;i++){
            const input = document.createElement("input");
            input.type = "radio";
            input.name = "movieNo";
            input.id = map.movieList[i].movieNo;
            input.value = map.movieList[i].movieNo;
            
            const label1 = document.createElement("label");
            label1.setAttribute("for", map.movieList[i].movieNo);
            label1.classList.add("checkBtn");
            label1.classList.add("hmbgr");
            label1.onclick = '' /* ========================================= */
            const img1 = document.createElement("img");
            img1.classList.add("rating");
            img1.setAttribute("src", map.movieList[i].rating);
            const span1 = document.createElement("span");
            span1.classList.add("movieTitle");
            span1.innerText = map.movieList[i].movieTitle;
            label1.append(img1, span1);
            
            
            const label2 = document.createElement("label");
            label2.setAttribute("for", map.movieList[i].movieNo);
            label2.classList.add("checkBtn");
            label2.classList.add("pzz");
            const pDiv = document.createElement("div");
            pDiv.classList.add("poster");
            const pImg = document.createElement("img");
            pImg.setAttribute("src", map.movieList[i].poster);
            pDiv.append(pImg);
            const cDiv = document.createElement("div");
            cDiv.classList.add("content");
            const tDiv = document.createElement("div");
            tDiv.classList.add("title");
            const rImg = document.createElement("img");
            rImg.classList.add("rating");
            rImg.setAttribute("src", map.movieList[i].rating);
            const strong = document.createElement("strong");
            strong.classList.add("movieTitle");
            strong.innerText = map.movieList[i].movieTitle;
            tDiv.append(rImg, strong);
            const rDiv = document.createElement("div");
            rDiv.classList.add("rate");
            const rSpan = document.createElement("span");
            rSpan.classList.add("rate_info");
            const em = document.createElement("em");
            em.innerText = map.movieList[i].ratio + '%';
            rSpan.append(em);
            rSpan.innerHTML = '예매율 ' + rSpan.innerHTML;
            const sSpan = document.createElement("span");
            sSpan.classList.add("star_info");
            const sImg = document.createElement("img");
            sImg.setAttribute("src", "/images/common/main/포스터/Star3.png");
            sSpan.append(sImg);
            sSpan.innerHTML = sSpan.innerHTML + ' ' + map.movieList[i].score;
            rDiv.append(rSpan,sSpan);
            const dDiv = document.createElement("div");
            dDiv.classList.add("date");
            const dSpan = document.createElement("span");
            dSpan.classList.add("rate_info");
            const rdSpan = document.createElement("span");
            rdSpan.innerText = map.movieList[i].releaseDate;
            dSpan.append(rdSpan);
            dSpan.innerHTML = '개봉일 ' + dSpan.innerHTML;
            dDiv.append(dSpan);

            if(movieNo == map.movieList[i].movieNo){
                input.checked = true;
                label1.classList.add("saveMovie");
                label2.classList.add("saveMovie");
            }
            cDiv.append(tDiv, rDiv, dDiv);
            label2.append(pDiv, cDiv);
            
            hiddenRadio.append(input);
            hamburger.append(label1);
            pizza.append(label2);
        }


        checkMovie = document.querySelectorAll(".MovieList>label");
        movieChoice();
        checkBtn = document.querySelectorAll(".checkBtn");
        for(let i=0;i<checkBtn.length;i++){
            checkBtn[i].addEventListener("click", e=>{
                theaterChoiceajax(e.currentTarget);
            });
        };
    })

});