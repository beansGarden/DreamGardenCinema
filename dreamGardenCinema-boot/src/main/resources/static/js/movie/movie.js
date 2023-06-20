
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

if (movieType != "") {

    /* 현재 상영작 버튼을 눌렀을 때 */
    currentBtn.addEventListener("click", () => {

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
                        </div>
                        
                        <div class="movie-title font5" th:text="${movie.movieTitle}">영화 제목</div>
    
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

                    movieItem.append(moviePosterContainer);


                    /* <div class="movie-title font5" th:text="${movie.movieTitle}">영화 제목</div> */

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

    /* 상영예정작 버튼을 눌렀을때 */
    promiseBtn.addEventListener("click", () => {

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


                    const movieTitle = document.createElement("div");

                    movieTitle.classList.add("movie-title");
                    movieTitle.classList.add("font5");

                    movieTitle.innerText = movie.movieTitle;

                    movieItem.append(movieTitle);


                    document.querySelector(".movieList--items").append(movieItem);
                }

                if(movieList.length != 10){
                    moreBtn.style.display = 'none';
                }



            })

            .catch(err => console.log(err));


    })

}
