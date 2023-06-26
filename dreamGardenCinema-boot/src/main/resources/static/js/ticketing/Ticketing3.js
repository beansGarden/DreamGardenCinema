// const btnPayment = document.querySelector(".btn_payment");
// btnPayment.addEventListener("click", ()=>{

//     fetch("/ticketing/kakaopay", {
//         method : "POST",
//         headers : {"Content-Type" : "application/json"},
//         body : JSON.stringify(data)
//     })
//     .then(resp => resp.json)
//     .then(data => {
//         alert(data.tid);
//     })


// });

// $('.btn_payment').click(function(){
//     $.ajax({
//         url:'/ticketing/kakaopay',
//         dataType:'json',
//         success:function(result){
//             alert(result);
//             console.log(result);
//         },
//         error:function(error){
//             alert(error);
//         }
//     });
// });


// btnPayment.addEventListener("click", ()=>{

//     const IMP = window.IMP;
//     IMP.init("imp28360342");

// });













const seatScreen = document.querySelector(".ticketingNav>li:nth-child(2)");