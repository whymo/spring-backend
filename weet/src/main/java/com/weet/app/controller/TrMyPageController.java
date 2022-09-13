package com.weet.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RequestMapping("/mypageT/") // 기본 URI ( base URI )
@Controller
public class TrMyPageController { 
	
	// =======================================================
		// 1. 트레이너회원 - 마이페이지 - 내활동 페이지 - TR게시판
	// =======================================================
	
	@RequestMapping("/activity/boardlist")
	public String myBoardList() {
		log.trace("myBoardList() invoked.");
		
		return "mypage/activity/boardlist";
	} // myBoardList
	
	// =======================================================
		// 2. 트레이너회원 - 마이페이지 - 내활동 페이지 - 좋아요
	// =======================================================
	
	@RequestMapping("/activity/boardlike")
	public String myBoardLike() {
		log.trace("myBoardLike() invoked.");
		
		return "mypage/activity/boardlike";
	} // myBoardLike
	
	// =======================================================
		// 3. 트레이너회원 - 마이페이지 - 내활동 페이지 - 댓글
	// =======================================================
	
	@RequestMapping("/activity/boardreply")
	public String myBoardReply() {
		log.trace("myBoardReply() invoked.");
		
		return "mypage/activity/boardreplye";
	} // myBoardReply
	
	// =======================================================
		// 4. 트레이너회원 - 마이페이지 - 내클래스룸 - 내클래스룸
	// =======================================================
	
	@RequestMapping("/class/my")
	public String myClass() {
		log.trace("myClass() invoked.");
		
		return "mypage/class/my";
	} // myClass
	
	// =======================================================
		// 5. 트레이너회원 - 마이페이지 - 내클래스룸 - 수강 종료 클래스룸
	// =======================================================
	
	@RequestMapping("/class/expired")
	public String expiredClass() {
		log.trace("expiredClass() invoked.");
		
		return "mypage/class/expired";
	} // expiredClass
	
	// =======================================================
		// 6. 트레이너회원 - 마이페이지 - 내클래스룸 - 예약 클래스룸
	// =======================================================
	
	@RequestMapping("/class/before")
	public String beforeClass() {
		log.trace("beforeClass() invoked.");
		
		return "mypage/class/before";
	} // beforeClass
	
	// =======================================================
		// 7. 트레이너회원 - 마이페이지 - 내클래스룸 - 찜 한 클래스룸
	// =======================================================
	
	@RequestMapping("/class/like")
	public String likeClass() {
		log.trace("likeClass() invoked.");
		
		return "mypage/class/like";
	} // likeClass
	
	// =======================================================
		// 8. 트레이너회원 - 마이페이지 - 마이바디
	// =======================================================
	
	@RequestMapping("/mybody")
	public String myBody() {
		log.trace("myBody() invoked.");
		
		return "mypage/mybody";
	} // myBody
	
	// =======================================================
		// 9. 트레이너회원 - 마이페이지 - 구매내역
	// =======================================================
	
	@RequestMapping("/paylist")
	public String payList() {
		log.trace("payList() invoked.");
		
		return "mypage/pay/paylist";
	} // paylist
	
	// =======================================================
		// 10. 트레이너회원 - 마이페이지 - 구매내역 - 구매 상세 내역
	// =======================================================
	
	@RequestMapping("/pay/detail")
	public String detailPay() {
		log.trace("detailPay() invoked.");
		
		return "mypage/pay/detail";
	} // detailPay
	
	// =======================================================
		// 11. 트레이너회원 - 마이페이지 - 내정보 - 프로필 조회
	// =======================================================
	
	@RequestMapping("/profile/myprofile")
	public String profile() {
		log.trace("profile() invoked.");
		
		return "mypage/profile/myprofile";
	} // profile
	
	// =======================================================
		// 12. 트레이너회원 - 마이페이지 - 내정보 - 프로필 수정
	// =======================================================
	
	@RequestMapping("/profile/editprofile")
	public String editProfile() {
		log.trace("editProfile() invoked.");
		
		return "mypage/profile/editprofile";
	} // editProfile
	
	// =======================================================
		// 13. 트레이너회원 - 마이페이지 - 내정보 - 알림
	// =======================================================
	
	@RequestMapping("/profile/settings")
	public String settings() {
		log.trace("settings() invoked.");
		
		return "mypage/profile/settings";
	} // settings
	
	// =======================================================
		// 14. 트레이너회원 - 마이페이지 - 내정보 - 탈퇴
	// =======================================================
	
	@RequestMapping("/profile/quit")
	public String quit() {
		log.trace("quit() invoked.");
		
		return "mypage/profile/quit";
	} // quit
   

} // end class
