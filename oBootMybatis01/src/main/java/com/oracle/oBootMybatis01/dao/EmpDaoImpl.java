package com.oracle.oBootMybatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmpDaoImpl implements EmpDao {
	@Autowired
	private SqlSession session;
	
	@Override
	public int totalEmp() {
		int totEmpCount = 0;
		try {
			totEmpCount = session.selectOne("empTotal"); //selectOne selectList
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return totEmpCount;
	}

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		try {
			//                            Map ID         parameter
			empList = session.selectList("tkEmpListAll", emp);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return empList;
	}

	@Override
	public Emp detailEmp(int empno) {
		Emp emp = null;
		try {
			emp = session.selectOne("tkEmpSelOne", empno);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return emp;
	}

	@Override
	public void updateEmp(Emp emp) {
		try {
			session.update("TKempUpdate", emp);
		} catch (Exception e) {
			System.out.println("updateEmp err -> " + e.getMessage());
		}
	}

	@Override
	public List<Emp> listManager() {
		// emp 관리자만 Select           Naming Rule 
		List<Emp> empList = null;
		try {
			empList = session.selectList("tkSelectManager");
		} catch (Exception e) {
			System.out.println("listManager err -> " + e.getMessage());
		}
		
		return empList;
	}

	@Override
	public int insertEmp(Emp emp) {
		int result = 0;
		try {
			result = session.insert("tkInsertEmp", emp);
		} catch (Exception e) {
			System.out.println("insertEmp err -> " + e.getMessage());
		}
		return result;
	}

	@Override
	public int deleteEmp(int empno) {
		int result = 0;
		try {
			result = session.delete("tkDeleteEmp", empno);
		} catch (Exception e) {
			System.out.println("deleteEmp err -> " + e.getMessage());
		}
		return result;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		List<EmpDept> listEmpDept = null;
		try {
			listEmpDept = session.selectList("tkListEmpDept");
		} catch (Exception e) {
			System.out.println("deleteEmp err -> " + e.getMessage());
		}
		return listEmpDept;
	}

	@Override
	public String deptName(int deptno) {
		String resultStr = "";
		try {
			resultStr = session.selectOne("tkDeptName" , deptno);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl deptName Exception: " + e.getMessage());
		}
		return resultStr;
	}

}
