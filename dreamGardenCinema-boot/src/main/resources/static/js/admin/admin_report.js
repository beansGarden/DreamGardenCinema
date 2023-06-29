/* 체크박스 전체선택 */
function userSelectAll(userSelectAll)  {
    const checkboxes 
       = document.querySelectorAll('input[type="checkbox"]'); //체크박스 전체 불러오기
    
    checkboxes.forEach((checkbox) => {
      checkbox.checked = userSelectAll.checked
    })
  }

  /* 삭제 버튼 선택 삭제하기 */
  const delBtn = document.getElementById("deleteBtn"); //삭제버튼 
  const checkbox = document.getElementsByClassName("admin_reportCheckbox"); //check박스
  const checkboxNo = document.getElementsByClassName("admin_report_checkbox_no"); //번호
 
  delBtn.addEventListener(('click'),()=>{

 
  if (confirm("정말 삭제 하시겠습니까?")) {
    for(let i=0; i<checkbox.length; i++){
      if (checkbox[i].checked) {
   var reportNo = document.getElementsByClassName("admin_report_checkbox_no")[i].innerText //체크박스 옆 숫자 =  공지번호
  
} if(checkbox!=null){
reportDelete(reportNo);
}
}
}else return;

});

function reportDelete(reportNo){

  

  fetch("/adminreport/deletereportList", {
    method : "POST",
    headers : {"Content-Type": "application/json"},
    body : JSON.stringify({"reportNo" : reportNo})
  }).then(resp=> resp.text())
  .then(result=>{
    console.log(result);
    console.log(reportNo);

  }).catch(err=> console.log(err));

}

//체크박스 숫자 불러오기
function userSelectAll(checkbox) {
  var checkboxes = document.getElementsByClassName('admin_reportCheckbox');
  var count = document.getElementsByClassName('admin_reportCount')[0];
  var countAll = document.getElementsByClassName('admin_reportCountAll')[0];
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

var checkboxes = document.getElementsByClassName('admin_reportCheckbox');
for (var i = 0; i < checkboxes.length; i++) {
  checkboxes[i].addEventListener('change', function() {
    var count = document.getElementsByClassName('admin_reportCount')[0];
    var checkedCount = document.querySelectorAll('.admin_reportCheckbox:checked').length;
    count.textContent = checkedCount.toString();
  });
}

var countAll = document.getElementsByClassName('admin_reportCountAll')[0];
var totalItems = document.querySelectorAll('.admin_reportCheckbox').length;
countAll.textContent = totalItems.toString();



//report 전체 불러오기

  // Ajax 요청 함수
  function ajaxRequest(url, method, successCallback) {
    var xhr = new XMLHttpRequest();
    xhr.open(method, url, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            successCallback(xhr.responseText);
        }
    };
    xhr.send();
}

// 영화 개수 가져오기
function getreportCount() {
    ajaxRequest('/adminreportListAjax', 'GET', function(response) {
        var countElement = document.querySelector('.admin_reportCountAll');
        countElement.textContent = response;
    });
}

// 페이지 로드 시 영화 개수 가져오기 호출
getreportCount();
