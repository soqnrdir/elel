package com.study.free.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.code.service.CommonCodeServiceImpl;
import com.study.code.service.ICommonCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
import com.study.servlet.IController;

public class FreeEditController implements IController {

	ICommonCodeService codeService = new CommonCodeServiceImpl();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
	 	List<CodeVO> cateList = codeService.getCodeListByParent("BC00");
	 	req.setAttribute("cateList", cateList);

		IFreeBoardService freeService = new FreeBoardServiceImpl();
		try{
			String boNo = req.getParameter("boNo");
			FreeBoardVO free = freeService.getBoard(Integer.parseInt(boNo));
			req.setAttribute("free", free);
		}catch(BizNotFoundException ex){
			ResultMessageVO messageVO = new ResultMessageVO();
		 	messageVO.setResult(false)
			 .setTitle("수정 실패")
			 .setMessage("해당 글이 존재하지 않습니다.")
			 .setUrl("/free/freeList.wow")
			 .setUrlTitle("목록으로");
		 	req.setAttribute("messageVO", messageVO);
		 	return "common/message";
			
		}
		return "free/freeEdit";
	}

}
