// 기존 정보 삭제

window.onbeforeunload = function(){

    fetch("/ticketing/out", {
        method : 'POST',
        headers : {'Content-Type': 'application/json'},
        body : JSON.stringify(data)
    })
    .then(resp => resp.text())
    .then(result => {
    })
}
if(window.performance.navigation.type === 1){
    location.href = 'http://localhost/';
}



// 쿠폰 AJAX
const coupon = document.querySelector("#coupon");
coupon.addEventListener("change", e=>{

    const couponNo = coupon.options[coupon.selectedIndex].value;
    fetch("/ticketing/coupon", {
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify({"couponNo" : couponNo, "ticketNo" : ticketNo})
    })
    .then(resp => resp.text())
    .then(resultPrice => {
        document.querySelector(".price>div:last-child>span").innerText = resultPrice;
    })
})

// //  새로고침 막기
document.onkeydown = function(e){
    /* F5, Ctrl+r, Ctrl+F5 */
    if(e.keyCode == 116 || e.ctrlKey == true && (e.keyCode == 82)){
        e.preventDefault();
        return;
    }
}


const seatScreen = document.querySelector(".ticketingNav>li:nth-child(2)");

function seatback(){
    let f = document.createElement('form');
    
    const theater = document.querySelector(".seat>span:first-child>span:first-child").innerText[0];
    const time = document.querySelector(".date>span:last-child").innerText.substr(0,5);

    let beforedate = document.querySelector(".date>span:first-child").innerText.split(".");
    let newdate = "20" + beforedate[0] + beforedate[1] + beforedate[2].substr(0,2) + " " + beforedate[2].substr(3).split(")")[0];
    

    let obj;
    obj = document.createElement('input');
    obj.setAttribute('type', 'hidden');
    obj.setAttribute('name', 'movieTime');
    obj.setAttribute('value', theater+","+time);

    let obj1;
    obj1 = document.createElement('input');
    obj1.setAttribute('type', 'hidden');
    obj1.setAttribute('name', "date");
    obj1.setAttribute('value', newdate);

    let obj2;
    obj2 = document.createElement('input');
    obj2.setAttribute('type', 'hidden');
    obj2.setAttribute('name', 'movieNo');
    obj2.setAttribute('value', movieNo);
    
    f.append(obj, obj1, obj2);
    f.setAttribute('method', 'post');
    f.setAttribute('action', '/ticketing/seat');
    document.body.appendChild(f);
    f.submit();
}

// function listView(userid){
//     let f = document.createElement('form');
    
//     let obj;
//     obj = document.createElement('input');
//     obj.setAttribute('type', 'hidden');
//     obj.setAttribute('name', 'userid');
//     obj.setAttribute('value', userid);
    
//     f.appendChild(obj);
//     f.setAttribute('method', 'post');
//     f.setAttribute('action', 'view.do');
//     document.body.appendChild(f);
//     f.submit();
// }
