package edu.kh.dgc.ticketing.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import edu.kh.dgc.mypage.model.dto.Coupon;
import edu.kh.dgc.ticketing.model.dto.Ticket;
import edu.kh.dgc.ticketing.model.service.PaymentService;
import edu.kh.dgc.ticketing.model.service.TicketingService;
import edu.kh.dgc.user.model.dto.User;
import jakarta.servlet.http.HttpSession;

@PropertySource("classpath:/config2.properties")
@RequestMapping("/ticketing")
@Controller
public class PaymentController {

	@Autowired
	private PaymentService PaymentService;
	
	@Autowired
	private TicketingService TicketingService;

	@Value("${port-one.RESTAPIKey}")
	private String RESTAPIKey;

	@Value("${port-one.RESTAPISecret}")
	private String RESTAPISecret;

	//결제 검증(실존하는 결제인지)
	@ResponseBody
	@PostMapping("/verify_iamport/{imp_uid}")
	public IamportResponse<Payment> verifyIamportPOST(@PathVariable(value = "imp_uid") String imp_uid)
			throws IamportResponseException, IOException {

		IamportClient client = new IamportClient(RESTAPIKey, RESTAPISecret);
		IamportResponse<Payment> result = client.paymentByImpUid(imp_uid);

		return result;
	}

	@ResponseBody
	@PostMapping("/complete")
	public int paymentComplete(HttpSession session,@RequestBody Ticket ticket
			,@SessionAttribute("loginUser") User user
			,User updateUser)
			throws Exception {
		
//		int amountPaid = ticketingController.getAmountPaid();
		
//		System.out.println("amountPaid : "+ amountPaid);
		// 정보 넘길때 예매번호 생성해야함
		String token = PaymentService.getToken();
//		System.out.println("imp_uid : " + ticket.getTicketImpId());
//		System.out.println("PayAmount : " + ticket.getPayAmount());

		// 결제 완료된 금액
		String amount = PaymentService.paymentInfo(ticket.getTicketImpId(), token);
//		System.out.println("amount : "+amount);
		
		//DB결제 금액 체크
		Ticket ticketCheck = TicketingService.ticketInfo(ticket.getTicketNo());

		int res = 1;

		System.out.println("==============" + !ticketCheck.getPayAmount().equals(amount));
		// 결제된 금액과 DB의 금액이 같지 않으면
		if (!ticketCheck.getPayAmount().equals(amount)) {
			res = 0;
			// 결제 취소
			System.out.println("결제 취소");
			
			String reasonCancellationPayment = "결제 금액 오류";
			PaymentService.payMentCancle(token, ticket.getTicketImpId(), amount, reasonCancellationPayment);
			ticket.setReasonCancellationPayment(reasonCancellationPayment);
			int result1 = TicketingService.updateReasonCancellationPayment(ticket);
			
			if(result1 == 0) {
				System.out.println("updateReasonCancellationPayment : 실패");
			}
			return res;
		}
		int userAmount = user.getUserAmount();
		int totalAmount = userAmount + Integer.parseInt(amount);
		
		updateUser.setUserAmount(totalAmount);
		updateUser.setUserNo(user.getUserNo());
		
		// 결제 성공 시 
		// and 누적 금액 += , 등급 update -> 쿠폰 insert
		int result2 = TicketingService.updategetTicketImpUid(ticket);
		System.out.println("결제 성공 시 ============="+ result2);
		if(result2>0) {
			int	updateAmount = TicketingService.updateAmount(updateUser);
				
				if(updateAmount>0) {
					
					user.setUserAmount(updateUser.getUserAmount());
					
				}else {
					System.out.println("updateAmount : 실패");
				}
		}
		else{  // 결제 성공 후 정보가 업데이트 되지 않았으면
			res = 0;
		}
		return res;
	}
	
	
}