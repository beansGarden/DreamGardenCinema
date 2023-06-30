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
    .then(function(response) {
      if (response.ok) {
        return response.json();
      } else {
        throw new Error("AJAX Error: " + response.status);
      }
    })
    .then(function(data) {
      // 처리할 로직 작성
    })
    .catch(function(error) {
      console.log(error);
    });
}


  //공지사항 영화 개수 전체 불러오기*****************************************************************

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
function getCinemaCount() {
    ajaxRequest('/adminCinemaListAjax', 'GET', function(response) {
        var countElement = document.querySelector('.admin_cinemaCountAll');
        countElement.textContent = response;
    });
}

getCinemaCount()
/* *********************************************************************************************** */

function first() {

  const CinemalistContents = document.querySelector(".admin_cinemaManageAll");
  const existingCinemaItems = document.querySelectorAll(".admin_cinemaOne");
  existingCinemaItems.forEach((item) => {
    /* item.remove(); */
  });
  const movieTheater = '1'; 

  fetch(`/adminCinemaManage/${movieTheater}`)
    .then((resp) => resp.json())
    .then((cinemaList) => {
      console.log(cinemaList);

      for (let cList of cinemaList) {


        /* const tbody1 = document.querySelector("#schedule-table tbody");
        tbody1.innerHTML = ""; */

        // 전체 감싸는 div
        const cinemaManageWrap = document.createElement("div");
        cinemaManageWrap.classList.add("admin_cinemaManageWrap");

        // 상영관 관리 제목
        const cinemaManageTitle = document.createElement("span");
        cinemaManageTitle.id = "admin_cinemaManageTitle";
        cinemaManageTitle.innerText = cList.innerText
        cinemaManageWrap.appendChild(cinemaManageTitle);

        // 상영관별 보이기 div
        const cinemaManageAll = document.querySelector(".admin_cinemaManageAll");

      // 1관
      const cinemaOne = document.createElement("div");
      cinemaOne.classList.add("admin_cinemaOne");
      cinemaOne.id = "cinemaOne";
      const cinemaOneLink = document.createElement("span");
      cinemaOneLink.innerText = "1관";
      cinemaOne.appendChild(cinemaOneLink);
      cinemaManageAll.appendChild(cinemaOne);

      // 2관
      const cinemaTwo = document.createElement("div");
      cinemaTwo.classList.add("admin_cinemaOne");
      cinemaTwo.id = "cinemaTwo";
      const cinemaTwoLink = document.createElement("span");
      cinemaTwoLink.innerText = "2관";
      cinemaTwo.appendChild(cinemaTwoLink);
      cinemaManageAll.appendChild(cinemaTwo);

      // 3관
      const cinemaThree = document.createElement("div");
      cinemaThree.classList.add("admin_cinemaOne");
      cinemaThree.id = "cinemaThree";
      const cinemaThreeLink = document.createElement("span");
      cinemaThreeLink.innerText = "3관";
      cinemaThree.appendChild(cinemaThreeLink);
      cinemaManageAll.appendChild(cinemaThree);


              // 상영 날짜
        const cinemaManageDate = document.createElement("div");
        cinemaManageDate.classList.add("admin_cinemaManageDate");
        const dateInput = document.createElement("input");
        dateInput.type = "date";
        cinemaManageDate.appendChild(dateInput);
        cinemaManageAll.appendChild(cinemaManageDate);

        cinemaManageWrap.appendChild(cinemaManageAll);

        // 검색창
        const cinemaManageSearchWrap = document.createElement("div");
        cinemaManageSearchWrap.classList.add("admin_cinemaManageSearchWrap");

        const cinemaSearchWrap2 = document.createElement("div");
        cinemaSearchWrap2.classList.add("admin_cinemaSearchWrap2");

        // 검색 select
        const cinemaManageSelect = document.createElement("select");
        cinemaManageSelect.classList.add("admin_cinemaManageSelect");
        const titleOption = document.createElement("option");
        titleOption.value = "title";
        titleOption.innerText = "제목";
        const directorOption = document.createElement("option");
        directorOption.value = "director";
        directorOption.innerText = "감독";
        const actorOption = document.createElement("option");
        actorOption.value = "actor";
        actorOption.innerText = "배우";
        cinemaManageSelect.appendChild(titleOption);
        cinemaManageSelect.appendChild(directorOption);
        cinemaManageSelect.appendChild(actorOption);
        cinemaSearchWrap2.appendChild(cinemaManageSelect);

        // 검색 input
        const cinemaManageInput = document.createElement("input");
        cinemaManageInput.type = "text";
        cinemaManageInput.classList.add("admin_cinemaManageInput");
        cinemaSearchWrap2.appendChild(cinemaManageInput)

        cinemaManageSearchWrap.appendChild(cinemaSearchWrap2);
        cinemaManageWrap.appendChild(cinemaManageSearchWrap);

        // 목록 count
        const cinemaManageList = document.createElement("div");
        cinemaManageList.classList.add("admin_cinemaManageList");
        const countSpan1 = document.createElement("span");
        countSpan1.innerText = "목록";
        const countSpan2 = document.createElement("span");
        countSpan2.innerText = "( 총";
        const countSpan3 = document.createElement("span");
        countSpan3.classList.add("admin_cinemaManageCount");
        countSpan3.innerText = "0";
        const countSpan4 = document.createElement("span");
        countSpan4.innerText = "개)";
        cinemaManageList.appendChild(countSpan1);
        cinemaManageList.appendChild(countSpan2);
        cinemaManageList.appendChild(countSpan3);
        cinemaManageList.appendChild(countSpan4);
        cinemaManageWrap.appendChild(cinemaManageList);

        // 게시판
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
        const tbody = document.createElement("tbody");
         const cinemaList = []; // Replace this with your actual cinema list data

         for (let list of cinemaList) {
           const cinemaTr = document.createElement("tr");
           const checkbox = document.createElement("input");
           checkbox.type = "checkbox";
           checkbox.classList.add("admin_cinemaManageCheckbox");
           const td1 = document.createElement("td");
           td1.appendChild(checkbox);
           const td2 = document.createElement("td");
           td2.innerText = list.movieNo;
           const td3 = document.createElement("td");
           td3.innerText = list.movieTitle;
           const td4 = document.createElement("td");
           td4.innerText = list.runningTime;
           const td5 = document.createElement("td");
           td5.innerText = list.releaseDate;
           const td6 = document.createElement("td");
           td6.innerText = list.movieday;
           const td7 = document.createElement("td");
           td7.innerText = list.movieTime;

           cinemaTr.appendChild(td1);
           cinemaTr.appendChild(td2);
           cinemaTr.appendChild(td3);
           cinemaTr.appendChild(td4);
           cinemaTr.appendChild(td5);
           cinemaTr.appendChild(td6);
           cinemaTr.appendChild(td7);
           tbody.appendChild(cinemaTr);
         }

        scheduleTable.appendChild(thead);
        scheduleTable.appendChild(tbody);
        cinemaManageBoardWrap.appendChild(scheduleTable);
        cinemaManageWrap.appendChild(cinemaManageBoardWrap);
      }

    });


  }

/* 2관****************************************************************************************** */
  function second() {

    const CinemalistContents = document.querySelector(".admin_cinemaManageAll");
    const existingCinemaItems = document.querySelectorAll(".admin_cinemaOne");
    existingCinemaItems.forEach((item) => {
      // item.remove();
    });
  
    const movieTheater = '2'; //M인 카테고리 타이틀
  
    fetch("/adminCinemaManage/" + movieTheater)
      .then((resp) => resp.json())
      .then((cinemaList) => {
        console.log(cinemaList);
  
        for (let cList of cinemaList) {
  
  
          const tbody1 = document.querySelector("#schedule-table tbody");
          tbody1.innerHTML = "";
  
          // 전체 감싸는 div
          const cinemaManageWrap = document.createElement("div");
          cinemaManageWrap.classList.add("admin_cinemaManageWrap");
  
          // 상영관 관리 제목
          const cinemaManageTitle = document.createElement("span");
          cinemaManageTitle.id = "admin_cinemaManageTitle";
          cinemaManageTitle.innerText = "상영관 관리";
          cinemaManageWrap.appendChild(cinemaManageTitle);
  
          // 상영관별 보이기 div
          const cinemaManageAll = document.querySelector(".admin_cinemaManageAll");
  
        // 1관
        const cinemaOne = document.createElement("div");
        cinemaOne.classList.add("admin_cinemaOne");
        cinemaOne.id = "cinemaOne";
        const cinemaOneLink = document.createElement("span");
        cinemaOneLink.innerText = "1관";
        cinemaOne.appendChild(cinemaOneLink);
        cinemaManageAll.appendChild(cinemaOne);
  
        // 2관
        const cinemaTwo = document.createElement("div");
        cinemaTwo.classList.add("admin_cinemaOne");
        cinemaTwo.id = "cinemaTwo";
        const cinemaTwoLink = document.createElement("span");
        cinemaTwoLink.innerText = "2관";
        cinemaTwo.appendChild(cinemaTwoLink);
        cinemaManageAll.appendChild(cinemaTwo);
  
        // 3관
        const cinemaThree = document.createElement("div");
        cinemaThree.classList.add("admin_cinemaOne");
        cinemaThree.id = "cinemaThree";
        const cinemaThreeLink = document.createElement("span");
        cinemaThreeLink.innerText = "3관";
        cinemaThree.appendChild(cinemaThreeLink);
        cinemaManageAll.appendChild(cinemaThree);
  
  
                // 상영 날짜
          const cinemaManageDate = document.createElement("div");
          cinemaManageDate.classList.add("admin_cinemaManageDate");
          const dateInput = document.createElement("input");
          dateInput.type = "date";
          cinemaManageDate.appendChild(dateInput);
          cinemaManageAll.appendChild(cinemaManageDate);
  
          cinemaManageWrap.appendChild(cinemaManageAll);
  
          // 검색창
          const cinemaManageSearchWrap = document.createElement("div");
          cinemaManageSearchWrap.classList.add("admin_cinemaManageSearchWrap");
  
          const cinemaSearchWrap2 = document.createElement("div");
          cinemaSearchWrap2.classList.add("admin_cinemaSearchWrap2");
  
          // 검색 select
          const cinemaManageSelect = document.createElement("select");
          cinemaManageSelect.classList.add("admin_cinemaManageSelect");
          const titleOption = document.createElement("option");
          titleOption.value = "title";
          titleOption.innerText = "제목";
          const directorOption = document.createElement("option");
          directorOption.value = "director";
          directorOption.innerText = "감독";
          const actorOption = document.createElement("option");
          actorOption.value = "actor";
          actorOption.innerText = "배우";
          cinemaManageSelect.appendChild(titleOption);
          cinemaManageSelect.appendChild(directorOption);
          cinemaManageSelect.appendChild(actorOption);
          cinemaSearchWrap2.appendChild(cinemaManageSelect);
  
          // 검색 input
          const cinemaManageInput = document.createElement("input");
          cinemaManageInput.type = "text";
          cinemaManageInput.classList.add("admin_cinemaManageInput");
          cinemaSearchWrap2.appendChild(cinemaManageInput)
  
          cinemaManageSearchWrap.appendChild(cinemaSearchWrap2);
          cinemaManageWrap.appendChild(cinemaManageSearchWrap);
  
          // 목록 count
          const cinemaManageList = document.createElement("div");
          cinemaManageList.classList.add("admin_cinemaManageList");
          const countSpan1 = document.createElement("span");
          countSpan1.innerText = "목록";
          const countSpan2 = document.createElement("span");
          countSpan2.innerText = "( 총";
          const countSpan3 = document.createElement("span");
          countSpan3.classList.add("admin_cinemaManageCount");
          countSpan3.innerText = "0";
          const countSpan4 = document.createElement("span");
          countSpan4.innerText = "개)";
          cinemaManageList.appendChild(countSpan1);
          cinemaManageList.appendChild(countSpan2);
          cinemaManageList.appendChild(countSpan3);
          cinemaManageList.appendChild(countSpan4);
          cinemaManageWrap.appendChild(cinemaManageList);
  
          // 게시판
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
          const tbody = document.createElement("tbody");
           const cinemaList = []; // Replace this with your actual cinema list data
  
           for (let list of cinemaList) {
             const cinemaTr = document.createElement("tr");
             const checkbox = document.createElement("input");
             checkbox.type = "checkbox";
             checkbox.classList.add("admin_cinemaManageCheckbox");
             const td1 = document.createElement("td");
             td1.appendChild(checkbox);
             const td2 = document.createElement("td");
             td2.innerText = list.movieNo;
             const td3 = document.createElement("td");
             td3.innerText = list.movieTitle;
             const td4 = document.createElement("td");
             td4.innerText = list.runningTime;
             const td5 = document.createElement("td");
             td5.innerText = list.releaseDate;
             const td6 = document.createElement("td");
             td6.innerText = list.movieday;
             const td7 = document.createElement("td");
             td7.innerText = list.movieTime;
  
             cinemaTr.appendChild(td1);
             cinemaTr.appendChild(td2);
             cinemaTr.appendChild(td3);
             cinemaTr.appendChild(td4);
             cinemaTr.appendChild(td5);
             cinemaTr.appendChild(td6);
             cinemaTr.appendChild(td7);
             tbody.appendChild(cinemaTr);
           }
  
          scheduleTable.appendChild(thead);
          scheduleTable.appendChild(tbody);
          cinemaManageBoardWrap.appendChild(scheduleTable);
          cinemaManageWrap.appendChild(cinemaManageBoardWrap);
        }
  
      });
  
  
    }




    function third() {

      const CinemalistContents = document.querySelector(".admin_cinemaManageAll");
      const existingCinemaItems = document.querySelectorAll(".admin_cinemaOne");
      existingCinemaItems.forEach((item) => {
       /*  item.remove(); */
      });
    
      const movieTheater = '3'; //M인 카테고리 타이틀
    
      fetch("/adminCinemaManage/" + movieTheater)
        .then((resp) => resp.json())
        .then((cinemaList) => {
          console.log(cinemaList);
    
          for (let cList of cinemaList) {
    
    
            const tbody1 = document.querySelector("#schedule-table tbody");
            tbody1.innerHTML = "";
    
            // 전체 감싸는 div
            const cinemaManageWrap = document.createElement("div");
            cinemaManageWrap.classList.add("admin_cinemaManageWrap");
    
            // 상영관 관리 제목
            const cinemaManageTitle = document.createElement("span");
            cinemaManageTitle.id = "admin_cinemaManageTitle";
            cinemaManageTitle.innerText = "상영관 관리";
            cinemaManageWrap.appendChild(cinemaManageTitle);
    
            // 상영관별 보이기 div
            const cinemaManageAll = document.querySelector(".admin_cinemaManageAll");
    
          // 1관
          const cinemaOne = document.createElement("div");
          cinemaOne.classList.add("admin_cinemaOne");
          cinemaOne.id = "cinemaOne";
          const cinemaOneLink = document.createElement("span");
          cinemaOneLink.innerText = "1관";
          cinemaOne.appendChild(cinemaOneLink);
          cinemaManageAll.appendChild(cinemaOne);
    
          // 2관
          const cinemaTwo = document.createElement("div");
          cinemaTwo.classList.add("admin_cinemaOne");
          cinemaTwo.id = "cinemaTwo";
          const cinemaTwoLink = document.createElement("span");
          cinemaTwoLink.innerText = "2관";
          cinemaTwo.appendChild(cinemaTwoLink);
          cinemaManageAll.appendChild(cinemaTwo);
    
          // 3관
          const cinemaThree = document.createElement("div");
          cinemaThree.classList.add("admin_cinemaOne");
          cinemaThree.id = "cinemaThree";
          const cinemaThreeLink = document.createElement("span");
          cinemaThreeLink.innerText = "3관";
          cinemaThree.appendChild(cinemaThreeLink);
          cinemaManageAll.appendChild(cinemaThree);
    
    
                  // 상영 날짜
            const cinemaManageDate = document.createElement("div");
            cinemaManageDate.classList.add("admin_cinemaManageDate");
            const dateInput = document.createElement("input");
            dateInput.type = "date";
            cinemaManageDate.appendChild(dateInput);
            cinemaManageAll.appendChild(cinemaManageDate);
    
            cinemaManageWrap.appendChild(cinemaManageAll);
    
            // 검색창
            const cinemaManageSearchWrap = document.createElement("div");
            cinemaManageSearchWrap.classList.add("admin_cinemaManageSearchWrap");
    
            const cinemaSearchWrap2 = document.createElement("div");
            cinemaSearchWrap2.classList.add("admin_cinemaSearchWrap2");
    
            // 검색 select
            const cinemaManageSelect = document.createElement("select");
            cinemaManageSelect.classList.add("admin_cinemaManageSelect");
            const titleOption = document.createElement("option");
            titleOption.value = "title";
            titleOption.innerText = "제목";
            const directorOption = document.createElement("option");
            directorOption.value = "director";
            directorOption.innerText = "감독";
            const actorOption = document.createElement("option");
            actorOption.value = "actor";
            actorOption.innerText = "배우";
            cinemaManageSelect.appendChild(titleOption);
            cinemaManageSelect.appendChild(directorOption);
            cinemaManageSelect.appendChild(actorOption);
            cinemaSearchWrap2.appendChild(cinemaManageSelect);
    
            // 검색 input
            const cinemaManageInput = document.createElement("input");
            cinemaManageInput.type = "text";
            cinemaManageInput.classList.add("admin_cinemaManageInput");
            cinemaSearchWrap2.appendChild(cinemaManageInput)
    
            cinemaManageSearchWrap.appendChild(cinemaSearchWrap2);
            cinemaManageWrap.appendChild(cinemaManageSearchWrap);
    
            // 목록 count
            const cinemaManageList = document.createElement("div");
            cinemaManageList.classList.add("admin_cinemaManageList");
            const countSpan1 = document.createElement("span");
            countSpan1.innerText = "목록";
            const countSpan2 = document.createElement("span");
            countSpan2.innerText = "( 총";
            const countSpan3 = document.createElement("span");
            countSpan3.classList.add("admin_cinemaManageCount");
            countSpan3.innerText = "0";
            const countSpan4 = document.createElement("span");
            countSpan4.innerText = "개)";
            cinemaManageList.appendChild(countSpan1);
            cinemaManageList.appendChild(countSpan2);
            cinemaManageList.appendChild(countSpan3);
            cinemaManageList.appendChild(countSpan4);
            cinemaManageWrap.appendChild(cinemaManageList);
    
            // 게시판
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
            const tbody = document.createElement("tbody");
             const cinemaList = []; // Replace this with your actual cinema list data
    
             for (let list of cinemaList) {
               const cinemaTr = document.createElement("tr");
               const checkbox = document.createElement("input");
               checkbox.type = "checkbox";
               checkbox.classList.add("admin_cinemaManageCheckbox");
               const td1 = document.createElement("td");
               td1.appendChild(checkbox);
               const td2 = document.createElement("td");
               td2.innerText = list.movieNo;
               const td3 = document.createElement("td");
               td3.innerText = list.movieTitle;
               const td4 = document.createElement("td");
               td4.innerText = list.runningTime;
               const td5 = document.createElement("td");
               td5.innerText = list.releaseDate;
               const td6 = document.createElement("td");
               td6.innerText = list.movieday;
               const td7 = document.createElement("td");
               td7.innerText = list.movieTime;
    
               cinemaTr.appendChild(td1);
               cinemaTr.appendChild(td2);
               cinemaTr.appendChild(td3);
               cinemaTr.appendChild(td4);
               cinemaTr.appendChild(td5);
               cinemaTr.appendChild(td6);
               cinemaTr.appendChild(td7);
               tbody.appendChild(cinemaTr);
             }
    
            scheduleTable.appendChild(thead);
            scheduleTable.appendChild(tbody);
            cinemaManageBoardWrap.appendChild(scheduleTable);
            cinemaManageWrap.appendChild(cinemaManageBoardWrap);
          }
    
        });
    
    
      }
  
  
  
  
  