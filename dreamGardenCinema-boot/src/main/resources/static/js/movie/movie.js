
let currentPage = 0;

/* 영화유형 ->  현재 상영 : current, 상영 예정 : promise */
var movieType = "";

document.onload = checkType();

/* 타입체크 -> 현재 상영 : current, 상영예정 : promise */
function checkType() {

    if (location.pathname == "/movie/current") {
        movieType = "current";
    }

    if (location.pathname == "/movie/promise") {
        movieType = "promise";
    }

    return movieType;
}


/*  타입(현재 상영, 상영예정)별 비동기 요청  */
const currentBtn = document.getElementById("typeCurrent");
const promiseBtn = document.getElementById("typePromise");
const moreBtn = document.getElementById("listMore");
const navMenu = document.querySelector(".nav-menu");

if (movieType != "") {

    /* 현재 상영작 버튼을 눌렀을 때 */
    currentBtn.addEventListener("click", () => {

        navMenu.innerHTML = "";

        /* 
            <button>예매순</button>
            |
            <button>평점순</button>
            |
            <button>관람평 많은순</button>
        */

        const byTicket = document.createElement("button");
        byTicket.innerText = "예매순";
        byTicket.className = "sort-active";
        byTicket.id = "byTicket";

        const byStar = document.createElement("button");
        byStar.innerText = "평점순";
        byStar.className = "sort-non-active";
        byStar.id = "byStar";
        
        const byReview = document.createElement("button");
        byReview.innerText = "관람평 많은순";
        byReview.className = "sort-non-active";
        byReview.id = "byReview";

        navMenu.append(byTicket);
        navMenu.innerHTML += " | ";
        navMenu.append(byStar);
        navMenu.innerHTML += " | ";
        navMenu.append(byReview);

        moreBtn.style.display = 'flex';

        currentPage = 0;

        if (movieType = "promise") {

            currentBtn.classList.replace("non-active", "active");
            promiseBtn.classList.replace("active", "non-active");


        }

        movieType = "current";

        document.querySelector(".movieList--items").innerHTML = "";

        const data = {
            "currentPage": currentPage,
            "movieType": movieType
        };

        fetch("/movie/list", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        })

            .then(response => response.json())

            .then(movieList => {

                /* 
                <div class="movieList--items" >
    
                    <div class="movieItem" th:each="movie : ${MovieListC}">
    
                        <div class="movie-poster-container">
                            <img class="movie-poster" th:src="${movie.poster}">
                            <img class="movie-rating" th:src="${movie.rating}">
                            <div class="movie-action">
                                <a class="font4" href="#">예매하기</a>
                                <a class="font4" th:href="@{/movie/?movie=${movie.movieNo}}">상세보기</a>
                            </div>
                        </div>
                        
                        <div class="movie-title font5" th:text="${movie.movieTitle}">영화 제목</div>

                        <div class="movie-data">
                            <span class="ratio">
                                예매율
                                <em th:text="${movie.ratio}+'%'">예매율</em>
                            </span>
                            <span class="star">
                                <img th:src="@{/images/common/main/포스터/star3.png}">
                                <em th:text="${movie.score}"></em>
                            </span>
                        </div>
    
                    </div>
    
                </div>
                */

                for (let movie of movieList) {

                    /* <div class="movieItem" th:each="movie : ${MovieListC}"> */
                    const movieItem = document.createElement("div");

                    movieItem.classList.add("movieItem");

                    /*
                        <div class="movie-poster-container">
                            <img class="movie-poster" th:src="${movie.poster}">
                            <img class="movie-rating" th:src="${movie.rating}">
                            <div class="movie-action">
                                <a class="font4" href="@{/ticketing/date/} + ${movieInfo.movieNo}">예매하기</a>
                                <a class="font4" th:href="@{/movie/?movie=${movie.movieNo}}">상세보기</a>
                            </div>
                            <div class="movie-data">
                                <span class="ratio">
                                    예매율
                                    <em th:text="${movie.ratio}+'%'">예매율</em>
                                </span>
                                <span class="star">
                                    <img th:src="@{/images/common/main/포스터/star3.png}">
                                    <em th:text="${movie.score}"></em>
                                </span>
                            </div>
                        </div> 
                    */


                    const moviePosterContainer = document.createElement("div");

                    moviePosterContainer.classList.add("movie-poster-container");

                    const moviePoster = document.createElement("img");

                    moviePoster.classList.add("movie-poster");
                    moviePoster.setAttribute("src", movie.poster);
                    moviePosterContainer.append(moviePoster);

                    const movieRating = document.createElement("img");
                    movieRating.classList.add("movie-rating");
                    movieRating.setAttribute("src", movie.rating);
                    moviePosterContainer.append(movieRating);
                    
                    const movieAction = document.createElement("div");
                    movieAction.classList.add("movie-action");
                    
                    const ticket = document.createElement("a");

                    ticket.classList.add("font4")
                    ticket.innerText = "예매하기";
                    ticket.setAttribute("href", "/ticketing/date/" + movie.movieNo);
                    
                    movieAction.append(ticket);
                    
                    const detail = document.createElement("a");
                    
                    detail.classList.add("font4")
                    detail.innerText = "상세보기";
                    detail.setAttribute("href", "/movie/movieDetail=" + movie.movieNo);

                    movieAction.append(detail);

                    moviePosterContainer.append(movieAction);

                    movieItem.append(moviePosterContainer);


                    /* <div class="movie-title font5" th:text="${movie.movieTitle}">영화 제목</div> */

                    const movieTitle = document.createElement("div");

                    movieTitle.classList.add("movie-title");
                    movieTitle.classList.add("font5");

                    movieTitle.innerText = movie.movieTitle;

                    movieItem.append(movieTitle);

                    /* 
                    <div class="movie-data">
                        <span class="ratio">
                            예매율
                            <em th:text="${movie.ratio}+'%'">예매율</em>
                        </span>
                        <span class="star">
                            <img th:src="@{/images/common/main/포스터/star3.png}">
                            <em th:text="${movie.score}"></em>
                        </span>
                    </div> 
                    */
                    
                    const movieData = document.createElement("div");

                    movieData.classList.add("movie-data");

                    /* 
                    <span class="ratio">
                        예매율
                        <em th:text="${movie.ratio}+'%'">예매율</em>
                    </span> 
                    */
                    const ratio = document.createElement("span");
                    ratio.classList.add("ratio")
                    ratio.innerText = "예매율";

                    const ratioInnerData = document.createElement("em")
                    ratioInnerData.innerText = movie.ratio + "%";

                    ratio.append(ratioInnerData);

                    /*
                    <span class="star">
                        <img th:src="@{/images/common/main/포스터/star3.png}">
                        <em th:text="${movie.score}"></em>
                    </span>
                    */
                    const star = document.createElement("span");
                    star.classList.add("star");

                    const starImg = document.createElement("img");
                    starImg.setAttribute("src", "/images/common/main/포스터/star3.png")
                    star.append(starImg);

                    const starScore = document.createElement("em");
                    starScore.innerText = movie.score;
                    star.append(starScore);
                    
                    movieData.append(ratio);
                    movieData.append(star);                    

                    movieItem.append(movieData);


                    document.querySelector(".movieList--items").append(movieItem);
                }


            })

            .catch(err => console.log(err));


    });




    /* 상영예정작 버튼을 눌렀을때 */
    promiseBtn.addEventListener("click", () => {

        navMenu.innerHTML = "";

/* 
            <button>개봉일순</button>
 */

        const byRelease = document.createElement("div");
        byRelease.innerText = "개봉일순";
        byRelease.classList.add("sort-active");

        navMenu.append(byRelease);

        moreBtn.style.display = 'flex';

        currentPage = 0;

        if (movieType = "current") {

            currentBtn.classList.replace("active", "non-active");
            promiseBtn.classList.replace("non-active", "active");

        }

        movieType = "promise";

        document.querySelector(".movieList--items").innerHTML = "";

        const data = {
            "currentPage": currentPage,
            "movieType": movieType
        };

        fetch("/movie/list", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        })

            .then(response => response.json())

            .then(movieList => {

                for (let movie of movieList) {

                    const movieItem = document.createElement("div");

                    movieItem.classList.add("movieItem");

                    const moviePosterContainer = document.createElement("div");

                    moviePosterContainer.classList.add("movie-poster-container");

                    const moviePoster = document.createElement("img");

                    moviePoster.classList.add("movie-poster");
                    moviePoster.setAttribute("src", movie.poster);
                    moviePosterContainer.append(moviePoster);

                    const movieRating = document.createElement("img");
                    movieRating.classList.add("movie-rating");
                    movieRating.setAttribute("src", movie.rating);
                    moviePosterContainer.append(movieRating);

                    movieItem.append(moviePosterContainer);

                    const movieAction = document.createElement("div");
                    movieAction.classList.add("movie-action");
                    
                    const detail = document.createElement("a");
                    
                    detail.classList.add("font4")
                    detail.innerText = "상세보기";
                    detail.setAttribute("href", "/movie/movieDetail=" + movie.movieNo);

                    movieAction.append(detail);

                    moviePosterContainer.append(movieAction);


                    const movieTitle = document.createElement("div");

                    movieTitle.classList.add("movie-title");
                    movieTitle.classList.add("font5");

                    movieTitle.innerText = movie.movieTitle;

                    movieItem.append(movieTitle);


                    document.querySelector(".movieList--items").append(movieItem);
                }

            })

            .catch(err => console.log(err));

    });

    /* 펼처보기(more)버튼을 눌렀을 때 */
    moreBtn.addEventListener("click", () => {

        currentPage += 1;

        const data = {
            "currentPage": currentPage,
            "movieType": movieType
        };

        fetch("/movie/list", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        })

            .then(response => response.json())

            .then(movieList => {

                for (let movie of movieList) {

                    const movieItem = document.createElement("div");

                    movieItem.classList.add("movieItem");

                    const moviePosterContainer = document.createElement("div");

                    moviePosterContainer.classList.add("movie-poster-container");

                    const moviePoster = document.createElement("img");

                    moviePoster.classList.add("movie-poster");
                    moviePoster.setAttribute("src", movie.poster);
                    moviePosterContainer.append(moviePoster);

                    const movieRating = document.createElement("img");
                    movieRating.classList.add("movie-rating");
                    movieRating.setAttribute("src", movie.rating);
                    moviePosterContainer.append(movieRating);

                    movieItem.append(moviePosterContainer);

                    const movieAction = document.createElement("div");
                    movieAction.classList.add("movie-action");
                    
                    if(movieType == "current"){
                    const ticket = document.createElement("a");

                    ticket.classList.add("font4")
                    ticket.innerText = "예매하기";
                    movieRating.setAttribute("src", movie.rating);
                    
                    movieAction.append(ticket);
                    }

                    const detail = document.createElement("a");
                    
                    detail.classList.add("font4")
                    detail.innerText = "상세보기";
                    detail.setAttribute("href", "/movie/movieDetail=" + movie.movieNo);

                    movieAction.append(detail);

                    moviePosterContainer.append(movieAction);


                    const movieTitle = document.createElement("div");

                    movieTitle.classList.add("movie-title");
                    movieTitle.classList.add("font5");

                    movieTitle.innerText = movie.movieTitle;

                    movieItem.append(movieTitle);

                    if(movieType = "current"){
                    
                    const movieData = document.createElement("div");

                    movieData.classList.add("movie-data");

                    const ratio = document.createElement("span");
                    ratio.classList.add("ratio")
                    ratio.innerText = "예매율";

                    const ratioInnerData = document.createElement("em")
                    ratioInnerData.innerText = movie.ratio + "%";

                    ratio.append(ratioInnerData);

                    const star = document.createElement("span");
                    star.classList.add("star");

                    const starImg = document.createElement("img");
                    starImg.setAttribute("src", "/images/common/main/포스터/star3.png")
                    star.append(starImg);

                    const starScore = document.createElement("em");
                    starScore.innerText = movie.score;
                    star.append(starScore);
                    
                    movieData.append(ratio);
                    movieData.append(star);                    

                    movieItem.append(movieData);
                    }


                    document.querySelector(".movieList--items").append(movieItem);
                }

                if(movieList.length != 10){
                    moreBtn.style.display = 'none';
                }



            })

            .catch(err => console.log(err));


    })

}
