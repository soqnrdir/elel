package com.study.free.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
import com.study.servlet.IController;

public class FreeModifyController implements IController {

	IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		FreeBoardVO board = new FreeBoardVO();
		BeanUtils.populate(board, req.getParameterMap());
		ResultMessageVO messageVO = new ResultMessageVO();
		int boNo = Integer.parseInt(req.getParameter("boNo"));
	
		try{
			freeBoardService.modifyBoard(board);
			return "redirect:/free/freeView.wow?boNo=" + boNo;
		} catch(BizPasswordNotMatchedException e) {
			e.printStackTrace();
			messageVO.setResult(false)
	 		 .setTitle("글 수정 실패")
	 		 .setMessage("비밀번호가 올바르지 않습니다.")
	 		 .setUrl("/free/freeList.wow")
	 		 .setUrlTitle("목록으로");
			
	} 	catch(BizNotFoundException e) {
		e.printStackTrace();
		messageVO.setResult(false)
		 .setTitle("글 수정 실패")
		 .setMessage("해당 글 번호가 존재합니다.")
		 .setUrl("/free/freeList.wow")
		 .setUrlTitle("목록으로");
	}
		req.setAttribute("messageVO", messageVO);
		
		return "common/message";
	}
	
}

