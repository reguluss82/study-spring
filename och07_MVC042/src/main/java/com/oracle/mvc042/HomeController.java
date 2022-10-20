package com.oracle.mvc042;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.mvc042.dto.StudentInformation;

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
	
	@RequestMapping("/index")
	public String index() {
		System.out.println("HomeController index Start...");
		return "index";
	}
	
	@RequestMapping("/studentView1")
	public String studentView1(StudentInformation studentInformation , Model model) {
		logger.info("studentView1 Start");
		System.out.println("studentInformation.getName() -> "     + studentInformation.getName());
		System.out.println("studentInformation.getClassNum() -> " + studentInformation.getClassNum());
		System.out.println("studentInformation.getGradeNum() -> " + studentInformation.getGradeNum());
		System.out.println("studentInformation.getAge() -> "      + studentInformation.getAge());
		
		model.addAttribute("studentInfo", studentInformation);
		
		return "studentView";
	}
	
	@RequestMapping("/studentView2")
	public String studentView2(@ModelAttribute("studentInfo") StudentInformation studentInformation) {
		logger.info("studentView2 Start");
		System.out.println("studentInformation.getName()2 -> "     + studentInformation.getName());
		System.out.println("studentInformation.getClassNum()2 -> " + studentInformation.getClassNum());
		System.out.println("studentInformation.getGradeNum()2 -> " + studentInformation.getGradeNum());
		System.out.println("studentInformation.getAge()2 -> "      + studentInformation.getAge());
		
		return "studentView";
	}
	
}
