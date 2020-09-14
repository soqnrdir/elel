package com.study.mypage.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.study.common.vo.ResultMessageVO;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.login.vo.UserVO;
import com.study.member.service.IMemberService;
import com.study.member.service.MemberSeriviceImpl;
import com.study.member.vo.MemberVO;
import com.study.servlet.IController;

public class ModifyController implements IController {

	private IMemberService memberService = new MemberSeriviceImpl();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		MemberVO member = new MemberVO();
		BeanUtils.populate(member, req.getParameterMap());
		ResultMessageVO messageVO = new ResultMessageVO();

		try {
			HttpSession session = req.getSession();
			UserVO user = (UserVO) session.getAttribute("USER_INFO");

			memberService.modifyMember(member);
			String memId = user.getUserId();
			MemberVO mem = memberService.getMember(memId);
			req.setAttribute("mem", mem);
			return "mypage/info";

		} catch (BizPasswordNotMatchedException e) {
			e.printStackTrace();
			messageVO.setResult(false).setTitle("수정 실패").setMessage("비밀번호가 올바르지 않습니다.").setUrl("/mypage/info.wow");

		}
		req.setAttribute("messageVO", messageVO);
		return "common/message";

	}
}
