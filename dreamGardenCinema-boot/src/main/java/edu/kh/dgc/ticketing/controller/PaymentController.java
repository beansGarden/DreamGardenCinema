package edu.kh.dgc.ticketing.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;

import edu.kh.dgc.ticketing.model.dto.Payment;
import edu.kh.dgc.ticketing.model.service.OrderService;
import edu.kh.dgc.ticketing.model.service.PaymentService;

@PropertySource("classpath:/config.properties")
@RequestMapping("/ticketing")
@Controller
public class PaymentController {

   @Autowired
   private PaymentService PaymentService;
   
   @Autowired
   private OrderService orderService;
   
   @Value("${port-one.RESTAPIKey}")
   private String imp_key;

   @Value("${port-one.RESTAPISecret}")
   private String imp_secret;

   @ResponseBody
   @PostMapping(value = "/verify_iamport/{imp_uid}")
   public IamportResponse<Payment> verifyIamportPOST(@PathVariable(value = "imp_uid") String imp_uid)
         throws IamportResponseException, IOException {
      
      IamportClient client = new IamportClient(imp_key, imp_secret);
		/* IamportResponse<Payment> result = client.paymentByImpUid(imp_uid); */
      
      return null;
   }


//   @RequestMapping(value ="complete", method = RequestMethod.POST)
//   @ResponseBody
//   public int paymentComplete(String imp_uid, String merchant_uid,String totalPrice, 
//         HttpSession session,Order order) throws Exception {
//       
//       String token = PaymentService.getToken();
//       
//       //결제 완료된 금액
//       String amount = PaymentService.paymentInfo(order.getImp_uid(), token);
//       
//       System.out.println("확인"+order.getTotalPrice());
//       System.out.println("확인1 : " + order.getReserNum());
//       System.out.println(amount);
//       
//       int res = 1;
//       
//       if (order.getTotalPrice() != Long.parseLong(amount)) {
//         res = 0;
//         // 결제 취소
//         PaymentService.payMentCancle(token, order.getImp_uid(), amount,"결제 금액 오류");
//         return res;
//      }
//       System.out.println("check44 : " + order.getImp_uid());
//      orderService.insert_pay(order);
//      
//      
//
//      return res;
//      return 1;
//   }
}