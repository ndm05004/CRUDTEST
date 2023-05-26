package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IBoardrDao {
	
	
	public int insertBoard(BoardVO boardVO);
	
	
	public void increamentHit(int boNo);
	
	public BoardVO selectBoard(int boNo);
	
	public int updateBoard(BoardVO boardVO);
	
	public int deleteBoard(int boNo);
	
	
	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO);
	
	public List<BoardVO> selectBoardList(PaginationInfoVO<BoardVO> pagingVO);
	
	
	
}
