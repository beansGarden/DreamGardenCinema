const slide = document.querySelector(".slide");  // 슬라이드 구역
let slideWidth = slide.clientWidth;  // 슬라이드 넓이
// let slideWidth = 100;  // 슬라이드 넓이
const prevBtn = document.querySelector(".slide_prev_button");  // 이전 버튼
const nextBtn = document.querySelector(".slide_next_button");  // 다음 버튼
let slideItems = document.querySelectorAll(".slide_item"); // 모든 슬라이드
const maxSlide = slideItems.length;  // 슬라이드 개수
let currSlide = 1;  // 현재 슬라이드 위치 알려주는 변수
const pagination = document.querySelector(".slide_pagination") // 페이지네이션 구역
for(let i=0; i<maxSlide; i++){  // 페이지네이션 자동 생성
    if(i==0) pagination.innerHTML += '<li class="active">•</li>';
    else pagination.innerHTML += '<li>•</li>';
}
const paginationItems = document.querySelectorAll(".slide_pagination > li");  // 슬라이드의 모든 페이지네이션
const startSlide = slideItems[0];  // 첫번째 슬라이드(무한 슬라이드용)
const endSlide = slideItems[slideItems.length - 1];  // 마지막 슬라이드(무한 슬라이드용)
const startElem = document.createElement(startSlide.tagName);  // 읽기 전용 요소 생성
const endElem = document.createElement(endSlide.tagName);  // 읽기 전용 요소 생성
/* --- 엘리먼트에 클래스 적용 동일하게 해주기 --- */
endSlide.classList.forEach((c) => endElem.classList.add(c));
endElem.innerHTML = endSlide.innerHTML;
startSlide.classList.forEach((c) => startElem.classList.add(c));
startElem.innerHTML = startSlide.innerHTML;
/* ---------------------------------------------- */
slideItems[0].before(endElem);  // 첫번째 슬라이드 전에 생성한 마지막 슬라이드 요소 추가
slideItems[slideItems.length - 1].after(startElem);  // 마지막 슬라이드 뒤에 생성한 첫번째 슬라이드 요소 추가
// slideItems = document.querySelectorAll(".slide_item");
let offset = slideWidth * currSlide;  // 이동할 너비(슬라이드 넓이 * 슬라이드 위치)
slideItems.forEach((i) => {  // 슬라이드마다 style 위치 적용
    // i.setAttribute("style", `transform: translate(${-offset}vw)`);
    i.setAttribute("style", `left: ${-offset}px`);
})
function nextMove() {
    currSlide++;  // 슬라이드 번호 추가
    if(currSlide < maxSlide+1){  // 마지막 슬라이드를 넘어가지 않으면
        const offset = slideWidth * currSlide;  // offset 재설정(슬라이드 넓이 * 슬라이드 위치)
        slideItems.forEach((i) => {
            // i.setAttribute("style", `transform: translate(${-offset}vw)`);
            i.setAttribute("style", `left: ${-offset}px`);
        });
        paginationItems.forEach((i) => i.classList.remove("active")); // 활성화된 슬라이드 페이지네이션 삭제
        paginationItems[currSlide -1].classList.add("active");  // 현재 슬라이드와 맞는 페이지네이션 활성화
    } else {  // 마지막 슬라이드가 넘어가면
        currSlide = 0;  // 첫번째 슬라이드 전
        let offset = slideWidth * currSlide;
        slideItems.forEach((i)=>{ // 모두 처음으로 리셋
            // i.setAttribute("style", `transition: ${0}s; transform: translate(${-offset}vw)`)
            i.setAttribute("style", `transition: ${0}s; left: ${-offset}px`)
        });
        currSlide++;
        offset = slideWidth * currSlide;
        setTimeout(() => {  // 비동기 처리를 위해 transition이 제대로 적용되게 하기 위함
            slideItems.forEach((i) => {  // 각 슬라이드에 offset 적용
                // i.setAttribute("style", `transition: ${0.15}s; transform: translate(${-offset}vw)`);
                i.setAttribute("style", `transition: ${0.15}s; left: ${-offset}px`);
            });
        }, 0);
        paginationItems.forEach((i) => i.classList.remove("active"));
        paginationItems[currSlide - 1].classList.add("active");
    }
}
function prevMove() {
    currSlide--;  // 슬라이드 번호 빼기
    if (currSlide > 0) {  // 처음 슬라이드를 넘어가지 않으면
        const offset = slideWidth * currSlide; // 슬라이드를 이동시키기 위한 offset 계산
        slideItems.forEach((i) => { // 각 슬라이드 아이템의 left에 offset 적용
            // i.setAttribute("style", `transform: translate(${-offset}vw)`);
            i.setAttribute("style", `left: ${-offset}px`);
        });
        paginationItems.forEach((i) => i.classList.remove("active")); // 슬라이드 이동 시 현재 활성화된 pagination 변경
        paginationItems[currSlide - 1].classList.add("active");
    } else {
        currSlide = maxSlide + 1;  // 마지막 슬라이드 다음
        let offset = slideWidth * currSlide;
        slideItems.forEach((i) => {
            // i.setAttribute("style", `transition: ${0}s; transform: translate(${-offset}vw)`);
            i.setAttribute("style", `transition: ${0}s; left: ${-offset}px`);
        });
        currSlide--;
        offset = slideWidth * currSlide;
        setTimeout(() => {
            slideItems.forEach((i) => {
                // i.setAttribute("style", `transition: ${0.15}s; transform: translate(${-offset}vw)`);
                i.setAttribute("style", `transition: ${0.15}s; left: ${-offset}px`);
            });
        }, 0);
        // 슬라이드 이동 시 현재 활성화된 pagination 변경
        paginationItems.forEach((i) => i.classList.remove("active"));
        paginationItems[currSlide - 1].classList.add("active");
    }
}

// 버튼 엘리먼트에 클릭 이벤트 추가하기
nextBtn.addEventListener("click", () => {
    // 이후 버튼 누를 경우 현재 슬라이드를 변경
    nextMove();
});
// 버튼 엘리먼트에 클릭 이벤트 추가하기
prevBtn.addEventListener("click", () => {
    // 이전 버튼 누를 경우 현재 슬라이드를 변경
    prevMove();
});

// 브라우저 화면이 조정될 때 마다 slideWidth를 변경하기 위해
window.addEventListener("resize", () => {
    slideWidth = slide.clientWidth;
});

// 각 페이지네이션 클릭 시 해당 슬라이드로 이동하기
for (let i = 0; i < maxSlide; i++) {
    // 각 페이지네이션마다 클릭 이벤트 추가하기
    paginationItems[i].addEventListener("click", () => {
    // 클릭한 페이지네이션에 따라 현재 슬라이드 변경해주기(currSlide는 시작 위치가 1이기 때문에 + 1)
    currSlide = i + 1;
    // 슬라이드를 이동시키기 위한 offset 계산
    const offset = slideWidth * currSlide;
    // 각 슬라이드 아이템의 left에 offset 적용
    slideItems.forEach((i) => {
        i.setAttribute("style", `left: ${-offset}px`);
    });
    // 슬라이드 이동 시 현재 활성화된 pagination 변경
    paginationItems.forEach((i) => i.classList.remove("active"));
    paginationItems[currSlide - 1].classList.add("active");
    });
}

// 드래그(스와이프) 이벤트를 위한 변수 초기화
let startPoint = 0;
let endPoint = 0;

// PC 클릭 이벤트 (드래그)
slide.addEventListener("mousedown", (e) => {
    startPoint = e.pageX; // 마우스 드래그 시작 위치 저장
});

slide.addEventListener("mouseup", (e) => {
    endPoint = e.pageX; // 마우스 드래그 끝 위치 저장
    if (startPoint < endPoint) {
    // 마우스가 오른쪽으로 드래그 된 경우
    prevMove();
    } else if (startPoint > endPoint) {
    // 마우스가 왼쪽으로 드래그 된 경우
    nextMove();
    }
});




new Swiper('.sub_slide .swiper-container', {
    direction: 'horizontal', // 기본값
    spaceBetween: 30,
    slidesPerView: 5,
    navigation: {
      prevEl: '.sub_slide .swiper-prev',
      nextEl: '.sub_slide .swiper-next'
    }
});
const swiperSlide = document.querySelectorAll(".swiper-slide");
const swiperPrev = document.querySelector(".swiper-prev");
const swiperNext = document.querySelector(".swiper-next");
const svgBtn = document.querySelectorAll(".material-icons>svg");
swiperPrev.style.display = 'none';
let currSubSlide = 1;
swiperNext.addEventListener("click", ()=>{
    if(currSubSlide>=swiperSlide.length-5){
        swiperNext.style.display="none";
        return;
    }
    currSubSlide++;
    if(currSubSlide > 1){
        swiperPrev.style.display = 'block';
    }
})
swiperPrev.addEventListener("click", ()=>{
    if(currSubSlide<=1){
        swiperPrev.style.display="none";
        return;
    }
    currSubSlide--;
    if(currSubSlide < swiperSlide.length-5){
        swiperNext.style.display = 'block';
    }
})