package com.study.member.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberSeriviceImpl;
import com.study.member.vo.MemberVO;
import com.study.servlet.IController;

public class MemberDeleteController implements IController {
	
	private IMemberService memberService = new MemberSeriviceImpl();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		MemberVO member = new MemberVO();
		BeanUtils.populate(member, req.getParameterMap());
		ResultMessageVO messageVO = new ResultMessageVO();
		
		try{
			memberService.removeMember(member);
			messageVO.setResult(true)
			 .setTitle("회원 삭제 성공")
			 .setMessage("회원 삭제가 되었습니다.")
			 .setUrl("/member/memberList.wow")
			 .setUrlTitle("목록으로");
		}catch(BizPasswordNotMatchedException e) {
			e.printStackTrace();
			messageVO.setResult(false)
	 		 .setTitle("수정 실패")
	 		 .setMessage("비밀번호가 올바르지 않습니다.")
	 		 .setUrl("/member/memberList.wow")
	 		 .setUrlTitle("목록으로");
	}catch(BizNotFoundException e) {
			e.printStackTrace();
			messageVO.setResult(false)
			 .setTitle("삭제 실패")
			 .setMessage("회원이 존재하지 않습니다.")
			 .setUrl("/member/memberList.wow")
			 .setUrlTitle("목록으로");
		}
		
		req.setAttribute("messageVO", messageVO);
		return "common/message";
	}
}
