package com.oracle.oBootMybatis01.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.DeptVO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DeptDaoImpl implements DeptDao {
	
	@Autowired
	private final SqlSession session;
	
	@Override
	public List<Dept> deptSelect() {
		List<Dept> deptList = null;
		try {
			deptList = session.selectList("tkSelectDept");
		} catch (Exception e) {
			System.out.println("deptSelect err -> " + e.getLocalizedMessage());
		}
		return deptList;

	}

	@Override
	public void insertDept(DeptVO deptVO) {
		session.selectOne("procDeptInsert", deptVO);
	}

	@Override
	public void selListDept(HashMap<String, Object> map) {
		session.selectOne("procDeptList", map);
	}
	
}
