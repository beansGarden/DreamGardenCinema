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


//검색 리스트 불러오기
function getSearchList(){
	$.ajax({
		type: 'GET',
		url : "/getSearchList",
		data : $("form[name=search-form]").serialize(),
		success : function(result){
			//테이블 초기화
			$('#boardTable > tbody').empty();
			if(result.length>=1){
				result.forEach(function(item){
					str='<tr>'
					str += "<td>"+item.idx+"</td>";
					str+="<td>"+item.writer+"</td>";
					str+="<td><a href = '/qna/detail?idx=" + item.idx + "'>" + item.title + "</a></td>";
					str+="<td>"+item.date+"</td>";
					str+="<td>"+item.hit+"</td>";
					str+="</tr>"
					$('#boardTable').append(str);
        		})				 
			}
		}
	})
}