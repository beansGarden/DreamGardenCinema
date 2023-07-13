/* 체크박스 전체선택 */
function userSelectAll(userSelectAll)  {
    const checkboxes 
       = document.querySelectorAll('input[type="checkbox"]'); //체크박스 전체 불러오기
    
    checkboxes.forEach((checkbox) => {
      checkbox.checked = userSelectAll.checked
    })
  }

/* 복구 버튼 선택 탈퇴하기 */
const restoreBtn = document.getElementById("restoreBtn"); // 삭제 버튼
const checkbox = document.getElementsByClassName("admin_noticeCheckbox"); // 체크박스
const checkboxNo = document.getElementsByClassName("admin_notice_checkbox_no"); // 번호

restoreBtn.addEventListener('click', () => {
  if (confirm("정말 복구하시겠습니까?")) {
    const selectedNoticeNos = []; // 선택된 회원 번호들을 저장할 배열

    for (let i = 0; i < checkbox.length; i++) {
      if (checkbox[i].checked) {
        const noticeNo = checkboxNo[i].innerText;
        selectedNoticeNos.push(noticeNo);
      }
    }

    if (selectedNoticeNos.length > 0) {
      noticeRestore(selectedNoticeNos); // 선택된 회원 번호들을 전달하여 탈퇴 함수 호출
    }
  } else {
    return;
  }
});

function noticeRestore(noticeNos) {
  const promises = [];

  noticeNos.forEach(noticeNo => {
    const promise = fetch("/adminNotice/restoreNoticeList", {
      method: "POST",
      headers: {"Content-Type": "application/json"},
      body: JSON.stringify({"noticeNo": noticeNo})
    })
    .then(resp => resp.text())
    .then(result => {
      console.log(result);
      console.log(noticeNo); // 번호 나옴
    })
    .catch(err => console.log(err));

    promises.push(promise);
  });

  Promise.all(promises)
    .then(() => {
      alert("공지사항 게시글이 복구되었습니다."); // 복구 완료 메시지
      // 체크박스 선택 해제
      for (let i = 0; i < checkbox.length; i++) {
        checkbox[i].checked = false;
      }
    })
    .catch(err => console.log(err));
    location.reload();
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

// 공지사항 개수 가져오기
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

// 공지사항 개수 가져오기
function getNoticeInCount() {
  ajaxRequest('/adminNoticeInListAjax', 'GET', function(response) {
      var countElement = document.querySelector('.adminNoticeInCountAll');
      countElement.textContent = response;
  });
}
getNoticeInCount()

/*  삭제한 공지사항 수 불러오기 */
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

// 삭제한 공지사항 수 
function getNoticeOutCount() {
  ajaxRequest('/adminNoticeOutListAjax', 'GET', function(response) {
      var countElement = document.querySelector('.adminNoticeOutCountAll');
      countElement.textContent = response;
  });
}
getNoticeOutCount()