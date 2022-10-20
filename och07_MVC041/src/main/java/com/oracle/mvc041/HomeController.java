package com.oracle.mvc041;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping("index")
	public String goIndex() {
		logger.info("index Start...");
		return "index";
	}
	
	//@RequestMapping("student")
	@RequestMapping(value = "student" , method = RequestMethod.GET) // err 405 허용되지 않는 메소드 (form method 불일치)
	public String getStudent(HttpServletRequest request, Model model) {
		logger.info("getStudent Start...");
		String id = request.getParameter("id");
		System.out.println("GET id : " + id);
		model.addAttribute("studentId", id);
		
		return "student/studentId";
	}
	
	@RequestMapping(value = "student" , method = RequestMethod.POST)
	public String postStudent(HttpServletRequest request, Model model) {
		logger.info("postStudent Start...");
		String id = request.getParameter("id");
		System.out.println("POST id : " + id);
		model.addAttribute("studentId", id);
		
		return "student/studentId";
	}
	
}
