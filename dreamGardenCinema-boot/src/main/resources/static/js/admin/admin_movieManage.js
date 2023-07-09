
let screenType = "current";

let currentPage;		// 현재 페이지 번호
let listCount;			// 전체 게시글 수

let limit;			// 한 페이지 목록에 보여지는 게시글 수
let pageSize;		// 보여질 페이지 번호 개수

let maxPage;			// 마지막 페이지 번호
let startPage;			// 보여지는 맨 앞 페이지 번호 
let endPage;			// 보여지는 맨 뒤 페이지 번호

let prevPage;			// 이전 페이지의 페이지 번호 맨 끝
let nextPage;			// 다음 페이지의 페이지 번호 맨 앞


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
    document.querySelector('.pagination').innerHTML = '';

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
    document.querySelector('.pagination').innerHTML = '';

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




async function selectList(){

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

        {
            currentPage = response.pagination.currentPage;		// 현재 페이지 번호
            listCount = response.pagination.listCount;			// 전체 게시글 수

            limit = response.pagination.limit;			// 한 페이지 목록에 보여지는 게시글 수
            pageSize = response.pagination.pageSize;		// 보여질 페이지 번호 개수

            maxPage = response.pagination.maxPage;			// 마지막 페이지 번호
            startPage = response.pagination.startPage;			// 보여지는 맨 앞 페이지 번호 
            endPage = response.pagination.endPage;			// 보여지는 맨 뒤 페이지 번호

            prevPage = response.pagination.prevPage;			// 이전 페이지의 페이지 번호 맨 끝
            nextPage = response.pagination.nextPage;			// 다음 페이지의 페이지 번호 맨 앞

        }

    })
    .then(reloadPageList())
    .catch(err => console.log(err));

}


function pagination(element){
    

    currentPage = element.getAttribute('data-page');
    if(currentPage == 'first'){
        currentPage = startPage;
    }
    if(currentPage == 'prev'){
        currentPage = prevPage;
    }
    if(currentPage == 'next'){
        currentPage = nextPage;
    }
    if(currentPage == 'max'){
        currentPage = endPage;
    }


    selectList();

    const paginationButtons = document.querySelectorAll('.pagination button');

    paginationButtons.forEach((button) => {
        button.className = '';
    });


    const currentPaginationButton = document.querySelector(`.pagination button[data-page='${currentPage}']`)

    currentPaginationButton.className = 'current'

}

function reloadPageList(){

/*     
    <div class="pagination-area">

        <ul class="pagination">

            <!-- 첫 페이지로 이동 -->
            <li><button data-page="first" onclick="pagination(this)">&lt;&lt;</button></li>

            <!-- 이전 목록 마지막 번호로 이동 -->
            <li><button th:data-page="prev" onclick="pagination(this)">&lt;</button></li>

            <th:block th:each="i : ${#numbers.sequence(pagination.startPage, pagination.endPage)}">

                <!-- 현재 보고있는 페이지 -->
                <li th:if="${i == pagination.currentPage}">
                    <button class="num current" th:data-page="${i}" data-page-role="num" th:text="${i}" onclick="pagination(this)">현재 페이지</button>
                </li>

                <!-- 현재 페이지를 제외한 나머지 -->
                <li th:unless="${i == pagination.currentPage}">
                    <button class="num" th:data-page="${i}" data-page-role="num" th:text="${i}"  onclick="pagination(this)">나머지 페이지</button>
                </li>

            </th:block>
            
            <!-- 다음 목록 시작 번호로 이동 -->
            <li><button th:data-page="next"  onclick="pagination(this)">&gt;</button></li>

            <!-- 끝 페이지로 이동 -->
            <li><button th:data-page="max" onclick="pagination(this)">&gt;&gt;</button></li>


        </ul>

    </div>
*/
    var paginationElement = document.querySelector('.pagination');
    paginationElement.innerHTML = '';

    var firstPageButton = document.createElement('li');
    firstPageButton.appendChild(createButton('<<', 'first', pagination));

    var prevPageButton = document.createElement('li');
    prevPageButton.appendChild(createButton('<', 'prev', pagination));

    paginationElement.appendChild(firstPageButton);
    paginationElement.appendChild(prevPageButton);

    for (let i = startPage; i <= endPage; i++) {
        var pageItem = document.createElement('li');
        var button = createButton(i.toString(), i, pagination);
        button.classList.add('num');
        if (i === currentPage) {
            button.classList.add('current');
        }

        pageItem.appendChild(button);
        paginationElement.appendChild(pageItem);
    }

    var nextPageButton = document.createElement('li');
    nextPageButton.appendChild(createButton('>', 'next', pagination));

    var lastPageButton = document.createElement('li');
    lastPageButton.appendChild(createButton('>>', 'max', pagination));

    paginationElement.appendChild(nextPageButton);
    paginationElement.appendChild(lastPageButton);

}

function createButton(text, dataPage, clickHandler) {
    var button = document.createElement('button');
    button.textContent = text;
    button.dataset.page = dataPage;
    button.addEventListener('click', function() {
        clickHandler(this);
    });

    return button;
}