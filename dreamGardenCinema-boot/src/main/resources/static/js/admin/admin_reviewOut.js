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
  var checkboxes = document.getElementsByClassName('admin_reviewCheckbox');
  var count = document.getElementsByClassName('admin_reviewCount')[0];
  var countAll = document.getElementsByClassName('admin_reviewCountAll')[0];
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

var checkboxes = document.getElementsByClassName('admin_reviewCheckbox');
for (var i = 0; i < checkboxes.length; i++) {
  checkboxes[i].addEventListener('change', function() {
    var count = document.getElementsByClassName('admin_reviewCount')[0];
    var checkedCount = document.querySelectorAll('.admin_reviewCheckbox:checked').length;
    count.textContent = checkedCount.toString();
  });
}

var countAll = document.getElementsByClassName('admin_reviewCountAll')[0];
var totalItems = document.querySelectorAll('.admin_reviewCheckbox').length;
countAll.textContent = totalItems.toString();


//review 전체 불러오기

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
function getReviewCount() {
  ajaxRequest('/adminReviewListAjax', 'GET', function(response) {
      var countElement = document.querySelector('.adminReviewCountAll');
      countElement.textContent = response;

 
  });
}
getReviewCount()



/* 복구 버튼 선택 삭제하기======================================== */
const restoreBtn = document.getElementById("restoreBtn"); // 복구 버튼
const checkbox = document.getElementsByClassName("admin_reviewCheckbox"); // 체크박스
const checkboxNo = document.getElementsByClassName("admin_review_checkbox_no"); // 번호


restoreBtn.addEventListener('click', () => {
  if (confirm("정말 복구하시겠습니까?")) {
    const selectedReviewNos = []; // 선택된 리뷰 번호들을 저장할 배열

    for (let i = 0; i < checkbox.length; i++) {
      if (checkbox[i].checked) {
        const reviewNo = checkboxNo[i].innerText;
        selectedReviewNos.push(reviewNo);
      }
    }

    if (selectedReviewNos.length > 0) {
      reviewRestore(selectedReviewNos); // 선택된 리뷰 번호들을 전달하여 복구 함수 호출
    }
  } else {
    return;
  }
});

function reviewRestore(reviewNos) {
  const promises = [];

  reviewNos.forEach(reviewNo => {
    const promise = fetch("/adminReivew/restoreReviewList", {
      method: "POST",
      headers: {"Content-Type": "application/json"},
      body: JSON.stringify({"reviewNo": parseInt(reviewNo)})
    })
    .then(resp => resp.text())
    .then(result => {
      console.log(result);
      console.log(reviewNo); // 번호 나옴

      const tr = document.getElementsByClassName("tr");
      const delFl = document.getElementsByClassName("deleteFl");

      for (let i = 0; i < checkbox.length; i++) {
        if (checkbox[i].checked) {
          delFl[i].innerText = 'N';
          console.log("del" + delFl[i]);
          console.log("tr" + tr[i]);
        }
      }
    })
    .catch(err => console.log(err));

    promises.push(promise);
  });

  Promise.all(promises)
    .then(() => {
      alert("리뷰가 복구되었습니다."); // 복구 완료 메시지
      // 체크박스 선택 해제
      for (let i = 0; i < checkbox.length; i++) {
        checkbox[i].checked = false;
      }
    })
    .catch(err => console.log(err));
}

/* 전체 삭제 안 한 수 불러오기 */
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

// 삭제 안 한 게시글 개수 가져오기
function getReviewInCount() {
  ajaxRequest('/adminReviewInListAjax', 'GET', function(response) {
      var countElement = document.querySelector('.adminReviewInCountAll');
      countElement.textContent = response;
  });
}
getReviewInCount()



/* 삭제한 게시글 수 불러오기 */
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

// 게시글 수 가져오기
function getReviewOutCount() {
  ajaxRequest('/adminReviewOutListAjax', 'GET', function(response) {
      var countElement = document.querySelector('.adminReviewOutCountAll');
      countElement.textContent = response;
  });
}
getReviewOutCount()