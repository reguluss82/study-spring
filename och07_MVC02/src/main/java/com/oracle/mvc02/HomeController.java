package com.oracle.mvc02;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/board/view")
	public String view() {
		logger.info("Welcome home! {} start...", "/board/view");
		return "board/view";
	}
	
	@RequestMapping("/board/content")
	//URL 과 method 이름은 맞춰주는것이 좋다.(같은 method이름이 중복되는경우 오류)
	public String content(Model model) { 
		System.out.println("HomeController content start...");
		model.addAttribute("id", 365);
		return "board/content";
	}
	
	@RequestMapping("/board/reply")
	public ModelAndView reply() {
		System.out.println("Homecontroller reply start...");
		// 목적 : parameter와 View를 한방에 처리 
		ModelAndView mav = new ModelAndView();
		mav.addObject("id", 50);
		mav.setViewName("board/reply");
		
		return mav;
	}
	
}
