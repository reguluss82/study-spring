package com.oracle.oBootMybatis01.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis01.dao.DeptDao;
import com.oracle.oBootMybatis01.dao.EmpDao;
import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.DeptVO;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {
	private final EmpDao  ed;
	private final DeptDao dd;
	
	@Override
	public int totalEmp() {
		return ed.totalEmp();
	}

	@Override
	public List<Emp> listEmp(Emp emp) {
		return ed.listEmp(emp);
	}

	@Override
	public Emp detailEmp(int empno) {
		return ed.detailEmp(empno);
	}

	@Override
	public void updateEmp(Emp emp) {
		ed.updateEmp(emp);
	}
	
	// emp -> 관리자
	@Override
	public List<Emp> listManager() {
		return ed.listManager();
	}
	
	// dept 정보
	@Override
	public List<Dept> deptSelect() {
		return dd.deptSelect();
	}

	@Override
	public int insertEmp(Emp emp) {
		int result = ed.insertEmp(emp);
		return result;
	}

	@Override
	public int deleteEmp(int empno) {
		return ed.deleteEmp(empno);
	}

	@Override
	public List<EmpDept> listEmpDept() {
		return ed.listEmpDept();
	}

	@Override
	public void insertDept(DeptVO deptVO) {
		dd.insertDept(deptVO);
	}

}
