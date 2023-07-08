
let screenType = "current";
let currentPage;
let maxPage;

const screenWaitBtn = document.getElementById("screenWait");
const screenPromiseBtn = document.getElementById("screenPromise");
const screenCurrentBtn = document.getElementById("screenCurrent");
const screenDownBtn = document.getElementById("screenDown");
const highlightBtn  = document.getElementById("highlight");
const wholeBtn = document.getElementById("whole");


screenWaitBtn.addEventListener("click", (e) => {

    currentPage = e.target.getAttribute("data-page")

    screenType = "wait"; 

    screenWaitBtn.className = "menu-active";
    screenPromiseBtn.className = "";
    screenCurrentBtn.className = "";
    screenDownBtn.className = "";
    highlightBtn.className = "";
    wholeBtn.className = "";

    selectList()

});


screenPromiseBtn.addEventListener("click", (e) => {

    screenType = "promise";
    currentPage = e.target.getAttribute("data-page")

    screenWaitBtn.className = "";
    screenPromiseBtn.className = "menu-active";
    screenCurrentBtn.className = "";
    screenDownBtn.className = "";
    highlightBtn.className = "";
    wholeBtn.className = "";

    selectList()

});


screenCurrentBtn.addEventListener("click", (e) => {

    screenType = "current";
    currentPage = e.target.getAttribute("data-page")

    screenWaitBtn.className = "";
    screenPromiseBtn.className = "";
    screenCurrentBtn.className = "menu-active";
    screenDownBtn.className = "";
    highlightBtn.className = "";
    wholeBtn.className = "";

    selectList()

});


screenDownBtn.addEventListener("click", (e) => {

    screenType = "down";
    currentPage = e.target.getAttribute("data-page")

    screenWaitBtn.className = "";
    screenPromiseBtn.className = "";
    screenCurrentBtn.className = "";
    screenDownBtn.className = "menu-active";
    highlightBtn.className = "";
    wholeBtn.className = "";

    selectList()

});


highlightBtn.addEventListener("click", (e) => {

    screenType = "highlight";
    currentPage = e.target.getAttribute("data-page")

    screenWaitBtn.className = "";
    screenPromiseBtn.className = "";
    screenCurrentBtn.className = "";
    screenDownBtn.className = "";
    highlightBtn.className = "menu-active";
    wholeBtn.className = "";

    selectList()

});


wholeBtn.addEventListener("click", (e) => {

    screenType = "all";
    currentPage = e.target.getAttribute("data-page")

    screenWaitBtn.className = "";
    screenPromiseBtn.className = "";
    screenCurrentBtn.className = "";
    screenDownBtn.className = "";
    highlightBtn.className = "";
    wholeBtn.className = "menu-active";
    
    selectList()
});




function selectList(){

    const data = {
        "screenType": screenType,
        "currentPage" : currentPage
    };

    const tbody = document.querySelector("tbody")

    tbody.innerHTML = "";

    fetch("/adminMovieManage/selectList", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(response => {

/* 
    <tbody>
        <tr th:each="list : ${movieList}">
            <td th:text="${list.movieNo}">영화번호</td>
            <td th:text="${list.movieTitle}">영화제목</td>
            <td th:text="${list.releaseDate}">개봉일</td>
            <td class="movieStatus">
                <button>상세보기</button>
                <button>수정</button>
                // <button>삭제</button>
            </td>
        </tr>
    </tbody>
 */

        for(let movie of response.selectList){
            
            const infoRow = document.createElement("tr");

            const movieNo = document.createElement("td");
            movieNo.innerText = movie.movieNo;
            infoRow.append(movieNo);

            const movieTitle = document.createElement("td");
            movieTitle.innerText = movie.movieTitle;
            infoRow.append(movieTitle);

            const movieReleaseDate = document.createElement("td");
            movieReleaseDate.innerText = movie.releaseDate;
            infoRow.append(movieReleaseDate)

            const movieStatue = document.createElement("td");

            const btnSelect = document.createElement("button");
            btnSelect.innerText = "상세보기";
            movieStatue.append(btnSelect);
            const btnUpdate = document.createElement("button");
            btnUpdate.innerText = "수정";
            movieStatue.append(btnUpdate);
            
            if(screenType == 'wait' || screenType == 'down' || screenType == 'highlight'){
            const btnDelete = document.createElement("button");
            btnDelete.innerText = "삭제";
            movieStatue.append(btnDelete);
            }

            movieStatue.classList.add("movieStatus");

            infoRow.append(movieStatue);

            tbody.append(infoRow);
        }



        if(screenType == 'wait'){
            if(selectList.length < 9 ){
                const infoRow = document.createElement("tr");

                const movieNo = document.createElement("td");
                infoRow.append(movieNo);

                const movieTitle = document.createElement("td");
                infoRow.append(movieTitle);

                const movieReleaseDate = document.createElement("td");
                infoRow.append(movieReleaseDate)

                const movieStatue = document.createElement("td");

                const btnCreate = document.createElement("button");
                btnCreate.innerText = "정보등록";
                movieStatue.append(btnCreate);

                movieStatue.classList.add("movieStatus");

                infoRow.append(movieStatue);

                tbody.append(infoRow);
            }
            const warningRow = document.createElement("tr");
            const warning = document.createElement("td");
            warning.setAttribute("colspan", 4);
            warning.innerText = "임시대기정보는 최대 8개 까지 저장가능합니다."

            warningRow.append(warning);

            tbody.append(warningRow);
        }
        
        if(screenType == 'highlight'){
            if(selectList.length < 8){
            const infoRow = document.createElement("tr");

            const movieNo = document.createElement("td");
            infoRow.append(movieNo);

            const movieTitle = document.createElement("td");
            infoRow.append(movieTitle);

            const movieReleaseDate = document.createElement("td");
            infoRow.append(movieReleaseDate)

            const movieStatue = document.createElement("td");

            const btnCreate = document.createElement("button");
            btnCreate.innerText = "정보등록";
            movieStatue.append(btnCreate);

            movieStatue.classList.add("movieStatus");

            infoRow.append(movieStatue);

            tbody.append(infoRow);
            }

            const warningRow = document.createElement("tr");
            const warning = document.createElement("td");
            warning.setAttribute("colspan", 4);
            warning.innerText = "광고는 최대 8개 까지 등록가능합니다."

            warningRow.append(warning);

            tbody.append(warningRow);
        }

    })
    .catch(err => console.log(err));

}


function pagination(element){
    
    currentPage = element.getAttribute('data-page');

    selectList();

    const paginationButtons = document.querySelectorAll('.pagination button');

    paginationButtons.forEach((button) => {
        button.className = '';
    });

    const currentPaginationButtons = document.querySelector(`.pagination button[data-page='${currentPage}']`)

    currentPaginationButtons.className = 'current'

}