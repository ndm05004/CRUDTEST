package kr.or.ddit.board.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Inject
	IBoardService boardService;
	
	@RequestMapping(value="/boardList.do")
	public String boardlist(
			@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord,
			Model model) {
		
		PaginationInfoVO<BoardVO> pagingVO = new PaginationInfoVO<BoardVO>();
		
		if(StringUtils.isNotBlank(searchWord)) {
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		
		pagingVO.setCurrentPage(currentPage);
		
		int totalRecord = boardService.selectBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
	
		List<BoardVO> dataList = boardService.selectBoardList(pagingVO);
		pagingVO.setDataList(dataList);
		model.addAttribute("pagingVO", pagingVO);
		
		return "pages/ddit_list";
	}
	
	@RequestMapping(value="/detail.do", method = RequestMethod.GET)
	public String boardDetail(String boNo, Model model){
		
		int selectBoNo=0;
		
		try {
			selectBoNo = Integer.parseInt(boNo);
		} catch (Exception e) {
			return "redirect:/board/boardList.do?error="+"notFound";
		}
		
		BoardVO boardVO = boardService.selectBoard(selectBoNo);
			
		if(boardVO == null) {
			return "redirect:/board/boardList.do?error="+"notFound";
		}
		
		model.addAttribute("board", boardVO);
		return "pages/ddit_detail";
	}
	
	
	@RequestMapping(value="/delete.do", method = RequestMethod.POST)
	public String boardDelete(int boNo, Model model) {
		
		int result = boardService.deleteBoard(boNo);
		
		
		if(result == 1) {
			return "redirect:/board/boardList.do";
		}else {
			return "redirect:/board/detail.do?boNo="+boNo+"error=fail";
		}
	
	}
	
	@RequestMapping(value="/form.do", method=RequestMethod.GET)
	public String boardForm() {
		return "pages/ddit_form";
	}
	
	@RequestMapping(value="/insert.do", method=RequestMethod.POST)
	public String boardInsert(BoardVO boardVO, Model model) {
		String goPage = ""; // 페이지를 담을 공간
		Map<String, String> errors = new HashMap<String, String>();

		if(StringUtils.isBlank(boardVO.getBoTitle())) {
			errors.put("boTitle", "제목을 입력해주세요");
		}
		
		if(StringUtils.isBlank(boardVO.getBoContent())) {
			errors.put("boContent", "내용을 입력해주세요");
		}
		
		if(errors.size()>0) { 

			model.addAttribute("errors", errors);
			model.addAttribute("board", boardVO);
			goPage = "pages/ddit_form";
		}else { 
			int result = boardService.insertBoard(boardVO);
			if(result==1) { 
				goPage = "redirect:/board/detail.do?boNo=" + boardVO.getBoNo();
			}else {
				errors.put("msg", "서버에러! 다시 시도해주세요");
				model.addAttribute("errors", errors);
				goPage = "pages/ddit_form";
			}
		}
		return goPage;
	}
	
	
	@RequestMapping(value ="/update.do", method = RequestMethod.GET)
	public String UpdateForm(String boNo, Model model) {
		
		int selectBoNo=0;
		
		try {
			selectBoNo = Integer.parseInt(boNo);
		} catch (Exception e) {
			return "redirect:/board/boardList.do?error="+"notFound";
		}	
		
		BoardVO boardVO = boardService.selectBoard(selectBoNo);
		
		if(boardVO == null) {
			return "redirect:/board/boardList.do?error="+"notFound";
		}
		
		model.addAttribute("board", boardVO); 
		model.addAttribute("status", "u"); 
		return "pages/ddit_form";
	}
	
	
	@RequestMapping(value="/update.do", method = RequestMethod.POST)
	public String boardUpdate(BoardVO boardVO, Model model) {
		
		String goPage = "";
		int result = boardService.updateBoard(boardVO);
		
		if(result==1) {
			goPage = "redirect:/board/detail.do?boNo=" + boardVO.getBoNo();
		}else {
			model.addAttribute("board", boardVO);
			model.addAttribute("status", "u");
			goPage = "pages/ddit_form";
		}
		return goPage;
	}
	
}

