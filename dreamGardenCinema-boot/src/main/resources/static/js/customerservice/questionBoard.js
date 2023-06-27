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


/* 입력 여부 확인 */
const QAfrm = document.querySelector("#QAfrm");
const cusQATitle = document.querySelector("[name='qnaTitle']");
const cusQAContent = document.querySelector("#QADetailContents");
const QASelection = document.querySelector("#QASelection");
const cusName = document.querySelector("#cusName");
const cusTel = document.querySelector("#cusTel");
const QASelectNum = document.querySelector("#QASelectNum");
const fileInput = document.querySelector("#QAInsertFile");
const QAEmail = document.querySelector("#QAEmail");
const QAAgree = document.querySelector("[name='QAAgree']");


QAfrm.addEventListener("submit", e => {
    console.log(QASelection.value);

    if (!QASelection.value) {
        alert("분류를 선택해주세요");
        QASelection.focus();
        e.preventDefault();
        return;
    }

    if (cusQATitle.value.trim().length == 0) {
        alert("제목을 입력해주세요");
        cusQATitle.value = "";
        cusQATitle.focus();
        e.preventDefault();
        return;
    }

    if (cusQAContent.value.trim().length == 0) {
        alert("내용을 입력해주세요");
        cusQAContent.value = "";
        cusQAContent.focus();
        e.preventDefault();
        return;
    }

    if (fileInput.files.length === 0) {
        alert("파일을 선택해주세요");
        fileInput.focus();
        e.preventDefault();
        return;
    }


    // 비회원 여부 체크박스가 체크되었을 때(비회원)
    if (QAmemChkBox.checked && cusName.value.trim().length == 0) {
        alert("성함을 입력해주세요");
        cusName.value = "";
        cusName.focus();
        e.preventDefault();
        return;
    }

    if (QAmemChkBox.checked && !QASelectNum.value) {
        alert("연락처 옵션을 선택해주세요");
        QASelectNum.focus();
        e.preventDefault();
        return;
    }

    if (QAmemChkBox.checked && cusTel.value.trim().length == 0) {
        alert("전화번호를 입력해주세요");
        cusTel.value = "";
        cusTel.focus();
        e.preventDefault();
        return;
    }

    if (QAmemChkBox.checked && QAEmail.value.trim().length == 0) {
        alert("이메일을 입력해주세요");
        QAEmail.value = "";
        QAEmail.focus();
        e.preventDefault();
        return;
    }

    if (!QAAgree.checked) {
        alert("개인정보활용 동의 체크란을 확인해 주세요");
        QAAgree.value = "";
        QAAgree.focus();
        e.preventDefault();
        return;
    }


});


// 전화번호에 숫자만 입력할 수 있게하기
const cusTelInput = document.querySelector("#cusTel");

cusTelInput.addEventListener("input", () => {
    // 입력된 값에서 숫자 이외의 문자 제거
    const sanitizedValue = cusTelInput.value.replace(/\D/g, "");
    // 정규표현식 사용 대문자 'D'는 숫자를 의미
    cusTelInput.value = sanitizedValue;
});

// 이메일 유효성 검사

QAEmail.addEventListener("input", () => {
    const QAEmailInput = QAEmail.value.trim();
    const regEx = /^[A-Za-z\d\-\_]{4,}@[가-힣\w\-\_]+(\.\w+){1,3}$/;

    if (QAEmailInput.length > 0 && regEx.test(QAEmailInput)) {
        QAEmail.style.color = "blue";
    } else {
        QAEmail.style.color = "red";
    }
});



