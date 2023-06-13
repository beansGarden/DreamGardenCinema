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

// 영화 선택 이벤트
const checkMovie = document.querySelectorAll(".MovieList>label");
let sameLabel;
for(let i=0;i<checkMovie.length;i++){
    checkMovie[i].addEventListener("click", e=>{
        for(let j=0;j<checkMovie.length;j++){
            checkMovie[j].style.border = "none";
        }
        sameLabel = e.currentTarget.getAttribute("for");
        for(let j=0;j<checkMovie.length;j++){
            if(checkMovie[j].getAttribute("for") == sameLabel){
                checkMovie[j].style.border = "1px solid #0E0753";
            }
        }
    })
}

// 날짜 설정
const swiperWrapper = document.querySelector(".swiper-wrapper");
let today = new Date();
for(let i=0;i<10;i++){
    let month = today.getMonth()+1;
    let date = today.getDate();
    let day = today.getDay();
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
    if(i==0) {
        li.classList.add("dateActive");
        em.innerText = "오늘";
    } else {
        em.innerText = day;
    }
    const input = document.createElement("input");
    input.type = "radio";
    input.id = `Date${i}`;
    input.name = "movieTime";
    if(month<10){
        month = "0"+month;
    }
    if(date<10){
        date = "0"+date;
    }
    input.value = `${today.getFullYear()}${month}${date}`;
    label.append(input, innerStrong, em);
    a.append(label);
    li.append(strong, a);
    swiperWrapper.append(li);
    today.setDate(today.getDate()+1);
}

// 날짜 선택 이벤트
const headerDate = document.querySelector(".headerDate");
function dateSet(){
    const choiceDate = document.querySelectorAll(".swiper-slide");
    for(let i=0;i<choiceDate.length;i++){
        choiceDate[i].addEventListener("click", e=>{
            for(let j=0;j<choiceDate.length;j++){
                choiceDate[j].classList.remove("dateActive");
            }
            e.currentTarget.classList.add("dateActive");

            // 날짜 헤더 변경
            let date = e.currentTarget.querySelector("a>label>input").value;
            let day = e.currentTarget.querySelector("a>label>em").innerText;
            headerDate.innerText = `${date.slice(0,4)}-${date.slice(4,6)}-${date.slice(6,8)}(${day})`;
        })
    }
}