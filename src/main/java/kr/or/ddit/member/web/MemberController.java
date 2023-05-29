package kr.or.ddit.member.web;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.MembershipService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {
	
	@Inject
	IMemberService service;

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		return "pages/ddit_signin";
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login() {
		
		return "pages/ddit_signin";
	}
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		

		session.removeAttribute("memberVO");
		session.invalidate();
		
		return "pages/ddit_signin";
	}
	
	
	@RequestMapping(value = "/pages/sign-up", method = RequestMethod.GET)
	public String sigup() {
		
		return "pages/ddit_signup";
	}
	
	@RequestMapping(value = "/singin.do", method = RequestMethod.POST)
	public String sigUpCheck(MemberVO memberVO, Model model) {
		
		log.info(memberVO.getMem_Id());
		log.info(memberVO.getMem_Pw());
		log.info(memberVO.getMem_Phone());
		log.info(memberVO.getMem_Name());
		log.info(memberVO.getMem_Email());
		log.info(memberVO.getMem_Gender());
		log.info(memberVO.getMem_Agree());
		
		if(StringUtils.isEmpty(memberVO.getMem_Id()) || 
				StringUtils.isEmpty(memberVO.getMem_Pw()) ||
				StringUtils.isEmpty(memberVO.getMem_Phone()) ||
				StringUtils.isEmpty(memberVO.getMem_Name()) ||
				StringUtils.isEmpty(memberVO.getMem_Email()) ||
				StringUtils.isEmpty(memberVO.getMem_Gender()) ||
				StringUtils.isEmpty(memberVO.getMem_Agree())) {
			model.addAttribute("flag","missing");
			return "pages/sign-up";
		}
			
		
		int check = service.memSignup(memberVO);
		
		if(check == 1) {
			model.addAttribute("flag","success");
			return "pages/ddit_signin";
		}else {
			model.addAttribute("flag","error");
			return "pages/ddit_signup";
		}
		
	}
	
	@RequestMapping(value="/pages/list.do", method =RequestMethod.POST)
	public String signInCheck(MemberVO memberVO, Model model, HttpSession session) {
		
		
		log.info(memberVO.getMem_Id());
		log.info(memberVO.getMem_Pw());
		
		int check = service.memLogin(memberVO);

		if(check==1) {
			session.setAttribute("memberVO", memberVO);
			return "redirect: /board/boardList.do";
		}else {
			model.addAttribute("flag","no");
			return "pages/ddit_signin";
			
		}
		
	}
	

	
}
