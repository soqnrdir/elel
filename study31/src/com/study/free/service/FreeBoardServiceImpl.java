package com.study.free.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.study.common.util.MybaisSqlSessionFactory;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.free.dao.IFreeBoardDao;
import com.study.free.vo.FreeBoardSearchVO;
import com.study.free.vo.FreeBoardVO;

public class FreeBoardServiceImpl implements IFreeBoardService {
	// private IFreeBoardDao boardDao = new FreeBoardDaoOracle();
	SqlSessionFactory factory = MybaisSqlSessionFactory.getSqlSessionFactory();

	@Override
	public List<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO) {
			try (SqlSession sqlSession = factory.openSession()) {
			IFreeBoardDao freeBoardDao = sqlSession.getMapper(IFreeBoardDao.class);
	
			// 건수를 구해서 searchVO 설정 -> searchVO.pageSetting() -> list 호출
			int cnt = freeBoardDao.getBoardCount(searchVO);
			searchVO.setTotalRowCount(cnt);
			searchVO.pageSetting();
			List<FreeBoardVO> list = freeBoardDao.getBoardList(searchVO);
			return list;
		}
	}

	@Override
	public FreeBoardVO getBoard(int boNo) throws BizNotFoundException {
			try (SqlSession sqlSession = factory.openSession()) {
			IFreeBoardDao freeBoardDao = sqlSession.getMapper(IFreeBoardDao.class);
			
			FreeBoardVO vo = freeBoardDao.getBoard(boNo);
			if (vo == null) {
				throw new BizNotFoundException("[" + boNo + "] 조회 실패 ");
			}
			return vo;
		}
	}

	@Override
	public void registBoard(FreeBoardVO board) {
			try (SqlSession sqlSession = factory.openSession()) {
			IFreeBoardDao freeBoardDao = sqlSession.getMapper(IFreeBoardDao.class);

			freeBoardDao.insertBoard(board);
			sqlSession.commit();
		}
	}

	@Override
	public void modifyBoard(FreeBoardVO board)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		
			try (SqlSession sqlSession = factory.openSession()) {
			IFreeBoardDao freeBoardDao = sqlSession.getMapper(IFreeBoardDao.class);
			
			FreeBoardVO vo = freeBoardDao.getBoard(board.getBoNo());
			if (vo == null) {
				throw new BizNotFoundException("글이 존재 하지 않습니다.");
			}
			if (!vo.getBoPass().equals(board.getBoPass())) {
				throw new BizPasswordNotMatchedException("비밀번호가 일치하지 않습니다.");
			}
			int cnt = freeBoardDao.updateBoard(board);
			if (cnt < 1) {
				throw new BizNotEffectedException("수정 실패");
			}
			sqlSession.commit();
		}
	}

	@Override
	public void removeBoard(FreeBoardVO board)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {

		try (SqlSession sqlSession = factory.openSession()) {
			IFreeBoardDao freeBoardDao = sqlSession.getMapper(IFreeBoardDao.class);

			FreeBoardVO vo = freeBoardDao.getBoard(board.getBoNo());
			if (vo == null) {
				throw new BizNotFoundException("글이 존재 하지 않습니다.");
			}
			if (!vo.getBoPass().equals(board.getBoPass())) {
				throw new BizPasswordNotMatchedException("비밀번호가 일치하지 않습니다.");
			}
			int cnt = freeBoardDao.deleteBoard(board);
			if (cnt < 1) {
				throw new BizNotEffectedException("삭제 실패");
			}
			sqlSession.commit();
		}
	}

	@Override
	public void increaseHit(int boNo) {
		try (SqlSession sqlSession = factory.openSession()) {
			IFreeBoardDao freeBoardDao = sqlSession.getMapper(IFreeBoardDao.class);
			freeBoardDao.increaseHit(boNo);
			sqlSession.commit();
		}
	}
}