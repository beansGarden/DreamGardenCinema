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
const delBtn = document.getElementById("deleteBtn"); //삭제버튼 
const checkbox = document.getElementsByClassName("admin_faqCheckbox"); //check박스
const checkboxNo = document.getElementsByClassName("admin_faq_checkbox_no"); //번호

delBtn.addEventListener(('click'),()=>{

  const flCheck = document.querySelectorAll(".flCheck");
  
  for(let i=0; i<flCheck.length;i++){
    
  if(flCheck[i].innerText === 'Y')
 
   confirm("이미 삭제된 게시글 입니다")

   return;

} 
   
  if (confirm("정말 삭제 하시겠습니까?")) {
  for(let i=0; i<checkbox.length; i++){
    if (checkbox[i].checked) {
 var faqNo = document.getElementsByClassName("admin_faq_checkbox_no")[i].innerText //체크박스 옆 숫자 =  공지번호

} if(checkbox!=null){
faqDelete(faqNo);

}}

}else return;

}
);


function faqDelete(faqNo){



fetch("/adminFaq/deleteFaqList", {
  method : "POST",
  headers : {"Content-Type": "application/json"},
  body : JSON.stringify({"FAQNo" : parseInt(faqNo)})
}).then(resp=> resp.text())
.then(result=>{
  console.log(result);
  console.log(faqNo); //번호 나옴

}).catch(err=> console.log(err));

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