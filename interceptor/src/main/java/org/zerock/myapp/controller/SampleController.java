package org.zerock.myapp.controller;

import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Log4j2
@NoArgsConstructor


@RequestMapping("/sample")
@Controller("sampleController")
public class SampleController {
	
	
	
	@ResponseStatus(code=HttpStatus.OK)
	
	@GetMapping("/doA")
	public String doA(Locale locale, Model model) {
		log.debug("doA(locale, model) invoked.");
		
		log.info("\t+ locale: " + locale + ", type: " + locale.getClass().getName());
		log.info("\t+ model: " + model + ", type: " + model.getClass().getName());
		log.info("");
		
		model.addAttribute("serverTime", "/sample/doA");
		
		return "home";
	} // doA
	
	
	@GetMapping("/doB")
	public String doB(Locale locale, Model model) {
		log.debug("doB(locale, model) invoked.");
		
		log.info("\t+ locale: " + locale + ", type: " + locale.getClass().getName());
		log.info("\t+ model: " + model + ", type: " + model.getClass().getName());
		log.info("");
		
		model.addAttribute("serverTime", "/sample/doB");
		
		Integer.parseInt("삼");
		
		return "home";
	} // doB
	
	
	@GetMapping("/doC")
	public String doC(Model model) {
		log.debug("doC(model) invoked.");

		log.info("\t+ model: " + model + ", type: " + model.getClass().getName());
		log.info("");
		
		model.addAttribute("serverTime", "/sample/doC");
		model.addAttribute("result", "OK");
		
		return "home";
	} // doC
	
	
	@GetMapping("/doD")
	public String doD(Model model) {
		log.debug("doD(model) invoked.");
		
		log.info("");

		model.addAttribute("serverTime", "/sample/doD");
		model.addAttribute("result", "OK");
		
		return "home";
	} // doD

} // end class
