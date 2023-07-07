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
  var checkboxes = document.getElementsByClassName('admin_faqCheckbox');
  var count = document.getElementsByClassName('admin_faqCount')[0];
  var countAll = document.getElementsByClassName('admin_faqCountAll')[0];
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

var checkboxes = document.getElementsByClassName('admin_faqCheckbox');
for (var i = 0; i < checkboxes.length; i++) {
  checkboxes[i].addEventListener('change', function() {
    var count = document.getElementsByClassName('admin_faqCount')[0];
    var checkedCount = document.querySelectorAll('.admin_faqCheckbox:checked').length;
    count.textContent = checkedCount.toString();
  });
}

var countAll = document.getElementsByClassName('admin_faqCountAll')[0];
var totalItems = document.querySelectorAll('.admin_faqCheckbox').length;
countAll.textContent = totalItems.toString();

/* 삭제 버튼 선택 삭제하기 */
const deleteBtn = document.getElementById("deleteBtn"); // 복구 버튼
 const checkbox = document.getElementsByClassName("admin_faqCheckbox"); // 체크박스
 const checkboxNo = document.getElementsByClassName("admin_faq_checkbox_no"); // 번호

 
 deleteBtn.addEventListener('click', () => {
   if (confirm("정말 복구하시겠습니까?")) {
     const selectedFaqNos = []; // 선택된 회원 번호들을 저장할 배열
 
     for (let i = 0; i < checkbox.length; i++) {
       if (checkbox[i].checked) {
         const faqNo = checkboxNo[i].innerText;
         selectedFaqNos.push(faqNo);
       }
     }
 
     if (selectedFaqNos.length > 0) {
       faqDelete(selectedFaqNos); // 선택된 회원 번호들을 전달하여 복구 함수 호출
     }
   } else {
     return;
   }
 });
 
 function faqDelete(faqNos) {
   const promises = [];
 
   faqNos.forEach(faqNo => {
     const promise = fetch("/adminFaq/deleteFaqList", {
       method: "POST",
       headers: {"Content-Type": "application/json"},
       body: JSON.stringify({"FAQNo": faqNo})
     })
     .then(resp => resp.text())
     .then(result => {
       console.log(result);
       console.log(faqNo); // 번호 나옴
     })
     .catch(err => console.log(err));
 
     promises.push(promise);
   });
 
   Promise.all(promises)
     .then(() => {
       alert("게시글이 복구되었습니다."); // 복구 완료 메시지
       // 체크박스 선택 해제
       for (let i = 0; i < checkbox.length; i++) {
         checkbox[i].checked = false;
       }
     })
     .catch(err => console.log(err));
 }
 


//FAQ 전체 불러오기

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
function getFaqCount() {
  ajaxRequest('/adminFaqListAjax', 'GET', function(response) {
      var countElement = document.querySelector('.adminFaqCountAll');
      countElement.textContent = response;
  });
}
getFaqCount()


/* 전체 삭제 안 한 게시글 수 불러오기 */
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

//  전체 삭제 안 한 게시글 수 가져오기
function getFaqInCount() {
  ajaxRequest('/adminFaqInListAjax', 'GET', function(response) {
      var countElement = document.querySelector('.adminFAQInCountAll');
      countElement.textContent = response;
  });
}
getFaqInCount()

/*  삭제 한 게시글 수 불러오기 */
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

// 삭제  한 게시글 수 가져오기
function getFaqOutCount() {
  ajaxRequest('/adminFaqOutListAjax', 'GET', function(response) {
      var countElement = document.querySelector('.adminFAQOutCountAll');
      countElement.textContent = response;
  });
}
getFaqOutCount()