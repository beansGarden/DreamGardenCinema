// 탭
$(document).ready(function(){

    $('ul.account-tabs li').click(function(){
    var tab_id = $(this).attr('data-tab');

    $('ul.account-tabs li').removeClass('current');
    $('.tab-content').removeClass('current');

    $(this).addClass('current');
    $("#"+tab_id).addClass('current');
    })

})
const movePwFind = document.getElementById("movePwFind");
const idFindTab = document.querySelector('[data-tab="idFind"]');
const pwFindTab = document.querySelector('[data-tab="pwFind"]');
const idFindBox = document.getElementById("idFind")
const pwFindBox = document.getElementById("pwFind")
if (movePwFind != null) {
    movePwFind.addEventListener("click", function (e) {
        idFindTab.classList.remove("current");
        idFindBox.classList.remove("current");
        pwFindTab.classList.add("current");
        pwFindBox.classList.add("current");
    });
}

// input span 애니메이션
const inputs = document.querySelectorAll('input');

inputs.forEach(input => {
    input.addEventListener('focus', addClass);
    input.addEventListener('input', addClass);
    input.addEventListener('blur', removeClass);
});

function addClass(event) {
    const input = event.target;
    const span = input.nextElementSibling;
    if (span.tagName.toLowerCase() === "span") {
    span.classList.remove('output-font-size-ani');
    span.classList.add('input-font-size-ani');
    }
}

function removeClass(event) {
    const input = event.target;
    const span = input.nextElementSibling;
    if (input.value.trim() === '' && span.tagName.toLowerCase() === "span") {
        span.classList.remove('input-font-size-ani');
        span.classList.add('output-font-size-ani');
    }
}
// ----------------------------------------------- 아이디 찾기 -----------------------------------------------
const userBirth1 = document.getElementById("userBirth1");
const userTel = document.getElementById("userTel");
const resultId = document.getElementById("resultId");
const moveLogin = document.getElementById("moveLogin");

const userTelSpan = document.getElementById("userTelSpan");
const userBirth1Span = document.getElementById("userBirth1Span");
const idFindBtn = document.getElementById("idFindBtn");
const idFindMove = document.getElementById("idFindMove");

if (idFindBtn != null) {
    idFindBtn.addEventListener("click", function (e) {

        if (userBirth1.value.trim().length == 0 ) {
            userBirth1.value = "";
            return;
        }
        if (userTel.value.trim().length == 0 ) {
            userTel.value = "";
            return;
        }
        const regEx = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
        // 영문자로 시작하는 영문자 또는 숫자 5~20자 
        if (validateDateOfBirth(userBirth1.value) && regEx.test(userTel.value)) {

            const queryParams = new URLSearchParams();
            queryParams.append('userBirth1', userBirth1.value);
            queryParams.append('userTel', userTel.value);
            console.log(userBirth1.value);
            console.log(userTel.value);

            fetch('/dupCheck/idFind?' + queryParams)// 중복 체크
                .then(response => response.text())
                .then(userId => {
                    if (userId != "") {
                        resultId.innerText = 
                        "회원님의 아이디는\u00a0\u00a0\u00a0\u00a0" + userId + "\u00a0\u00a0\u00a0\u00a0입니다.";
                        console.log(userId);
                        userBirth1.classList.add("display-none")
                        userTel.classList.add("display-none")
                        userTelSpan.classList.add("display-none")
                        userBirth1Span.classList.add("display-none")
                        idFindBtn.classList.add("display-none")
                        
                        resultId.classList.remove("display-none")
                        moveLogin.classList.remove("display-none")
                        movePwFind.classList.remove("display-none")

                    } else {
                        resultId.innerText = "일치하는 아이디가 없습니다.";
                        userBirth1.classList.add("display-none")
                        userTel.classList.add("display-none")
                        userTelSpan.classList.add("display-none")
                        userBirth1Span.classList.add("display-none")
                        idFindBtn.classList.add("display-none")
                        
                        resultId.classList.remove("display-none")
                        idFindMove.classList.remove("display-none")
                    }
                })
                .catch(err => console.log(err));
        } else { // 유효하지 않은 경우(무효인 경우)
            alert("생년월일 또는 휴대폰 번호를 입력해주세요.");
        }
    });
}

function validateDateOfBirth(dateStr) {
    // 날짜 형식에 맞는지 검사 (YYYYMMDD)
    let pattern = /^(19\d{2}|20\d{2})(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])$/;
    if (!pattern.test(dateStr)) {
        return false;
    }

    // 월과 일을 추출
    let year = parseInt(dateStr.slice(0, 4));
    let month = parseInt(dateStr.slice(4, 6));
    let day = parseInt(dateStr.slice(6));

    // 월별 일수 체크
    let daysInMonth;
    if (month === 2) {  // 2월
        if ((year % 4 === 0 && year % 100 !== 0) || year % 400 === 0) {
            daysInMonth = 29;
        } else {
            daysInMonth = 28;
        }
    } else if ([4, 6, 9, 11].includes(month)) {  // 4, 6, 9, 11월
        daysInMonth = 30;
    } else {  // 나머지 월
        daysInMonth = 31;
    }
    return day <= daysInMonth;
}
userTel.addEventListener("input", function () {
    this.value = this.value.replace(/\D/g, ""); // 숫자 이외의 값 제거
});
userBirth1.addEventListener("input", function () {
    this.value = this.value.replace(/\D/g, ""); // 숫자 이외의 값 제거
});

// ----------------------------------------------- 비밀번호 찾기 -----------------------------------------------
const sendAuthKeyBtn = document.getElementById("sendAuthKeyBtn");
const pwFindBtn = document.getElementById("pwFindBtn");

const authKeyLifeTime = document.getElementById("authKeyLifeTime");
const authKey = document.getElementById("authKey");
const authKeyResend = document.querySelector(".authKeyResend");

authKey.addEventListener("input", function () {
    this.value = this.value.replace(/\D/g, ""); // 숫자 이외의 값 제거
});
userTel.addEventListener("input", function () {
    this.value = this.value.replace(/\D/g, ""); // 숫자 이외의 값 제거
});
let timeRemaining = 5 * 60;
let buttonClicked = false;
let interval;

if (sendAuthKeyBtn != null) {
    sendAuthKeyBtn.addEventListener("click", function (e) {
        sendAuthKeyButtonClick(sendAuthKeyBtn);
    });
}
if (authKeyResend != null) {
    authKeyResend.addEventListener("click", function (e) {
        sendAuthKeyButtonClick(authKeyResend);
        authKeyResend.classList.remove("display-none");
    });
}

function sendAuthKeyButtonClick(sendAuthKeyBtn) {
    if (interval) {
        clearInterval(interval);
        interval = null;
    }
console.log(userTel.value);
    if (userTel.value) {
        authKeyLifeTime.classList.remove("display-none");
        authKeyLifeTime.classList.add("authKey-green-text");
        authKeyLifeTime.classList.remove("authKey-red-text");
        authKeyResend.classList.remove("display-none");

        sendAuthKeyBtn.classList.add("display-none");
        signBtn.classList.remove("display-none");
        let data = {
            userTel: userTel.value // userTel 값을 객체에 담기
        };

        /* fetch() API 방식 ajax */
        // fetch("/user/sms/send?userTel=" + userTel.value, {
        //     method: "POST",
        //     headers: {
        //         "Content-Type": "application/json"
        //     },
        //     body: JSON.stringify(data) // JSON 형식으로 변환하여 body에 담기
        // })
        //     .then(resp => resp.json())
        //     .then(result => {
        //         if (result.statusCode == 202) {
        //             console.log("인증번호 발송 성공");
        //             console.log(result);
        //         } else {
        //             console.log("인증번호 발송 실패");
        //             console.log(result);
        //         }
        //     })
        //     .catch(err => {
        //         console.log("이메일 발송 중 에러 발생");
        //         console.log(err);
        //     });

        alert("인증번호가 발송 되었습니다.");
        timeRemaining = 5 * 60;
        startCountdown();
    } else {
        alert("휴대폰 번호를 작성해주세요.");
        userTel.focus();
    }
}
function startCountdown() {
    interval = setInterval(function () {
        // 분과 초 계산
        const minutes = Math.floor(timeRemaining / 60).toString().padStart(2, '0');
        const seconds = (timeRemaining % 60).toString().padStart(2, '0');

        // 텍스트 업데이트
        authKeyLifeTime.innerText = `${minutes}:${seconds}`;

        // 시간 감소
        timeRemaining--;

        // 시간이 0보다 작거나 같으면 인터벌 종료
        if (timeRemaining < 0) {
            clearInterval(interval);
            authKeyLifeTime.innerText = "00:00";
            authKeyLifeTime.classList.remove("authKey-green-text");
            authKeyLifeTime.classList.add("authKey-red-text");
            interval = null;
        }
    }, 1000);
}