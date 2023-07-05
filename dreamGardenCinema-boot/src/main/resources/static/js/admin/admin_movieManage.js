
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

    

    screenType = "wait"; 

    screenWaitBtn.className = "colorsubbutton";
    screenPromiseBtn.className = "";
    screenCurrentBtn.className = "";
    screenDownBtn.className = "";
    highlightBtn.className = "";
    wholeBtn.className = "";

    selectList()

});


screenPromiseBtn.addEventListener("click", () => {

    screenType = "promise";

    screenWaitBtn.className = "";
    screenPromiseBtn.className = "colorsubbutton";
    screenCurrentBtn.className = "";
    screenDownBtn.className = "";
    highlightBtn.className = "";
    wholeBtn.className = "";

    selectList()

});


screenCurrentBtn.addEventListener("click", () => {

    screenType = "current";

    screenWaitBtn.className = "";
    screenPromiseBtn.className = "";
    screenCurrentBtn.className = "colorsubbutton";
    screenDownBtn.className = "";
    highlightBtn.className = "";
    wholeBtn.className = "";

    selectList()

});


screenDownBtn.addEventListener("click", () => {

    screenType = "down";

    screenWaitBtn.className = "";
    screenPromiseBtn.className = "";
    screenCurrentBtn.className = "";
    screenDownBtn.className = "colorsubbutton";
    highlightBtn.className = "";
    wholeBtn.className = "";

    selectList()

});


highlightBtn.addEventListener("click", () => {

    screenType = "highlight";

    screenWaitBtn.className = "";
    screenPromiseBtn.className = "";
    screenCurrentBtn.className = "";
    screenDownBtn.className = "";
    highlightBtn.className = "colorsubbutton";
    wholeBtn.className = "";

    selectList()

});


wholeBtn.addEventListener("click", () => {

    screenType = "all";

    screenWaitBtn.className = "";
    screenPromiseBtn.className = "";
    screenCurrentBtn.className = "";
    screenDownBtn.className = "";
    highlightBtn.className = "";
    wholeBtn.className = "colorsubbutton";
    
    selectList()
});




function selectList(){

    const data = {
        "screenType": screenType
    };

    const tbody = document.querySelector("tbody")

    tbody.innerHTML = "";

    fetch("/adminMovieManage/selectList", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(selectList => {

/* 
    <tbody>
        <tr th:each="list : ${movieList}">
            <td th:text="${list.movieNo}">영화번호</td>
            <td th:text="${list.movieTitle}">영화제목</td>
            <td th:text="${list.releaseDate}">개봉일</td>
            <td class="movieStatus">
                <button>상세보기</button>
                <button>수정</button>
                <button>삭제</button>
            </td>
        </tr>
    </tbody>
 */

        for(let movie of selectList){

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
            const btnDelete = document.createElement("button");
            btnDelete.innerText = "삭제";
            movieStatue.append(btnDelete);

            movieStatue.classList.add("movieStatus");

            infoRow.append(movieStatue);

            tbody.append(infoRow);
        }
        
    })
    .catch(err => console.log(err));

}