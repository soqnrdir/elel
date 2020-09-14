package com.study.free.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
import com.study.servlet.IController;

public class FreeDeleteController implements IController {

	IFreeBoardService freeBoardService = new FreeBoardServiceImpl();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		FreeBoardVO board = new FreeBoardVO();
		BeanUtils.populate(board, req.getParameterMap());
		ResultMessageVO messageVO = new ResultMessageVO();
		
		try {
			board.setBoIp(req.getRemoteAddr());
			freeBoardService.removeBoard(board);
			messageVO.setResult(true)
			 .setTitle("글 삭제 성공")
			 .setMessage("글 삭제가 되었습니다.")
			 .setUrl("/free/freeList.wow")
			 .setUrlTitle("목록으로");
					
	} catch (BizPasswordNotMatchedException e) {
		e.printStackTrace();
		messageVO.setResult(false)
		 .setTitle("글 삭제 실패")
		 .setMessage("비밀번호가 올바르지 않습니다.")
		 .setUrl("/free/freeList.wow")
		 .setUrlTitle("목록으로");
	}	
		req.setAttribute("messageVO", messageVO);
		return "common/message";
	}

}
