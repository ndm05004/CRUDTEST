package kr.or.ddit.member.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.vo.MemberVO;

@Service
public class MemberServiceImpl implements IMemberService {
	
	@Inject
	IMemberDAO memberDao;
	
	@Override
	public int memSignup(MemberVO memberVO) {
		
		return memberDao.memSignup(memberVO);
	}

	@Override
	public int memLogin(MemberVO memberVO) {
				
		return memberDao.memLogin(memberVO);
	}

}
