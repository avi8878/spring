package com.avi.batch.model;

import com.avi.batch.Department;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class DepartmentService {

    @Autowired
    DepartmentServiceOther departmentServiceOther;

    @Autowired
    DepartmentRepo departmentRepo;


    @Transactional
    public void updateDepartment(){

        Department department=   new Department();
        department.setDepartmentId(10001L);
        department.setDepartmentName("Account");
        department.setDepartmentCode("DEP6765");
        department.setDepartmentAddress("Noida");
        try {
            updateOtherDepartment();
        }catch (Exception e){

        }
        departmentRepo.save(department);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateOtherDepartment(){
        Department department=   new Department();
        department.setDepartmentId(20001L);
        department.setDepartmentName("IT");
        department.setDepartmentCode("DEPooo");
        department.setDepartmentAddress("Noida");
        departmentRepo.save(department);

        if(1==1){
            throw  new RuntimeException();
        }
    }

}
