package com.oracle.oBootMybatis01.service;

import java.util.List;

import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.Emp;

public interface EmpService {
	int       totalEmp();

	List<Emp> listEmp(Emp emp);

	Emp detailEmp(int empno);

	void updateEmp(Emp emp);

	List<Emp> listManager();

	List<Dept> deptSelect();
}
