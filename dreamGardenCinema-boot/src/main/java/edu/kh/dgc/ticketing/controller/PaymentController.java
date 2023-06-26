package edu.kh.dgc.ticketing.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import edu.kh.dgc.ticketing.model.dto.Order;
import edu.kh.dgc.ticketing.model.service.OrderService;
import edu.kh.dgc.ticketing.model.service.PaymentService;
import jakarta.servlet.http.HttpSession;

@PropertySource("classpath:/config2.properties")
@RequestMapping("/ticketing")
@Controller
public class PaymentController {

	@Autowired
	private PaymentService PaymentService;

	@Autowired
	private OrderService orderService;

	@Value("${port-one.RESTAPIKey}")
	private String RESTAPIKey;

	@Value("${port-one.RESTAPISecret}")
	private String RESTAPISecret;

	//결제 검증(실존하는 결제인지)
	@ResponseBody
	@PostMapping(value = "/verify_iamport/{imp_uid}")
	public IamportResponse<Payment> verifyIamportPOST(@PathVariable(value = "imp_uid") String imp_uid)
			throws IamportResponseException, IOException {

		IamportClient client = new IamportClient(RESTAPIKey, RESTAPISecret);
		IamportResponse<Payment> result = client.paymentByImpUid(imp_uid);

		return result;
	}

	@RequestMapping(value = "/complete", method = RequestMethod.POST)
	@ResponseBody
	public int paymentComplete(String imp_uid, String merchant_uid, String totalPrice, HttpSession session, Order order)
			throws Exception {

		String token = PaymentService.getToken();
		System.out.println("token : "+token);
		System.out.println("merchant_uid : "+merchant_uid);
		System.out.println("totalPrice : "+totalPrice);

		// 결제 완료된 금액
		String amount = PaymentService.paymentInfo(imp_uid, token);
//		String amount = PaymentService.paymentInfo(order.getImp_uid(), token);
		System.out.println("amount : "+amount);
		
//		System.out.println("확인" + order.getTotalPrice());
//		System.out.println("확인1 : " + order.getReserNum());

		int res = 1;

		if (order.getTotalPrice() != Long.parseLong(amount)) {
			res = 0;
			// 결제 취소
			PaymentService.payMentCancle(token, order.getImp_uid(), amount, "결제 금액 오류");
			return res;
		}
		System.out.println("check44 : " + order.getImp_uid());
//		orderService.insert_pay(order);

		return res;
	}
}