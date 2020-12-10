package kr.or.ksmart37.ksmart_mybatis.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ksmart37.ksmart_mybatis.dto.Member;

@Controller
public class MainController {

	
	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	
	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("title", "main 화면");
		
		return "main";
	}
	@GetMapping("/main2")
	public String main2(Model model) {
		model.addAttribute("title", "main 화면");
		
		return "main2";
	}
	
	@PostMapping("")
	@RequestMapping(value="/listTest1", method = RequestMethod.POST)
	public String listTest1(@ModelAttribute Member parameterList) {
		
		log.info("test : {}", parameterList.toString());
		
		return "redirect:/";
	}
}
