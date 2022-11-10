package com.oracle.oBootMybatis01.dao;

import java.util.List;

import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;

public interface EmpDao {
	int       totalEmp();

	List<Emp> listEmp(Emp emp);

	Emp       detailEmp(int empno);

	void      updateEmp(Emp emp);

	List<Emp> listManager();

	int insertEmp(Emp emp);

	int deleteEmp(int empno);

	List<EmpDept> listEmpDept();
}
