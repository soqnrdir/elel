package com.study.code.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.study.code.dao.ICommonCodeDao;
import com.study.code.vo.CodeVO;
import com.study.common.util.MybaisSqlSessionFactory;

public class CommonCodeServiceImpl implements ICommonCodeService {

	SqlSessionFactory factory = MybaisSqlSessionFactory.getSqlSessionFactory();

	@Override
	public List<CodeVO> getCodeListByParent(String parentCode) {
		try (SqlSession sqlSession = factory.openSession()) {
			ICommonCodeDao codeDao = sqlSession.getMapper(ICommonCodeDao.class);
			List<CodeVO> list = codeDao.getCodeListByParent(parentCode);
			return list;
		}
	}
}
