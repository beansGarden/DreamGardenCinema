const rowsPerPage = 10;  // 보여줄 행의 갯수
const maxPageNum = 10;  // 페이지그룹 개수

const rows = document.getElementsByClassName("FAQ-inquiry-content");  // 데이터 행
console.log(rows);
const rowsCount = rows.length;  // 100/8  12.5 -> 13                // 총 행의 개수
const pageCount = Math.ceil(rowsCount / rowsPerPage); // 올림처리     // 총 페이지 개수
const FAQpaginationNumbers = document.querySelector('#FAQpaginationNumbers');  // 전체 페이지 리스트
const arrowLeftBtn = document.querySelector(".fa-arrow-left");  // 왼쪽화살표
const arrowRightBtn = document.querySelector(".fa-arrow-right");  // 오른쪽화살표
let pageActiveIdx = 0; // 현재 보고있는 페이지그룹 번호
let currentPageNum = 0; // 현재 보고 있는 페이지네이션 번호

//페이지네이션 생성
for (let i = 1; i <= pageCount; i++) {
  FAQpaginationNumbers.innerHTML += `<li><a href="">${i}</a></li>`;  // 페이지수 나타낼 HTML 추가
}
const paginationNumberBtn = FAQpaginationNumbers.querySelectorAll('a');  // 페이지네이션 숫자들(a태그)

for (nb of paginationNumberBtn) {  // 페이지네이션 번호 감추기
  nb.style.display = 'none';
}

paginationNumberBtn.forEach((item, idx) => {  // 페이지네이션 전부 중에
  // item = for문 중 현재 요소 / idx = 현재 요소의 인덱스
  item.addEventListener('click', e => {  // 선택 옵션있으면
    e.preventDefault();  // a태그 깜빡거림 없애기
    // 테이블 출력 함수
    displayRow(idx);
  });
});

function displayRow(idx) {
  // let rowsArray = Array.from(rows);
  let start = idx * rowsPerPage;  // 인덱스번호부터
  let end = start + rowsPerPage;  // 보여줄 행의 개수
  let rowsArray = [...rows]; // 데이터 행 배열로 바꾸기(slice 하기 위함)

  for (ra of rowsArray) {  // 전체 행 감추기
    ra.style.display = 'none';
  }

  let newRows = rowsArray.slice(start, end);  // 인덱스 행부터 보여줄 행의 개수 만큼의 배열
  for (nr of newRows) {  // 이만큼만 감춘것 없애기
    nr.style.display = '';
  }
  for (let pnb of paginationNumberBtn) {  // 페이지네이션 전부 active 클래스 제거
    pnb.classList.remove('active');
  }
  paginationNumberBtn[idx].classList.add('active');  // 선택한 페이지네이션만 active 클래스 추가
}  // displayRow 함수 끝
displayRow(0);  // 페이지 새로고침하면 0 첫화면

// 페이지네이션 그룹 표시 함수
function displayPage(num) {
  for (nb of paginationNumberBtn) {  // 페이지네이션 번호 감추기
    nb.style.display = 'none';
  }
  let totalPageCount = Math.ceil(pageCount / maxPageNum);  // 화면에 보여줄 페이지 네이션 수

  let pageArr = [...paginationNumberBtn];  // 페이지네이션 번호 배열로 만들기
  let start = num * maxPageNum;  // 페이지네이션 인덱스번호부터
  let end = start + maxPageNum;  // 보여줄 페이지네이션 갯수
  let pageListArr = pageArr.slice(start, end);  // 이만큼 자른 배열

  for (let item of pageListArr) {  // 페이지네이션 자른 배열들만 보이게 하기
    item.style.display = 'block';
  }
  if (pageActiveIdx == 0) {  // 첫화면이면 화살표 왼쪽 안보이게 오른쪽 보이게
    arrowLeftBtn.style.display = 'none';
  } else {
    arrowLeftBtn.style.display = 'block';
  }

  if (pageActiveIdx == totalPageCount - 1) {  // 마지막 화면이면 화살표 왼쪽 보이게 오른쪽 안보이게
    arrowRightBtn.style.display = 'none';
  } else {
    arrowRightBtn.style.display = 'block';
  }
}
displayPage(0);  // 페이지 새로고침하면 첫 페이지네이션

arrowRightBtn.addEventListener("click", () => {
  let nextPageNum = pageActiveIdx * maxPageNum + maxPageNum;
  displayRow(nextPageNum);
  ++pageActiveIdx;
  displayPage(pageActiveIdx);
});

arrowLeftBtn.addEventListener("click", () => {
  let nextPageNum = pageActiveIdx * maxPageNum - 1;
  displayRow(nextPageNum);
  --pageActiveIdx;
  displayPage(pageActiveIdx);
});  // https://www.youtube.com/watch?v=drXZCq3Y9d8&list=PL-qMANrofLyvzqz2yYzNectJnYo5ZifE7&index=74&ab_channel=Rock%27sEasyweb





/************** 상영관 관련 게시글 목록 출력하기 **************/
function theList() {

  const FAQlistContents = document.querySelector(".FAQ-list-contents");
  const existingFAQItems = document.querySelectorAll(".FAQ-first-box");
  existingFAQItems.forEach((item) => {
    item.remove();
  });

  const FAQCategory = 'P'; // p인 카테고리 타이틀

  fetch("/customerservice/theaterFAQ?FAQCategory=" + FAQCategory)
    .then((resp) => resp.json())
    .then((theaterList) => {
      console.log(theaterList);

      for (let list of theaterList) {
        const firstBox = document.createElement("div");
        firstBox.classList.add("FAQ-first-box");

        const inquiryContent = document.createElement("div");
        inquiryContent.classList.add("FAQ-inquiry-content");

        const categoryTitle = document.createElement("span");
        categoryTitle.classList.add("categoryTitle");
        categoryTitle.innerHTML = list.categoryTitle;
        inquiryContent.appendChild(categoryTitle);

        const contentsContainer = document.createElement("a");
        contentsContainer.classList.add("FAQ-contents-container");

        const Qicon = document.createElement("img");
        Qicon.classList.add("Qicon");
        Qicon.src = "/images/customerservice/Q아이콘.png";
        contentsContainer.appendChild(Qicon);

        const faqTitle = document.createElement("span");
        faqTitle.innerHTML = list.faqtitle;
        contentsContainer.appendChild(faqTitle);

        inquiryContent.appendChild(contentsContainer);

        const FAQBtn = document.createElement("div");
        FAQBtn.classList.add("FAQ-btn");

        const downBtn = document.createElement("img");
        downBtn.classList.add("downBtn");
        downBtn.src = "/images/customerservice/down화살표.png";
        FAQBtn.appendChild(downBtn);

        const upBtn = document.createElement("img");
        upBtn.classList.add("upBtn");
        upBtn.src = "/images/customerservice/up화살표.png";
        FAQBtn.appendChild(upBtn);

        inquiryContent.appendChild(FAQBtn);
        firstBox.appendChild(inquiryContent);

        const answerBox = document.createElement("div");
        answerBox.classList.add("FAQ-answer-box");
        answerBox.style.display = "none";

        const answer = document.createElement("div");
        answer.classList.add("FAQ-answer");

        const Aicon = document.createElement("img");
        Aicon.src = "/images/customerservice/A아이콘.png";
        answer.appendChild(Aicon);

        const pre = document.createElement("pre");
        pre.innerHTML = list.faqcontent;
        answer.appendChild(pre);

        answerBox.appendChild(answer);
        firstBox.appendChild(answerBox);

        FAQlistContents.appendChild(firstBox);
      }

      if (theaterList.length > 0) {

        for (let i = 0; i < theaterList.length; i++) {
          const FAQContents = document.querySelectorAll(".FAQ-contents-container")[i];
          const answerBox = document.getElementsByClassName("FAQ-answer-box")[i];
          const downBtn = document.querySelectorAll(".downBtn")[i];
          const upBtn = document.querySelectorAll(".upBtn")[i];

          console.log(answerBox);
          downBtn.addEventListener("click", () => {
            if (answerBox.style.display === "none") {
              answerBox.style.display = "block";
              downBtn.style.display = "none";
              upBtn.style.display = "block";
            }
          });
          upBtn.addEventListener("click", () => {
            if (answerBox.style.display === "block") {
              answerBox.style.display = "none";
              downBtn.style.display = "block";
              upBtn.style.display = "none";
            }
          });

        }
      }

    })
    .catch((error) => {
      console.error("An error occurred while fetching theater FAQs:", error);
    });
}
/**************************************************************/


/************** 회원 관련 게시글 목록 출력하기 ****************/
function cusList() {

  const FAQlistContents = document.querySelector(".FAQ-list-contents");
  const existingFAQItems = document.querySelectorAll(".FAQ-first-box");
  existingFAQItems.forEach((item) => {
    item.remove();
  });

  const FAQCategory = 'M'; //M인 카테고리 타이틀

  fetch("/customerservice/customerFAQ?FAQCategory=" + FAQCategory)
    .then((resp) => resp.json())
    .then((customList) => {
      console.log(customList);

      for (let cList of customList) {
        const firstBox = document.createElement("div");
        firstBox.classList.add("FAQ-first-box");

        const inquiryContent = document.createElement("div");
        inquiryContent.classList.add("FAQ-inquiry-content");

        const categoryTitle = document.createElement("span");
        categoryTitle.classList.add("categoryTitle");
        categoryTitle.innerHTML = cList.categoryTitle;
        inquiryContent.appendChild(categoryTitle);

        const contentsContainer = document.createElement("a");
        contentsContainer.classList.add("FAQ-contents-container");

        const Qicon = document.createElement("img");
        Qicon.classList.add("Qicon");
        Qicon.src = "/images/customerservice/Q아이콘.png";
        contentsContainer.appendChild(Qicon);

        const faqTitle = document.createElement("span");
        faqTitle.innerHTML = cList.faqtitle;
        contentsContainer.appendChild(faqTitle);

        inquiryContent.appendChild(contentsContainer);

        const FAQBtn = document.createElement("div");
        FAQBtn.classList.add("FAQ-btn");

        const downBtn = document.createElement("img");
        downBtn.classList.add("downBtn");
        downBtn.src = "/images/customerservice/down화살표.png";
        FAQBtn.appendChild(downBtn);

        const upBtn = document.createElement("img");
        upBtn.classList.add("upBtn");
        upBtn.src = "/images/customerservice/up화살표.png";
        FAQBtn.appendChild(upBtn);

        inquiryContent.appendChild(FAQBtn);
        firstBox.appendChild(inquiryContent);

        const answerBox = document.createElement("div");
        answerBox.classList.add("FAQ-answer-box");
        answerBox.style.display = "none";

        const answer = document.createElement("div");
        answer.classList.add("FAQ-answer");

        const Aicon = document.createElement("img");
        Aicon.src = "/images/customerservice/A아이콘.png";
        answer.appendChild(Aicon);

        const pre = document.createElement("pre");
        pre.innerHTML = cList.faqcontent;
        answer.appendChild(pre);

        answerBox.appendChild(answer);
        firstBox.appendChild(answerBox);

        FAQlistContents.appendChild(firstBox);
      }

      if (customList.length > 0) {

        for (let i = 0; i < customList.length; i++) {
          const FAQContents = document.querySelectorAll(".FAQ-contents-container")[i];
          const answerBox = document.getElementsByClassName("FAQ-answer-box")[i];
          const downBtn = document.querySelectorAll(".downBtn")[i];
          const upBtn = document.querySelectorAll(".upBtn")[i];

          console.log(answerBox);
          downBtn.addEventListener("click", () => {
            if (answerBox.style.display === "none") {
              answerBox.style.display = "block";
              downBtn.style.display = "none";
              upBtn.style.display = "block";
            }
          });
          upBtn.addEventListener("click", () => {
            if (answerBox.style.display === "block") {
              answerBox.style.display = "none";
              downBtn.style.display = "block";
              upBtn.style.display = "none";
            }
          });

        }
      }

    })
    .catch((error) => {
      console.error("An error occurred while fetching theater FAQs:", error);
    });
}
/**************************************************************/




/************** 멤버십 관련 게시글 목록 출력하기 **************/
function memList() {

  const FAQlistContents = document.querySelector(".FAQ-list-contents");
  const existingFAQItems = document.querySelectorAll(".FAQ-first-box");
  existingFAQItems.forEach((item) => {
    item.remove();
  });

  const FAQCategory = 'U'; //M인 카테고리 타이틀

  fetch("/customerservice/membershipFAQ?FAQCategory=" + FAQCategory)
    .then((resp) => resp.json())
    .then((membList) => {
      console.log(membList);

      for (let mList of membList) {
        const firstBox = document.createElement("div");
        firstBox.classList.add("FAQ-first-box");

        const inquiryContent = document.createElement("div");
        inquiryContent.classList.add("FAQ-inquiry-content");

        const categoryTitle = document.createElement("span");
        categoryTitle.classList.add("categoryTitle");
        categoryTitle.innerHTML = mList.categoryTitle;
        inquiryContent.appendChild(categoryTitle);

        const contentsContainer = document.createElement("a");
        contentsContainer.classList.add("FAQ-contents-container");

        const Qicon = document.createElement("img");
        Qicon.classList.add("Qicon");
        Qicon.src = "/images/customerservice/Q아이콘.png";
        contentsContainer.appendChild(Qicon);

        const faqTitle = document.createElement("span");
        faqTitle.innerHTML = mList.faqtitle;
        contentsContainer.appendChild(faqTitle);

        inquiryContent.appendChild(contentsContainer);

        const FAQBtn = document.createElement("div");
        FAQBtn.classList.add("FAQ-btn");

        const downBtn = document.createElement("img");
        downBtn.classList.add("downBtn");
        downBtn.src = "/images/customerservice/down화살표.png";
        FAQBtn.appendChild(downBtn);

        const upBtn = document.createElement("img");
        upBtn.classList.add("upBtn");
        upBtn.src = "/images/customerservice/up화살표.png";
        FAQBtn.appendChild(upBtn);

        inquiryContent.appendChild(FAQBtn);
        firstBox.appendChild(inquiryContent);

        const answerBox = document.createElement("div");
        answerBox.classList.add("FAQ-answer-box");
        answerBox.style.display = "none";

        const answer = document.createElement("div");
        answer.classList.add("FAQ-answer");

        const Aicon = document.createElement("img");
        Aicon.src = "/images/customerservice/A아이콘.png";
        answer.appendChild(Aicon);

        const pre = document.createElement("pre");
        pre.innerHTML = mList.faqcontent;
        answer.appendChild(pre);

        answerBox.appendChild(answer);
        firstBox.appendChild(answerBox);

        FAQlistContents.appendChild(firstBox);
      }

      if (membList.length > 0) {

        for (let i = 0; i < membList.length; i++) {
          const FAQContents = document.querySelectorAll(".FAQ-contents-container")[i];
          const answerBox = document.getElementsByClassName("FAQ-answer-box")[i];
          const downBtn = document.querySelectorAll(".downBtn")[i];
          const upBtn = document.querySelectorAll(".upBtn")[i];

          console.log(answerBox);
          downBtn.addEventListener("click", () => {
            if (answerBox.style.display === "none") {
              answerBox.style.display = "block";
              downBtn.style.display = "none";
              upBtn.style.display = "block";
            }
          });
          upBtn.addEventListener("click", () => {
            if (answerBox.style.display === "block") {
              answerBox.style.display = "none";
              downBtn.style.display = "block";
              upBtn.style.display = "none";
            }
          });

        }
      }

    })
    .catch((error) => {
      console.error("An error occurred while fetching theater FAQs:", error);
    });
}
/**************************************************************/


/************** 검색어가 FAQ 제목과 일치하는 목록 출력하기 **************/
const searchArea = document.querySelector(".notice-search-area");
const FAQlistContents = document.querySelector(".FAQ-list-contents");
const searchBtn = document.querySelector(".service-search-btn");

searchBtn.addEventListener("click", (e) => {
  const existingFAQItems = document.querySelectorAll(".FAQ-first-box");
  existingFAQItems.forEach((item) => {
    item.remove();
  });
  e.preventDefault();
  const query = document.getElementById("query").value.trim();

  fetch("/customerservice/searchFAQ?searchQuery=" + query)
    .then(resp => resp.json())
    .then(searchFAQList => {

      console.log(searchFAQList);

      if (searchFAQList.length == 0) {

        const firstBox = document.createElement("div");
        firstBox.classList.add("FAQ-first-box");


        const noneContent = document.createElement("span");
        noneContent.classList.add("divideFAQ");
        noneContent.innerText = "일치하는 검색 결과가 없습니다.";
        noneContent.style.fontWeight = "bold";
        noneContent.style.fontSize = "17px";
        noneContent.style.display = "flex";
        noneContent.style.justifyContent = "center";
        noneContent.style.padding="10px 0px 10px 175px";
        noneContent.style.borderBottom="2px solid #ccc";
        
        

        firstBox.appendChild(noneContent);
        FAQlistContents.appendChild(firstBox);

        // FAQlistContents.innerHTML="";

      } else {

        for (let sList of searchFAQList) {
          const firstBox = document.createElement("div");
          firstBox.classList.add("FAQ-first-box");

          const inquiryContent = document.createElement("div");
          inquiryContent.classList.add("FAQ-inquiry-content");

          const categoryTitle = document.createElement("span");
          categoryTitle.classList.add("categoryTitle");
          categoryTitle.innerHTML = sList.categoryTitle;
          inquiryContent.appendChild(categoryTitle);

          const contentsContainer = document.createElement("a");
          contentsContainer.classList.add("FAQ-contents-container");

          const Qicon = document.createElement("img");
          Qicon.classList.add("Qicon");
          Qicon.src = "/images/customerservice/Q아이콘.png";
          contentsContainer.appendChild(Qicon);

          const faqTitle = document.createElement("span");
          faqTitle.innerHTML = sList.faqtitle;
          contentsContainer.appendChild(faqTitle);

          inquiryContent.appendChild(contentsContainer);

          const FAQBtn = document.createElement("div");
          FAQBtn.classList.add("FAQ-btn");

          const downBtn = document.createElement("img");
          downBtn.classList.add("downBtn");
          downBtn.src = "/images/customerservice/down화살표.png";
          FAQBtn.appendChild(downBtn);

          const upBtn = document.createElement("img");
          upBtn.classList.add("upBtn");
          upBtn.src = "/images/customerservice/up화살표.png";
          FAQBtn.appendChild(upBtn);

          inquiryContent.appendChild(FAQBtn);
          firstBox.appendChild(inquiryContent);

          const answerBox = document.createElement("div");
          answerBox.classList.add("FAQ-answer-box");
          answerBox.style.display = "none";

          const answer = document.createElement("div");
          answer.classList.add("FAQ-answer");

          const Aicon = document.createElement("img");
          Aicon.src = "/images/customerservice/A아이콘.png";
          answer.appendChild(Aicon);

          const pre = document.createElement("pre");
          pre.innerHTML = sList.faqcontent;
          answer.appendChild(pre);

          answerBox.appendChild(answer);
          firstBox.appendChild(answerBox);

          FAQlistContents.appendChild(firstBox);
        
          
        }

        if (searchFAQList.length > 0) {

          for (let i = 0; i < searchFAQList.length; i++) {
            const answerBox = document.getElementsByClassName("FAQ-answer-box")[i];
            const downBtn = document.querySelectorAll(".downBtn")[i];
            const upBtn = document.querySelectorAll(".upBtn")[i];

            console.log(answerBox);
            downBtn.addEventListener("click", () => {
              if (answerBox.style.display === "none") {
                answerBox.style.display = "block";
                downBtn.style.display = "none";
                upBtn.style.display = "block";
              }
            });
            upBtn.addEventListener("click", () => {
              if (answerBox.style.display === "block") {
                answerBox.style.display = "none";
                downBtn.style.display = "block";
                upBtn.style.display = "none";
              }
            });

          }
        }
        
        // FAQlistContents.innerHTML = "";

      }

    })

})
/************************************************************************/

// 상영관 아이콘 애니메이션
const reel1 = document.getElementById("reel-1");
const reel2 = document.getElementById("reel-2");

const reel = document.getElementById("camera-loader");
const transform = document.getElementsByClassName("transform");
reel.addEventListener("mouseover", ()=>{
  
  reel1.innerHTML = `<animateTransform attributeName="transform" attributeType="XML" type="rotate"
  dur="1.7s" begin="0" from="0 17.66 22.74" to="360 17.66 22.74"
  repeatCount="indefinite" class="transform"/>`
  
  reel2.innerHTML = `<animateTransform attributeName="transform" attributeType="XML" type="rotate"
  dur="1s" begin="0" from="0 51.4 17.7" to="360 51.4 17.7"
  repeatCount="indefinite" class="transform"/>`
  
})
reel.addEventListener("mouseout", ()=>{
  
  reel1.innerHTML = `<animateTransform attributeName="transform" attributeType="XML" type="rotate"
  dur="0" begin="0" from="0 17.66 22.74" to="360 17.66 22.74"
  repeatCount="indefinite" class="transform"/>`
  
  reel2.innerHTML = `<animateTransform attributeName="transform" attributeType="XML" type="rotate"
  dur="0" begin="0" from="0 51.4 17.7" to="360 51.4 17.7"
  repeatCount="indefinite" class="transform"/>`
  
})

/************************************************************************/

// 멤버십 아이콘 애니메이션

var crown = document.querySelector("#crown");
// var container = document.querySelector(".container");

crown.addEventListener("mouseover", () => {
  crown.style.animationDuration = "450ms";
});


// crown.addEventListener("mouseout", () => {
//   crown.style.animation = "end_image 0s linear infinite";
//   crown.style.transformOrigin = "50% 50%";
// });

/************************************************************************/





















