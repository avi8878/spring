package com.avi.batch.model;

import com.avi.batch.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class DepartmentServiceOther {



    @Autowired
    DepartmentRepo departmentRepo;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateDepartment(){
        Department department=   new Department();
        department.setDepartmentId(20001L);
        department.setDepartmentName("IT");
        department.setDepartmentCode("DEP34343");
        department.setDepartmentAddress("Noida");
        departmentRepo.save(department);

        if(1==1){
            throw  new RuntimeException();
        }
    }

}
