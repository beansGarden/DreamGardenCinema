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
  const checkbox = document.getElementsByClassName("admin_qnaCheckbox"); //check박스
  const checkboxNo = document.getElementsByClassName("admin_qna_checkbox_no"); //번호
 
  delBtn.addEventListener(('click'),()=>{

 
  if (confirm("정말 삭제 하시겠습니까?")) {
    for(let i=0; i<checkbox.length; i++){
      if (checkbox[i].checked) {
   var qnaNo = document.getElementsByClassName("admin_qna_checkbox_no")[i].innerText //체크박스 옆 숫자 =  공지번호
  
} if(checkbox!=null){
qnaDelete(qnaNo);
}
}
}else return;

});

function qnaDelete(qnaNo){

  

  fetch("/adminQna/deleteQnaList", {
    method : "POST",
    headers : {"Content-Type": "application/json"},
    body : JSON.stringify({"qnaNo" : qnaNo})
  }).then(resp=> resp.text())
  .then(result=>{
    console.log(result);
    console.log(qnaNo);

  }).catch(err=> console.log(err));

}

//체크박스 숫자 불러오기
function userSelectAll(checkbox) {
  var checkboxes = document.getElementsByClassName('admin_qnaCheckbox');
  var count = document.getElementsByClassName('admin_qnaCount')[0];
  var countAll = document.getElementsByClassName('admin_qnaCountAll')[0];
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

var checkboxes = document.getElementsByClassName('admin_qnaCheckbox');
for (var i = 0; i < checkboxes.length; i++) {
  checkboxes[i].addEventListener('change', function() {
    var count = document.getElementsByClassName('admin_qnaCount')[0];
    var checkedCount = document.querySelectorAll('.admin_qnaCheckbox:checked').length;
    count.textContent = checkedCount.toString();
  });
}

var countAll = document.getElementsByClassName('admin_qnaCountAll')[0];
var totalItems = document.querySelectorAll('.admin_qnaCheckbox').length;
countAll.textContent = totalItems.toString();



//QNA 전체 불러오기

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

// QNA 개수 가져오기
function getQnaCount() {
  ajaxRequest('/adminQnaListAjax', 'GET', function(response) {
      var countElement = document.querySelector('.adminQnaCountAll');
      countElement.textContent = response;
  });
}
getQnaCount()


/* 전체 삭제 안 안 한 수 불러오기 */
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

// QNA 개수 가져오기
function getQnaInCount() {
  ajaxRequest('/adminQnaInListAjax', 'GET', function(response) {
      var countElement = document.querySelector('.adminQnaInCountAll');
      countElement.textContent = response;
  });
}
getQnaInCount()

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
function getQnaOutCount() {
  ajaxRequest('/adminQnaOutListAjax', 'GET', function(response) {
      var countElement = document.querySelector('.adminQnaOutCountAll');
      countElement.textContent = response;
  });
}
getQnaOutCount()

//답변 여부에 따른 구분

/* 
window.addEventListener('DOMContentLoaded', function() {
  var answerBtn = document.getElementById('answerBtn');
  var table = document.getElementById('boardTable');
  var rows = Array.from(table.getElementsByTagName('tr'));

  rows.shift(); // 첫 번째 행은 테이블의 헤더이므로 제외

  answerBtn.addEventListener('click', function(e) {
      e.preventDefault();

      // '답변여부' 열(qnaCheckFl) 값을 기준으로 내림차순 정렬
      rows.sort(function(a, b) {
          var valueA = a.querySelector('#qnaCheckFl').textContent;
          var valueB = b.querySelector('#qnaCheckFl').textContent;

          if (valueA === 'N' && valueB !== 'N') {
              return -1;
          } else if (valueA !== 'N' && valueB === 'N') {
              return 1;
          } else {
              return 0;
          }
      });

      // 정렬된 행을 다시 테이블에 추가
      var tbody = table.querySelector('tbody');
      rows.forEach(function(row) {
          tbody.appendChild(row);
      });
  });
});
/*  */
/* //비회원 글만 보기
window.addEventListener('DOMContentLoaded', function() {
  var nomemberLink = document.getElementById('nomember');
  var memberLink = document.getElementById('member');
  var table = document.getElementById('boardTable');
  var rows = Array.from(table.getElementsByTagName('tr'));

  rows.shift(); // 첫 번째 행은 테이블의 헤더이므로 제외

  nomemberLink.addEventListener('click', function(e) {
      e.preventDefault();

      // '등록인' 열의 텍스트를 기준으로 필터링하여 '비회원'인 행만 보이도록 설정
      rows.forEach(function(row) {
          var userId = row.querySelector('td:nth-child(5)').textContent;
          if (userId === '비회원') {
              row.style.display = '';
          } else {
              row.style.display = 'none';
          }
      });
  });

  memberLink.addEventListener('click', function(e) {
      e.preventDefault();

      // '등록인' 열의 텍스트를 기준으로 필터링하여 '비회원'인 행을 제외한 행만 보이도록 설정
      rows.forEach(function(row) {
          var userId = row.querySelector('td:nth-child(5)').textContent;
          if (userId !== '비회원') {
              row.style.display = '';
          } else {
              row.style.display = 'none';
          }
      });
  });
});
 */ 

//페이지네이션 답변 없을 때
    const pagination = document.querySelector('.pagination');

    pagination.addEventListener('click', (event) => {
        if (event.target.tagName === 'A') {
            event.preventDefault();
            const currentPage = event.target.textContent;
            const url = `/adminQnaAnswerYN?cp=${currentPage}`;
            window.location.href = url;
        }
    });

//페이지네이션 비회원
    const nomemberBtn = document.getElementById('nomember');

    pagination.addEventListener('click', (event) => {
        if (event.target.tagName === 'A') {
            event.preventDefault();
            const currentPage = event.target.textContent;
            const url = `/adminQnaAll?cp=${currentPage}`;
            window.location.href = url;
        }
    });

    nomemberBtn.addEventListener('click', (event) => {
        event.preventDefault();
        const url = "/adminQnaNomember";
        window.location.href = url;
    });

