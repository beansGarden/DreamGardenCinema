console.log(loginUser.userEmail);
console.log(loginUser.userNickname);
console.log(loginUser.userTel);

function requestPay() {

    fetch("/ticketing/info", {
        method : 'POST',
        headers : {'Content-Type' : 'application/json'},
        body : JSON.stringify({"ticketNo" : ticketNo})
    })
    .then(resp=>resp.json())
    .then(ticketInfo => {

        if(ticketInfo == null){
            alert("잘못된 정보입니다.");
            return;
        }

        const movieTitle = ticketInfo.movieTitle;
        const payAmount = Number(ticketInfo.payAmount);
        const ticketId = ticketInfo.ticketId;
        // IMP.request_pay(param, callback) 결제창 호출
        const screenPay = document.querySelector(".price>div:last-child>span").innerText;

        console.log("DB 값 : " + payAmount);
        console.log("화면 값 : " + screenPay);

        // 화면의 값을 바꿨을 때 요청 X
        if(payAmount == screenPay){
            let imp_uid = '';
            IMP.init("imp15468635");
            IMP.request_pay({ // param
                pg: "kakaopay.TC0ONETIME",
                pay_method: "card",
                merchant_uid: ticketId,
                name: movieTitle,
                amount: payAmount,
                buyer_email: loginUser.userEmail,
                buyer_name: loginUser.userNickname,
                buyer_tel: loginUser.userTel
            }, function (rsp) { // callback
                if (rsp.success) {// 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
                    // 결제 성공 시 로직,
                    imp_uid = rsp.imp_uid;
                    console.log(rsp);
                    // 결제검증(실존하는 결제인가)
                    $.ajax({
                        url: '/ticketing/verify_iamport/' + imp_uid,
                        type: 'POST'
                    }).done(function (data) {
                        // 주문정보 생성 및 테이블에 d저장 
                        console.log("지금 1"); 
                        // 데이터를 json으로 보내기 위해 바꿔준다.
                        data = JSON.stringify({
                            "payAmount": data.response.amount,
                            "ticketNo" : ticketNo,
                            "ticketImpId": imp_uid // 바뀐 imp_uid
                        });
                        console.log(data);
                        console.log("지금 2");
                        $.ajax({
                            url: "/ticketing/complete", // 예: https://www.myservice.com/payments/complete
                            type: "POST",
                            dataType: 'json',
                            contentType: 'application/json',
                            data: data
                        })
                        .done(function (res) {
                            if (res > 0) {
                                alert('예매가 완료되었습니다');
                                location.href = "/ticketing/complete/"+ticketNo;
                            }
                            else {
                                alert('예매 실패!');
                                location.href = "/ticketing/date";
                            }
                        })
                        
                    })
                } else {
                    alert("결제에 실패하였습니다.", "에러 내용: " + rsp.error_msg, "error");
                    location.href = "/ticketing/date";
                }
            });
        } else {
            alert("금액을 확인해주세요");
        }
    })
}

