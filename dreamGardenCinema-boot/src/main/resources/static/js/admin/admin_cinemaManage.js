/* 체크박스 전체선택 */
function cinemaSelectAll(cinemaSelectAll) { //온클릭 
  const checkboxes
    = document.querySelectorAll('input[type="checkbox"]'); //체크박스 전체 불러오기

  checkboxes.forEach((checkbox) => { //체크박스 전체 선택
    checkbox.checked = cinemaSelectAll.checked //체크박스누르면 
  })
}
//체크박스 숫자 불러오기
function cinemaSelectAll(checkbox) {
  var checkboxes = document.getElementsByClassName('admin_cinemaCheckbox');
  var count = document.getElementsByClassName('admin_cinemaCount')[0];
  var countAll = document.getElementsByClassName('admin_cinemaCountAll')[0];
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

var checkboxes = document.getElementsByClassName('admin_cinemaCheckbox');
for (var i = 0; i < checkboxes.length; i++) {
  checkboxes[i].addEventListener('change', function () {
    var count = document.getElementsByClassName('admin_cinemaCount')[0];
    var checkedCount = document.querySelectorAll('.admin_cinemaCheckbox:checked').length;
    count.textContent = checkedCount.toString();
  });
}

var countAll = document.getElementsByClassName('admin_cinemaCountAll')[0];
var totalItems = document.querySelectorAll('.admin_cinemaCheckbox').length;
countAll.textContent = totalItems.toString();


/* 날짜 선택해서 hidden에 넣기 */
function setHiddenValue() {
  // 선택한 날짜 요소 가져오기
  var selectedDateElement = document.getElementById("selectedDate");

  // 숨겨진 값에 선택한 날짜 할당
  var hiddenValueElement = document.getElementById("hiddenValue");
  hiddenValueElement.value = selectedDateElement.value;
}

/* 날짜 입력 비동기 */
function getMovieScheduleByDay() {
  var movieday = document.getElementById("hiddenValue").value;

  fetch("/adminMovieSchedule?movieday=" + movieday)
    .then(function (response) {
      if (response.ok) {
        return response.json();
      } else {
        throw new Error("AJAX Error: " + response.status);
      }
    })
    .then(function (data) {
      // 처리할 로직 작성
    })
    .catch(function (error) {
      console.log(error);
    });
}


//공지사항 영화 개수 전체 불러오기*****************************************************************

// Ajax 요청 함수
function ajaxRequest(url, method, successCallback) {
  var xhr = new XMLHttpRequest();
  xhr.open(method, url, true);
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      successCallback(xhr.responseText);
    }
  };
  xhr.send();
}

// 영화 개수 가져오기
function getCinemaCount() {
  ajaxRequest('/adminCinemaListAjax', 'GET', function (response) {
    var countElement = document.querySelector('.admin_cinemaCountAll');
    countElement.textContent = response;
  });
}

getCinemaCount()
/* *********************************************************************************************** */

function first() {
  const existingCinemaItems = document.querySelectorAll(".admin_cinemaOne");
  existingCinemaItems.forEach((item) => {
    /*  item.remove();  */
  });
  const movieTheater = '1';

  fetch(`/adminCinemaManage/${movieTheater}`)
    .then((resp) => resp.json())
    .then((cinemaMap) => {
      const cinemaList = cinemaMap.cinemaList;


      for (let cList of cinemaList) {
        console.log(cList);
        
        
              const tbody = document.querySelector("tbody");
              tbody.innerHTML = "";

        // tbody1 생성
        const table1 = document.querySelector("table");
        const tbody1 = document.createElement("tbody");
        const cinemaManageBoardWrap = document.createElement("div");
        cinemaManageBoardWrap.classList.add("admin_cinemaManageBoardWrap");
        const scheduleTable = document.createElement("table");
        scheduleTable.id = "schedule-table";
        const thead = document.createElement("thead");
        const tr = document.createElement("tr");
        const th1 = document.createElement("th");
        const checkboxAll = document.createElement("input");
        checkboxAll.type = "checkbox";
        checkboxAll.classList.add("admin_cinemaManageCheckboxAll");
        th1.appendChild(checkboxAll);
        const th2 = document.createElement("th");
        th2.innerText = "영화 번호";
        const th3 = document.createElement("th");
        th3.innerText = "제목";
        const th4 = document.createElement("th");
        th4.innerText = "러닝타임 (분)";
        const th5 = document.createElement("th");
        th5.innerText = "개봉일";
        const th6 = document.createElement("th");
        th6.innerText = "상영일";
        const th7 = document.createElement("th");
        th7.innerText = "상영시간";

        tr.appendChild(th1);
        tr.appendChild(th2);
        tr.appendChild(th3);
        tr.appendChild(th4);
        tr.appendChild(th5);
        tr.appendChild(th6);
        tr.appendChild(th7);
        thead.appendChild(tr);


        // 각각의 cinemaTr 생성
        const cinemaTr = document.createElement("tr");
        const checkbox = document.createElement("input");
        checkbox.type = "checkbox";
        checkbox.classList.add("admin_cinemaManageCheckbox");
        const td1 = document.createElement("td");
        td1.appendChild(checkbox);
        cinemaTr.appendChild(td1);
        const td2 = document.createElement("td");
        td2.innerHTML = cList.movieNo;
        cinemaTr.appendChild(td2);
        const td3 = document.createElement("td");
        td3.innerHTML = cList.movieTitle;
        cinemaTr.appendChild(td3);
        const td4 = document.createElement("td");
        td4.innerHTML = cList.runningTime;
        cinemaTr.appendChild(td4);
        const td5 = document.createElement("td");
        td5.innerHTML = cList.releaseDate;
        cinemaTr.appendChild(td5);
        const td6 = document.createElement("td");
        td6.innerHTML = cList.movieday;
        cinemaTr.appendChild(td6);
        const td7 = document.createElement("td");
        td7.innerHTML = cList.movieTime;
        cinemaTr.appendChild(td7);


        tbody1.appendChild(cinemaTr);
        table1.appendChild(tbody1);


      }
    });
}


/* *********************************************************************************************** */

function second() {
  const existingCinemaItems = document.querySelectorAll(".admin_cinemaOne");
  existingCinemaItems.forEach((item) => {
    /*  item.remove();  */
  });
  const movieTheater = '2';

  fetch(`/adminCinemaManage/${movieTheater}`)
    .then((resp) => resp.json())
    .then((cinemaMap) => {
      const cinemaList = cinemaMap.cinemaList;


      
      for (let cList of cinemaList) {
        console.log(cList);
        
        const tbody = document.querySelector("tbody");
        tbody.innerHTML = "";

        // tbody1 생성
        const table2 = document.querySelector("table");
        const tbody1 = document.createElement("tbody");


        // 각각의 cinemaTr 생성
        const cinemaTr = document.createElement("tr");
        const checkbox = document.createElement("input");
        checkbox.type = "checkbox";
        checkbox.classList.add("admin_cinemaManageCheckbox");
        const td1 = document.createElement("td");
        td1.appendChild(checkbox);
        cinemaTr.appendChild(td1);
        const td2 = document.createElement("td");
        td2.innerHTML = cList.movieNo;
        cinemaTr.appendChild(td2);
        const td3 = document.createElement("td");
        td3.innerHTML = cList.movieTitle;
        cinemaTr.appendChild(td3);
        const td4 = document.createElement("td");
        td4.innerHTML = cList.runningTime;
        cinemaTr.appendChild(td4);
        const td5 = document.createElement("td");
        td5.innerHTML = cList.releaseDate;
        cinemaTr.appendChild(td5);
        const td6 = document.createElement("td");
        td6.innerHTML = cList.movieday;
        cinemaTr.appendChild(td6);
        const td7 = document.createElement("td");
        td7.innerHTML = cList.movieTime;
        cinemaTr.appendChild(td7);

        tbody1.appendChild(cinemaTr);
        table2.appendChild(tbody1);

      }
       
    });
}
/* *********************************************************************************************** */

function third() {
  const existingCinemaItems = document.querySelectorAll(".admin_cinemaOne");
  existingCinemaItems.forEach((item) => {
    /*  item.remove();  */
  });
  const movieTheater = '3';

  fetch(`/adminCinemaManage/${movieTheater}`)
    .then((resp) => resp.json())
    .then((cinemaMap) => {
      const cinemaList = cinemaMap.cinemaList;

        
      for (let cList of cinemaList) {
        
        const tbody = document.querySelector("tbody");
        tbody.innerHTML = "";

        // tbody3 생성
        const table3 = document.querySelector("table");
        const tbody1 = document.createElement("tbody");


        // 각각의 cinemaTr 생성
        const cinemaTr = document.createElement("tr");
        const checkbox = document.createElement("input");
        checkbox.type = "checkbox";
        checkbox.classList.add("admin_cinemaManageCheckbox");
        const td1 = document.createElement("td");
        td1.appendChild(checkbox);
        cinemaTr.appendChild(td1);
        const td2 = document.createElement("td");
        td2.innerHTML = cList.movieNo;
        cinemaTr.appendChild(td2);
        const td3 = document.createElement("td");
        td3.innerHTML = cList.movieTitle;
        cinemaTr.appendChild(td3);
        const td4 = document.createElement("td");
        td4.innerHTML = cList.runningTime;
        cinemaTr.appendChild(td4);
        const td5 = document.createElement("td");
        td5.innerHTML = cList.releaseDate;
        cinemaTr.appendChild(td5);
        const td6 = document.createElement("td");
        td6.innerHTML = cList.movieday;
        cinemaTr.appendChild(td6);
        const td7 = document.createElement("td");
        td7.innerHTML = cList.movieTime;
        cinemaTr.appendChild(td7);

        tbody1.appendChild(cinemaTr);
        table3.appendChild(tbody1);


      }
    });
}