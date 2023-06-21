
/* in (info, comment) 
    처음 페이지 들어갈때는 영화 정보가 먼저 보여지니까
    영화정보가 초기 타입이다.
*/
var currentInfoType = "info";

const infoBtn = document.getElementById("infoBtn");
const commentBtn = document.getElementById("commentBtn");

const infoBar = document.getElementById("infoBar");
const commentBar = document.getElementById("commentBar");

infoBtn.addEventListener("click", () => {
    
    if(currentInfoType == "comment"){

        commentBtn.classList.remove("select-active");
        infoBtn.classList.add("select-active");

        commentBar.classList.remove("selected-bar");
        infoBar.classList.add("selected-bar");

        currentInfoType = "info"

    }

});

commentBtn.addEventListener("click", () => {

    if(currentInfoType == "info"){
        infoBtn.classList.remove("select-active");
        commentBtn.classList.add("select-active");

        infoBar.classList.remove("selected-bar");
        commentBar.classList.add("selected-bar");

        currentInfoType = "comment"
    }

});