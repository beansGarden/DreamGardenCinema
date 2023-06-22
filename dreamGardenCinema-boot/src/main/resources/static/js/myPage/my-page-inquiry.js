/* 내정보 변경 */

/* 내 정보 변경 클릭 시 모달 */
const my_page_myinfo = document.querySelector(".my-page-myinfo");
const my_page_changeinfo_modal_area=document.querySelector(".my-page-changeinfo-modal-area");
const my_page_changeinfo_update_btn2 = document.querySelector(".my-page-changeinfo-update-btn>:nth-child(2)");

my_page_myinfo.addEventListener("click",()=>{

    my_page_changeinfo_modal_area.style.display = "flex";

    document.body.style.overflow='hidden';

})

my_page_changeinfo_update_btn2.addEventListener("click",()=>{

    my_page_changeinfo_modal_area.style.display = "none";

    document.body.style.overflow='auto';

})
/* 주소 검색 (다음API) */
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
        }
    }).open();
}

/* 닉네임 변경 클릭 */
const my_page_myprofile = document.querySelector(".my-page-myprofile");
const my_page_change_nickname_area = document.querySelector(".my-page-change-nickname-area");
const my_page_change_nickname_update_btn2 = document.querySelector(".my-page-change-nickname-update-btn>:nth-child(2)");


my_page_myprofile.addEventListener("click",()=>{

  my_page_change_nickname_area.style.display = "flex";

  document.body.style.overflow='hidden';

})

/* 취소 버튼 */
my_page_change_nickname_update_btn2.addEventListener("click",()=>{

  my_page_change_nickname_area.style.display = "none";

  document.body.style.overflow='auto';

})


/* 회원 탈퇴 모달 영역 */
const my_page_secession_btn = document.querySelector("#my-page-secession");
const my_page_secession_area = document.querySelector(".my-page-secession-area");
const my_page_secession_pwcheck_btn2 = document.querySelector(".my-page-secession-pwcheck-btn>:nth-child(2)")
/* 회원 탈퇴 nav 클릭 시 */
my_page_secession_btn.addEventListener("click",()=>{

    my_page_secession_area.style.display = "flex";

    document.body.style.overflow='hidden';
})

my_page_secession_pwcheck_btn2.addEventListener("click",()=>{

    my_page_secession_area.style.display = "none";

    document.body.style.overflow='auto';

})

/* 회원 탈퇴 다음 버튼 클릭 시 화면 */
/* 탈퇴 버튼 */
const my_page_secession_pwcheck_btn = document.querySelector(".my-page-secession-pwcheck-btn>:nth-child(1)");
/* 취소 버튼 */
const my_page_secession_btn2 = document.querySelector(".my-page-secession-btn>:nth-child(2)");

const my_page_secession_pwcheck_container = document.querySelector(".my-page-secession-pwcheck-container");
const my_page_secession_info_bg = document.querySelector(".my-page-secession-info-bg");

my_page_secession_pwcheck_btn.addEventListener("click",()=>{
    my_page_secession_pwcheck_container.style.display="none";

    my_page_secession_info_bg.style.display = "flex";

    document.body.style.overflow='hidden';

})
/* 취소 버튼 클릭 시 */
my_page_secession_btn2.addEventListener("click",()=>{

    my_page_secession_area.style.display = "none";

    my_page_secession_pwcheck_container.style.display="flex";

    my_page_secession_info_bg.style.display = "none";

    document.body.style.overflow='auto';

})
/* 문의 목록 UP/DOWN */
const up_down_img = document.querySelector("#up-down-img");
let my_page_inquiry_table_display = document.querySelectorAll(".my-page-inquiry-table-display");
const my_page_inquiry_content = document.querySelector("#my-page-inquiry-content");

up_down_img.addEventListener("click",()=>{

  my_page_inquiry_table_display[0].classList.toggle("my-page-inquiry-table-display");
  my_page_inquiry_table_display[1].classList.toggle("my-page-inquiry-table-display");

  if (up_down_img.src.includes("upbtn.png")) {
    up_down_img.src = "/images/myPage/downbtn.png";
  } else {
    up_down_img.src = "/images/myPage/upbtn.png";
  }

})

/* 닉네임 수정 창에서 수정 버튼 클릭 시 */
const changeBtn = document.querySelector("#changeBtn")
const change_nickname_form = document.querySelector("#change-nickname-form")

changeBtn.addEventListener("click",()=>{
    if(confirm("닉네임을 변경하시겠습니까?")){
        change_nickname_form.submit()

        alert("닉네임이 변경되었습니다")

    }else{
        alert("취소되었습니다")
    }
})
/* 내 정보 변경 비밀번호 일치 여부 */
const userPw = document.querySelector("#userPw")
const line1 = document.querySelector("#line1")
const loginUserPw = document.querySelector("#loginUserPw").value

/* 현재 비밀번호 확인 영역 */
userPw.addEventListener("input",()=>{
  if(userPw.value == loginUserPw){
      line1.classList.add("clear")
      line1.classList.remove("line1")
      line1.classList.remove("error")
  }else{
      line1.classList.add("error")
      line1.classList.remove("line1")
      line1.classList.remove("clear")
  }
})
userPw.addEventListener("focus", () => {
  line1.classList.remove("error");
  line1.classList.remove("clear");
  line1.classList.add("line1");
});

const line2 = document.querySelectorAll("#line2")
const newPw = document.querySelector("#newPw")
const checkPw = document.querySelector("#checkPw")
/* 새 비밀번호 확인 영역 */

checkPw.addEventListener("input", () => {
  const newPwValue = newPw.value;
  const checkPwValue = checkPw.value;
  const regex = /^(?=.*[a-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-z\d$@$!%*#?&]{8,}$/;

  if (regex.test(newPwValue) && newPwValue === checkPwValue) {
    Array.from(line2).forEach((element) => {
      element.classList.add("clear");
      element.classList.remove("line2");
      element.classList.remove("error");
    });
  } else {
    Array.from(line2).forEach((element) => {
      element.classList.add("error");
      element.classList.remove("line2");
      element.classList.remove("clear");
    });
  }
});
newPw.addEventListener("input", () => {
  const newPwValue = newPw.value;
  const checkPwValue = checkPw.value;

  if (newPwValue === checkPwValue) {
    Array.from(line2).forEach((element) => {
      element.classList.add("clear");
      element.classList.remove("line2");
      element.classList.remove("error");
    });
  } else {
    Array.from(line2).forEach((element) => {
      element.classList.add("error");
      element.classList.remove("line2");
      element.classList.remove("clear");
    });
  }
});

newPw.addEventListener("focus", () => {
  Array.from(line2).forEach((element) => {
    element.classList.remove("error");
    element.classList.remove("clear");
    element.classList.add("line2");
  });
});

checkPw.addEventListener("focus", () => {
  Array.from(line2).forEach((element) => {
    element.classList.remove("error");
    element.classList.remove("clear");
    element.classList.add("line2");
  });
});





/* ***** 페이지 네이션 부분 ***** */
const rowsPerPage = 10;  // 보여줄 행의 갯수
const maxPageNum = 10;  // 페이지그룹 개수

const rows = document.getElementsByClassName("my-page-inquiry-content");  // 데이터 행
console.log(rows);
const rowsCount = rows.length;  // 100/8  12.5 -> 13                // 총 행의 개수
const pageCount = Math.ceil(rowsCount/rowsPerPage); // 올림처리     // 총 페이지 개수
const paginationNumbers = document.querySelector('#paginationNumbers');  // 전체 페이지 리스트
const arrowLeftBtn = document.querySelector(".fa-arrow-left");  // 왼쪽화살표
const arrowRightBtn = document.querySelector(".fa-arrow-right");  // 오른쪽화살표
let pageActiveIdx = 0; // 현재 보고있는 페이지그룹 번호
let currentPageNum = 0; // 현재 보고 있는 페이지네이션 번호

//페이지네이션 생성
for(let i=1;i<=pageCount;i++){
  paginationNumbers.innerHTML += `<li><a href="">${i}</a></li>`;  // 페이지수 나타낼 HTML 추가
}
const paginationNumberBtn = paginationNumbers.querySelectorAll('a');  // 페이지네이션 숫자들(a태그)

for(nb of paginationNumberBtn){  // 페이지네이션 번호 감추기
    nb.style.display = 'none';
}

paginationNumberBtn.forEach((item,idx)=>{  // 페이지네이션 전부 중에
                          // item = for문 중 현재 요소 / idx = 현재 요소의 인덱스
  item.addEventListener('click', e=>{  // 선택 옵션있으면
    e.preventDefault();  // a태그 깜빡거림 없애기
    // 테이블 출력 함수
    displayRow(idx);
    });
});

function displayRow(idx){
  // let rowsArray = Array.from(rows);
  let start = idx*rowsPerPage;  // 인덱스번호부터
  let end = start+rowsPerPage;  // 보여줄 행의 개수
  let rowsArray = [...rows]; // 데이터 행 배열로 바꾸기(slice 하기 위함)
    
  for(ra of rowsArray){  // 전체 행 감추기
    ra.style.display = 'none';
    }

  let newRows = rowsArray.slice(start,end);  // 인덱스 행부터 보여줄 행의 개수 만큼의 배열
  for(nr of newRows){  // 이만큼만 감춘것 없애기
    nr.style.display = '';
    }
  for(let pnb of paginationNumberBtn){  // 페이지네이션 전부 active 클래스 제거
    pnb.classList.remove('active');
    }
  paginationNumberBtn[idx].classList.add('active');  // 선택한 페이지네이션만 active 클래스 추가
}  // displayRow 함수 끝
displayRow(0);  // 페이지 새로고침하면 0 첫화면

// 페이지네이션 그룹 표시 함수
function displayPage(num){
  for(nb of paginationNumberBtn){  // 페이지네이션 번호 감추기
    nb.style.display = 'none';
    }
  let totalPageCount = Math.ceil(pageCount/maxPageNum);  // 화면에 보여줄 페이지 네이션 수

  let pageArr = [...paginationNumberBtn];  // 페이지네이션 번호 배열로 만들기
  let start = num*maxPageNum;  // 페이지네이션 인덱스번호부터
  let end = start+maxPageNum;  // 보여줄 페이지네이션 갯수
  let pageListArr = pageArr.slice(start, end);  // 이만큼 자른 배열

  for(let item of pageListArr){  // 페이지네이션 자른 배열들만 보이게 하기
    item.style.display = 'block';
    }
  if(pageActiveIdx == 0){  // 첫화면이면 화살표 왼쪽 안보이게 오른쪽 보이게
    arrowLeftBtn.style.display = 'none';
    } else{
    arrowLeftBtn.style.display = 'block';
    }

  if(pageActiveIdx == totalPageCount-1){  // 마지막 화면이면 화살표 왼쪽 보이게 오른쪽 안보이게
    arrowRightBtn.style.display = 'none';
    } else{
    arrowRightBtn.style.display = 'block';
    }
}
displayPage(0);  // 페이지 새로고침하면 첫 페이지네이션

arrowRightBtn.addEventListener("click",()=>{
    let nextPageNum = pageActiveIdx*maxPageNum+maxPageNum;
    displayRow(nextPageNum);
    ++pageActiveIdx;
    displayPage(pageActiveIdx);
});

arrowLeftBtn.addEventListener("click",()=>{
    let nextPageNum = pageActiveIdx*maxPageNum-1;
    displayRow(nextPageNum);
    --pageActiveIdx;
    displayPage(pageActiveIdx);
});  // https://www.youtube.com/watch?v=drXZCq3Y9d8&list=PL-qMANrofLyvzqz2yYzNectJnYo5ZifE7&index=74&ab_channel=Rock%27sEasyweb


