package kr.or.ddit.board.dao;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl implements IMemberDao {

	
	@Inject
	private SqlSessionTemplate sqlSession;
	
	
	
}
