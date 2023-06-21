
function requestPay() {
    // IMP.request_pay(param, callback) 결제창 호출
    IMP.init("imp15468635");
    IMP.request_pay({ // param
        pg: "kakaopay",
        pay_method: "card",
        merchant_uid: "test-000003",
        name: "범죄도시3",
        amount: 1,
        buyer_email: "ckcc0813@gmail.com",  
        buyer_name: "홍길동",
        buyer_tel: "010-4242-4242",
        buyer_addr: "서울특별시 강남구 신사동",
        buyer_postcode: "01181"
    }, function (rsp) { // callback
        if (rsp.success) {
            // 결제 성공 시 로직,
            console.log(rsp);
        } else {
            // 결제 실패 시 로직,
            console.log(rsp);
        }
    });
}