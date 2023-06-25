console.log(loginUser.userEmail);
console.log(loginUser.userNickname);
console.log(loginUser.userTel);
function requestPay() {
    // IMP.request_pay(param, callback) 결제창 호출
    let uid = '';
    IMP.init("imp15468635");
    IMP.request_pay({ // param
        pg: "kakaopay.TC0ONETIME",
        pay_method: "kakaopay",
        merchant_uid: "test-000041",
        name: "범죄도시3",
        amount: 1,
        buyer_email: loginUser.userEmail,
        buyer_name: loginUser.userNickname,
        buyer_tel: loginUser.userTel
    }, function (rsp) { // callback
        if (rsp.success) {// 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
            // 결제 성공 시 로직,
            imp_uid = rsp.imp_uid;
            // 결제검증(실존하는 결제인가)
            $.ajax({
                url: '/ticketing/verify_iamport/'+imp_uid,
                type: 'POST',
                dataType: 'json'
            }).done(function (data) {
                console.log(data);
                // 결제를 요청했던 금액과 실제 결제된 금액이 같으면 해당 주문건의 결제가 정상적으로 완료된 것으로 간주한다.
                if (1 == data.response.amount) {
                    // jQuery로 HTTP 요청
                    // 주문정보 생성 및 테이블에 저장 
                    // @@ 주문정보는 상품 개수만큼 생성되어야 해서 상품 개수만큼 반복문을 돌린다
                    // 이때 order code는 모두 같아야 한다.
                    console.log("지금 1");
                    console.log("data.response.amount : "+data.response.amount);
                    // 데이터를 json으로 보내기 위해 바꿔준다.
                    data = JSON.stringify({
                        "ticketNo": rsp.merchant_uid,
                        "userNum": loginUser.userNum, // 회원번호
                        "movieName": rsp.name,
                        "orderDate": new Date().getTime(),
                        "totalPrice": rsp.amount,
                        "imp_uid": rsp.imp_uid
                    });
                    console.log(data);
                    console.log("지금 2");
                    jQuery.ajax({
                        url: "/ticketing/complete", // 예: https://www.myservice.com/payments/complete
                        type: "POST",
                        dataType: 'json',
                        contentType: 'application/json',
                        data: data
                    })
                        .done(function (res) {
                            console.log(data)
                            console.log(data.merchant_uid)
                            console.log(res)
                            if (res > 0) {
                                console.log(res);
                                alert('주문정보 저장 성공');
                                // createPayInfo(uid);
                            }
                            else {
                                console.log(data);
                                alert('주문정보 저장 실패');
                            }
                        })
                }
                else {
                    alert('결제 실패');
                }
            })
        } else {
            // alert("결제에 실패하였습니다.", "에러 내용: " + rsp.error_msg, "error");
            alert("결제에 실패하였습니다.",  "error");
        }
    });
}

