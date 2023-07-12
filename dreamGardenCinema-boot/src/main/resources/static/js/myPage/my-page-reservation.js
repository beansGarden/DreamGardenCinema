/* 내 정보 변경 클릭 시 모달 */
const my_page_myinfo = document.querySelector(".my-page-myinfo");
const my_page_changeinfo_modal_area = document.querySelector(".my-page-changeinfo-modal-area");
const my_page_changeinfo_update_btn2 = document.querySelector(".my-page-changeinfo-update-btn>:nth-child(2)");

my_page_myinfo.addEventListener("click", () => {

    my_page_changeinfo_modal_area.style.display = "flex";

    document.body.style.overflow = 'hidden';

})

my_page_changeinfo_update_btn2.addEventListener("click", () => {

    my_page_changeinfo_modal_area.style.display = "none";

    document.body.style.overflow = 'auto';

})
/* 주소 검색 (다음API) */
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
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


my_page_myprofile.addEventListener("click", () => {

    my_page_change_nickname_area.style.display = "flex";

    document.body.style.overflow = 'hidden';

})

/* 취소 버튼 */
my_page_change_nickname_update_btn2.addEventListener("click", () => {

    my_page_change_nickname_area.style.display = "none";

    document.body.style.overflow = 'auto';

})

/* 회원 탈퇴 모달 영역 */
const my_page_secession_btn = document.querySelector("#my-page-secession");
const my_page_secession_area = document.querySelector(".my-page-secession-area");
const my_page_secession_pwcheck_btn2 = document.querySelector(".my-page-secession-pwcheck-btn>:nth-child(2)")
/* 회원 탈퇴 nav 클릭 시 */
my_page_secession_btn.addEventListener("click", () => {

    my_page_secession_area.style.display = "flex";

    document.body.style.overflow = 'hidden';
})

my_page_secession_pwcheck_btn2.addEventListener("click", () => {

    my_page_secession_area.style.display = "none";

    document.body.style.overflow = 'auto';

})


/* 닉네임 수정 창에서 수정 버튼 클릭 시 */
const changeBtn = document.querySelector("#changeBtn")
const change_nickname_form = document.querySelector("#change-nickname-form")

changeBtn.addEventListener("click", () => {
    if (confirm("닉네임을 변경하시겠습니까?")) {
        change_nickname_form.submit()

        alert("닉네임이 변경되었습니다")

    } else {
        alert("취소되었습니다")
    }
})

/* 유효성 검사를 위한 배열 */
const checkObj = {
    "userPw": false,
    "checkPw": false,
    "userEmail": true,
    "userAddress": true
};

const myPageBtn = document.querySelector("#myPageBtn");
/* 이메일 변경 영역 */
const userEmail = document.querySelector("#changeEmail")
const emailLine = document.querySelector("#emailLine")

userEmail.addEventListener("input", () => {
    const userEmailValue = userEmail.value;
    const regEx = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

    if (regEx.test(userEmailValue)) {
        emailLine.classList.add("clearEmail")
        emailLine.classList.remove("emailLine")
        emailLine.classList.remove("errorEmail")
        checkObj.userEmail = true;
    } else {
        emailLine.classList.add("errorEmail")
        emailLine.classList.remove("emailLine")
        emailLine.classList.remove("clearEmail")
        checkObj.userEmail = false;
    }
})
userEmail.addEventListener("focus", () => {
    line1.classList.remove("errorEmail");
    line1.classList.remove("clearEmail");
    line1.classList.add("emailLine");
});

/* 내 정보 변경 비밀번호 일치 여부 */
const userPw = document.querySelector("#userPw")
const line1 = document.querySelector("#line1")
const loginUserPw = document.querySelector("#loginUserPw").value
/* 현재 비밀번호 확인 영역 */
userPw.addEventListener("input", () => {
    const regex = /^(?=.*[a-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-z\d$@$!%*#?&]{8,}$/;
    const userPwValue = userPw.value;

    const data = {
        "secessionPwValue" : userPwValue
    };
    if(userPwValue.trim().length  > 0) {
        fetch("/myPage/secessionCheck", {
            method : "POST",
            headers : {"Content-Type": "application/json"},
            body : JSON.stringify(data)

        })

    .then(resp=>resp.text())
    .then(result=>{


        if (regex.test(userPwValue)&&result>0) {
            line1.classList.add("clear")
            line1.classList.remove("line1")
            line1.classList.remove("error")
            checkObj.userPw = true;
        } else {
            line1.classList.add("error")
            line1.classList.remove("line1")
            line1.classList.remove("clear")
            checkObj.userPw = false;
        }
    })
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
            checkObj.checkPw = true;
        });
    } else {
        Array.from(line2).forEach((element) => {
            element.classList.add("error");
            element.classList.remove("line2");
            element.classList.remove("clear");
            checkObj.checkPw = false;
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

const detailAddress = document.querySelector("#detailAddress");
detailAddress.addEventListener("input",e=>{

    if (detailAddress.value === '') {
        checkObj.userAddress = false;
    } else {
        checkObj.userAddress = true;
    }

})

const changeInfo = document.querySelector("#changeInfo")

changeInfo.addEventListener("submit", e => {
    e.preventDefault();

    let isValid = true;

    for (let key in checkObj) {
        if (!checkObj[key]) {
            isValid = false;
            switch (key) {
                case "userEmail":
                    alert("이메일이 유효하지 않습니다");
                    userEmail.focus();
                    break;
                case "userPw":
                    alert("현재 비밀번호가 유효하지 않습니다");
                    userPw.focus();
                    break;
                case "checkPw":
                    alert("새 비밀번호 확인이 유효하지 않습니다");
                    checkPw.focus();
                    break;
                case "userAddress":
                    alert("상세주소가 유효하지 않습니다");
                    detailAddress.focus();
                    break;
            }
            break;
        }
    }

    if (isValid) {
        e.target.submit();
    }
});


// 회원 탈퇴 시 비밀번호 AJAX

const secessionPw = document.querySelector("#secessionPw")
const line3 = document.querySelector("#line3")
secessionPw.addEventListener("input",e=>{

    const secessionPwValue = secessionPw.value;

    const data = {
        "secessionPwValue" : secessionPwValue
    };

    if(secessionPwValue.trim().length  > 0) { // 입력한 값이 있을 때
        fetch("/myPage/secessionCheck", {
            method : "POST",
            headers : {"Content-Type": "application/json"},
            body : JSON.stringify(data)

        })

        .then(resp=>resp.text())
        .then(result=>{
            
            if(result>0){
                line3.classList.add("clearPw")
                line3.classList.remove("line3")
                line3.classList.remove("errorPw")
            }else{
                line3.classList.add("errorPw")
                line3.classList.remove("line3")
                line3.classList.remove("clearPw")
            }

        })
        
    }
})
/* 탈퇴 버튼 */
const my_page_secession_pwcheck_btn = document.querySelector(".my-page-secession-pwcheck-btn>:nth-child(1)");
const my_page_secession_btn2 = document.querySelector(".my-page-secession-btn>:nth-child(2)");

const my_page_secession_pwcheck_container = document.querySelector(".my-page-secession-pwcheck-container");
const my_page_secession_info_bg = document.querySelector(".my-page-secession-info-bg");

my_page_secession_pwcheck_btn.addEventListener("click", e => {
    if (line3.classList.contains("errorPw")) {
        e.preventDefault();
        secessionPw.focus();
        alert("비밀번호를 확인해주세요");
    }
});
my_page_secession_pwcheck_btn.addEventListener("click", e => {
    if (line3.classList.contains("clearPw")) {
        /* 회원 탈퇴 다음 버튼 클릭 시 화면 */
        my_page_secession_pwcheck_container.style.display = "none";

        my_page_secession_info_bg.style.display = "flex";
    
        document.body.style.overflow = 'hidden';
    }
});

secessionPw.addEventListener("focus", () => {
    line3.classList.remove("errorPw");
    line3.classList.remove("clearPw");
    line3.classList.add("line3");
});

/* 취소 버튼 클릭 시 */
my_page_secession_btn2.addEventListener("click", () => {

    my_page_secession_area.style.display = "none";

    my_page_secession_pwcheck_container.style.display = "flex";

    my_page_secession_info_bg.style.display = "none";

    document.body.style.overflow = 'auto';

})

const secessionCheckbox = document.querySelector("#secessionCheckbox");
const secessionButton = document.querySelector("#secessionButton");

secessionButton.addEventListener("click", (e) => {
    if (!secessionCheckbox.checked) {
        e.preventDefault(); 
        alert("회원 탈퇴에 동의해야 합니다.");
    } else {
        if (confirm("정말로 탈퇴하시겠습니까?")) {

        }else{
            e.preventDefault();
            alert("탈퇴 취소")
        }
    }
});

/* 예매 취소 버튼 클릭 시 */
const cancelReservation = document.querySelector(".cancel-reservation");
const cancelBtn = document.querySelectorAll(".my-page-contents-btn");
const cancelForm = document.querySelectorAll(".cancelForm");

cancelReservation.addEventListener("click",()=>{
        
    alert("취소하실 티켓의 예매 취소 버튼을 클릭해주세요")
    
    for(let i=0; i<cancelBtn.length; i++){
            
            cancelBtn[i].style.display = 'block';
    }
})

for(let i = 0 ; i<cancelBtn.length; i++){

    cancelBtn[i].addEventListener("click",e=>{
        
        if(confirm("정말 취소하시겠습니까?")){

        }else{
            e.preventDefault();
            alert("선택이 취소되었습니다. 다시 시도해주세요")
        }
    });

}