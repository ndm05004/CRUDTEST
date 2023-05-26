package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IBoardService {

	public int insertBoard(BoardVO boardVO);
		
	public BoardVO selectBoard(int boNo);
	
	public int updateBoard(BoardVO boardVO);
	
	public int deleteBoard(int boNo);
	
	
	public int selectBoardCount(PaginationInfoVO<BoardVO> pagingVO);
	
	public List<BoardVO> selectBoardList(PaginationInfoVO<BoardVO> pagingVO);
}
