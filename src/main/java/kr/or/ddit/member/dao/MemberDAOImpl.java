package kr.or.ddit.member.dao;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.MemberVO;

@Repository
public class MemberDAOImpl implements IMemberDAO {
	
	@Inject
	private SqlSessionTemplate sqlSession;

	@Override
	public int memSignup(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return sqlSession.insert("member.memSignup", memberVO);
	}

	@Override
	public int memLogin(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("member.memLogin", memberVO);
	}

}
