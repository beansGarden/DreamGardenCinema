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
const checkbox = document.getElementsByClassName("admin_noticeCheckbox"); //check박스
const checkboxNo = document.getElementsByClassName("admin_notice_checkbox_no"); //번호

delBtn.addEventListener(('click'),()=>{


if (confirm("정말 삭제 하시겠습니까?")) {
  for(let i=0; i<checkbox.length; i++){
    if (checkbox[i].checked) {
 var noticeNo = document.getElementsByClassName("admin_notice_checkbox_no")[i].innerText //체크박스 옆 숫자 =  공지번호

} if(checkbox!=null){ //체크박스가 null이 아니면 함수 실행
noticeDelete(noticeNo);
}
}
}else return;

});

function noticeDelete(noticeNo){

fetch("/adminNotice/deleteNoticeList", {
  method : "POST",
  headers : {"Content-Type": "application/json"},
  body : JSON.stringify({"noticeNo" : noticeNo})
}).then(resp=> resp.text())
.then(result=>{
  console.log(result);
  console.log(noticeNo); //번호 나옴

}).catch(err=> console.log(err));

}

//체크박스 숫자 불러오기
function userSelectAll(checkbox) {
    var checkboxes = document.getElementsByClassName('admin_noticeCheckbox');
    var count = document.getElementsByClassName('admin_noticeCount')[0];
    var countAll = document.getElementsByClassName('admin_noticeCountAll')[0];
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
  
  var checkboxes = document.getElementsByClassName('admin_noticeCheckbox');
  for (var i = 0; i < checkboxes.length; i++) {
    checkboxes[i].addEventListener('change', function() {
      var count = document.getElementsByClassName('admin_noticeCount')[0];
      var checkedCount = document.querySelectorAll('.admin_noticeCheckbox:checked').length;
      count.textContent = checkedCount.toString();
    });
  }
  
  var countAll = document.getElementsByClassName('admin_noticeCountAll')[0];
  var totalItems = document.querySelectorAll('.admin_noticeCheckbox').length;
  countAll.textContent = totalItems.toString();
  
  //공지사항 notice 전체 불러오기

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
function getNoticeCount() {
  ajaxRequest('/adminNoticeListAjax', 'GET', function(response) {
      var countElement = document.querySelector('.adminNoticeCountAll');
      countElement.textContent = response;
  });
}
getNoticeCount()


/* 전체 삭제 안 한 공지사항 수 불러오기 */
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
function getUserInCount() {
  ajaxRequest('/adminNoticeInListAjax', 'GET', function(response) {
      var countElement = document.querySelector('.adminNoticeInCountAll');
      countElement.textContent = response;
  });
}
getUserInCount()

/* 전체 삭제한 공지사항 수 불러오기 */
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

// 회원 수 가져오기
function getUserOutCount() {
  ajaxRequest('/adminNoticeOutListAjax', 'GET', function(response) {
      var countElement = document.querySelector('.adminNoticeOutCountAll');
      countElement.textContent = response;
  });
}
getUserOutCount()