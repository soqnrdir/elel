package com.study.member.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberSeriviceImpl;

import com.study.member.vo.MemberVO;
import com.study.servlet.IController;

public class MemberViewController implements IController {
	
	private IMemberService memberService = new MemberSeriviceImpl();


	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		try{
		 	String memId = req.getParameter("memId");
		 	MemberVO mem = memberService.getMember(memId);
		 	req.setAttribute("mem", mem);
		 	} catch(BizNotFoundException ex) {
		 		ResultMessageVO messageVO = new ResultMessageVO();
			 	messageVO.setResult(false)
				 .setTitle("조회 실패")
				 .setMessage("해당 회원이 존재하지 않습니다.")
				 .setUrl("/member/memberList.wow")
				 .setUrlTitle("목록으로");
			 	req.setAttribute("messageVO", messageVO);
			 	return "common/message";
		 	}
	 	return "member/memberView";
	}
}
