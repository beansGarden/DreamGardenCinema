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
}

let socket = new SockJS("/click");

// 좌석 선택
function seatClick(e){

    let choiceSeat = document.querySelectorAll(".choiceSeat");
    
    const seatNo = e.getAttribute("seatno");
    var data = {};
    
    data.userNo = loginUser.userNo;
    data.movieNo = movie.movieNo;
    data.movieTheater = ticket.movieTheater;
    data.movieTime = ticket.movieTime;
    data.seatNo = seatNo;
    if(e.classList.contains("choiceSeat")){
        data.checked = 'Y';
    } else {
        data.checked = 'N';
        if(choiceSeat.length==btn1.innerText){
            alert("인원 수 만큼 선택해주세요");
            return;
        }
    }

    console.log(data);

    socket.send(JSON.stringify(data));

    e.classList.toggle("choiceSeat");
}

socket.onmessage = function(event) {

    let result = event.data;
    let resultList = result.split(" ");
    const seat = document.querySelectorAll(".seat");
    for(let i=0;i<seat.length;i++){
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