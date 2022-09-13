package org.zerock.myapp.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.domain.Ticket;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@CrossOrigin

@RequestMapping("/request")
@RestController
public class RequestBodyController {
	
	// ========================================================================
	// + @RequestBody : (***)
	// + 요청 메시지의 body 에 포함되어 있는 순수한 데이터(XML or JSON)를 끄집어내어, 
	// + Rest 컨트롤러의 핸들러 메소드의 매개변수에 넣어주는 역할 수행
	// ========================================================================
	
	@PostMapping(path="/ticket", produces = MediaType.APPLICATION_JSON_VALUE)
	public Ticket ticket( 
						 // @RequestBody String tickets // ok!
							@RequestBody Ticket tickets ) {
		
		log.trace( "\t + ticket({}) invoked.", tickets );
		// + ticket(Ticket(tno=1008, grade=A, price=15400.0)) invoked.
		// + 요청 메시지에 들어있는 순수한 데이터를 매개변수로 하여, Ticket으로 받아들였다.
		
		return tickets;
	} // Ticket

} // end class
