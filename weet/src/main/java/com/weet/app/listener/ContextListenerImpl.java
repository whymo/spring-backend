package com.weet.app.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
//@NoArgsConstructor

//@WebListener // @WebListener가 없으면 web.xml에 직접 등록해줘야 한다.(***)
public class ContextListenerImpl implements ServletContextListener {
	
	// =========================================================
	
	public ContextListenerImpl () { 
		
		log.trace("Default Constructor invoked.");
		
	} // ContextListenerImpl
	
	// =========================================================

	@Override
    public void contextDestroyed(ServletContextEvent sce)  { 
         
		log.trace("contextDestroyed({}) invoked.", sce);
		
    } // contextDestroyed ( sce ) : WAS가 내려가기 직전에 수행
	
	// =========================================================

	@Override
    public void contextInitialized(ServletContextEvent sce)  { 
    	
    	log.trace("contextInitialized({}) invoked.", sce);
    	
    } // contextInitialized ( sce ) : 서비스하기 직전에 수행
	
	// =========================================================
	
} // end class
