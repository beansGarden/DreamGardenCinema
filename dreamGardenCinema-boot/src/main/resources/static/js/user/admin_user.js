/* 체크박스 전체선택 */
function userSelectAll(userSelectAll) {
  const checkboxes
    = document.querySelectorAll('input[type="checkbox"]'); //체크박스 전체 불러오기

  checkboxes.forEach((checkbox) => {
    checkbox.checked = userSelectAll.checked
  })
}

/* 삭제 버튼 선택 탈퇴하기 */
const delBtn = document.getElementById("admin_userSignOut"); //삭제버튼 
const checkbox = document.getElementsByClassName("admin_userCheckbox"); //check박스
const checkboxNo = document.getElementsByClassName("admin_user_checkbox_no"); //번호

delBtn.addEventListener(('click'), () => {

  if (confirm("정말 삭제 하시겠습니까?")) {
    for (let i = 0; i < checkbox.length; i++) {
      if (checkbox[i].checked) { //체크박스가 선택됐을 때
        var userNo = document.getElementsByClassName("admin_user_checkbox_no")[i].innerText //체크박스 옆 숫자 =  공지번호

      } if (checkbox != null) {
        userDelete(userNo);
      }
    }
  } else return;

});

function userDelete(userNo) {

  fetch("/adminUser/deleteUserList", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ "userNo": userNo })
  }).then(resp => resp.text())
    .then(result => {
      console.log(result);
      console.log(userNo);

    }).catch(err => console.log(err));

}