/* 체크박스 전체선택 */
function userSelectAll(userSelectAll)  {
    const checkboxes 
       = document.querySelectorAll('input[type="checkbox"]'); //체크박스 전체 불러오기
    
    checkboxes.forEach((checkbox) => {
      checkbox.checked = userSelectAll.checked
    })
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
        var countElement = document.querySelector('.adminReportCountAll');
        countElement.textContent = response;
    });
    console.log(response);
    console.log(countElement);
}

// 페이지 로드 시 영화 개수 가져오기 호출
getreportCount();
