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

// 에러아이콘 추가 함수
function addUserErrIcon(parentElementClass) {
    const nullInputIconBox = document.createElement("div");
    nullInputIconBox.classList.add("userErrIcon");
    const svgCode = '<svg class="null-input-icon" fill="#d7373f" xmlns="http://www.w3.org/2000/svg" height="20" viewBox="0 -960 960 960" width="20"><path d="m40-120 440-760 440 760H40Zm104-60h672L480-760 144-180Zm340.175-57q12.825 0 21.325-8.675 8.5-8.676 8.5-21.5 0-12.825-8.675-21.325-8.676-8.5-21.5-8.5-12.825 0-21.325 8.675-8.5 8.676-8.5 21.5 0 12.825 8.675 21.325 8.676 8.5 21.5 8.5ZM454-348h60v-224h-60v224Zm26-122Z"/></svg>';
    nullInputIconBox.innerHTML = svgCode;
    const headerSearchModContainer = document.getElementById(parentElementClass);
    headerSearchModContainer.parentNode.insertBefore(nullInputIconBox, headerSearchModContainer.nextSibling);
}
// 에러아이콘 제거 함수
function removeUserErrIcon(parentQuerySelector) {
    let inputWrapper = document.querySelector(parentQuerySelector);
    if (inputWrapper) {
        let errIcon = inputWrapper.querySelector('.userErrIcon');
        if (errIcon) {
            errIcon.parentNode.removeChild(errIcon);
        }
    }
}
// input container 에러아이콘 유무 체크 함수
function checkUserErrIcon(classUserInput) {
    // checkUserErrIcon(classUserEmail); // 사용 예시
    const checkUserErrIcon = classUserInput.querySelector('.userErrIcon');
    if (checkUserErrIcon != null) {
        // console.log("에러아이콘 있음");
        return true;
    } else {
        // console.log("에러아이콘 없음");
        return false;
    }
}


/* 유효성 검사 진행 여부 확인용 객체 */
// -> 모든 value가 true인 경우에만 회원 가입 진행
const checkObj = {
    "userId": false,
    "userPw": false,
    "userRePw": false,
    "userNickname": false,
    "userTel": false,
    "userEmail": false,
    "userBirth1": false,
    "userGender": false
};
/* input container class */
const classUserId = document.querySelector('.user-id');
const classUserPw = document.querySelector('.user-pw');
const classUserRePw = document.querySelector('.user-re-pw');
const classUserEmail = document.querySelector('.user-email');
const classUserNickname = document.querySelector('.user-nickname');
const classUserBirth = document.querySelector('.user-birth1');
const classUserGender = document.querySelector('.gender-box');
const classUserTel = document.querySelector('.user-tel');
const classUserAuthKey = document.querySelector('.auth-key');

const userErrIcon = document.getElementById("userErrIcon");

// 이메일 오류뜰때 계속해서 아이콘 생기는거 방지 진행중///////////////////

// ----------------------------------------------- 아이디 유효성 검사 -----------------------------------------------
const userId = document.getElementById("userId");

if (userId != null) {

    userId.addEventListener("input", () => {

        if (userId.value.trim().length == 0) {
            userId.value = "";

            userId.classList.add("err-input-border");
            if (checkUserErrIcon(classUserId) == false) { addUserErrIcon("userId"); }
            checkObj.userId = false;
            return;
        }

        const regEx = /^[A-Za-z]+[A-Za-z0-9]{4,19}$/;
        // 영문자로 시작하는 영문자 또는 숫자 5~20자 
        if (regEx.test(userId.value)) {
            fetch('/dupCheck/id?id=' + userId.value)// 중복 체크
                .then(response => response.text())
                .then(count => {
                    if (count == 0) {
                        userId.classList.add("check-input-border");
                        userId.classList.remove("err-input-border");
                        removeUserErrIcon(".user-id");

                        checkObj.userId = true;
                    } else {
                        userId.classList.add("err-input-border");
                        userId.classList.remove("check-input-border");
                        if (checkUserErrIcon(classUserId) == false) { addUserErrIcon("userId"); }

                        checkObj.userId = false;
                    }
                })
                .catch(err => console.log(err));
        } else { // 유효하지 않은 경우(무효인 경우)
            userId.classList.add("err-input-border");
            userId.classList.remove("check-input-border");
            if (checkUserErrIcon(classUserId) == false) { addUserErrIcon("userId"); }
            checkObj.userId = false;
        }
    });
}
// ----------------------------------------------- 아이디 유효성 검사 끝 -----------------------------------------------
// ----------------------------------------------- 이메일 유효성 검사 -----------------------------------------------
const userEmail = document.getElementById("userEmail");

if (userEmail != null) {

    userEmail.addEventListener("input", () => {

        if (userEmail.value.trim().length == 0) {
            userEmail.value = "";

            userEmail.classList.add("err-input-border");
            if (checkUserErrIcon(classUserEmail) == false) { addUserErrIcon("userEmail"); }
            checkObj.userEmail = false;
            return;
        }

        const regEx = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

        if (regEx.test(userEmail.value)) {

            fetch('/dupCheck/email?email=' + userEmail.value)// 중복 체크
                .then(response => response.text())
                .then(count => {
                    if (count == 0) {
                        userEmail.classList.add("check-input-border");
                        userEmail.classList.remove("err-input-border");
                        removeUserErrIcon(".user-email");

                        checkObj.userEmail = true;
                    } else {
                        userEmail.classList.add("err-input-border");
                        userEmail.classList.remove("check-input-border");
                        if (checkUserErrIcon(classUserEmail) == false) { addUserErrIcon("userEmail"); }

                        checkObj.userEmail = false;
                    }
                })
                .catch(err => console.log(err));
        } else { // 유효하지 않은 경우(무효인 경우)
            userEmail.classList.add("err-input-border");
            userEmail.classList.remove("check-input-border");
            if (checkUserErrIcon(classUserEmail) == false) { addUserErrIcon("userEmail"); }
            checkObj.userEmail = false;
        }
    });
}
// ----------------------------------------------- 이메일 유효성 검사 끝 -----------------------------------------------
// ----------------------------------------------- 비밀번호 유효성 검사 -----------------------------------------------
const userPw = document.getElementById("userPw");
const userRePw = document.getElementById("userRePw");

if (userPw != null || userRePw != null) {
    userPw.addEventListener("input", (e) => {

        if (userPw.value.trim().length == 0) {
            userPw.value = "";

            userPw.classList.add("err-input-border");
            if (checkUserErrIcon(classUserPw) == false) { addUserErrIcon("userPw"); }
            checkObj.userPw = false;
            return;
        }
        const regEx = /^(?=.*[a-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-z\d$@$!%*#?&]{8,}$/;
        // 8~ 영문 소문자, 최소 1개의 숫자 혹은 특수 문자 포함

        if (regEx.test(userPw.value)) {
            checkObj.userPw = true;

            // 비밀번호가 유효하게 작성된 상태에서
            // 비밀번호 확인이 입력되지 않았을 때
            if (userRePw.value.trim().length == 0 || userRePw.value.trim().length != 0) {
                userPw.classList.add("check-input-border");
                userPw.classList.remove("err-input-border");
                removeUserErrIcon(".user-pw");
            } else {
                // 비밀번호가 유효하게 작성된 상태에서
                // 비밀번호 확인이 입력되어 있을 때
                // 비밀번호 == 비밀번호 확인  (같을 경우)
                if (userPw.value == userRePw.value) {
                    userRePw.classList.add("check-input-border");
                    userRePw.classList.remove("err-input-border");
                    removeUserErrIcon(".user-re-pw");
                    checkObj.userRePw = true;

                } else { // 다를 경우
                    userRePw.classList.add("err-input-border");
                    userRePw.classList.remove("check-input-border");
                    if (checkUserErrIcon(classUserRePw) == false) { addUserErrIcon("userRePw"); }
                    checkObj.userRePw = false;
                }
            }
        } else { // 유효하지 않은 경우
            userPw.classList.add("err-input-border");
            userPw.classList.remove("check-input-border");
            if (checkUserErrIcon(classUserPw) == false) { addUserErrIcon("userPw"); }
            checkObj.userPw = false;
        }
    });

    userPw.addEventListener("input", (e) => {
        // 비밀번호 확인 유효성 검사
        if (checkObj.userPw) { // 비밀번호가 유효하게 작성된 경우에

            // 비밀번호 == 비밀번호 확인  (같을 경우)
            if (userPw.value == userRePw.value) {
                userRePw.classList.add("check-input-border");
                userRePw.classList.remove("err-input-border");
                removeUserErrIcon(".user-re-pw");
                checkObj.userRePw = true;

            } else { // 다를 경우
                userRePw.classList.add("err-input-border");
                userRePw.classList.remove("check-input-border");
                if (checkUserErrIcon(classUserRePw) == false) { addUserErrIcon("userRePw"); }
                checkObj.userRePw = false;
            }

        } else { // 비밀번호가 유효하지 않은 경우
            checkObj.userRePw = false;
        }
    });
    userRePw.addEventListener('input', () => {

        if (checkObj.userPw) { // 비밀번호가 유효하게 작성된 경우에

            // 비밀번호 == 비밀번호 확인  (같을 경우)
            if (userPw.value == userRePw.value) {
                userRePw.classList.add("check-input-border");
                userRePw.classList.remove("err-input-border");
                removeUserErrIcon(".user-re-pw");
                checkObj.userRePw = true;

            } else { // 다를 경우
                userRePw.classList.add("err-input-border");
                userRePw.classList.remove("check-input-border");
                if (checkUserErrIcon(classUserRePw) == false) { addUserErrIcon("userRePw"); }
                checkObj.userRePw = false;
            }

        } else { // 비밀번호가 유효하지 않은 경우
            checkObj.userRePw = false;
        }
    });
}
// ----------------------------------------------- 비밀번호 유효성 검사 끝 -----------------------------------------------
// ----------------------------------------------- 닉네임 유효성 검사 -----------------------------------------------
const userNickname = document.getElementById("userNickname");

if (userNickname != null) {
    // 닉네임이 입력이 되었을 떄
    userNickname.addEventListener("input", () => {
        // 닉네임에 입력이 되지 않은 경우
        if (userNickname.value.trim() == '') {
            nickMessage.innerText = "한글,영어,숫자로만 2~10글자";

            nickMessage.classList.remove("confirm", "error");
            checkObj.userNickname = false;
            userNickname.value = ""; // 빈칸 입력 방지 코드
            return;
        }

        const regEx = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$/;
        // 2자 이상 16자 이하, 영어 or 숫자 or 한글(한글 초성 및 모음 불허)

        if (regEx.test(userNickname.value)) { // 유효

            fetch("/dupCheck/nickname?nickname=" + userNickname.value)
                .then(resp => resp.text()) // 응답 객체를를 text로 파싱(변환)
                .then(count => {
                    // 중복되면 1, 중복 아니면 0
                    if (count == 0) {
                        userNickname.classList.add("check-input-border");
                        userNickname.classList.remove("err-input-border");
                        removeUserErrIcon(".user-nickname");
                        checkObj.userNickname = true;
                    } else {
                        userNickname.classList.add("err-input-border");
                        userNickname.classList.remove("check-input-border");
                        if (checkUserErrIcon(classUserNickname) == false) { addUserErrIcon("userNickname"); }
                        checkObj.userNickname = false; // 유효 X
                    }
                })
                .catch(err => console.log(err));

        } else { // 무효
            userNickname.classList.add("err-input-border");
            userNickname.classList.remove("check-input-border");
            if (checkUserErrIcon(classUserNickname) == false) { addUserErrIcon("userNickname"); }
            checkObj.userNickname = false;
        }
    });
}
// ----------------------------------------------- 닉네임 유효성 검사 끝 -----------------------------------------------
// ----------------------------------------------- 생년월일 유효성 검사 -----------------------------------------------

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

const userBirth1 = document.getElementById("userBirth1");

if (userBirth1 != null) {
    userBirth1.addEventListener("input", () => {
        if (userBirth1.value.trim() == '') {

            userBirth1.classList.add("err-input-border");
            userBirth1.classList.remove("check-input-border");
            if (checkUserErrIcon(classUserBirth) == false) { addUserErrIcon("userBirth1"); }
            return;
        }
        if (validateDateOfBirth(userBirth1.value)) { // 유효

            userBirth1.classList.add("check-input-border");
            userBirth1.classList.remove("err-input-border");
            removeUserErrIcon(".user-birth1");
            checkObj.userBirth1 = true;
        } else { // 무효
            userBirth1.classList.add("err-input-border");
            userBirth1.classList.remove("check-input-border");
            if (checkUserErrIcon(classUserBirth) == false) { addUserErrIcon("userBirth1"); }
            checkObj.userBirth1 = false;
        }
    });
}
userBirth1.addEventListener("input", function () {
    this.value = this.value.replace(/\D/g, ""); // 숫자 이외의 값 제거
});

// ----------------------------------------------- 생년월일 유효성 검사 끝 -----------------------------------------------
// ----------------------------------------------- 휴대폰 번호 유효성 검사 -----------------------------------------------
const userTel = document.getElementById("userTel");
if (userTel != null) {
    userTel.addEventListener("input", () => {

        // number 최대 입력수 제한
        let userTelValue = userTel.value;
        let count = userTelValue.length;
        if (count > 11) {
            userTelValue = userTelValue.slice(0, 11);
            userTel.value = userTelValue;
        }

        if (userTel.value.trim() == '') {

            userTel.classList.add("err-input-border");
            userTel.classList.remove("check-input-border");
            if (checkUserErrIcon(classUserTel) == false) { addUserErrIcon("userTel"); }
            return;
        }
        const regEx = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
        if (regEx.test(userTel.value)) { // 유효
            userTel.classList.add("check-input-border");
            userTel.classList.remove("err-input-border");
            removeUserErrIcon(".user-tel");
            checkObj.userTel = true;
        } else {
            userTel.classList.add("err-input-border");
            userTel.classList.remove("check-input-border");
            if (checkUserErrIcon(classUserTel) == false) { addUserErrIcon("userTel"); }
            checkObj.userTel = false;
        }
    });
}
userTel.addEventListener("input", function () {
    this.value = this.value.replace(/\D/g, ""); // 숫자 이외의 값 제거
});
// ----------------------------------------------- 휴대폰 번호 유효성 검사 끝 -----------------------------------------------
// ----------------------------------------------- 성별 체크 검사 -----------------------------------------------
const radioButtons = document.getElementsByClassName('userGender');
for (var i = 0; i < radioButtons.length; i++) {
    radioButtons[i].addEventListener('change', function () {
        if (this.checked) {
            checkObj.userGender = true;
        }
    });
}
// ----------------------------------------------- 성별 체크 검사 -----------------------------------------------

// ----------------------------------------------- 휴대폰 인증 -----------------------------------------------
// 인증번호 발송
const sendAuthKeyBtn = document.getElementById("sendAuthKeyBtn");
const signBtn = document.getElementById("signBtn");
const authKeyLifeTime = document.getElementById("authKeyLifeTime");

authKey.addEventListener("input", function () {
    this.value = this.value.replace(/\D/g, ""); // 숫자 이외의 값 제거
});

let timeRemaining = 5 * 60;
let buttonClicked = false;
let interval;

if (sendAuthKeyBtn != null) {
    sendAuthKeyBtn.addEventListener("click", function (e) {
        
        if (interval) {
            clearInterval(interval);
            interval = null;
        }

        if (checkObj.userTel) {

            authKeyLifeTime.classList.add("authKey-green-text");
            authKeyLifeTime.classList.remove("authKey-red-text");
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
            //             console.log("인증번호 발송 성공")
            //             console.log(result);
            //         } else {
            //             console.log("인증번호 발송 실패")
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

    });

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
// ----------------------------------------------- 휴대폰 인증 끝 -----------------------------------------------

/* 약관 */
// const checkTerms = document.getElementById("checkTerms");
// const checkTermsAll = document.getElementById("checkTermsAll");

// const checkTerms1 = document.getElementById("checkTerms1");
// const checkTerms2 = document.getElementById("checkTerms2");
// const checkTerms3 = document.getElementById("checkTerms3");

// let isAllChecked = false;

// function toggleCheckboxes() {
//     isAllChecked = !isAllChecked;
//     checkTerms1.checked = isAllChecked;
//     checkTerms2.checked = isAllChecked;
//     checkTerms3.checked = isAllChecked;
//     checkTerms.checked = isAllChecked;
//     checkTermsAll.checked = isAllChecked;
//     checkObj.checkTerm1 = isAllChecked;
//     checkObj.checkTerm2 = isAllChecked;
// };
// checkTermsAll.addEventListener("click", toggleCheckboxes);

// let checkTerms1Boolean = false;
// checkTerms1.addEventListener("click", ()=>{
//     checkTerms1Boolean = !checkTerms1Boolean
//     checkTerms1.checked = checkTerms1Boolean;
//     checkObj.checkTerm1 = checkTerms1Boolean;
// });
// let checkTerms2Boolean = false;
// checkTerms2.addEventListener("click", ()=>{
//     checkTerms2Boolean = !checkTerms2Boolean
//     checkTerms2.checked = checkTerms2Boolean;
//     checkObj.checkTerm2 = checkTerms2Boolean;
// });


/* 약관 확인하기 창 열기 */
const openTermsBtn = document.getElementsByClassName("open-termsBtn");

if (openTermsBtn[0] != null) {
    openTermsBtn[0].addEventListener("click", () => {
        document.getElementsByClassName("termsbackground")[0].style.display = "block";
    });

}

/* 약관 확인하기 창 닫기 */
const closeSignupBtn = document.getElementsByClassName("close-signup-terms")[0];
if (closeSignupBtn) {
    closeSignupBtn.addEventListener("click", () => {
        document.getElementsByClassName("termsbackground")[0].style.display = 'none';
    });
}


/* signup 제출 시 검사 */
if (document.getElementById("signUpFrmBox") != null) {
    document.getElementById("signUpFrmBox").addEventListener("submit", e => {

        for (let key in checkObj) {

            if (!checkObj[key]) { // 각 key에 대한 value(true/false)를 얻어와
                // false인 경우 == 유효하지 않다!
                switch (key) {
                    case "userId":
                        alert("아이디가 유효하지 않습니다"); break;

                    case "userPw":
                        alert("비밀번호가 유효하지 않습니다"); break;

                    case "userRePw":
                        alert("비밀번호가 확인되지 않았습니다"); break;

                    case "userNickname":
                        alert("닉네임이 유효하지 않습니다"); break;

                    case "userTel":
                        alert("전화번호가 유효하지 않습니다"); break;

                    case "userEmail":
                        alert("이메일이 유효하지 않습니다"); break;

                    case "userBirth1":
                        alert("생년월일이 유효하지 않습니다"); break;

                    case "userGender":
                        alert("성별이 체크되지 않았습니다"); break;

                    case "authKey":
                        alert("인증번호가 확인되지 않았습니다"); break;
                }

                e.preventDefault(); // form 태그 기본 이벤트 제거
                return; // 함수 종료
            }
        }
    });
}



