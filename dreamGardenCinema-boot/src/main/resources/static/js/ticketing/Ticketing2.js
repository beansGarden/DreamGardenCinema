const btn1 = document.querySelector("#btn1");
const plusBtn = document.querySelector("#plus");
const minusBtn = document.querySelector("#minus");

// 인원 수 덧셈 뺄셈
plusBtn.addEventListener("click", ()=>{
    if(btn1.innerText>7){
        return;
    }
    btn1.innerText++;
})
minusBtn.addEventListener("click", ()=>{
    let choiceSeat = document.querySelectorAll(".choiceSeat");
    if(choiceSeat.length==btn1.innerText){
        alert("좌석을 인원 수에 맞게 선택해주세요");
        return;
    }
    if(btn1.innerText<1){
        return;
    }
    btn1.innerText--;
})


// 좌석 만들기
const movieSeat = document.querySelector(".movieSeat");
let alpha = 'A';
// String.fromCharCode(alpha.charCodeAt())
(function () {
    for(let i=0;i<10;i++){
        const lineDiv = document.createElement("div");
        lineDiv.classList.add("seatLine");
        lineDiv.classList.add(`col${i}`);
        
        for(let j=0;j<15;j++){
            if(j==0){
                const span = document.createElement('span');
                span.innerText = alpha;
                lineDiv.append(span);
                alpha = String.fromCharCode(alpha.charCodeAt() + 1);
            } else{
                const seatNo = document.createElement("a");
                seatNo.classList.add("seat");
                const seatSpan = document.createElement('span');
                seatSpan.innerText = j;
                if(j==3 | j==13){
                    seatNo.style.marginLeft = "30px"
                }
                seatNo.setAttribute("seatNo", alpha+j);
                seatNo.setAttribute("onclick",'seatClick(this)');
                seatNo.append(seatSpan);
                lineDiv.append(seatNo);
            }
        }
        movieSeat.append(lineDiv);
    }
})()

function seatClick(e){
    let choiceSeat = document.querySelectorAll(".choiceSeat");
    if(choiceSeat.length==btn1.innerText){
        if(!e.classList.contains("choiceSeat")){
            alert("인원 수 만큼 선택해주세요");
        }
        e.classList.remove("choiceSeat");
        return;
    }

    e.classList.toggle("choiceSeat");
}