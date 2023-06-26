/* 글자수 세기 */
// 제목
const QATitleCount = document.getElementById('QATitleCount');
const customerQuestionTitle = document.getElementById('customerQuestionTitle');

// 내용
const QAContentsCount = document.getElementById('QAContentsCount');
const QADetailContents = document.getElementById('QADetailContents');

// 글자수 카운트(제목)
customerQuestionTitle.addEventListener("input", () => {

    const count = customerQuestionTitle.value.length;
    QATitleCount.innerHTML = count;

});

// 글자수 카운트(내용)
QADetailContents.addEventListener("input", () => {

    const count = QADetailContents.value.length;
    QAContentsCount.innerHTML = count;

});

/******************************* 고객정보 입력란 ********************************/
const QAmemChkBox = document.querySelector("#QAmemChkBox");
const QACusInfo = document.querySelector(".QA-cus-info-hidden");

QAmemChkBox.addEventListener("change", () => {
    if (QAmemChkBox.checked) {
        QACusInfo.style.display = "block";
    } else {
        QACusInfo.style.display = "none";
    }

});
/*******************************************************************************/

// 이미지 업로드
// const insertImage = document.getElementById("QAInsertFile");

// for(let i=0; i<insertImage.length; i++){

// }

/* 입력 여부 확인 */
const QAfrm = document.querySelector("#QAfrm");
const cusQATitle = document.querySelector("#customerQuestionTitle");
const cusQAContent = document.querySelector("#QADetailContents");
const QASelection = document.querySelector("#QASelection");
const cusName = document.querySelector("#cusName");
const cusTel = document.querySelector("#cusTel");
const QASelectNum = document.querySelector("#QASelectNum");


QAfrm.addEventListener("submit", (e) => {

    if (cusQATitle.length.trim() == 0) {
        alert("제목을 입력해주세요");
        cusQATitle.value = "";
        cusQATitle.focus();
        e.preventDefault();
        return;
    }
    if (cusQAContent.length.trim() == 0) {
        alert("내용을 입력해주세요");
        cusQAContent.value = "";
        cusQAContent.focus();
        e.preventDefault();
        return;
    }

    if (!QASelection.value) {
        alert("옵션을 선택 해주세요");
        cusQAContent.focus();
        e.preventDefault();
        return;
    }

    // 비회원 여부 체크박스가 체크 되었을때(비회원)
    if (QAmemChkBox.checked && cusName.length.trim() == 0) {
        alert("성함을 입력해주세요");
        cusName.value = "";
        cusName.focus();
        e.preventDefault();
        return;
    }

    if (QAmemChkBox.checked && !QASelectNum.value) {
        alert("연락처 옵션을 선택 해주세요");
        QASelectNum.focus();
        e.preventDefault();
        return;
    }

    if (QAmemChkBox.checked && cusTel.length.trim() == 0) {
        alert("전화번호를 입력해주세요");
        cusTel.value = "";
        cusTel.focus();
        e.preventDefault();
        return;
    }

})


// 전화번호에 숫자만 입력할 수 있게하기
const cusTelInput = document.querySelector("#cusTel");

cusTelInput.addEventListener("input", () => {
    // 입력된 값에서 숫자 이외의 문자 제거
    const sanitizedValue = cusTelInput.value.replace(/\D/g, "");
    // 정규표현식 사용 대문자 'D'는 숫자를 의미
    cusTelInput.value = sanitizedValue;
});

