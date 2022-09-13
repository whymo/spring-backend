package org.zerock.myapp.controller;

import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.zerock.myapp.exception.ControllerException;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@RequestMapping("/sample/")
@Controller
public class SampleController {
	
	// ==================================================================================
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/doA")
	public String doA ( Locale locale, Model model ) throws ControllerException {
		// + Locale locale : Locale은 현재 거주하고 있는 지역을 얻어낼 수 있다.
		log.trace("doA() invoked.");
		
		try {
			
			model.addAttribute("serverTime", "/sample/doA");
			return"home";
			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try - catch
		
	} // doA
	
	// ==================================================================================
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/doB")
	public String doB ( Locale locale, Model model ) throws ControllerException {
		// + Locale locale : Locale은 현재 거주하고 있는 지역을 얻어낼 수 있다.
		log.trace("doB() invoked.");
		
		try {
			
			model.addAttribute("serverTime", "/sample/doB");
			return"home";
			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try - catch
		
	} // doB
	
	// ==================================================================================
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/doC")
	public String doC ( Locale locale, Model model ) throws ControllerException {
		// + Locale locale : Locale은 현재 거주하고 있는 지역을 얻어낼 수 있다.
		log.trace("doC() invoked.");
		
		try {
			
			model.addAttribute("serverTime", "/sample/doC");
			return"home";
			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try - catch
		
	} // doC
	
	// ==================================================================================
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/doD")
	public String doD ( Locale locale, Model model ) throws ControllerException {
		// + Locale locale : Locale은 현재 거주하고 있는 지역을 얻어낼 수 있다.
		log.trace("doD() invoked.");
		
		try {
			
			model.addAttribute("serverTime", "/sample/doD");
			return"home";
			
		} catch (Exception e) {
			throw new ControllerException(e);
		} // try - catch
		
	} // doD
	
	// ==================================================================================

} // end class
