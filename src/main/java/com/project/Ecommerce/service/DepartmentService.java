package com.project.Ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.Ecommerce.model.Department;
import com.project.Ecommerce.dao.Departmentdao;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private Departmentdao departmentDao;

    public List<Department> getAllDepartments() {
        return departmentDao.findAll();
    }
}
