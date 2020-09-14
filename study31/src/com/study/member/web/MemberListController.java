package com.study.member.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.code.service.CommonCodeServiceImpl;
import com.study.code.service.ICommonCodeService;
import com.study.code.vo.CodeVO;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberSeriviceImpl;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;
import com.study.servlet.IController;

public class MemberListController implements IController {
	
	private IMemberService memberService = new MemberSeriviceImpl();
	private ICommonCodeService codeService = new CommonCodeServiceImpl();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		MemberSearchVO searchVO = new MemberSearchVO();
		BeanUtils.populate(searchVO, req.getParameterMap());
		req.setAttribute("searchVO", searchVO);

		List<MemberVO> members = memberService.getMemberList(searchVO);
		req.setAttribute("members", members);
		
	 	List<CodeVO> jobList = codeService.getCodeListByParent("JB00");
	 	List<CodeVO> hobbyList = codeService.getCodeListByParent("HB00");
	 	
	 	req.setAttribute("jobList", jobList);
	 	req.setAttribute("hobbyList", hobbyList);
	 	
	 	return "member/memberList";
	}
}
