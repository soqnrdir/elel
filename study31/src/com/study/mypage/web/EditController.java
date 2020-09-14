package com.study.mypage.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.code.service.CommonCodeServiceImpl;
import com.study.code.service.ICommonCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizNotFoundException;
import com.study.login.vo.UserVO;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberSeriviceImpl;

import com.study.member.vo.MemberVO;
import com.study.servlet.IController;

public class EditController implements IController {
	
	private IMemberService memberService = new MemberSeriviceImpl();
	private ICommonCodeService codeService = new CommonCodeServiceImpl();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		List<CodeVO> jobList = codeService.getCodeListByParent("JB00");
	 	List<CodeVO> hobbyList = codeService.getCodeListByParent("HB00");
	 	req.setAttribute("jobList", jobList);
	 	req.setAttribute("hobbyList", hobbyList);


			HttpSession session = req.getSession();
			UserVO user = (UserVO)session.getAttribute("USER_INFO");
			// 로그인이 되지 않은 경우 홈 또는 로그인 화면으로
			// 동일한 기능을 필터로 하면 좋겠다...
//			if(user == null) {
//				return "redirect:/";
//			}
		 	String memId = user.getUserId();
		 	MemberVO mem = memberService.getMember(memId);
		 	req.setAttribute("mem", mem);
		 	return "mypage/edit";

	}
}
