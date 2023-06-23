/* 체크박스 전체선택 */
function userSelectAll(userSelectAll)  {
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
 
  delBtn.addEventListener(('click'),()=>{
 
  if (confirm("정말 삭제 하시겠습니까?")) {
    for(let i=0; i<checkbox.length; i++){
      if (checkbox[i].checked) { //체크박스가 선택됐을 때
   var userNo = document.getElementsByClassName("admin_user_checkbox_no")[i].innerText //체크박스 옆 숫자 =  공지번호
  
} if(checkbox!=null){
userDelete(userNo);
}
}
}else return;

});

function userDelete(userNo){

  fetch("/adminUser/deleteUserList", {
    method : "POST",
    headers : {"Content-Type": "application/json"},
    body : JSON.stringify({"userNo" : userNo})
  }).then(resp=> resp.text())
  .then(result=>{
    console.log(result);
    console.log(userNo);

  }).catch(err=> console.log(err));

}

//체크박스 숫자 불러오기
function userSelectAll(checkbox) { 
  var checkboxes = document.getElementsByClassName('admin_userCheckbox'); //전체 선택하는 체크박스
  var count = document.getElementsByClassName('admin_userCount')[0]; //선택한 일부 user 나오는 span
// countAll이 전체 데이터의 개수를 세도록 변경
var countAll = document.getElementsByClassName('admin_userCountAll')[0];
var totalItems = document.querySelectorAll('.admin_userBoardWrap table tbody tr').length;


  var checkedCount = 0;

  if (checkbox.checked) {
    for (var i = 0; i < checkboxes.length; i++) {
      checkboxes[i].checked = true;
    }
    checkedCount = checkboxes.length;
  } else {
    for (var i = 0; i < checkboxes.length; i++) {
      checkboxes[i].checked = false;
    }
    checkedCount = 0;
  }

  count.textContent = checkedCount.toString();
}

var checkboxes = document.getElementsByClassName('admin_userCheckbox');
for (var i = 0; i < checkboxes.length; i++) {
  checkboxes[i].addEventListener('change', function() {
    var count = document.getElementsByClassName('admin_userCount')[0];
    var checkedCount = document.querySelectorAll('.admin_userCheckbox:checked').length;
    count.textContent = checkedCount.toString();
  });
}

var countAll = document.getElementsByClassName('admin_userCountAll')[0];
var totalItems = document.querySelectorAll('.admin_userCheckbox').length;
countAll.textContent = totalItems.toString();
