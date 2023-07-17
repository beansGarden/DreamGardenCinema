package edu.kh.dgc.common.scheduling;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.SessionStatus;

import edu.kh.dgc.mypage.model.service.MypageService;
import edu.kh.dgc.ticketing.model.dto.Ticket;
import edu.kh.dgc.ticketing.model.service.PaymentService;
import edu.kh.dgc.ticketing.model.service.TicketingService;
import edu.kh.dgc.user.model.service.UserService;
import edu.kh.dgc.user.model.dto.User;
import jakarta.servlet.ServletContext;

@Service
public class MembershipScheduling {

	@Autowired
	private MypageService service;

	@Autowired
	private PaymentService PaymentService;

	@Autowired
	private TicketingService TicketingService;

	@Autowired
	private UserService UserService;

	// 테스트용 15초
//	@Scheduled(cron = "0,15,30,45 * * * * *") 
	// 매달 1월 AM 5
	@Scheduled(cron = "0 0 5 1 * *") 
	// 10분 마다
//	@Scheduled(cron = "0 */10 * * * *")
	public void membership() {
		System.out.println("스케줄러 실행");

		Ticket ticket = new Ticket();

		// 유저 전체 쿠폰 삭제
		int deleteAllCoupon = service.deleteAllCoupon();

		// 유저 전체 RATING 1 업데이트
		int updateRating1 = service.updateRating1();

		// 누적 금액 4만원 미만 고객 등급 브론즈 업데이트
		int updateAllBronze = service.updateAllBronze();
		// 누적 금액 4만원 이상 10만원 미만 고객 실버 업데이트
		int updateAllSilver = service.updateAllSilver();
		// 누적 금액 10만원 이상 20만원 미만 고객 골드 업데이트
		int updateAllGold = service.updateAllGold();
		// 누적 금액 20만원 이상 고객 플래티넘 업데이트
		int updateAllPlatinum = service.updateAllPlatinum();

		// 브론즈 등급 고객 userNo전체 조회
		List<User> bronzeNoList = service.selectBronzeUserNo();

		// 브론즈 등급 고객 브론즈 쿠폰 insert
		if (!bronzeNoList.isEmpty()) {

			for (int i = 0; i < bronzeNoList.size(); i++) {

				User user = bronzeNoList.get(i);

				String selectNo = Integer.toString(user.getUserNo());
				
				System.out.println(selectNo);
				// 브론즈 쿠폰 1개 insert
				int insertAllBronzeCoupon = UserService.insertCoupon(selectNo);
			}

		}

		// 실버 등급 고객 userNo 전체 조회
		List<User> silverNoList = service.selectSilverUserNo();

		if (!silverNoList.isEmpty()) {

			for (int i = 0; i < silverNoList.size(); i++) {

				User user = silverNoList.get(i);

				int userNo = user.getUserNo();

				// 브론즈 쿠폰 1개,실버 쿠폰 2개 insert
				int insertAllSilverCoupon = service.insertSilverCoupon(userNo);
			}

		}

		// 골드 등급 고객 userNo 전체 조회
		List<User> goldNoList = service.selectGoldUserNo();

		if (!goldNoList.isEmpty()) {

			for (int i = 0; i < goldNoList.size(); i++) {

				User user = goldNoList.get(i);

				int userNo = user.getUserNo();

				// 브론즈 쿠폰 1개,실버 쿠폰 2개,골드 쿠폰 3개 insert
				int insertAllGoldCoupon = service.insertAllGoldCoupon(userNo);
			}

		}

		// 플래티넘 등급 고객 userNo 전체 조회
		List<User> platinumNoList = service.platinumNoList();

		if (!platinumNoList.isEmpty()) {

			for (int i = 0; i < platinumNoList.size(); i++) {

				User user = platinumNoList.get(i);

				int userNo = user.getUserNo();

				// 브론즈 쿠폰 1개,실버 쿠폰 2개,골드 쿠폰 3개,플래티넘 쿠폰 2개 insert
				int insertAllPlatinumCoupon = service.insertAllPlatinumCoupon(userNo);
			}

		}

		// 모든 유저 누적 금액 초기화
		int updateAllAmount = service.updateAllAmount();
		System.out.println(updateAllAmount);

		System.out.println("스케줄러 실행 완료");

	}
}
