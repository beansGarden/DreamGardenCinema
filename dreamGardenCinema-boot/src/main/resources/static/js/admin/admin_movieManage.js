/* 체크박스 전체선택 */
function userSelectAll(userSelectAll)  {
    const checkboxes 
       = document.querySelectorAll('input[type="checkbox"]'); //체크박스 전체 불러오기
    
    checkboxes.forEach((checkbox) => {
      checkbox.checked = userSelectAll.checked
    })
  }

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
function getMovieCount() {
    ajaxRequest('/adminMovieListAjax', 'GET', function(response) {
        var countElement = document.querySelector('.admin_movieManageCount');
        countElement.textContent = response;
    });
}

// 페이지 로드 시 영화 개수 가져오기 호출
getMovieCount();