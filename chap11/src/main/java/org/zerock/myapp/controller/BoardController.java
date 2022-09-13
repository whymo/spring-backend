package org.zerock.myapp.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.BoardService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Controller
@RequestMapping("/board/*")	// 기본 URI
public class BoardController implements InitializingBean {
	
	// + Bean 의존성 주입
	// + 자동 의존성 주입 발생 : (1) 주입받을 필드가 1개 (2) 이 필드를 매개변수로 가지는 생성자
	// + 위와 같은 상황일 때에는 따로 주입 시그널을 보내지 않아도 자동으로 주입된다.
	@Setter(onMethod_= {@Autowired})
	private BoardService boardService;
	
	// ===============================================
	// + 1. 게시판 목록조회 요청 처리 핸들러 메소드 
	// ===============================================
	
	// @ResponseStatus(code = HttpStatus.OK) : 컨트롤러 메소드 테스트 수행시 오류를 발생시킬 수 있다.
	@GetMapping("/list")	// 상세 URI
	public void list (Model model) throws ControllerException {
		log.info("list({}) invoked.", model);
		
		try {
			
			// + 목록 조회하는 service
			List<BoardVO> list = this.boardService.getAllList();
			
			// + 아래와 같은 방법 외에도 model이 아닌 Map으로 받아서, 
			// + model에 저장하는 것이 아니라 Map에 저장하는 방법도 있다.
			// Map<String, Object> map = new HashMap<>();
			// map.put("__LIST__", list);
			
			// + 리스트가 비어있지 않을 때 model로 바인딩
			if ( list != null ) {
				
				model.addAttribute("__LIST__",list);
				// + model은 Request Scope에 저장하게 되는데,
				// + 이때 공유 영역에서는 NULL을 저장하지 않는다.
				// + 그렇기에 이 경우 사실 if문은 필요 없다.
				
				list.forEach(log::info);
				
			} // if
			
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try - catch : ServiceException을 ControllerException으로
		
	} // list
	
	// ===============================================
	// + 특정 게시물 조회 처리 요청 메소드
	// ===============================================
	
	@GetMapping({"/get", "/modify"})	// 상세 URI
	public void get (BoardDTO dto, Model model) throws ControllerException {
		log.info("get({}) invoked.", model);
		
		try {
			
			// + 목록 조회하는 service
			BoardVO vo = this.boardService.get(dto);
			log.info("\t + vo : {}", vo);
			
			model.addAttribute("__BOARD__",vo);
			
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try - catch : ServiceException을 ControllerException으로
		
	} // list
	
	// ===============================================
	// + 2. 새로운 게시물 등록 처리 요청 메소드 (***)
	// ===============================================
	// + 단순 View 호출은 GET방식, 
	// + 비지니스 처리를 하기 위해서는 Post로 해야 한다.
	// ===============================================
	// + 전송파라미터를 객체로 얻어내면, Spring에서 이것을 Command Object라고 부르며
	// + Model을 사용하지 않아도 자동으로 View까지 전송된다. (***)
	// ===============================================
	
	@PostMapping("/register")
	public String register (BoardDTO dto, RedirectAttributes rttrs) throws ControllerException {
		
		log.trace("\t register({}, {}) invoked.", dto, rttrs);
		
		try {
			
			// + 필드에 주입받은 서비스 객체의 메소드 호출 -> 핵심 메소드 호출
			if ( this.boardService.add(dto) == 1 ) {
				rttrs.addAttribute("__RESULT__", "success");
			} else {
				rttrs.addAttribute("__RESULT__", "failed");
			} // if - else
			
			// + 업데이트 한 후에는 전체 목록 조회 페이지로 이동해야 한다. (***)
			return "redirect:/board/list";
			
		} catch(Exception e) { 
			throw new ControllerException (e);
		} // try - catch
		
	} // register
	
	// ===============================================
	// + 등록 요청 메소드에서 return을 "redirect:/board/list"로 지정하였기에
	// + 만약 등록 요청이 들어왔다면, 요청처리 후 리스트로 돌아가게 된다.
	// ===============================================
	
	@PostMapping("/register2")
	public String register2 (BoardDTO dto, RedirectAttributes rttrs) throws ControllerException {
		
		log.trace("\t register2({}, {}) invoked.", dto, rttrs);
		
		try {
			
			// + 필드에 주입받은 서비스 객체의 메소드 호출 -> 핵심 메소드 호출
			if ( this.boardService.addAuto(dto) ) {
				
				rttrs.addAttribute("__RESULT__", "success");
				
				// rttrs.addFlashAttribute("__RESULT__", "success");
				// + addFlashAttribute도 Request Scope을 통해 전달은 가능하지만, 추천하지는 x
				
			} else {
				rttrs.addAttribute("__RESULT__", "failed");
			} // if - else
			
			// + 업데이트 한 후에는 전체 목록 조회 페이지로 이동해야 한다. (***)
			return "redirect:/board/list";
			
		} catch(Exception e) { 
			throw new ControllerException (e);
		} // try - catch
		
	} // register
	
	// ===============================================
	// + 3. 게시판에 특정 게시물 삭제 요청 메소드
	// ===============================================
	
	@PostMapping("/remove")
	public String remove( BoardDTO dto, RedirectAttributes rttrs ) throws ControllerException {
		
		log.info("remove({}, {}) invoked.", dto, rttrs );
		
		try {
			
			if ( this.boardService.remove(dto) == 1 ) {
				rttrs.addAttribute("__RESULT__", "success");
			} else {
				rttrs.addAttribute("__RESULT__", "failed");
			} // if - else
			
			return "redirect:/board/list";
			
		} catch(Exception e) {
			throw new ControllerException (e);
		} // try - catch
		
	} // remove
	
	// ===============================================
	// + 4. 새로운 게시물 수정 요청 메소드
	// ===============================================
	
	@PostMapping("/update")
	public String update ( BoardDTO dto, RedirectAttributes rttrs ) throws ControllerException {
		
		log.info("update({}, {}) invoked.", dto, rttrs);
		
		try {
			
			if ( this.boardService.update(dto) ) {
				rttrs.addAttribute("__RESULT__", "success");
			} else {
				rttrs.addAttribute("__RESULT__", "failed");
			} // if - else
			
			return "redirect:/board/list";
			
		} catch (Exception e) { throw new ControllerException (e); } // try - catch
		
	} // update
	
	// ===============================================
	// + 5. 신규 게시물 등록화면 요청 처리
	// ===============================================
	
	@GetMapping("/register")
	public void register() {
		
		log.trace("register() invoked.");

	} // register
	
	// ===============================================
	// + 선처리
	// ===============================================
	@Override
	public void afterPropertiesSet() throws Exception {
		
		log.trace("afterPropertiesSet() invoked.");
		
		// 필드에 의존성이 잘 주입 되었는지 확인한다.
		Objects.requireNonNull(this.boardService);
		log.info("\t + this.boardService : {}", this.boardService);
		
	} // afterPropertiesSet

} // end class
