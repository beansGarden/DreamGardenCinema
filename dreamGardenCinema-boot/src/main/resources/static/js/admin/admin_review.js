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

/* 삭제 버튼 선택 삭제하기 */
const delBtn = document.getElementById("deleteBtn"); // 삭제 버튼
const checkbox = document.getElementsByClassName("admin_reviewCheckbox"); // 체크박스
const checkboxNo = document.getElementsByClassName("admin_review_checkbox_no"); // 번호

delBtn.addEventListener('click', () => {
  if (confirm("정말 삭제하시겠습니까?")) {
    const selectedReviewNos = []; // 선택된 리뷰 번호들을 저장할 배열

    for (let i = 0; i < checkbox.length; i++) {
      if (checkbox[i].checked) {
        const reviewNo = checkboxNo[i].innerText;
        selectedReviewNos.push(reviewNo);
      }
    }

    if (selectedReviewNos.length > 0) {
      reviewDelete(selectedReviewNos); // 선택된 리뷰 번호들을 전달하여 삭제 함수 호출
    }
  } else {
    return;
  }
});

function reviewDelete(reviewNos) {
  const promises = [];

  reviewNos.forEach(reviewNo => {
    const promise = fetch("/adminReivew/deleteReviewList", {
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
          delFl[i].innerText = '';
          delFl[i].innerText = 'Y';
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
      alert("리뷰가 삭제되었습니다."); // 삭제 완료 메시지
    })
    .catch(err => console.log(err));
}


/* 복구 버튼 선택 삭제하기 */
const restoreBtn = document.getElementById("restoreBtn"); // 삭제 버튼


restoreBtn.addEventListener('click', () => {
  if (confirm("정말 복구하시겠습니까?")) {
    for (let i = 0; i < checkbox.length; i++) {
      if (checkbox[i].checked) {
        var reviewNo = document.getElementsByClassName("admin_review_checkbox_no")[i].innerText; // 체크박스 옆 숫자 = 리뷰 번호
        if (checkboxNo != null) {
          reviewRestore(reviewNo);
        }
      }
    }
  } else {
    return;
  }
});

function reviewRestore(reviewNo) {
  fetch("/adminReivew/restoreReviewList", {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify({"reviewNo": parseInt(reviewNo)})
  })
  .then(resp => resp.text())
  .then(result => {
    console.log(result);
    console.log(reviewNo); // 번호 나옴
    alert("리뷰가 복구되었습니다."); // 삭제 완료 메시지
    
    const tr = document.getElementsByClassName("tr");
    const delFl = document.getElementsByClassName("deleteFl");

    for (let i = 0; i < checkbox.length; i++) {
      if (checkbox[i].checked) {

        delFl[i].innerText = '';
        delFl[i].innerText = 'N';
        console.log("del" + delFl[i]);
        console.log("tr" + tr[i]);
    }
    }
  })
  .catch(err => console.log(err));



}
