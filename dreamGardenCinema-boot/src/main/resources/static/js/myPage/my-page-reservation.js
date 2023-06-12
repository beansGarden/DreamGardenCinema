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