package kr.or.ddit.member.web;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value = "/member")
public class MemberController {
	
	@RequestMapping(value = "/ddit_signup", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("dd");
		
		return "pages/ddit_signup";
	}
	
}
