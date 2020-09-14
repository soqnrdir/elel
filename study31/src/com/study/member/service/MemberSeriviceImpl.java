package com.study.member.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.study.common.util.MybaisSqlSessionFactory;
import com.study.exception.BizDuplicateKeyException;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.member.dao.IMemberDao;
import com.study.member.vo.MemberSearchVO;
import com.study.member.vo.MemberVO;

public class MemberSeriviceImpl implements IMemberService {

	SqlSessionFactory factory = MybaisSqlSessionFactory.getSqlSessionFactory();

	@Override
	public void registMember(MemberVO member) throws BizDuplicateKeyException {

		try (SqlSession sqlSession = factory.openSession()) {
			IMemberDao memberDao = sqlSession.getMapper(IMemberDao.class);

			memberDao.insertMember(member);
			sqlSession.commit();
		}
	}

	@Override
	public void modifyMember(MemberVO member)
			throws BizNotEffectedException, BizNotFoundException, BizPasswordNotMatchedException {

		try (SqlSession sqlSession = factory.openSession()) {
			IMemberDao memberDao = sqlSession.getMapper(IMemberDao.class);

			MemberVO vo = memberDao.getMember(member.getMemId());
			if (vo == null) {
				throw new BizNotFoundException("[" + member.getMemId() + "] 조회 실패");
			}
			if (!vo.getMemPass().equals(member.getMemPass())) {
				throw new BizPasswordNotMatchedException("비밀번호가 일치하지 않습니다.");
			}
			int cnt = memberDao.updateMember(member);
			if (cnt < 1) {
				throw new BizNotEffectedException("[" + member.getMemId() + "] 수정 실패");
			}
			sqlSession.commit();
		}
	}

	@Override
	public void removeMember(MemberVO member)
			throws BizNotEffectedException, BizNotFoundException, BizPasswordNotMatchedException {

		try (SqlSession sqlSession = factory.openSession()) {
			IMemberDao memberDao = sqlSession.getMapper(IMemberDao.class);

			MemberVO vo = memberDao.getMember(member.getMemId());
			if (vo == null) {
				throw new BizNotFoundException("글이 존재 하지 않습니다.");
			}
			if (!vo.getMemPass().equals(member.getMemPass())) {
				throw new BizPasswordNotMatchedException("비밀번호가 일치하지 않습니다.");
			}
			int cnt = memberDao.deleteMember(member);
			if (cnt < 1) {
				throw new BizNotEffectedException("삭제 실패");
			}
			sqlSession.commit();
		}
	}

	@Override
	public MemberVO getMember(String memId) throws BizNotFoundException {
		try (SqlSession sqlSession = factory.openSession()) {
			IMemberDao memberDao = sqlSession.getMapper(IMemberDao.class);

			MemberVO vo = memberDao.getMember(memId);
			if (vo == null) {
				throw new BizNotFoundException("[" + memId + "] 조회 실패");
			}
			return vo;

		}
	}

	@Override
	public List<MemberVO> getMemberList(MemberSearchVO searchVO) {

		try (SqlSession sqlSession = factory.openSession()) {
			IMemberDao memberDao = sqlSession.getMapper(IMemberDao.class);

			int cnt = memberDao.getMemberCount(searchVO);
			searchVO.setTotalRowCount(cnt);
			searchVO.pageSetting();

			List<MemberVO> list = memberDao.getMemberList(searchVO);
			return list;
		}
	}
}
