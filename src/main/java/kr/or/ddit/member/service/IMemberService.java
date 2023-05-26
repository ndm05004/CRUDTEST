package kr.or.ddit.member.service;

import kr.or.ddit.vo.MemberVO;

public interface IMemberService {
	public int memSignup(MemberVO memberVO);
	
	public int memLogin(MemberVO memberVO);
}
