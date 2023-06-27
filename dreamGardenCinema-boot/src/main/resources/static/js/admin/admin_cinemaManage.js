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







  /* 2관으로 넘어가기 */
  const cinemaList = document.querySelectorAll('.admin_cinemaOne');

cinemaList.forEach(cinema => {
  cinema.addEventListener('click', function (event) {
    event.preventDefault();

    const cinemaNumber = this.textContent[0];

    fetch(`/adminCinemaManage/${cinemaNumber}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ 'movieTheater': cinemaNumber })
    })
      .then(resp => resp.text())
      .then(result => {
        console.log(result);
        console.log(cinemaNumber)
        .then(data => {
          // 서버로부터 받은 데이터(data)를 처리하는 부분
          // 예시: 받은 데이터를 콘솔에 출력
          console.log(data);
          
          // 리다이렉션을 수행해야 하는 경우
          if (data.redirect) {
            window.location.href = data.redirect; // 받은 리다이렉션 URL로 이동
          } else {
            // 리다이렉션이 아닌 경우 추가적인 처리
          }
        })
        .catch(error => {
          console.log('Fetch Error:', error);

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
          const cinemaManageAll = document.createElement("div");
          cinemaManageAll.classList.add("admin_cinemaManageAll");

          // 1관
          const cinemaOne = document.createElement("div");
          cinemaOne.classList.add("admin_cinemaOne");
          cinemaOne.id = "cinemaOne";
          const cinemaOneLink = document.createElement("a");
          cinemaOneLink.href = "#";
          cinemaOneLink.innerText = "1관";
          cinemaOne.appendChild(cinemaOneLink);
          cinemaManageAll.appendChild(cinemaOne);

          // 2관
          const cinemaTwo = document.createElement("div");
          cinemaTwo.classList.add("admin_cinemaOne");
          cinemaTwo.id = "cinemaTwo";
          const cinemaTwoLink = document.createElement("a");
          cinemaTwoLink.href = "@{/cinema/{movieTheater}(movieTheater=${movieTheater})}";
          cinemaTwoLink.innerText = "2관";
          cinemaTwo.appendChild(cinemaTwoLink);
          cinemaManageAll.appendChild(cinemaTwo);

          // 3관
          const cinemaThree = document.createElement("div");
          cinemaThree.classList.add("admin_cinemaOne");
          cinemaThree.id = "cinemaThree";
          const cinemaThreeLink = document.createElement("a");
          cinemaThreeLink.href = "#";
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
          // const cinemaList = []; // Replace this with your actual cinema list data

          // for (let list of cinemaList) {
          //   const cinemaTr = document.createElement("tr");
          //   const checkbox = document.createElement("input");
          //   checkbox.type = "checkbox";
          //   checkbox.classList.add("admin_cinemaManageCheckbox");
          //   const td1 = document.createElement("td");
          //   td1.appendChild(checkbox);
          //   const td2 = document.createElement("td");
          //   td2.innerText = list.movieNo;
          //   const td3 = document.createElement("td");
          //   td3.innerText = list.movieTitle;
          //   const td4 = document.createElement("td");
          //   td4.innerText = list.runningTime;
          //   const td5 = document.createElement("td");
          //   td5.innerText = list.releaseDate;
          //   const td6 = document.createElement("td");
          //   td6.innerText = list.movieday;
          //   const td7 = document.createElement("td");
          //   td7.innerText = list.movieTime;

          //   cinemaTr.appendChild(td1);
          //   cinemaTr.appendChild(td2);
          //   cinemaTr.appendChild(td3);
          //   cinemaTr.appendChild(td4);
          //   cinemaTr.appendChild(td5);
          //   cinemaTr.appendChild(td6);
          //   cinemaTr.appendChild(td7);
          //   tbody.appendChild(cinemaTr);
          // }

          scheduleTable.appendChild(thead);
          scheduleTable.appendChild(tbody);
          cinemaManageBoardWrap.appendChild(scheduleTable);
          cinemaManageWrap.appendChild(cinemaManageBoardWrap);



        });



    });



  }); 

});

 
   /* cinemaList.forEach(cinema => {
    cinema.addEventListener('click', function (event) {
      event.preventDefault();
  
      const cinemaNumber = this.querySelector('a').innerText[0];
  
      fetch(`/adminCinemaManage/${cinemaNumber}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ 'movieTheater': cinemaNumber })
      })
        .then(resp => resp.text())
        .then(result => {
          console.log(result);
          console.log(cinemaNumber);
  
          // 기존 테이블 내용 지우기
          const tbody = document.querySelector("#schedule-table tbody");
          tbody.innerHTML = "";
  
          // 새로운 데이터로 테이블 채우기
          const cinemaList = JSON.parse(result); // 새로운 데이터를 파싱
          cinemaList.forEach(movie => {
            const cinemaTr = document.createElement("tr");
            const checkbox = document.createElement("input");
            checkbox.type = "checkbox";
            checkbox.classList.add("admin_cinemaManageCheckbox");
            const td1 = document.createElement("td");
            td1.appendChild(checkbox);
            const td2 = document.createElement("td");
            td2.innerText = movie.movieNo;
            const td3 = document.createElement("td");
            td3.innerText = movie.movieTitle;
            const td4 = document.createElement("td");
            td4.innerText = movie.runningTime;
            const td5 = document.createElement("td");
            td5.innerText = movie.releaseDate;
            const td6 = document.createElement("td");
            td6.innerText = movie.movieday;
            const td7 = document.createElement("td");
            td7.innerText = movie.movieTime;
  
            cinemaTr.appendChild(td1);
            cinemaTr.appendChild(td2);
            cinemaTr.appendChild(td3);
            cinemaTr.appendChild(td4);
            cinemaTr.appendChild(td5);
            cinemaTr.appendChild(td6);
            cinemaTr.appendChild(td7);
            tbody.appendChild(cinemaTr);
          });
  
        });
    });
  }); 
   
/* 
  cinemaList.forEach(cinema => {
    cinema.addEventListener('click', function (event) {
      event.preventDefault();
  
      const cinemaNumber = this.querySelector('a').innerText[0];
  
      fetch(`/adminCinemaManage/${cinemaNumber}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ 'movieTheater': cinemaNumber })
      })
        .then(resp => resp.text())
        .then(result => {
          console.log(result);
          console.log(cinemaNumber);
  
          // 기존 테이블 내용 지우기
          const tbody = document.querySelector("#schedule-table tbody");
          tbody.innerHTML = ""; // 기존 내용을 비웁니다.
  
          // 새로운 데이터로 테이블 채우기
          const cinemaList = JSON.parse(result); // 새로운 데이터를 파싱
          cinemaList.forEach(movie => {
            const cinemaTr = document.createElement("tr");
            const checkbox = document.createElement("input");
            checkbox.type = "checkbox";
            checkbox.classList.add("admin_cinemaManageCheckbox");
            const td1 = document.createElement("td");
            td1.appendChild(checkbox);
            const td2 = document.createElement("td");
            td2.innerText = movie.movieNo;
            const td3 = document.createElement("td");
            td3.innerText = movie.movieTitle;
            const td4 = document.createElement("td");
            td4.innerText = movie.runningTime;
            const td5 = document.createElement("td");
            td5.innerText = movie.releaseDate;
            const td6 = document.createElement("td");
            td6.innerText = movie.movieday;
            const td7 = document.createElement("td");
            td7.innerText = movie.movieTime;
  
            cinemaTr.appendChild(td1);
            cinemaTr.appendChild(td2);
            cinemaTr.appendChild(td3);
            cinemaTr.appendChild(td4);
            cinemaTr.appendChild(td5);
            cinemaTr.appendChild(td6);
            cinemaTr.appendChild(td7);
            tbody.appendChild(cinemaTr);
          });
  
        });
    });
  }); */
  