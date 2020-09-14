package com.study.member.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.code.service.CommonCodeServiceImpl;
import com.study.code.service.ICommonCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberSeriviceImpl;

import com.study.member.vo.MemberVO;
import com.study.servlet.IController;

public class MemberEditController implements IController {
	
	private IMemberService memberService = new MemberSeriviceImpl();
	private ICommonCodeService codeService = new CommonCodeServiceImpl();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

	 	List<CodeVO> jobList = codeService.getCodeListByParent("JB00");
	 	List<CodeVO> hobbyList = codeService.getCodeListByParent("HB00");
	 	req.setAttribute("jobList", jobList);
	 	req.setAttribute("hobbyList", hobbyList);

		try{
			String memId = req.getParameter("memId");
			MemberVO mem = memberService.getMember(memId);
			req.setAttribute("mem", mem);
		}catch(BizNotFoundException e){
			ResultMessageVO messageVO = new ResultMessageVO();
		 	messageVO.setResult(false)
			 .setTitle("수정 실패")
			 .setMessage("해당 회원이 존재하지 않습니다.")
			 .setUrl("/member/memberList.wow")
			 .setUrlTitle("목록으로");
		 	req.setAttribute("messageVO", messageVO);
		 	return "common/message";
		}
	 	return "member/memberEdit";
	}
}
