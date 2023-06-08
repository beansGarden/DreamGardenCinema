const hamburgerBtn = document.querySelector(".hamburgerBtn");
const pizzaBtn = document.querySelector(".pizzaBtn");
const hamburger = document.querySelector(".hamburger");
const pizza = document.querySelector(".pizza");
hamburgerBtn.addEventListener("click", ()=>{  /* 햄버거 버튼 누르면 제목 리스트 나옴 */
    hamburger.style.display = "block";
    pizza.style.display = "none";
})
pizzaBtn.addEventListener("click", ()=>{  /* 피자 버튼 누르면 포스터 추가된 리스트 나옴 */
    pizza.style.display = "block";
    hamburger.style.display = "none";
})

const checkMovie = document.querySelectorAll(".MovieList>label");
let sameLabel;
for(let i=0;i<checkMovie.length;i++){
    checkMovie[i].addEventListener("click", e=>{
        for(let j=0;j<checkMovie.length;j++){
            checkMovie[j].style.border = "none";
        }
        sameLabel = e.currentTarget.getAttribute("for");
        for(let j=0;j<checkMovie.length;j++){
            if(checkMovie[j].getAttribute("for") == sameLabel){
                checkMovie[j].style.border = "1px solid #0E0753";
            }
        }
    })
}

const choiceDate = document.querySelectorAll(".swiper-slide");
for(let i=0;i<choiceDate.length;i++){
    choiceDate[i].addEventListener("click", e=>{
        for(let j=0;j<choiceDate.length;j++){
            choiceDate[j].querySelector("a strong").style.backgroundColor = '';
            choiceDate[j].querySelector("a strong").style.color = "black";
        }
        sameLabel = e.currentTarget.querySelector("a label").getAttribute("for");
        for(let j=0;j<choiceDate.length;j++){
            if(choiceDate[j].querySelector("a label").getAttribute("for") == sameLabel){
                choiceDate[j].querySelector("a strong").style.backgroundColor = "#0E0753";
                choiceDate[j].querySelector("a strong").style.color = "white";
            }
        }
    })
}