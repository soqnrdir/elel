package com.study.free.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.code.service.CommonCodeServiceImpl;
import com.study.code.service.ICommonCodeService;
import com.study.code.vo.CodeVO;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;
import com.study.servlet.IController;

public class FreeListController implements IController{
	
	IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
	ICommonCodeService codeService = new CommonCodeServiceImpl();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		FreeBoardSearchVO searchVO = new FreeBoardSearchVO();
		BeanUtils.populate(searchVO, req.getParameterMap());
		
//		searchVO.setSearchCategory(req.getParameter("searchCategory"));
//		searchVO.setSearchType(req.getParameter("searchType"));
//		searchVO.setSearchWord(req.getParameter("searchWord"));
//		searchVO.setCurPage(Integer.parseInt(req.getParameter("curPage")));
//		searchVO.setRowSizePerPage(Integer.parseInt(req.getParameter("rowSizePerPage")));
		
		req.setAttribute("searchVO", searchVO);
		
		List<FreeBoardVO> boards = freeBoardService.getBoardList(searchVO);
		req.setAttribute("boards", boards);
		
	 	List<CodeVO> cateList = codeService.getCodeListByParent("BC00");
	 	req.setAttribute("cateList", cateList);
	 	return "free/freeList";
	}
}
