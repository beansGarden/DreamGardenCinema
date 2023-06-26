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


// 좌석 만들기(1관)
if(ticket.movieTheater == 1){
    const movieSeat = document.querySelector(".movieSeat");
    let alpha = 'A';
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
                    // 예매된 좌석이 있으면 클래스 추가("alreadyChk")
                    for(let x=0;x<chkSeatList.length;x++){
                        if(chkSeatList[x].seatNo == alpha+j){
                            seatNo.classList.add("alreadyChk");
                        }
                    }
                    const seatSpan = document.createElement('span');
                    seatSpan.innerText = j;
                    if(j==3 | j==13){
                        seatNo.style.marginLeft = "30px"
                    }

                    const inputcb = document.createElement("input");
                    inputcb.type = 'checkbox';
                    inputcb.name = 'seatList';
                    inputcb.value = alpha+j;

                    seatNo.setAttribute("seatNo", alpha+j);
                    seatNo.setAttribute("onclick",'seatClick(this)');
                    seatNo.append(seatSpan, inputcb);
                    lineDiv.append(seatNo);
                }
            }
            movieSeat.append(lineDiv);
        }
    })()
}

let socket = new SockJS("/click");
const pay = document.querySelector(".pay");
// 좌석 선택
function seatClick(e){

    if(e.classList.contains("alreadyChk")){  // 예약된 좌석 막기
        alert("이미 예약된 좌석입니다.");
        return;
    }

    let choiceSeat = document.querySelectorAll(".choiceSeat");
    
    var data = {};
    data.ticket = ticket;
    data.seatNo = e.getAttribute("seatno");



    if(!e.classList.contains("choiceSeat") && choiceSeat.length==btn1.innerText){
        alert("인원 수 만큼 선택해주세요");
        return;
    }

    socket.send(JSON.stringify(data));

    if(e.querySelector("[name=seatList]").checked){
        e.querySelector("[name=seatList]").checked = '';
    } else {
        e.querySelector("[name=seatList]").checked = 'true';
    }
    const chcSeat = document.querySelectorAll("[name=seatList]:checked");

    pay.innerHTML = movie.moviePrice * chcSeat.length;
    e.classList.toggle("choiceSeat");
}

// 웹소켓 메시지
socket.onmessage = function(event) {

    let result = event.data;
    let resultList = result.split(" ");
    const seat = document.querySelectorAll(".seat");
    console.log(result);
    for(let i=0;i<seat.length;i++){
        console.log(resultList[1]);
        console.log(resultList[1] == "alreadyChk");
        if(resultList[1] == "alreadyChk"){
            if(seat[i].getAttribute("seatno")==resultList[0]){
                seat[i].classList.add("alreadyChk");
            }
        }
        if(resultList[1] == "cancelChk"){
            if(seat[i].getAttribute("seatno")==resultList[0]){
                seat[i].classList.remove("alreadyChk");
            }
        }
        if(resultList[1] == "fail"){
            alert("이미 선택된 좌석입니다.");
        }
    }
}

const frm = document.getElementById("frm");


frm.addEventListener("submit", e=>{

    if(document.querySelectorAll("[name='seatList']:checked").length == 0){
        e.preventDefault();
        alert("좌석을 선택해주세요");
        return;
    }
});