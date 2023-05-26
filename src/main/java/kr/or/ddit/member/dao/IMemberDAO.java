package kr.or.ddit.member.dao;

import kr.or.ddit.vo.MemberVO;

public interface IMemberDAO {

	
	public int memSignup(MemberVO memberVO);
	
	public int memLogin(MemberVO memberVO);
	
}
