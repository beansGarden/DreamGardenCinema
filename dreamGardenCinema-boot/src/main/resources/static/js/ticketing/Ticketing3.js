// 기존 정보 삭제
window.onbeforeunload = function(){
    fetch("/ticketing/out", {
        method : 'POST',
        headers : {'Content-Type': 'application/json'},
        body : JSON.stringify(data)
    })
    .then(resp => resp.text())
    .then(result => {
        alert("예매 취소");
    })
}

// 쿠폰 AJAX
const coupon = document.querySelector("#coupon");
coupon.addEventListener("change", e=>{

    const couponNo = coupon.options[coupon.selectedIndex].value;
    fetch("/ticketing/coupon", {
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify({"couponNo" : couponNo, "ticketNo" : ticketNo, "moviePrice" : moviePrice, "seatSize" : seatSize})
    })
    .then(resp => resp.text())
    .then(resultPrice => {
        console.log(resultPrice);
        document.querySelector(".price>div:last-child>span").innerText = resultPrice;
    })
})

//  새로고침 막기
document.onkeydown = function(e){
    /* F5, Ctrl+r, Ctrl+F5 */
    if(e.keyCode == 116 || e.ctrlKey == true && (e.keyCode == 82)){
        e.preventDefault();
        return;
    }
}

const seatScreen = document.querySelector(".ticketingNav>li:nth-child(2)");