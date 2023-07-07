/* 체크박스 전체선택 */
function userSelectAll(userSelectAll)  {
    const checkboxes 
       = document.querySelectorAll('input[type="checkbox"]'); //체크박스 전체 불러오기
    
    checkboxes.forEach((checkbox) => {
      checkbox.checked = userSelectAll.checked
    })
  }

  /* 삭제 버튼 선택 탈퇴하기 */
  const delBtn = document.getElementById("admin_userSignOut"); // 삭제 버튼
  const checkbox = document.getElementsByClassName("admin_faqCheckbox"); // 체크박스
  const checkboxNo = document.getElementsByClassName("admin_faq_checkbox_no"); // 번호
  
  delBtn.addEventListener('click', () => {
    if (confirm("정말 탈퇴하시겠습니까?")) {
      const selectedUserNos = []; // 선택된 회원 번호들을 저장할 배열
  
      for (let i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked) {
          const userNo = checkboxNo[i].innerText;
          selectedUserNos.push(userNo);
        }
      }
  
      if (selectedUserNos.length > 0) {
        userDelete(selectedUserNos); // 선택된 회원 번호들을 전달하여 탈퇴 함수 호출
      }
    } else {
      return;
    }
  });
  
  function userDelete(userNos) {
    const promises = [];
  
    userNos.forEach(userNo => {
      const promise = fetch("/adminUser/deleteUserList", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({"userNo": userNo})
      })
      .then(resp => resp.text())
      .then(result => {
        console.log(result);
        console.log(userNo); // 번호 나옴
      })
      .catch(err => console.log(err));
  
      promises.push(promise);
    });
  
    Promise.all(promises)
      .then(() => {
        alert("회원이 탈퇴되었습니다."); // 탈퇴 완료 메시지
        // 체크박스 선택 해제
        for (let i = 0; i < checkbox.length; i++) {
          checkbox[i].checked = false;
        }
      })
      .catch(err => console.log(err));
  }
  

  /* 복구 버튼 복구하기 */
  const restoreBtn = document.getElementById("admin_userSignRestore"); // 복구 버튼

  
  restoreBtn.addEventListener('click', () => {
    if (confirm("정말 복구하시겠습니까?")) {
      const selectedUserNos = []; // 선택된 회원 번호들을 저장할 배열
  
      for (let i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked) {
          const userNo = checkboxNo[i].innerText;
          selectedUserNos.push(userNo);
        }
      }
  
      if (selectedUserNos.length > 0) {
        userRestore(selectedUserNos); // 선택된 회원 번호들을 전달하여 복구 함수 호출
      }
    } else {
      return;
    }
  });
  
  function userRestore(userNos) {
    const promises = [];
  
    userNos.forEach(userNo => {
      const promise = fetch("/adminUser/restoreUserList", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({"userNo": userNo})
      })
      .then(resp => resp.text())
      .then(result => {
        console.log(result);
        console.log(userNo); // 번호 나옴
      })
      .catch(err => console.log(err));
  
      promises.push(promise);
    });
  
    Promise.all(promises)
      .then(() => {
        alert("회원이 복구되었습니다."); // 복구 완료 메시지
        // 체크박스 선택 해제
        for (let i = 0; i < checkbox.length; i++) {
          checkbox[i].checked = false;
        }
      })
      .catch(err => console.log(err));
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

//회원전체 불러오기
/* 
  // Ajax 요청 함수
  function ajaxRequest(url, method, successCallback) {
    var xhr = new XMLHttpRequest();
    xhr.open(method, url, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            successCallback(xhr.responseText);
        }
    };
    xhr.send(JSON.stringify(data));
}


// 영화 개수 가져오기
function getUserCount() {
  var type = document.querySelector('#type').value; // 선택한 검색 유형
  var keyword = document.querySelector('#keyword').value; // 입력한 검색 키워드

  var data = {
      type: type,
      keyword: keyword
  };

  ajaxRequest('/adminUserListAjax', 'POST', data, function(response) {
      var countElement = document.querySelector('.admin_userCountAll');
      countElement.textContent = response;
  });
}
 */
/* 전체 회원 수 불러오기 */
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

// 회원 개수 가져오기
function getUserCount() {
  ajaxRequest('/adminUserListAjax', 'GET', function(response) {
      var countElement = document.querySelector('.adminUserCountAll');
      countElement.textContent = response;
  });
}
getUserCount()

/* 전체 탈퇴 안 한 수 불러오기 */
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
  ajaxRequest('/adminUserInListAjax', 'GET', function(response) {
      var countElement = document.querySelector('.adminUserInCountAll');
      countElement.textContent = response;
  });
}
getUserInCount()

/* 전체 탈퇴한 회원 수 불러오기 */
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
  ajaxRequest('/adminUserOutListAjax', 'GET', function(response) {
      var countElement = document.querySelector('.adminUserOutCountAll');
      countElement.textContent = response;
  });
}
getUserOutCount()