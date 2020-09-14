package com.study.login.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.study.common.util.MybaisSqlSessionFactory;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.login.vo.UserVO;
import com.study.member.dao.IMemberDao;
import com.study.member.vo.MemberVO;

public class LoginServiceImpl implements ILoginService {

	
	SqlSessionFactory factory = MybaisSqlSessionFactory.getSqlSessionFactory();
	
	@Override
	public UserVO loginCheck(UserVO user) throws BizNotFoundException, BizPasswordNotMatchedException {
		
		try (SqlSession sqlSession = factory.openSession()) {
			IMemberDao memberDao = sqlSession.getMapper(IMemberDao.class);
			MemberVO vo = memberDao.getMember(user.getUserId());
			if(vo == null) {
				throw new BizNotFoundException(user.getUserId() + "회원이 존재하지 않습니다.");
			}
			if(!vo.getMemPass().equals(user.getUserPass())) {
				throw new BizPasswordNotMatchedException();
			}
			
			// 성공
			UserVO userVO = new UserVO();
			userVO.setUserId(vo.getMemId());
			userVO.setUserPass(vo.getMemPass());
			userVO.setUserName(vo.getMemName());
			userVO.setUserRole(memberDao.getUserRoleByUserId(vo.getMemId())); // 현재 권한 테이블이 없어서 그냥 "MEMBER" 로 설정
			return userVO;
			
		}
		
		
	}

	@Override
	public void logout(UserVO user) {
		// TODO Auto-generated method stub
		
	}

}
